package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import sw.sustainable.springlabs.fpay.domain.model.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @NotNull(message = "The orderer is required.")
    @Valid
    private Orderer orderer;

    @Size(min = 1)
    @Valid
    private List<PurchaseOrderItem> newlyOrderedItem;

    private List<OrderItem> convert2OrderItems() {
        return newlyOrderedItem.stream()
            .map(this::convert2OrderItem)
            .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item) {
        return OrderItem.builder()
            .itemIdx(item.getItemIdx())
            .productId(item.getProductId())
            .productName(item.getProductName())
            .price(item.getPrice())
            .size("FREE")
            .state(OrderStatus.ORDER_COMPLETED)
            .build();
    }

    public Order toEntity() {
        return Order.builder()
            .orderId(Order.generateOrderId())
            .items(convert2OrderItems())
            .name(orderer.getName())
            .phoneNumber(orderer.getPhoneNumber())
            .status(OrderStatus.ORDER_COMPLETED)
            .paymentId(UUID.randomUUID())
            .build();
    }
}