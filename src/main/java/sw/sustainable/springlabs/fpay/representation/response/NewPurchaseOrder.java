package sw.sustainable.springlabs.fpay.representation.response;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;

import java.util.*;

@Getter
@Slf4j
public class NewPurchaseOrder {
    private final UUID orderId;

    private final Orderer orderer;

    private final String paymentId;

    private final int totalPrice;

    private final OrderStatus status;

    @Getter
    private List<NewPurchaseOrderItem> items = new ArrayList<>();

    private NewPurchaseOrder(UUID id, String name, String phoneNumber, String paymentId, int totalPrice, OrderStatus status, List<OrderItem> items) {
        this.orderId = id;
        this.orderer = new Orderer(name, phoneNumber);
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = NewPurchaseOrderItem.from(items);
    }

    public static NewPurchaseOrder from(Order order) {
        log.info("orderItems -> {}", order.getItems());
        return new NewPurchaseOrder(order.getOrderId(), order.getName(), order.getPhoneNumber(), order.getPaymentId(), order.getTotalPrice(),
                order.getStatus(), order.getItems());
    }
}
