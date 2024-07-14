package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, String> {
    Optional<List<PaymentLedger>> findByPaymentKey(String paymentKey);

    Optional<PaymentLedger> findTopByPaymentKeyOrderByIdDesc(String paymentKey);
}