package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Embeddable
@RequiredArgsConstructor
@Access(AccessType.FIELD)
public class OrderItem {
    @Column(name= "order_id")
    private final UUID orderId;
    @Column(name= "product_id")
    private final UUID productId;
    @Column(name= "item_idx")
    private final int itemIdx;
    @Column(name= "product_name")
    private final String productName;
    @Column(name= "product_price")
    private final int price;
    @Column(name= "product_szie")
    private final String size;
    private final int amount;

    public int calculateAmount() {
        return price * amount;
    }
}
