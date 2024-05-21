package sw.sustainable.springlabs.fpay.presentation.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;
import sw.sustainable.springlabs.fpay.presentation.request.Orderer;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PurchaseOrder {
    @NotNull
    @NotBlank
    private final Orderer orderer;

    @Size(min = 1)
    private final List<sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrderItem> newlyOrderedItem;

    private List<OrderItem> convert2OrderItems() {
        return newlyOrderedItem.stream()
                .map(this::convert2OrderItem)
                .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item){
        return OrderItem.builder()
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .size("FREE")
                .state(OrderStatus.ORDER_COMPLETED)
                .build();
    }

    public Order toEntity(){
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
