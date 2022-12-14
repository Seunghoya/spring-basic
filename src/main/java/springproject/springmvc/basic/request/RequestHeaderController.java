package springproject.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    /**
     *
     HttpServletRequest
     HttpServletResponse
     HttpMethod : HTTP 메서드를 조회한다. org.springframework.http.HttpMethod
     Locale : Locale 정보를 조회한다.
     @RequestHeader : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
     @RequestHeader("host") : 특정 HTTP 헤더를 조회한다.
     @CookieValue : 특정 쿠키를 조회한다.

     MultiValueMap -> MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
     */
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false)
                          String cookie
    ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
