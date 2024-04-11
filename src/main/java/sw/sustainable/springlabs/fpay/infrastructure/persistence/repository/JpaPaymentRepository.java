package sw.sustainable.springlabs.fpay.infrastructure.persistence.repository;


import sw.sustainable.springlabs.fpay.domain.DomainPaymentRepository;
import sw.sustainable.springlabs.fpay.domain.Payment;

public class JpaPaymentRepository implements DomainPaymentRepository {
    private PaymentRepository paymentRepository;

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public void save(Payment payment) {

    }

    @Override
    public void delete(Payment payment) {

    }
}
