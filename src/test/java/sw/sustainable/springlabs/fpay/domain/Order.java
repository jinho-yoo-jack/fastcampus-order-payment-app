package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@Builder
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

    @Column(name = "Order_state")
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
    @OrderColumn(name = "item_idx")
    private List<OrderItem> items;

    protected Order() {
    }

    public Order(String name, String phoneNumber, List<OrderItem> items) throws Exception {
        if (!verifyHaveAtLeastOneItem(items)) throw new Exception("Noting Items");
        calculateTotalAmount(items);
        this.orderId = UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = OrderStatus.ORDER_COMPLETED;
    }

    public boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items != null && !items.isEmpty();
    }

    private void calculateTotalAmount(List<OrderItem> items) {
        this.totalPrice = items.stream()
                .map(OrderItem::calculateAmount)
                .reduce(0, Integer::sum);
    }

    public boolean isChangeableShippingAddress() {
        return !(status == OrderStatus.SHIPPING);
    }
}
