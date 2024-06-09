package sw.sustainable.springlabs.fpay.infrastructure.out.pg.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.payment.method.Card;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePaymentApproved {
    private String orderId;
    private String orderName;
    private Card card;
    private String paymentKey;
    private String lastTransactionKey;
    private String status; // 결제 상태
    private String method; // 결제 수단
    private int totalAmount;    // 총 결제 금액
    private int balanceAmount;  // 취소 가능한 금액
    private int suppliedAmount; // 공급 가액
    private int vat;
    private String requestedAt;
    private String approvedAt;
}