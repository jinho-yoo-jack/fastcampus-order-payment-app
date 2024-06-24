package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @Id
    @Setter
    @Column(name="order_id")
    private UUID orderId;

    @Column(name="item_idx")
    private int itemIdx;

    @Column(name="product_id")
    private UUID productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private int price;

    @Column(name="product_size")
    private String size;

    private int amount;

    private int quantity;

    @Convert(converter = OrderStatusConverter.class)
    @Column(name="order_state")
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
