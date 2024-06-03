package sw.sustainable.springlabs.fpay.infrastructure.out.pg.response;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.payment.method.Card;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ResponsePaymentApproved {
    private final String orderId;
    private final String orderName;
    private final Card card;
    private final String paymentKey;
    private final String lastTransactionKey;
    @NotNull
    private final String status; // 결제 상태
    private final String method; // 결제 수단
    private final int totalAmount;    // 총 결제 금액
    private final int balanceAmount;  // 취소 가능한 금액
    private final int suppliedAmount; // 공급 가액
    private final int vat;
    private final LocalDateTime requestedAt;
    private final LocalDateTime approvedAt;

}
