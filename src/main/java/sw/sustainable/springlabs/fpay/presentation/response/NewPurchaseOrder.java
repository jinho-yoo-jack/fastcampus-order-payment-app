package sw.sustainable.springlabs.fpay.presentation.response;

import lombok.Getter;
import sw.sustainable.springlabs.fpay.domain.model.*;
import sw.sustainable.springlabs.fpay.presentation.request.Orderer;

import java.util.*;

@Getter
public class NewPurchaseOrder {
    private final UUID orderId;

    private final Orderer orderer;

    private final UUID paymentId;

    private final int totalPrice;

    private final OrderStatus status;

    private final List<OrderItem> items;

    private NewPurchaseOrder(UUID id, String name, String phoneNumber, UUID paymentId, int totalPrice, OrderStatus status, List<OrderItem> items) {
        this.orderId = id;
        this.orderer = new Orderer(name, phoneNumber);
        this.paymentId = paymentId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
    }

    public static NewPurchaseOrder from(Order order) {
        return new NewPurchaseOrder(order.getOrderId(), order.getName(), order.getPhoneNumber(), order.getPaymentId(), order.getTotalPrice(),
                order.getStatus(), order.getOrderedItems());
    }
}
