package sw.sustainable.springlabs.fpay.presentation.in.web;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.infrastructure.common.ApiResponse;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrder;
import sw.sustainable.springlabs.fpay.presentation.response.NewPurchaseOrder;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;

    @PostMapping("/new")
    public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder newOrder) {
        return NewPurchaseOrder.from(createNewOrderUseCase.create(newOrder));
    }

    @GetMapping
    public String test(){
        return "TEST";
    }

}
