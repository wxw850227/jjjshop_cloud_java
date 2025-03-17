package net.jjjshop.shop.controller.setting;


import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.Printer;
import net.jjjshop.common.enums.PrinterTypeEnum;
import net.jjjshop.framework.common.api.ApiResult;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.framework.log.annotation.OperationLog;
import net.jjjshop.shop.param.setting.PrinterPageParam;
import net.jjjshop.shop.param.setting.PrinterParam;
import net.jjjshop.shop.service.settings.PrinterService;
import net.jjjshop.shop.vo.setting.PrinterVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "shop-打印机管理")
@RestController
@RequestMapping("/shop/setting/printer")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("/setting/printer/index")
    @OperationLog(name = "index")
    @Operation(summary = "index")
    public ApiResult<Paging<Printer>> index(@RequestBody @Validated PrinterPageParam printerPageParam) throws Exception {
        return ApiResult.ok(printerService.getList(printerPageParam));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("/setting/printer/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<String> add(@RequestBody @Validated PrinterParam printerParam) {
        if (printerService.add(printerParam)) {
            return ApiResult.ok(null, "添加成功");
        } else {
            return ApiResult.fail("添加失败");
        }
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    @RequiresPermissions("/setting/printer/add")
    @OperationLog(name = "add")
    @Operation(summary = "add")
    public ApiResult<List<String>> toAdd() {
        return ApiResult.ok(printerService.getPrinterTypeName());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("/setting/printer/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<String> edit(@RequestBody @Validated PrinterParam printerParam) {
        if (printerService.edit(printerParam)) {
            return ApiResult.ok(null, "修改成功");
        } else {
            return ApiResult.fail("修改失败");
        }
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    @RequiresPermissions("/setting/printer/edit")
    @OperationLog(name = "edit")
    @Operation(summary = "edit")
    public ApiResult<Map<String, Object>> toEdit(Integer printerId) {
        Map<String, Object> result = new HashMap<String, Object>();
        PrinterVo vo = new PrinterVo();
        Printer printer = printerService.getById(printerId);
        BeanUtils.copyProperties(printer, vo);
        if(StringUtils.isNotEmpty(printer.getPrinterConfig())){
            vo.setPrinterConfig(JSONObject.parseObject(printer.getPrinterConfig()));
        }
        if (PrinterTypeEnum.FEI_E_YUN.getPrinterType().equals(vo.getPrinterType())) {
            vo.setPrinterType(PrinterTypeEnum.FEI_E_YUN.getText());
        }
        result.put("printer", vo);
        result.put("printerType", printerService.getPrinterTypeName());
        return ApiResult.ok(result);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("/setting/printer/delete")
    @OperationLog(name = "delete")
    @Operation(summary = "delete")
    public ApiResult setDelete(Integer id) {
        if (printerService.setDelete(id)) {
            return ApiResult.ok("删除成功");
        } else {
            return ApiResult.fail("删除失败");
        }
    }
}
