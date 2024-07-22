package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentSettlementsUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.SendSettlementsInfoUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.mq.Producer;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentSettlementsRepository;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RPaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.Settlements;
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
public class SettlementsService implements PaymentSettlementsUseCase, SendSettlementsInfoUseCase {
    private final static String SETTLEMENTS_TOPIC = "settlements";
    private final PaymentAPIs mockTossPayment;
    private final PaymentSettlementsRepository paymentSettlementsRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;
    private final Producer<RPaymentSettlements> producer;
//    private final Producer<List<PaymentSettlements>> producer;

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

    @SneakyThrows
    @Override
    public boolean send() {
        List<ResponsePaymentSettlements> response = mockTossPayment.requestPaymentSettlement(createPaymentSettlement());
        List<PaymentSettlements> datum = response.stream()
            .map(ResponsePaymentSettlements::toEntity)
            .toList();
        RPaymentSettlements record = RPaymentSettlements.newBuilder()
            .setSettlements(datum.stream().map(data -> Settlements.newBuilder()
                .setId(data.getId())
                .setPaymentKey(data.getPaymentKey())
                .setTotalAmount(data.getTotalAmount())
                .setPayOutAmount(data.getPayOutAmount())
                .setCanceledAmount(data.getCanceledAmount())
                .setMethod(data.getMethod().toString())
                .setSoldDate(data.getSoldDate().toString())
                .setPaidOutDate(data.getPaidOutDate().toString())
                .build()
            ).toList())
            .build();
        producer.send(SETTLEMENTS_TOPIC, record);
        return true;
    }
}
