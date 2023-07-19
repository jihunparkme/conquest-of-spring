package com.conquest.spring.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorWebConfig implements WebMvcConfigurer {

    /**
     * WebMvcConfigurer 가 제공하는 addInterceptors() 를 사용해서 인터셉터 등록
     * 인터셉터는 addPathPatterns, excludePathPatterns 로 매우 정밀하게 URL 패턴 지정
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()) // 인터셉터 등록
                .order(1) // 인터셉터 호출 순서 지정
                .addPathPatterns("/**") // 인터셉터 적용 URL 패턴 지정
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 인터셉터 제외 패턴 지정
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error"
                );
    }
}
