package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;

public interface TransactionTypeRepository {
    TransactionType findById(String paymentKey);
    void save(TransactionType paymentDetailInfo);
}
