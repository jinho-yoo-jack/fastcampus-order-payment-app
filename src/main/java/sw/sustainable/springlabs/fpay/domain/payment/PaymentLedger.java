package sw.sustainable.springlabs.fpay.domain.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "payment_transaction")
@Builder
@Getter
@AllArgsConstructor
public class PaymentLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_id")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod method; // CARD:카드

    @Column(name = "payment_status")
    private String paymentStatus; // approve, cancel, settle

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "balance_amount")
    private int balanceAmount;

    @Column(name = "canceled_amount")
    private int canceledAmount;

    protected PaymentLedger() {
    }

    public boolean isCancellableAmountGreaterThan(int cancellationAmount){
        return balanceAmount >= cancellationAmount;
    }

}
