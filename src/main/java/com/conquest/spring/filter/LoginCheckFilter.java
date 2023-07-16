package com.conquest.spring.filter;

import com.conquest.spring.session.httpSession.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"}; // 인증과 무관하게 항상 허용하는 경로

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    /**
                     * 미인증 사용자는 로그인 화면으로 리다이렉트 처리
                     * 로그인 이후 요청 경로로 이동하기 위해 쿼리 파라미터로 요청 경로를 함께 전달
                     */
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    // 미인증 사용자는 다음(필터, 서블릿, 컨트롤러)으로 진행하지 않고 종료.
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            /**
             * 예외 로깅 가능 하지만, 톰캣까지 예외를 보내주어야 함
             * (ServletFilter 에서 터진 예외를 ServletContainer(WAS) 까지 올려줘야 함)
             */
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크 X
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
