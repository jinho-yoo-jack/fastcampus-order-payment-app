package sw.sustainable.springlabs.fpay.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;

@RestController
@RequestMapping("/cancel")
@RequiredArgsConstructor
@Slf4j
public class CancelController {
    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping("/payment")
    public boolean cancelPayment(@RequestBody @Valid CancelOrder cancelOrder) throws Exception {
        return paymentCancelUseCase.paymentCancel(cancelOrder);
    }

}
