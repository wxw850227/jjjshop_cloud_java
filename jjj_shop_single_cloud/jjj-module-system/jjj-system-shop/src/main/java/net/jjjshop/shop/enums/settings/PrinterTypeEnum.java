package net.jjjshop.shop.enums.settings;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Getter
@AllArgsConstructor
public enum PrinterTypeEnum {
    FEI_E_YUN("飞鹅打印机"),
    PRINTER_CENTER("365云打印");


    private String text;

    public static List<String> getTypeName() {
        List<String> result = new ArrayList<>();
        for (PrinterTypeEnum type : EnumSet.allOf(PrinterTypeEnum.class)) {
            result.add(type.getText());
        }
        return result;
    }

}
