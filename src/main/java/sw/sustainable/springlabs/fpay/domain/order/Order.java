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

    @Builder
    public Order(String name, String phoneNumber, List<OrderItem> items) throws Exception {
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

    public void orderAllCancel() {
        items.forEach(item -> item.update(OrderStatus.ORDER_CANCELLED));
        update(OrderStatus.ORDER_CANCELLED);
    }

    public void orderCancel(int[] itemIdxs) {
        for(int itemIdx : itemIdxs){
            orderCancelBy(itemIdx);
        }
    }

    private void orderCancelBy(int itemIdx) {
        this.items.stream().filter(orderItem -> orderItem.getItemIdx() == itemIdx)
            .forEach(item -> item.update(OrderStatus.ORDER_CANCELLED));
    }

    public static boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items == null || items.isEmpty();
    }

    public boolean verifyDuplicateOrderItemId() {
        List<UUID> productIds = this.getItems().stream().map(OrderItem::getProductId).distinct().toList();
        if (!productIds.isEmpty()) return true;
        else throw new IllegalArgumentException();
    }

    public boolean isNotOrderStatusPurchaseDecision() {
        return !(this.status.equals(OrderStatus.PURCHASE_DECISION));
    }


    private Order update(OrderStatus status) {
        this.status = status;
        this.getItems().forEach(item -> item.update(status));
        return this;
    }

    public void calculateTotalAmount() {
        this.totalPrice = this.items.stream().map(OrderItem::calculateAmount).reduce(0, Integer::sum);
    }

}
