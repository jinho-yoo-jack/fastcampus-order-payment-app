package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.method.Card;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentApproved extends ResponsePaymentCommon {
//    private String orderId;
    private String orderName;
    private Card card;
//    private String paymentKey;
    private String lastTransactionKey;
//    private String status; // 결제 상태
//    private String method; // 결제 수단
//    private int totalAmount;    // 총 결제 금액
//    private int balanceAmount;  // 취소 가능한 금액
    private int suppliedAmount; // 공급 가액
    private int vat;
    private String requestedAt; // 2024-06-18T15:13:15+09:00
    private String approvedAt;

    public PaymentLedger toPaymentTransactionEntity() {
        return PaymentLedger.builder()
            .paymentKey(this.getPaymentKey())
            .method(PaymentMethod.fromMethodName(this.getMethod()))
            .paymentStatus(this.getStatus())
            .totalAmount(this.getTotalAmount())
            .balanceAmount(this.getBalanceAmount())
            .canceledAmount(0)
            .build();
    }
}
