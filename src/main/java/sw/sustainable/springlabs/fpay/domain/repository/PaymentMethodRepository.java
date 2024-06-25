package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.payment.Payment;

public interface PaymentMethodRepository {
    Payment findById(String paymentKey);
    Payment save(Payment paymentInfo);
}
