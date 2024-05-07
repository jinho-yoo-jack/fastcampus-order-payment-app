package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
@Access(AccessType.FIELD)
public class Order {
    @Id
    @Column(name = "order_id")
    private UUID orderId;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "total_price")
    private int totalPrice;
    private OrderStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"))
    @OrderColumn(name = "item_idx")
    private List<OrderItem> items;

    public boolean verifyHaveAtLeastOneItem() {
        return items != null && !items.isEmpty();
    }

    ;

    private int calculateTotalAmount() {
        return items.stream()
                .map(OrderItem::calculateAmount)
                .reduce(0, Integer::sum);
    }

    ;

    public boolean isChangeableShippingAddress() {
        return !(status == OrderStatus.SHIPPING);
    }

    ;
}
