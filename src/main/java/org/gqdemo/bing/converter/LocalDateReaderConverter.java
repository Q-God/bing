package org.gqdemo.bing.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @version v1.0
 * @ClassName LocalDateReadConverter
 * @Description 自定义LocalDate2Date转换器
 * @Author Q
 */
@Component
public class LocalDateReaderConverter implements Converter<LocalDate, Date> {

    @Override
    public Date convert(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
