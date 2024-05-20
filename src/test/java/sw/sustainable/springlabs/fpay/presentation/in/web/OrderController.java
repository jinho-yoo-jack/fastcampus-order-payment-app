package sw.sustainable.springlabs.fpay.presentation.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.presentation.in.web.request.PurchaseOrder;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;

    @PostMapping("/new")
    public Order newOrder(@RequestBody @Valid PurchaseOrder newOrder) {
        return createNewOrderUseCase.create(newOrder);
    }

}
