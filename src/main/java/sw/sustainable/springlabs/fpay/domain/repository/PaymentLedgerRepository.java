package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;

import java.util.List;

public interface PaymentLedgerRepository {
    List<PaymentLedger> findAllByPaymentKey(String paymentKey);
    PaymentLedger findOneByPaymentKeyDesc(String paymentKey);
    PaymentLedger save(PaymentLedger paymentLedgerInfo);
}
