package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.Payment;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaBaseRepository<Payment, String> {
    Optional<Payment> findByPaymentInfoPaymentMethod(String paymentInfoPaymentMethod, Sort s);
}