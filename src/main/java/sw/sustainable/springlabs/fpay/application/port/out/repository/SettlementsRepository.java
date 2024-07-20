package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.util.List;

public interface SettlementsRepository {
    void bulkInsert(List<PaymentSettlements> paymentSettlements);

}
