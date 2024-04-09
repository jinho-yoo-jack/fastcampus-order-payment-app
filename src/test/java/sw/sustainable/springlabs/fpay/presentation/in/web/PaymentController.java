package sw.sustainable.springlabs.fpay.presentation.in.web;

import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullFill;

@RestController
@RequestMapping("fpay")
public class PaymentController {
    private PaymentFullFill paymentFullFill;

    @PostMapping("fullfill")
    public boolean receivedPaymentFullFillInfo(@RequestBody FullFillRequestMessage request){
        return true;
    }

}
