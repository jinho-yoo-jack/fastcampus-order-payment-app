package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment;

import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.TransactionTypeRepository;
import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;

@Repository
public class AccountTransactionTypeRepository implements TransactionTypeRepository {
    @Override
    public void save(TransactionType transactionType) {

    }
}
