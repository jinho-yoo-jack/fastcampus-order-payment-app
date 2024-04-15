package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentInfo {
    private PaymentMethod paymentMethod;
    private CardPayment cardPayment;
}
