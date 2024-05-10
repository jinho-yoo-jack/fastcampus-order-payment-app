package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase_order")
@SecondaryTable(name = "order_items", pkJoinColumns = @PrimaryKeyJoinColumn(name = "order_id"))
@AllArgsConstructor
@Getter
@Builder
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID orderId;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "Order_state")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"))
    @AttributeOverrides({
        @AttributeOverride(name = "orderId", column = @Column(name = "order_id")),
        @AttributeOverride(name = "itemIdx", column = @Column(name = "item_idx")),
        @AttributeOverride(name = "productId", column = @Column(name = "product_id")),
        @AttributeOverride(name = "productName", column = @Column(name = "product_name")),
        @AttributeOverride(name = "price", column = @Column(name = "product_price")),
        @AttributeOverride(name = "size", column = @Column(name = "product_size")),
        @AttributeOverride(name = "state", column = @Column(name = "order_state")),
    })
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {
    }

    public Order(UUID orderId, String name, String phoneNumber, List<OrderItem> items) throws Exception {
        if (!verifyHaveAtLeastOneItem(items)) throw new Exception("Noting Items");
        calculateTotalAmount(items);
        this.orderId = orderId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = OrderStatus.ORDER_COMPLETED;
        this.items = items;
    }

    public static UUID generateOrderId() {
        return UUID.randomUUID();
    }

    public List<OrderItem> getOrderItems() {
        return this.items;
    }

    public boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items != null && !items.isEmpty();
    }

    public List<OrderItem> getOrderedItems() {
        return items;
    }

    private void calculateTotalAmount(List<OrderItem> items) {
        this.totalPrice = items.stream()
            .map(OrderItem::calculateAmount)
            .reduce(0, Integer::sum);
    }

    public boolean isChangeableShippingAddress() {
        return !(status.equals(OrderStatus.SHIPPING));
    }
}
