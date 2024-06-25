package sw.sustainable.springlabs.fpay.representation.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

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
    private List<sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem> newlyOrderedItem;

    private List<sw.sustainable.springlabs.fpay.domain.order.OrderItem> convert2OrderItems() {
        return newlyOrderedItem.stream()
            .map(this::convert2OrderItem)
            .toList();
    }

    private sw.sustainable.springlabs.fpay.domain.order.OrderItem convert2OrderItem(PurchaseOrderItem item) {
        return sw.sustainable.springlabs.fpay.domain.order.OrderItem.builder()
            .itemIdx(item.getItemIdx())
            .productId(item.getProductId())
            .productName(item.getProductName())
            .price(item.getPrice())
            .size("FREE")
            .state(sw.sustainable.springlabs.fpay.domain.order.OrderStatus.ORDER_COMPLETED)
            .build();
    }

    public sw.sustainable.springlabs.fpay.domain.order.Order toEntity() {
        return sw.sustainable.springlabs.fpay.domain.order.Order.builder()
            .orderId(sw.sustainable.springlabs.fpay.domain.order.Order.generateOrderId())
            .items(convert2OrderItems())
            .name(orderer.getName())
            .phoneNumber(orderer.getPhoneNumber())
            .status(OrderStatus.ORDER_COMPLETED)
            .paymentId(UUID.randomUUID())
            .build();
    }
}