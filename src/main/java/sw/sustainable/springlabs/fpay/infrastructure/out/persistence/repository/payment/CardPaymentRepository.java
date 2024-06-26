package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentRepository;

@Repository
@RequiredArgsConstructor
public class CardPaymentRepository implements PaymentRepository {

    private final JpaCardPaymentRepository cardPaymentRepository;

    @Override
    public PaymentMethod findById(String paymentKey) {
        return cardPaymentRepository.findById(paymentKey)
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentDetailInfo) {
        return cardPaymentRepository.save((CardPayment) paymentDetailInfo);
    }

}