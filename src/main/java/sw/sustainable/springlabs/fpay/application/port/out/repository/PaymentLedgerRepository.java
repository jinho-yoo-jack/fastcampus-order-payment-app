package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;

public interface PaymentLedgerRepository {
    void save(PaymentLedger paymentLedger);
}
