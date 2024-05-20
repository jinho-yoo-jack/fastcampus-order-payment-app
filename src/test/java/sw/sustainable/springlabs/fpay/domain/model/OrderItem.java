package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus state;

    protected OrderItem() {}

    public int calculateAmount() {
        return price * amount;
    }
}
