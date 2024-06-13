package sw.sustainable.springlabs.fpay.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="card_payment")
public class CardPayment {
    @Id
    @Column(name = "payment_id")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    private String cardNumber;
    private String approveNo;
    private int amount;
    private String acquireStatus;
}
