package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sw.sustainable.springlabs.fpay.application.service.CancelService;
import sw.sustainable.springlabs.fpay.application.service.OrderService;
import sw.sustainable.springlabs.fpay.application.service.PaymentService;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.Cancel;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CancelServiceTests {
    @Mock
    private PaymentAPIs paymentAPIs;

    @Mock
    private OrderService orderService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private PaymentLedgerRepository paymentLedgerRepository;

    @InjectMocks
    private CancelService cancelService;

    @Test
    public void paymentCancel_success_Normal() throws Exception {

        UUID orderId = UUID.randomUUID();
        String paymentKey = "tgen_20240605132741Jtkz1";
        CancelOrder cancelMessage = new CancelOrder(orderId, new int[]{1}, "Reason",
            paymentKey, 3400);
        int cancellationAmount = 3400;

        ResponsePaymentCancel response = ResponsePaymentCancel.builder()
            .orderId(orderId.toString())
            .paymentKey(paymentKey)
            .method("카드")
            .status("DONE")
            .totalAmount(3400)
            .balanceAmount(0)
            .cancels(List.of(new Cancel("090A796806E726BBB929F4A2CA7DB9A7", "Reason", 3400, "DONE")))
            .build();

//        ResponsePaymentCancel response = new ResponsePaymentCancel(
//            paymentKey, "카드", "DONE", orderId, 3400, 0,
//            List.of(new Cancel("090A796806E726BBB929F4A2CA7DB9A7", "Reason", 3400, "DONE"))
//        );

        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, orderId, "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        when(orderService.getOrderInfo(orderId))
            .thenReturn(order);

        PaymentLedger paymentInfo = PaymentLedger.builder()
            .id(1)
            .paymentKey("")
            .method(PaymentMethod.CARD)
            .paymentStatus(PaymentStatus.DONE)
            .totalAmount(3400)
            .balanceAmount(3400)
            .canceledAmount(0)
            .build();

        when(paymentService.getLatestPaymentInfoOnlyOne(any()))
            .thenReturn(paymentInfo);

        when(paymentAPIs.requestPaymentCancel(any(), any()))
            .thenReturn(response);

        boolean result = cancelService.paymentCancel(cancelMessage);

        verify(orderService, Mockito.times(1)).getOrderInfo(orderId);
        verify(paymentService, Mockito.times(1)).getLatestPaymentInfoOnlyOne((any()));
        verify(paymentAPIs, Mockito.times(1)).requestPaymentCancel(any(), any());

        Assertions.assertTrue(order.isNotOrderStatusPurchaseDecision());
        Assertions.assertTrue(paymentInfo.isCancellableAmountGreaterThan(cancellationAmount));
        Assertions.assertTrue(cancelMessage.hasItemIdx());
        Assertions.assertTrue(result);


    }

}
