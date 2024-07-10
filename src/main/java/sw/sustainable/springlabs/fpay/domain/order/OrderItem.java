package sw.sustainable.springlabs.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_items")
@Builder
@Setter
@Getter
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_idx")
    private int itemIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

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
        int totalPrice = price * quantity;
        this.amount = totalPrice;
        return totalPrice;
    }
}
