package joos.core.web;

import jakarta.servlet.http.HttpServletRequest;
import joos.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    //ObjectProvider.getObject()를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testID");
        return "OK";
    }
}
