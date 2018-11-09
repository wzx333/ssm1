package com.itheima.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date>{
    @Override
    public Date convert(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date date = format.parse(s);
            return date;
        } catch (ParseException e) {
            System.out.println("字符串转化日期失败");
            e.printStackTrace();
        }
        return null;
    }
}
