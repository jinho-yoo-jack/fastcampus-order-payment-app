package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.order.Order;

public interface PaymentFullfillUseCase {
    void paymentFullfill(Order order);
}
