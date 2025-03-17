package net.jjjshop.shop.service.settings.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import net.jjjshop.common.entity.settings.Printer;
import net.jjjshop.common.enums.PrinterTypeEnum;
import net.jjjshop.common.mapper.settings.PrinterMapper;
import net.jjjshop.framework.common.service.impl.BaseServiceImpl;
import net.jjjshop.framework.core.pagination.PageInfo;
import net.jjjshop.framework.core.pagination.Paging;
import net.jjjshop.shop.param.setting.PrinterPageParam;
import net.jjjshop.shop.param.setting.PrinterParam;
import net.jjjshop.shop.service.settings.PrinterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小票打印机记录表 服务实现类
 * @author jjjshop
 * @since 2022-07-20
 */
@Slf4j
@Service
public class PrinterServiceImpl extends BaseServiceImpl<PrinterMapper, Printer> implements PrinterService {

    /**
     * 分页查询小票打印机
     * @param printerPageParam
     * @return
     */
    @Override
    public Paging<Printer> getList(PrinterPageParam printerPageParam) {
        Paging<Printer> paging = new Paging<>(this.page(new PageInfo<>(printerPageParam), new LambdaQueryWrapper<Printer>()
                .orderByAsc(Printer::getSort)));
        if(paging.getTotal() > 0){
            paging.getRecords().forEach(o -> {
                if (PrinterTypeEnum.FEI_E_YUN.getPrinterType().equals(o.getPrinterType())) {
                    o.setPrinterType(PrinterTypeEnum.FEI_E_YUN.getText());
                }
            });
        }
        return paging;
    }

    /**
     * 获取所有小票打印机
     * @param
     * @return
     */
    @Override
    public List<Printer> getAll() {
        return this.list();
    }

    /**
     * 添加小票打印机
     * @param printerParam
     * @return
     */
    @Override
    public boolean add(PrinterParam printerParam) {
        return this.save(this.getPrinterByType(printerParam));
    }

    /**
     * 修改小票打印机
     * @param printerParam
     * @return
     */
    @Override
    public boolean edit(PrinterParam printerParam) {
        return this.updateById(this.getPrinterByType(printerParam));
    }

    /**
     * 删除小票打印机
     * @param id
     * @return
     */
    @Override
    public boolean setDelete(Integer id) {
        return this.update(new LambdaUpdateWrapper<Printer>().eq(Printer::getPrinterId, id)
                .set(Printer::getIsDelete, 1));
    }

    /**
     * 查询小票打印机种类
     * @param
     * @return
     */
    @Override
    public List<String> getPrinterTypeName() {
        return PrinterTypeEnum.getTypeName();
    }

    /**
     * 通过打印机的种类获取打印机对象
     * @param printerParam
     * @return
     */
    public Printer getPrinterByType(PrinterParam printerParam) {
        Printer printer = new Printer();
        BeanUtils.copyProperties(printerParam, printer);
        if (PrinterTypeEnum.FEI_E_YUN.getText().equals(printerParam.getPrinterType())) {
            String feiE = printerParam.getFeieYun().toString();
            printer.setPrinterType(PrinterTypeEnum.FEI_E_YUN.getPrinterType());
            printer.setPrinterConfig(feiE);
        }
        return printer;
    }
}
