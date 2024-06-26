package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatusConverter;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;

import java.util.*;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@Getter
@Builder
public class Order {
    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID orderId;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "payment_id")
    private UUID paymentId;

    @NotBlank
    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "Order_state")
    @Convert(converter = OrderStatusConverter.class)
    private sw.sustainable.springlabs.fpay.domain.order.OrderStatus status;

    @OneToMany
    @JoinColumn()
    private List<sw.sustainable.springlabs.fpay.domain.order.OrderItem> items = new ArrayList<>();

    protected Order() {
    }

    public Order(UUID orderId, String name, String phoneNumber, List<sw.sustainable.springlabs.fpay.domain.order.OrderItem> items) throws Exception {
        if (verifyHaveAtLeastOneItem(items)) throw new Exception("Noting Items");
        calculateTotalAmount(items);
        this.orderId = orderId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = sw.sustainable.springlabs.fpay.domain.order.OrderStatus.ORDER_COMPLETED;
        this.items = items;
    }

    public static UUID generateOrderId() {
        return UUID.randomUUID();
    }

    public void save(OrderRepository orderRepository) throws Exception {
        if (verifyHaveAtLeastOneItem(items))
            throw new Exception("Noting Items");
//        orderRepository.save(this);
        orderRepository.save(null);
    }

    public sw.sustainable.springlabs.fpay.domain.order.Order update(sw.sustainable.springlabs.fpay.domain.order.OrderStatus status) {
        this.status = status;
        return null;
    }

    private void calculateTotalAmount(List<sw.sustainable.springlabs.fpay.domain.order.OrderItem> items) {
        this.totalPrice = items.stream()
                .map(sw.sustainable.springlabs.fpay.domain.order.OrderItem::calculateAmount)
                .reduce(0, Integer::sum);
    }

    public boolean verifyHaveAtLeastOneItem(List<sw.sustainable.springlabs.fpay.domain.order.OrderItem> items) {
        return items == null || items.isEmpty();
    }

    public boolean isChangeableShippingAddress() {
        return !(status.equals(sw.sustainable.springlabs.fpay.domain.order.OrderStatus.SHIPPING));
    }

    public boolean isPossibleToCancel() {
        return this.status.equals(OrderStatus.PURCHASE_DECISION);
    }

    public List<OrderItem> getOrderedItems() {
        return this.items;
    }
}
