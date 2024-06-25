package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentRepository;

@Repository
@RequiredArgsConstructor
public class CardPaymentRepository implements PaymentRepository<CardPayment> {

    private final JpaCardPaymentRepository cardPaymentRepository;

    @Override
    public CardPayment findById(String paymentKey) {
        return cardPaymentRepository.findById(paymentKey)
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public CardPayment save(CardPayment paymentInfo) {
        return cardPaymentRepository.save(paymentInfo);
    }
}