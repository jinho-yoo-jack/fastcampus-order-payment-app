package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

public interface JpaCardPaymentRepository extends JpaBaseRepository<CardPayment, String> {
}
