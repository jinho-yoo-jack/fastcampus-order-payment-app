package sw.sustainable.springlabs.fpay.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;

@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements PaymentCancelUseCase {
    private final PaymentAPIs paymentAPIs;
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
        if (wantedCancelOrder.isPossibleToCancel() && paymentInfo.isCancellableAmountGreaterThan(cancellationAmount)) {
            ResponsePaymentCancel response = paymentAPIs.paymentCancel(paymentKey, new PaymentCancel(cancelOrder.getCancelReason(), cancellationAmount));
            paymentLedgerRepository.save(response.toEntity());

            if (cancelOrder.getItemIdxs().length == 0)
                wantedCancelOrder.orderCancel();
            else
                wantedCancelOrder.orderCancel(cancelOrder.getItemIdxs());
            return true;
        }

        throw new Exception("Not Enough CancellationAmount");
    }
}
