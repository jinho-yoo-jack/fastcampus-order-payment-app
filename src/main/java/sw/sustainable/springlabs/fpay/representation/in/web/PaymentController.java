package sw.sustainable.springlabs.fpay.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullfillUseCase;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentFullfillUseCase paymentFullFillService;

    @GetMapping("/success")
    public String paymentFullfill(@RequestParam(value = "paymentType") String paymentType, @RequestParam(value = "orderId") String orderId,
                                  @RequestParam(value = "paymentKey") String paymentKey, @RequestParam(value = "amount") String amount
    ) {
        return "success";
    }

    @GetMapping("/fail")
    public String paymentFail(@RequestParam(value = "message") String message) {
        return "fail";
    }

    @PostMapping("/confirm")
    public String paymentConfirm(@RequestBody PaymentApproved paymentApproved) throws Exception {
        /*http://localhost:8080/payment/checkout.html?orderId=f8228ea2-915d-4967-aaa1-8c21e4aa8387&userId=fastcamp-y&ordererName=%EC%9C%A0%EC%A7%84%ED%98%B8&ordererPhoneNumber=01012341234&orderName=%EC%86%8D%EC%9D%B4%ED%8E%B8%ED%95%9C%EC%9A%B0%EC%9C%A0&amount=13400*/
        return paymentFullFillService.paymentApproved(paymentApproved);
    }

}
