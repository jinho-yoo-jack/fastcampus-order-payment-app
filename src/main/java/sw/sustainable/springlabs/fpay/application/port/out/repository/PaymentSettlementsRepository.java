package sw.sustainable.springlabs.fpay.application.port.out.repository;

import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.util.List;

public interface PaymentSettlementsRepository {
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
