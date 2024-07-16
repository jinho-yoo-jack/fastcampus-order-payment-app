package sw.sustainable.springlabs.fpay.representation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.response.NewPurchaseOrder;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;

    @PostMapping("/new")
    public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder purchaseOrder) throws Exception {
        return NewPurchaseOrder.from(createNewOrderUseCase.createNewOrder(purchaseOrder));
    }

}
