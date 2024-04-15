package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.DomainPaymentRepository;
import sw.sustainable.springlabs.fpay.domain.Payment;
import sw.sustainable.springlabs.fpay.domain.model.PaymentInfo;

@Component
public class JpaPaymentRepository implements DomainPaymentRepository {
    private PaymentRepository paymentRepository;

    @Override
    public PaymentInfo findByOrderId(Long id) {
        return null;
    }

    @Override
    public Payment of(PaymentInfo paymentInfo) {
        return null;
    }

    @Override
    public void save(Payment payment) {

    }

    @Override
    public void delete(Payment payment) {

    }
}
