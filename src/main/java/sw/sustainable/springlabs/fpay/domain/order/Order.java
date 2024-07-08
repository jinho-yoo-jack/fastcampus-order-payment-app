package sw.sustainable.springlabs.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID orderId;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "order_state")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {
    }

    public Order(String name, String phoneNumber, List<OrderItem> items) throws Exception {
        if (verifyHaveAtLeastOneItem(items)) throw new Exception("Noting Items");
        calculateTotalAmount(items);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = OrderStatus.ORDER_COMPLETED;
        this.items = items;
    }

    public static UUID generateOrderId() {
        return UUID.randomUUID();
    }

    public void orderPaymentFullFill(String paymentKey) {
        update(OrderStatus.PAYMENT_FULLFILL);
        this.paymentId = paymentKey;
    }

    public void orderCancel() {
        orderAllCancel();
    }

    public void orderCancel(int itemIdx) {
        orderCancelBy(itemIdx);
    }

    public void orderCancel(int[] itemIdxs) {
        Stream.<int[]>of(itemIdxs).forEach(this::orderCancel);
    }


    public static boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items == null || items.isEmpty();
    }

    public boolean verifyDuplicateOrderItemId() {
        List<UUID> productIds = this.getItems().stream().map(OrderItem::getProductId).distinct().toList();
        if (!productIds.isEmpty()) return true;
        else throw new IllegalArgumentException();
    }

    public boolean isChangeableShippingAddress() {
        return !(status.equals(OrderStatus.SHIPPING));
    }

    public boolean isPossibleToCancel() {
        return !this.status.equals(OrderStatus.PURCHASE_DECISION);
    }

    private void orderCancelBy(int itemIdx) {
        this.items.stream().filter(orderItem -> orderItem.getItemIdx() == itemIdx).forEach(item -> item.update(OrderStatus.ORDER_CANCELLED));
    }

    private void orderAllCancel() {
        items.forEach(item -> item.update(OrderStatus.ORDER_CANCELLED));
        update(OrderStatus.ORDER_CANCELLED);
    }

    private Order update(OrderStatus status) {
        this.status = status;
        this.getItems().forEach(item -> item.update(status));
        return this;
    }

    private void calculateTotalAmount(List<OrderItem> items) {
        this.totalPrice = items.stream().map(OrderItem::calculateAmount).reduce(0, Integer::sum);
    }

}
