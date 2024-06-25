package sw.sustainable.springlabs.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Embeddable
@AllArgsConstructor
@Getter
@Builder
public class OrderItem {
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

    public void update(OrderStatus state) {
        this.state = state;
    }

    public int calculateAmount() {
        return price * amount;
    }
}
