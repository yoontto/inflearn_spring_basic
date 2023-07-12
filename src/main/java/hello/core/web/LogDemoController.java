package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //서비스 계층에는 웹 관련 정보를 가급적 넘기지 않는게 좋음
        //웹 관련 정보는 컨트롤러에서 처리하는 것이 유지보수 측면에서 좋음
        String requestURL = request.getRequestURL().toString();

        //myLogger :: class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$35e8c2a2
        //CGLIB으로 클래스를 상속받은 가짜 프록시 객체를 만들어서 주입한다.
        //가짜 프록시 객체는 요청이 오면 내부에서 진짜 빈을 요청하는 위임 로직이 있음
        System.out.println("myLogger :: " + myLogger.getClass());

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";
    }

}
