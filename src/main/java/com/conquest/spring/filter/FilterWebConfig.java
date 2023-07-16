package com.conquest.spring.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterWebConfig {

    /**
     * FilterRegistrationBean 를 사용하여 필터 등록
     *
     * @ServletComponentScan, @WebFilter(filterName = "logFilter", urlPatterns = "/*") 로 필터 등록이 가능하지만 필터 순서 조절 불가
     * Spring Boot 는 WAS 를 들고 함께 띄우기 때문에, WAS 를 띄울 때 필터를 같이 세팅
     */
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); // 등록할 필터 지정
        filterRegistrationBean.setOrder(1); // 필터는 체인으로 동작하므로 순서 지정
        filterRegistrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 지정
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter()); // 로그인 필터 등록
        filterRegistrationBean.setOrder(2); // 로그 필터 이후 로그인 필터 적용
        filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청에 로그인 필터 적용
        return filterRegistrationBean;
    }
}
