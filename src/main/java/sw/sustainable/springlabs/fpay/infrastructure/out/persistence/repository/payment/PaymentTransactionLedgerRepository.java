package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentLedgerRepository;

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
        return jpaPaymentLedgerRepository.findOneByPaymentKeyOrderByIdDesc(paymentKey)
            .orElseThrow(() -> new NullPointerException("findOneByPaymentKeyDesc ::: Not found Payment Transaction"));
    }

    @Override
    public PaymentLedger save(PaymentLedger paymentLedgerInfo) {
        return jpaPaymentLedgerRepository.save(paymentLedgerInfo);
    }
}