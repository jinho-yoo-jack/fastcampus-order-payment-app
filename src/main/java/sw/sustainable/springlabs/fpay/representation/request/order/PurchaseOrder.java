package sw.sustainable.springlabs.fpay.representation.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.util.StringUtils;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;

import java.util.ArrayList;
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

    public List<OrderItem> convert2OrderItems(Order o) {
        return newlyOrderedItem.stream()
                .map(items -> convert2OrderItem(items, o))
                .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item, Order o) {
        return OrderItem.builder()
                .order(o)
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .size("FREE")
                .state(OrderStatus.ORDER_COMPLETED)
                .build();
    }

    public Order toEntity() {
        Order o = Order.builder()
                .items(new ArrayList<>())
                .name(this.getOrderer().getName())
                .phoneNumber(this.getOrderer().getPhoneNumber())
                .status(OrderStatus.ORDER_COMPLETED)
                .paymentId("")
                .build();
        o.getItems().addAll(this.convert2OrderItems(o));
        return o;
    }


}