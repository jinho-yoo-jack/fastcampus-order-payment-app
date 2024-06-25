package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatusConverter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    private UUID orderId;

    private int itemIdx;

    private UUID productId;

    private String productName;

    private int price;

    private String size;

    private int amount;

    private int quantity;

    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus state;

    protected OrderItem() {
    }

    public int calculateAmount() {
        return price * amount;
    }
}
