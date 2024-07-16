package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.TransactionTypeRepository;
import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CardTransactionTypeRepository implements TransactionTypeRepository {
    private final JpaCardPaymentRepository jpaCardPaymentRepository;

    @Override
    public void save(TransactionType transactionType) {
        jpaCardPaymentRepository.save((CardPayment) transactionType);
    }
}
