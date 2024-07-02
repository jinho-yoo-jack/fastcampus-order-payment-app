package sw.sustainable.springlabs.fpay.representation.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class NewPurchaseOrderItem {
//    private final int id;

    private final UUID orderId;

    private final int itemIdx;

    private final UUID productId;

    private final String productName;

    private final int price;

    private final String size;

    private final int amount;

    private final int quantity;

    private final OrderStatus itemStatus;

    public static List<NewPurchaseOrderItem> from(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        NewPurchaseOrderItem.builder()
                                .orderId(orderItem.getOrder().getOrderId())
                                .itemIdx(orderItem.getItemIdx())
                                .productName(orderItem.getProductName())
                                .price(orderItem.getPrice())
                                .size(orderItem.getSize())
                                .amount(orderItem.getAmount())
                                .quantity(orderItem.getQuantity())
                                .itemStatus(orderItem.getState())
                                .build())
                .toList();
    }

}