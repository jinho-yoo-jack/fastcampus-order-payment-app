package sw.sustainable.springlabs.fpay.presentation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @GetMapping("/success")
    public String paymentFullfill(@RequestParam(value = "paymentType") String paymentType,
                                  @RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "paymentKey") String paymentKey,
                                  @RequestParam(value = "amount") String amount
    ) {
        log.info("Request Params => paymentType ::: {}", paymentType);
        return "success";
    }

    @GetMapping("/fail")
    public String paymentFail(@RequestParam(value = "message") String message) {
        log.info("Request Params => paymentType ::: {}", message);
        return "fail";
    }


}
