package com.conquest.spring.type.conversion;

import com.conquest.spring.type.converter.IntegerToStringConverter;
import com.conquest.spring.type.converter.IpPortToStringConverter;
import com.conquest.spring.type.converter.StringToIntegerConverter;
import com.conquest.spring.type.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
    }
}
