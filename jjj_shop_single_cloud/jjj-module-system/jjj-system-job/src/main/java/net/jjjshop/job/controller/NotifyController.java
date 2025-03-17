

package net.jjjshop.job.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.app.App;
import net.jjjshop.common.enums.OrderPayStatusEnum;
import net.jjjshop.common.enums.OrderPayTypeEnum;
import net.jjjshop.common.factory.paysuccess.type.PaySuccessOrder;
import net.jjjshop.common.factory.paysuccess.type.PaySuccessTypeFactory;
import net.jjjshop.common.service.app.AppService;
import net.jjjshop.common.vo.order.PayDataVo;
import net.jjjshop.framework.common.controller.BaseController;
import net.jjjshop.framework.log.annotation.OperationLog;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Tag(name = "回调接口")
@RestController
@RequestMapping("/job/notify")
public class NotifyController extends BaseController {

    @Autowired
    private PaySuccessTypeFactory paySuccessTypeFactory;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private AppService appService;

    @RequestMapping(value = "/wxPay", method = RequestMethod.POST)
    @OperationLog(name = "wxPay")
    @Operation(summary = "微信支付回调")
    public String wxPay(HttpServletRequest request) {
        String wechatpaySerial = request.getHeader("wechatpay-serial");
        log.info("wechatpaySerial="+wechatpaySerial);
        if(StringUtils.hasText(wechatpaySerial)){
            App app = appService.list(new LambdaQueryWrapper<App>()
                    .eq(App::getWechatpaySerial, wechatpaySerial)).get(0);
            return this.wxPayV3(request, app);
        }else{
            return this.wxPayV2(request);
        }
    }

    public String wxPayV2(HttpServletRequest request) {
        try {
            log.info("微信支付回调");
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);

            // 加入自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            String orderId = result.getOutTradeNo();
            log.info("微信支付回调orderId="+orderId);
            String tradeNo = result.getTransactionId();
            log.info("微信支付回调tradeNo="+tradeNo);
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
            log.info("微信支付回调totalFee="+totalFee);
            JSONObject attach = JSON.parseObject(result.getAttach());
            PayDataVo payDataVo = new PayDataVo();
            payDataVo.setTransaction_id(result.getTransactionId());
            payDataVo.setAttach(attach);
            paySuccessTypeFactory.getFactory(attach.getInteger("orderType"))
                    .onPaySuccess(result.getOutTradeNo(), 10, OrderPayTypeEnum.WECHAT.getValue(), payDataVo);
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    public String wxPayV3(HttpServletRequest request, App app) {
        try {
            log.info("微信支付回调");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                // 打印Header参数的名称和值
                log.info("Header Name: " + headerName + ", Value: " + headerValue);
            }
            // 先解密
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            JSONObject jsonData = JSONObject.parseObject(xmlResult);
            String decryptData = AesUtils.decryptToString(jsonData.getJSONObject("resource").getString("associated_data"),
                    jsonData.getJSONObject("resource").getString("nonce"),
                    jsonData.getJSONObject("resource").getString("ciphertext"),
                    app.getApikey());
            log.info("decryptData"+ decryptData);
            JSONObject decryptJson = JSONObject.parseObject(decryptData);

            // 附加字段
            JSONObject attach = decryptJson.getJSONObject("attach");
            PaySuccessOrder order = paySuccessTypeFactory.getFactory(attach.getInteger("orderType")).getOrder(decryptJson.getString("out_trade_no"));
            if(order.getPayStatus().intValue() == OrderPayStatusEnum.SUCCESS.getValue().intValue()){
                return WxPayNotifyResponse.success("处理成功!");
            }
            // 处理支付回调
            PayDataVo payDataVo = new PayDataVo();
            payDataVo.setTransaction_id(decryptJson.getString("transaction_id"));
            payDataVo.setAttach(attach);
            paySuccessTypeFactory.getFactory(attach.getInteger("orderType"))
                    .onPaySuccess(decryptJson.getString("out_trade_no"), 10, OrderPayTypeEnum.WECHAT.getValue(), payDataVo);
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因:", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
