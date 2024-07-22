package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.TransactionTypeRepository;
import sw.sustainable.springlabs.fpay.application.service.PaymentService;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.*;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.CardTransactionTypeRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.*;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import sw.sustainable.springlabs.fpay.representation.request.order.*;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Mock
    private CardTransactionTypeRepository cardTransactionTypeRepository;

    private PaymentService paymentService;

    private Order order;

    private UUID orderId;

    @BeforeEach
    public void init() throws Exception {
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
    public void paymentApproved_success_isSuccessfulAndDone() throws Exception {
        // Given
        Order completedOrder = order;
        PaymentApproved paymentInfo = new PaymentApproved("NORMAL",
                "tgen_20240605132741Jtkz1", orderId.toString(), "3400");

        ResponsePaymentApproved response = ResponsePaymentApproved.builder()
                .orderId(orderId.toString())
                .paymentKey("tgen_20240605132741Jtkz1")
                .method("카드")
                .orderName("속이 편한 우유")
                .card(Card.builder()
                        .issuerCode(null)
                        .acquirerCode("41")
                        .number("54287966****112")
                        .approveNo("00000000")
                        .acquireStatus("READY")
                        .build()
                )
                .method("카드")
                .status("DONE")
                .orderId(orderId.toString())
                .build();

        when(paymentAPIs.requestPaymentApprove(paymentInfo))
                .thenReturn(response);

        when(paymentAPIs.isPaymentApproved(any()))
                .thenReturn(true);

        when(orderRepository.findById(any()))
                .thenReturn(completedOrder);

        doNothing().when(paymentLedgerRepository).save(any());

        // When
        Set<TransactionTypeRepository> transactionTypeRepositorySet = Set.of(cardTransactionTypeRepository);
        paymentService = new PaymentService(paymentAPIs, orderRepository, paymentLedgerRepository, transactionTypeRepositorySet);
        paymentService.init();
        String result = paymentService.paymentApproved(paymentInfo);

        // Then
        Mockito.verify(paymentAPIs, Mockito.times(1)).requestPaymentApprove(any());
        Mockito.verify(orderRepository, Mockito.times(2)).findById(any());
        Mockito.verify(paymentLedgerRepository, Mockito.times(1)).save(any());
        Mockito.verify(cardTransactionTypeRepository, Mockito.times(1)).save(any());
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
//    @Test
    public void verifyOrderIsCompleted_true_ORDER_COMPLETED() {
        when(orderRepository.findById(orderId))
                .thenReturn(order);

//        Assertions.assertDoesNotThrow(() -> paymentService.verifyOrderIsCompleted(orderId));
    }

    @DisplayName("주문 상태가 \"주문 완료\"가 아닌 경우")
//    @Test
    public void verifyOrderIsCompleted_false_ORDER_COMPLETED() {
        order.setStatus(OrderStatus.PAYMENT_FULLFILL);

        when(orderRepository.findById(orderId))
                .thenReturn(order);

//        assertThrows(IllegalArgumentException.class, () -> paymentService.verifyOrderIsCompleted(orderId));
    }
}
