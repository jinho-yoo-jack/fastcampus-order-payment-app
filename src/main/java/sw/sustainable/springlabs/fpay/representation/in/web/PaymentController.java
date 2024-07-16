package sw.sustainable.springlabs.fpay.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullFillUseCase;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
   private final PaymentFullFillUseCase paymentFullFillUseCase;

   @GetMapping("/success")
   public String paymentFullfill(@RequestParam(value = "paymentType") String paymentType, @RequestParam(value = "orderId") String orderId,
                                 @RequestParam(value = "paymentKey") String paymentKey, @RequestParam(value = "amount") String amount){
      return "success";
   }

   @GetMapping("/fail")
   public String paymentFail(@RequestParam(value = "message") String message){
      return "fail";
   }

   @PostMapping("/confirm")
   public String paymentConfirm(@RequestBody PaymentApproved paymentInfo) throws IOException {
      return paymentFullFillUseCase.paymentApproved(paymentInfo);
   }

}
