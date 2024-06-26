package sw.sustainable.springlabs.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @Id
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Order order;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "item_idx")
    private int itemIdx;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int price;

    @Column(name = "product_size")
    private String size;

    private int amount;

    private int quantity;

    @Column(name = "order_state")
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
