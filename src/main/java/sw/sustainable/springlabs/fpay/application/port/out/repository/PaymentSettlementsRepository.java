package sw.sustainable.springlabs.fpay.application.port.out.repository;

import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.util.List;
import java.util.Optional;

public interface PaymentSettlementsRepository {
    PaymentSettlements findById(String paymentKey);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
