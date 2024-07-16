package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;

public interface TransactionTypeRepository {
    void save(TransactionType transactionType);
}
