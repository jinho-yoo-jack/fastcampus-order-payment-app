package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

@Repository
public interface JpaPaymentRepository extends JpaBaseRepository<Payment, String> {
}