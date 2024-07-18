package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;

@Repository
@RequiredArgsConstructor
@Service
public class PaymentLedgerRepository implements sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository {
    private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Override
    public PaymentLedger findOneByPaymentKeyDesc(String paymentKey) {
        return jpaPaymentLedgerRepository.findTopByPaymentKeyOrderByIdDesc(paymentKey)
            .orElseThrow(() -> new NullPointerException("paymentKey " + paymentKey + " not found"));
    }

    @Override
    public void save(PaymentLedger paymentLedger) {
        jpaPaymentLedgerRepository.save(paymentLedger);
    }
}
