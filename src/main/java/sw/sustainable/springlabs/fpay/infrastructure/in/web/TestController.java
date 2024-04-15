package sw.sustainable.springlabs.fpay.infrastructure.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test(){
        return "ResponseBodyAdvice testing";
    }

    @GetMapping("/v2")
    public boolean test2() {
        return false;
    }
}
