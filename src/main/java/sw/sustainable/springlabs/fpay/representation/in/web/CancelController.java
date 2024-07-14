package sw.sustainable.springlabs.fpay.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.GetOrderInfoUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.response.NewPurchaseOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class CancelController {
    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping("/cancel")
    public boolean cancelPayment(@RequestBody @Valid CancelOrder cancelOrder) throws Exception {
        return paymentCancelUseCase.paymentCancel(cancelOrder);
    }

}
