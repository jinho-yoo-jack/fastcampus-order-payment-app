package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentSettlementsUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.repository.SettlementsRepository;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettlementsService implements PaymentSettlementsUseCase {
    private final PaymentAPIs mockPayment;
    private final SettlementsRepository settlementsRepository;

    @Override
    public boolean getPaymentSettlements() throws Exception {
        List<ResponsePaymentSettlements> response =mockPayment.requestPaymentSettlement();
        settlementsRepository.bulkInsert(response.stream().map(ResponsePaymentSettlements::toEntity).toList());
        return true;
    }
}
