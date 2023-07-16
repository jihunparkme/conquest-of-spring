package com.conquest.spring.type.conversion;

import com.conquest.spring.type.converter.IpPortToStringConverter;
import com.conquest.spring.type.converter.StringToIpPortConverter;
import com.conquest.spring.type.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TypeWebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new MyNumberFormatter());
    }
}
