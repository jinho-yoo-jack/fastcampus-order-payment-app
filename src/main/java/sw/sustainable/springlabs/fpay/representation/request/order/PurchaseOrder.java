package sw.sustainable.springlabs.fpay.representation.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

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