package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentMethodRepository;

@Repository
@RequiredArgsConstructor
public class PaymentRepository implements PaymentMethodRepository {
    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    public Payment findById(String paymentKey) {
        return jpaPaymentRepository.findById(paymentKey)
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public Payment save(Payment paymentInfo) {
        return jpaPaymentRepository.save(paymentInfo);
    }
}