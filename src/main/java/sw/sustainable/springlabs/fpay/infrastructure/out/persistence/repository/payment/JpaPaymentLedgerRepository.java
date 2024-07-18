package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.Optional;

public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, Long> {
    Optional<PaymentLedger> findTopByPaymentKeyOrderByIdDesc(String paymentKey);
}
