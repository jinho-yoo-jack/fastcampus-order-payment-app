package sw.sustainable.springlabs.fpay.domain.model.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER_COMPLETED("01"),
    ORDER_CANCELLED("02"),
    PAYMENT_FULLFILL("03"),
    SHIPPING_PREPARE("04"),
    SHIPPING("05"),
    SHIPPING_COMPLETED("06"),
    PURCHASE_DECISION("07");

    private final String code;

    OrderStatus(String c) {
        code = c;
    }
}
