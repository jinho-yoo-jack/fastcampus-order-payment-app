package sw.sustainable.springlabs.fpay.application.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements PaymentCancelUseCase {
    private final PaymentAPIs paymentAPIs;
    private final OrderRepository orderRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;

    @Transactional
    @Override
    public boolean paymentCancel(CancelOrder cancelRequestMessage) throws IOException {
        String paymentKey = cancelRequestMessage.getPaymentKey();
        int cancellationAmount = cancelRequestMessage.getCancellationAmount();
        Order wantedCancelOrder = orderRepository.findById(cancelRequestMessage.getOrderId());
        PaymentLedger paymentLatestHistory = paymentLedgerRepository.findOneByPaymentKeyDesc(paymentKey);
        if (wantedCancelOrder.isNotOrderStatusPurchaseDecision()
            && paymentLatestHistory.isCancellableAmountGreaterThan(cancellationAmount)) {

            ResponsePaymentCancel response = paymentAPIs.requestPaymentCancel(paymentKey, new PaymentCancel(cancelRequestMessage.getCancelReason(), cancellationAmount));
            paymentLedgerRepository.save(response.toEntity());

            if (cancelRequestMessage.hasItemIdx())
                wantedCancelOrder.orderCancel(cancelRequestMessage.getItemIdxs());
            else wantedCancelOrder.orderAllCancel();

            return true;
        }
        throw new IOException("OrderStatus is Purchase Decision or Not Enough cancellation amount");
    }
}
