package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.application.service.PaymentService;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.domain.repository.TransactionTypeRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.CardTransactionTypeRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@Slf4j
public class PaymentServiceTests {
    @Mock
    private PaymentAPIs paymentAPIs;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentLedgerRepository paymentLedgerRepository;

    private TransactionTypeRepository transactionTypeRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Order order;

    private UUID orderId;

    @BeforeEach
    public void init() {
        orderId = UUID.randomUUID();
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, orderId, "농심 짜파게티 4봉", 4500, 1, 4500)));
        order = newOrder.toEntity();
    }

    /**
     * 결제 승인(Approve)에 대한 단위 테스트
     * [TEST CASE#1] PG사 API 통신이 정상(isSuccessful)이고, 상태가 DONE 일 때, return success;
     * [TEST CASE#2] PG사 API 통신이 정상(isSuccessful)이지만, 상태가 NOT DONE 일 때, return fail;
     * [TEST CASE#3] PG사 API 통신이 실패(isFailed) 일 때, return fail;
     * [Exception] IOException 발생 했을 때, return fail;
     */
    @Test
    public void paymentApproved_success_isSuccessfulAndDone() throws IOException {
        // Given
        Mockito.when(paymentAPIs.requestPaymentApprove(any()))
                .thenReturn(ResponsePaymentApproved.builder().build());

        Mockito.when(orderRepository.findById(any()))
                .thenReturn(order);

//        PaymentLedger paymentLedger = new PaymentLedger();
        Mockito.when(paymentLedgerRepository.save(any()))
                .thenReturn(any());

        TransactionTypeRepository transactionTypeRepository = Mockito.mock(CardTransactionTypeRepository.class);
        doNothing().when(transactionTypeRepository).save(any());

        // When
        PaymentApproved requestMessage = new PaymentApproved("", "",
                orderId.toString(), "3400");
        String result = paymentService.paymentApproved(requestMessage);

        // Then
        Mockito.verify(paymentAPIs, Mockito.times(1)).requestPaymentApprove(any());
        assertEquals("success", result);

    }

    /**
     * 결제 승인(Approve) 요청에 대한 주문정보 유효성 검사 단위 테스트
     * - 주문 상태가 "주문 완료"여야 한다.
     * [TEST CASE#1] 주문 상태가 "주문 완료"인 경우, return true;
     * [TEST CASE#2] 주문 상태가 "주문 완료"가 아닌 다른 경우, return false;
     * [Exception] 결제 요청한 주문번호가 없는 경우, throw NotfoundException;
     */
    @DisplayName("주문 상태가 \"주문 완료\"인 경우")
    @Test
    public void verifyOrderIsCompleted_true_ORDER_COMPLETED() {
        Mockito.when(orderRepository.findById(orderId))
                .thenReturn(order);

        Assertions.assertDoesNotThrow(() -> paymentService.verifyOrderIsCompleted(orderId));
    }

    @DisplayName("주문 상태가 \"주문 완료\"가 아닌 경우")
    @Test
    public void verifyOrderIsCompleted_false_ORDER_COMPLETED() {
        order.setStatus(OrderStatus.PAYMENT_FULLFILL);

        Mockito.when(orderRepository.findById(orderId))
                .thenReturn(order);

        assertThrows(IllegalArgumentException.class, () -> paymentService.verifyOrderIsCompleted(orderId));
    }
}
