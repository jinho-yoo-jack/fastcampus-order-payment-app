package sw.sustainable.springlabs.fpay.domain.payment;

import jakarta.persistence.*;
import lombok.Getter;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;

@Entity
@Table(name = "payment")
@Getter
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    private String method;

    protected Payment() {
    }

    public Payment(String paymentKey, String method) {
        this.paymentKey = paymentKey;
        this.method = method;
    }

    public static Payment toEntity(ResponsePaymentApproved response) {
        return new Payment(response.getPaymentKey(), response.getMethod());
    }

}