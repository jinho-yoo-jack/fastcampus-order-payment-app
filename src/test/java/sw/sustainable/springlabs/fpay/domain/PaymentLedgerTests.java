package sw.sustainable.springlabs.fpay.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;

/**
 * payment_transaction 객체
 * define: 결제 도메인의 Aggregate; 거래 원장
 * description: 결제 업무의 모든 업무 규칙 기능(비즈니스 로직) 제공하는 클래스
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class PaymentLedgerTests {

    private PaymentLedger paymentLedger;

    @BeforeEach
    public void setUp() {
        // Table : payment_transaction : 1,tgen_20240710141124a1Rn3,카드,DONE,3400,3400,0,2024-07-10 05:11:50,2024-07-10 05:11:50
        this.paymentLedger = PaymentLedger.builder()
            .id(1)
            .paymentKey("")
            .method(PaymentMethod.CARD)
            .paymentStatus(PaymentStatus.DONE)
            .totalAmount(3400)
            .balanceAmount(3400)
            .canceledAmount(0)
            .build();
    }

    @DisplayName("취소 요청 금액 < 취소 가능한 금액(잔고) return true;")
    @Test
    public void isCancellableAmountGreaterThan_true_BalanceAmountBiggerThanCancellationAmount() {
        Assertions.assertTrue(this.paymentLedger.isCancellableAmountGreaterThan(400));
    }

    @DisplayName("취소 요청 금액 > 취소 가능한 금액(잔고) return false;")
    @Test public void isCancellableAmountGreaterThan_false_CancellationAmountBiggerThanBalanceAmount() {
        Assertions.assertFalse(this.paymentLedger.isCancellableAmountGreaterThan(3500));
    }

    @DisplayName("취소 요청 금액 == 취소 가능한 금액(잔고) return true;")
    @Test public void isCancellableAmountGreaterThan_true_CancellationAmountEqualsBalanceAmount() {
        Assertions.assertTrue(this.paymentLedger.isCancellableAmountGreaterThan(3400));
    }


}
