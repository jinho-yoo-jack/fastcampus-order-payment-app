package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderInfo {
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}
