package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;

@Service
@RequiredArgsConstructor
@Slf4j
public class CancelService implements PaymentCancelUseCase {
    private final OrderService orderService;
    private final PaymentService paymentService;

    @Override
    public Order paymentCancel(CancelOrder cancelOrder) throws Exception {
        String paymentKey = cancelOrder.getPaymentKey();
        int cancellationAmount = cancelOrder.getCancellationAmount();
        Order wantedCancelOrder = orderService.getOrderInfo(cancelOrder.getOrderId());
        String paymentMethod = paymentService.getPaymentMethod(paymentKey);
        PaymentMethod paymentInfo= paymentService.getPaymentMethodInfo(paymentMethod, paymentKey);
        if(wantedCancelOrder.isPossibleToCancel() && paymentInfo.isCancellableAmountGreaterThan(cancellationAmount)){
            // TODO: API CALL
        }
        throw new Exception("Not Enough CancellationAmount");
    }
}
