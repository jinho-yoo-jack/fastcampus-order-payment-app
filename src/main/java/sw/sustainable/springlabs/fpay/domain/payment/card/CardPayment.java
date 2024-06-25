package sw.sustainable.springlabs.fpay.domain.payment.card;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.ResponsePaymentApproved;

@Entity
@Table(name = "card_payment")
@Builder
@Getter
@AllArgsConstructor
public class CardPayment {
    @Id
    @Column(name = "payment_key")
    private String paymentKey; // example) tgen_20240605132741Jtkz1

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "approve_no")
    private String approveNo;

    @Column(name = "amount")
    private int totalAmount;

    @Column(name = "acquire_status")
    @Convert(converter = AcquireStatusConverter.class)
    private AcquireStatus acquireStatus;

    @Column(name = "cancel_amount")
    private int availableCancelAmount;

    @Column(name = "canceled_amount")
    private int canceledAmount;

    @Column(name = "issuer_code")
    private String issuer_code;

    @Column(name = "acquirer_code")
    private String acquirerCode;

    @Column(name = "acquirer_status")
    private String acquirerStatus;

    protected CardPayment() {
    }

    public CardPayment toEntity(ResponsePaymentApproved response) {
        return CardPayment.builder()
                .paymentKey(response.getPaymentKey())
                .cardNumber(response.getCard().getNumber())
                .approveNo(response.getCard().getApproveNo())
                .totalAmount(response.getTotalAmount())
                .acquireStatus(AcquireStatus.valueOf(response.getCard().getAcquireStatus()))
                .availableCancelAmount(response.getBalanceAmount())
                .canceledAmount(response.getTotalAmount() - response.getBalanceAmount())
                .acquirerCode(response.getCard().getAcquirerCode())
                .acquirerStatus(response.getCard().getAcquireStatus())
                .build();
    }
}
