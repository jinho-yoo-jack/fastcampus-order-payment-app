package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, Long> {
}
