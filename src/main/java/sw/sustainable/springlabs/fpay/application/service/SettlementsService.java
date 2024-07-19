package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentSettlementsUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentSettlementsRepository;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettlementsService implements PaymentSettlementsUseCase {
    private final PaymentAPIs mockTossPayment;
    private final PaymentSettlementsRepository paymentSettlementsRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;

    @SneakyThrows
    @Override
    public void getPaymentSettlements() throws IOException {
        List<ResponsePaymentSettlements> response = mockTossPayment.requestPaymentSettlement(createPaymentSettlement());
        List<PaymentSettlements> settlementsHistories = response.stream()
                .map(ResponsePaymentSettlements::toEntity)
                .toList();
        paymentSettlementsRepository.bulkInsert(settlementsHistories);
        paymentLedgerRepository.bulkInsert(
                settlementsHistories.stream().map(PaymentSettlements::toPaymentLedger)
                        .toList()
        );
    }

    private PaymentSettlement createPaymentSettlement() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(3).format(formatter);
        String endDate = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(1).format(formatter);
        return PaymentSettlement.builder()
                .startDate(startDate)
                .endDate(endDate)
                .page(1)
                .size(5000)
                .build();
    }
}
