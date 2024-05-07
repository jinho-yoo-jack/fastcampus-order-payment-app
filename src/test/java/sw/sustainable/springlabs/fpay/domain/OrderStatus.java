package sw.sustainable.springlabs.fpay.domain;

public enum OrderStatus {
    ORDER_COMPLETED,
    ORDER_CANCELLED,
    PAYMENT_FULLFILL,
    SHIPPING_PREPARE,
    SHIPPING,
    SHIPPING_COMPLETED,
    PURCHASE_DECISION;
}
