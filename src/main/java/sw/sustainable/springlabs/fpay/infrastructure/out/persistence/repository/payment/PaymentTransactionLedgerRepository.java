package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentTransactionLedgerRepository implements PaymentLedgerRepository {
    private final JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Override
    public List<PaymentLedger> findAllByPaymentKey(String paymentKey) {
        return jpaPaymentLedgerRepository.findByPaymentKey(paymentKey)
            .orElseThrow(() -> new NullPointerException("findAllByPaymentKey ::: Not found Payment Transactions"));
    }

    @Override
    public PaymentLedger findOneByPaymentKeyDesc(String paymentKey) {
        return jpaPaymentLedgerRepository.findTopByPaymentKeyOrderByIdDesc(paymentKey)
            .orElseThrow(() -> new NullPointerException("findOneByPaymentKeyDesc ::: Not found Payment Transaction"));
    }

    @Override
    public void save(PaymentLedger paymentLedgerInfo) {
        jpaPaymentLedgerRepository.save(paymentLedgerInfo);
    }
}