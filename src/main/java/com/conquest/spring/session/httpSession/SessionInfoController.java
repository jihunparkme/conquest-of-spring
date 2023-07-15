package com.conquest.spring.session.httpSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator().forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));
        // Session ID (JSESSIONID 값)
        log.info("sessionId={}", session.getId());
        // 세션 유효 시간 (sec.)
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        // 세션 생성 일시 (Long)
        log.info("creationTime={}", new Date(session.getCreationTime()));
        // 세션과 연결된 사용자가 최근에 서버에 접근한 시간 (Long) ->  클라이언트에서 서버로 sessionId(JSESSIONID)를 요청한 경우 갱신
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        // 새로 생성된 세션인지 확인
        log.info("isNew={}", session.isNew());

        return "세션 출력";
    }
}