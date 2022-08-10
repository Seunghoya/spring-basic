package springproject.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @Controller는 반환값을 String으로 주고 반환한 값을 뷰 이름으로 인식한다.
* @RestController는 반환값으로 뷰를 찾는게 아니라 HTTP메시지 바디에 바로 입력한다. */
@RestController
public class LogTestController {

    // 주의! slf4j의 Logger을 import 해야 한다.
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        bad case
//        log.trace("trace log=" + name);
//        +가 포함되면 연산 리소스가 더해지기 때문에 파라미터 호출하는 방식으로 작성해야 한다.

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}

/* System.out.println()으로 직접 출력하는것보다 훨씬 유용하다.
* 로그 레벨에 따라 상황에 맞게 조절해 운영서버에 불필요한 로그를 남기지 않아도 되고
* 개발서버에서 필요에 따른 로그들을 효율적으로 확인하며 개발할 수 있다.
* LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
* - 개발 서버는 debug 출력
* - 운영 서버는 info 출력
* 특히 파일로 남길 때 날짜나 특정 용량에 따른 로그를 분할하는 것도 가능하다.*/
