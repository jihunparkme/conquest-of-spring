package com.conquest.spring.exception.servlet.errorPage.dispatcherType;

import com.conquest.spring.exception.servlet.api.exceptionResolver.MyHandlerExceptionResolver;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * error-page/** 요청 시
 * 필터는 setDispatcherTypes 에 DispatcherType.ERROR 가 등록되어 호출되지만,
 * 인터셉터는 excludePathPatterns 에 오류 페이지 경로가 등록되어 호출되지 않음.
 *
 * error-page/** 요청 시에는
 * 필터와 인터셉터 모두 호출되지만,
 * 내부 응답으로 요청되는 error-page/** 요청은 필터만 호출
 */
@Configuration
public class DispatcherTypeWebConfig implements WebMvcConfigurer {

    /**
     * 필터는 필터 등록 시 특정 DispatcherType 인 경우 필터가 적용되도록 설정이 가능했지만,
     * 인터셉터는 스프링이 제공하는 기능이라서 DispatcherType 와 무관하게 항상 호출
     *
     * 대신 인터셉터의 excludePathPatterns 를 사용해서 특정 경로 제외 가능
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns( // 해당 경로 호출 시 인터셉터를 호출하지 않음
                        "/css/**", "/*.ico"
                        , "/error", "/error-page/**"
                );
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
    }

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }
}
