package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.settlements;

import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.Optional;

public interface JpaPaymentSettlementsRepository extends JpaBaseRepository<PaymentSettlements, Integer> {
    Optional<PaymentSettlements> findByPaymentKey(String paymentKey);
}
