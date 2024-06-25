package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;

import java.util.UUID;

public interface PaymentRepository {
    Payment findById(String paymentKey);
    Payment save(Payment paymentInfo);
    Payment update(Payment paymentInfo);
}
