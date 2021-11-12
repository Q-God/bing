package org.gqdemo.bing.converter;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version v1.0
 * @ClassName StringToDateConverter
 * @Description TODO
 * @Author Q
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    @SneakyThrows
    @Override
    public Date convert(String source) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd ");
        return formatter.parse(source);
    }
}
