package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import sw.sustainable.springlabs.fpay.domain.model.OrderInfo;
import sw.sustainable.springlabs.fpay.domain.model.PaymentInfo;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    private String id;

    @Embedded
    private PaymentInfo paymentInfo;

    @Embedded
    private OrderInfo orderInfo;
}
