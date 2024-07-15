package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;

import java.util.List;

public interface PaymentLedgerRepository {
    List<PaymentLedger> findAllByPaymentKey(String paymentKey);
    PaymentLedger findOneByPaymentKeyDesc(String paymentKey);
    void save(PaymentLedger paymentLedgerInfo);
    void bulkInsert(List<PaymentLedger> paymentLedgerHistories);
}
