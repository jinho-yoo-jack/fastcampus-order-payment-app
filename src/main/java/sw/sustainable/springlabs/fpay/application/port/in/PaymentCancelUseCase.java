package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;

import java.util.UUID;

public interface PaymentCancelUseCase {
    Order paymentCancel(CancelOrder cancelOrder) throws Exception;
}
