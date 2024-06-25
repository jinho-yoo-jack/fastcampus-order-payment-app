package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.Optional;

@Repository
public interface JpaCardPaymentRepository extends JpaBaseRepository<CardPayment, String> {
}