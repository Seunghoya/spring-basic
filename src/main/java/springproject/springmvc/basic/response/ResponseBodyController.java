package springproject.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springproject.springmvc.basic.HelloData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody // 메서드 레벨에서 사용중인 어노테이션을 삭제하고 클래스 레벨에서 한번에 선언해서 사용해도 된다. 이경우 v3의 메시지 바디에 직접 담아서 사용하는 것을 주의해야 한다
//@RestController -> @Controller과 @ResponseBody의 기능을 합친 어노테이션.
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse res) throws IOException {
        res.getWriter().write("ok");
    }
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }

    /*
    * 프로그램 상태에 따라 전달되는 상태코드를 변경하기 위해서는 ResponseEntity를 사용하는걸 권장한다.
    * @ResponseStatus 어노테이션을 사용하면 지정한 상태코드 외에는 전달하기 힘들다.(불가능하진 않다.) */



    /* @ResponseBody를 사용하면
    * - HTTP 바디에 String 내용을 직접 반환
    * - viewResolver 대신 HttpMessageConverter가 동작
    * - 기본 문자는 StringHttpMessageConverter이 처리하고
    * - 기본 객체는 MappingJacksonHttpMessageConverter이 처리한다
    * 기타 byte 등등은 HttpMessageConverter가 기본으로 등록되어 있다.
    *
    * Response는 클라이언트의 HTTP Accept Header와 서버의 컨트롤러 반환 타입 정보들을 조합해서 HttpMessageConverter가 선택된다.
    *
    * 스프링은 다음의 경우에 HTTP 메시지 컨버터를 적용한다.
    * - HTTP 요청: @RequestBody, HttpEntity(RequestEntity)
    * - Http 응답: @ResponseBody, HttpEntity(ResponseEntity)
    * */

    /*
    * ArgumentResolver
    * RequestMappingHandlerAdaptor는 ArgumentResolver를 호출해
    * 컨트롤러가 필요로 하는 다양한 형태의 파라미터 값을 생성해준다.
    * 그리고 파라미터 값이 모두 준비되면 컨트롤러를 호출해 값을 넘겨준다.
    *
    * 이런 이유로 어노테이션 기반의 컨트롤러에서 다양한 파라미터를 사용할 수 있다.
    * */
}
