package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name="order_items")
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    private UUID order_id;

    @Column(name = "item_idx")
    private int itemIdx;

    @Column(name = "product_id", nullable = true)
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private int price;

    @Column(name = "product_size")
    private String size;

    private int amount;

    protected OrderItem() {}

    public int calculateAmount() {
        return price * amount;
    }
}
