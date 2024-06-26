package sw.sustainable.springlabs.fpay.domain.order;

import jakarta.persistence.*;
import lombok.*;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;

import java.util.*;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@Getter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

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

//    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
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

    public void save(OrderRepository orderRepository) throws Exception {
        orderRepository.save(this);
    }

    public Order orderPaymentFullFill(){
        return update(OrderStatus.PAYMENT_FULLFILL);
    }

    public Order orderAllCancel(){
        return update(OrderStatus.ORDER_CANCELLED);
    }

    private Order update(OrderStatus status) {
        this.status = status;
        this.getItems().forEach(item -> item.update(status));
        return this;
    }

    private void calculateTotalAmount(List<OrderItem> items) {
        this.totalPrice = items.stream()
                .map(OrderItem::calculateAmount)
                .reduce(0, Integer::sum);
    }

    public boolean verifyHaveAtLeastOneItem(List<OrderItem> items) {
        return items == null || items.isEmpty();
    }

    public boolean isChangeableShippingAddress() {
        return !(status.equals(OrderStatus.SHIPPING));
    }

    public boolean isPossibleToCancel() {
        return this.status.equals(OrderStatus.PURCHASE_DECISION);
    }

}
