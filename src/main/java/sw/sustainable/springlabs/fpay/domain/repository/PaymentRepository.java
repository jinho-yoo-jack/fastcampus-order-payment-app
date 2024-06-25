package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;

import java.util.UUID;

public interface PaymentRepository<T> {
    T findById(String paymentKey);
    T save(T paymentInfo);
}
