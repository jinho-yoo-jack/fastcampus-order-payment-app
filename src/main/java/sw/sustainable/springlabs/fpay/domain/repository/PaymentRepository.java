package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;

public interface PaymentRepository {

    PaymentMethod findById(String paymentKey);

    PaymentMethod save(PaymentMethod paymentDetailInfo);
}
