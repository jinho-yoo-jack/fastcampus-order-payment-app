package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.application.port.out.repository.TransactionTypeRepository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class CardTransactionTypeRepository implements TransactionTypeRepository {
    private final JpaCardPaymentRepository jpaCardPaymentRepository;

    @Override
    public CardPayment findById(String paymentKey) {
        return jpaCardPaymentRepository.findById(paymentKey)
            .orElseThrow(() -> new NoSuchElementException(String.format("CardPayment with key '%s' not found", paymentKey)));
    }

    @Override
    public void save(TransactionType paymentDetailInfo) {
        jpaCardPaymentRepository.save((CardPayment) paymentDetailInfo);
    }
}
