package sw.sustainable.springlabs.fpay.domain;

import sw.sustainable.springlabs.fpay.domain.model.PaymentInfo;

public interface DomainPaymentRepository {
    PaymentInfo findByOrderId(Long id);
    Payment of(PaymentInfo paymentInfo);
    void save(Payment payment);
    void delete(Payment payment);
}
