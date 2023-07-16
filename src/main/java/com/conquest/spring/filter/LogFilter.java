package com.conquest.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

/**
 * 필터 사용을 위해 필터 인터페이스 구현
 */
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    /**
     * HTTP 요청이 오면 호출
     * - 고객의 요청 응답 정보를 한 번에 확인 가능
     * - 시간 정보를 추가해서 요청 시간 확인 및 성능 최적화 가능
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 요청 URI 정보

        String uuid = UUID.randomUUID().toString(); // HTTP 요청 구분 목적

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            /**
             * 다음 필터가 있으면 필터 호출. 필터가 없으면 서블릿 호출
             * (doFilter 를 호출하지 않으면 다음 단계로 진행되지 않음)
             */
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}