# conquest-of-spring

[영한님의 스프링 완전 정복](https://www.inflearn.com/roadmaps/373) 완강 기념 체득 프로젝트

---

`스프링 입문`: 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

---

[`스프링 핵심 원리 : 기본편`](https://jihunparkme.github.io/Spring-Core/)
```text
• Spring
• 좋은 객체지향의 5가지 원칙 (SOLID)
• 스프링과 객체지향
• Spring Container
    • Bean 조회
• Singleton Container
• Component Scan
• 의존관계 자동 주입
    • 조회 대상 빈이 2개 이상일 경우
    • 조회한 빈이 모두 필요할 경우
• 빈 생성주기 콜백
• 빈 스코프
```

---

`모든 개발자를 위한 HTTP 웹 기본 지식`
- [`Basic`](https://jihunparkme.github.io/Http-Web-Network_basic/)
  ```text
  • 인터넷 네트워크
      • IP(Internet Protocol)
      • TCP, UDP
      • PORT
      • DNS
  • URI과 웹 브라우저 요청 흐름
      • URI (Uniform Resource Identifier)
      • 웹 브라우저 요청 흐름
  • HTTP 기본
      • HTTP
      • 클라이언트 서버 구조
      • Stateful, Stateless
          • Stateful
          • Stateless
      • 비 연결성(connectionless)
      • HTTP 메시지
          • Start-Line
          • HTTP Header
          • Message Body
  ```
- [`Method`](https://jihunparkme.github.io/Http-Web-Network_method/)
  ```text
  • HTTP 메서드
      • API URI 설계
          • GET
          • POST
          • PUT
          • PATCH
          • DELETE
      • HTTP 메서드의 속성
  • HTTP 메서드 활용
      • 클라이언트에서 서버로 데이터 전송
      • HTTP API 설계 예시
  • HTTP 상태코드
      • 2xx (Successful): 요청 정상 처리
      • 3xx (Redirection): 요청을 완료를 위해 추가 행동 필요
      • 4xx (Client Error)
      • 5xx (Server Error)
  ```
- [`Header`](https://jihunparkme.github.io/Http-Web-Network_header/)
  ```text
  • HTTP 해더 (일반 헤더)
      • HTTP 헤더
      • 표현
      • Content negotiation
      • 전송 방식
      • 일반 정보
      • 특별한 정보
      • 인증
      • 쿠키
  • HTTP 해더 (캐시와 조건부 요청)
      • 캐시 기본 동작
      • 검증 헤더와 조건부 요청 (Last-Modified)
      • 검증 헤더와 조건부 요청 (ETag)
      • 캐시와 조건부 요청 헤더
      • 프록시 서버
      • 캐시 무효화
  ```

---

`스프링 MVC 1편: 백엔드 웹 개발 핵심 기술`
- [`Servlet`](https://jihunparkme.github.io/Spring-MVC-Part1-Servlet/)
  ```text
  • Web Application
      • Web Server
      • Web Application Server (WAS)
      • Web System
      • Servlet
      • Multi Thread
      • HTML, API, SSR, CSR
          • HTMl
          • SSR
          • CSR
  • Servlet
      • Project
      • HttpServletRequest
      • HTTP Request Data
          • Get(URL Query Parameter)
          • Post(HTML Form)
          • HTTP Message Body
      • HttpServletResponse
      • HTTP Response Data
          • 단순 텍스트
          • HTML
          • HTML API (MessageBody JSON)
  ```
- [`MVC`](https://jihunparkme.github.io/Spring-MVC-Part1-MVC/) (5. 스프링 MVC - 구조 이해 ~ )

```text
• Spring MVC Framework
    • MVC Pattern
    • 스프링 MVC 전체 구조
    • HandlerMapping & HandlerAdapter
    • ViewResolver
• Spring MVC 기본 기능
    • Logging
    • 요청 매핑
• HTTP Request
    • Http Request Data
    • @RequestParam
    • HTTP message body
        • TEXT
        • JSON
• HTTP Response
    • HTTP message body
        • TEXT
        • JSON
    • HTTP Message Converter
    • Request Mapping Handler Adapter 구조
    • PRG (Post/Redirect/Get)
```

---

`스프링 MVC 2편: 백엔드 웹 개발 활용 기술`
- [`Thymeleaf`](https://jihunparkme.github.io/Spring-MVC-Part2-Thymeleaf/)

  ```text
  • 기본 기능
  • Form
  ```

- [`ETC`](https://jihunparkme.github.io/Spring-MVC-Part2-Etc/)
  ```text
  • 메시지, 국제화
      • Spring Message Source
      • Web Application Message
  • Spring Type Converter
      • Type Converter
          • ConversionService
          • Apply Converter in Spring 🌞
          • Apply Converter in View Template
      • Formatter
          • FormattingConversionService
          • Apply Formatter in Spring 🌞
          • Annotation driven Formatting
  • File Upload
      • 전송 방식
      • Servlet File Upload
      • Spring File Upload 🌞
      • File Upload And Download
  ```

- [`Validation`](https://jihunparkme.github.io/Spring-MVC-Part2-Validation/)

  ```text
  • Spring Verification
  • BindingResult
  • Error Message
      • Apply Thymeleaf
      • MessageCodesResolver
      • 오류 코드 관리 전략
      • ValidationUtils
      • Spring 검증 오류 메시지
  • Validator
  • WebDataBinder
  • Bean Validation
      • Apply Bean Validation in Spring
      • 에러 코드
      • 글로벌 오류
      • groups
      • Form 전송 객체 분리 🌞
  • HTTP Message Converter
      • @ModelAttribute VS @RequestBody
  ```

- [`Login`](https://jihunparkme.github.io/Spring-MVC-Part2-Login/)

  ```text
  • Cookie
      • 쿠키 생성
      • 쿠키 조회
      • 쿠키 제거
      • 보안 문제
  • Session
      • HttpSession
      • 세션 생성
      • 세션 조회
      • 세션 제거
      • 세션 정보
      • 세션 타임아웃
  • Filter, Interceptor
      • Servlet Filter
          • 요청 로그
          • 인증 체크
      • 스프링 인터셉터 🌞
          • 요청 로그
          • 인증 체크
      • ArgumentResolver 활용 🌞
  ```
  
- [`Exception`](https://jihunparkme.github.io/Spring-MVC-Part2-Exception/)

---

[`스프링 DB 1편: 데이터 접근 핵심 원리`](https://jihunparkme.github.io/Spring-DB-Part1/)

---

[`스프링 DB 2편: 데이터 접근 활용 기술`](https://jihunparkme.github.io/Spring-DB-Part2/)

---

[`스프링 핵심 원리: 고급편`](https://jihunparkme.github.io/Spring-Core-Principles-Advanced/)

---

[`실전! 스프링 부트`](https://jihunparkme.github.io/spring-boot/)



8. 예외 처리와 오류 페이지.pdf
9. API 예외 처리.pdf

➔

