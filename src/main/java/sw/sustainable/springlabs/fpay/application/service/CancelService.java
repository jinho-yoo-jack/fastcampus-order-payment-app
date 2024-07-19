package sw.sustainable.springlabs.fpay.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;

@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements PaymentCancelUseCase {
    private final PaymentAPIs tossPayment;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PaymentLedgerRepository paymentLedgerRepository;

    @Transactional
    @Override
    public boolean paymentCancel(CancelOrder cancelOrder) throws Exception {
        String paymentKey = cancelOrder.getPaymentKey();
        int cancellationAmount = cancelOrder.getCancellationAmount();
        Order wantedCancelOrder = orderService.getOrderInfo(cancelOrder.getOrderId());
        PaymentLedger paymentInfo = paymentService.getLatestPaymentInfoOnlyOne(paymentKey);
        if (wantedCancelOrder.isNotOrderStatusPurchaseDecision() &&
            paymentInfo.isCancellableAmountGreaterThan(cancellationAmount)) {
            ResponsePaymentCancel response = tossPayment.requestPaymentCancel(paymentKey, new PaymentCancel(cancelOrder.getCancelReason(), cancellationAmount));
            paymentLedgerRepository.save(response.toEntity());

            if (cancelOrder.hasItemIdx())
                wantedCancelOrder.orderCancel(cancelOrder.getItemIdxs());
            else
                wantedCancelOrder.orderAllCancel();
            return true;
        }

        throw new Exception("Not Enough CancellationAmount");
    }
}
