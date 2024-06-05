package sw.sustainable.springlabs.fpay.presentation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullfillUseCase;
import sw.sustainable.springlabs.fpay.presentation.request.payment.PaymentApproved;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentFullfillUseCase paymentFullFillService;

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

    @PostMapping("/confirm")
    public String paymentConfirm(@RequestBody PaymentApproved paymentApproved) throws Exception {
        return paymentFullFillService.paymentApproved(paymentApproved);
    }


}
