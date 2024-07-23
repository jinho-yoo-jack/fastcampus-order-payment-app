package sw.sustainable.springlabs.fpay.applicaton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.mq.Producer;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RPaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RecordSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.settlements.PaymentSettlementsRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class PaymentSettlementsServiceTests {
    private final static String SETTLEMENTS_TOPIC = "settlements";

    @Autowired
    private PaymentLedgerRepository paymentLedgerRepository;

    @Autowired
    private PaymentSettlementsRepository paymentSettlementsRepository;

    @Autowired
    private PaymentAPIs mockTossPayment;

    @Autowired
    private Producer<List<PaymentSettlements>> producer;


    @Test
    public void getPaymentSettlements() {
        /*1,tgen_20240715164615N7UM8,카드,DONE,13400,13400,0,0,2024-07-15 07:46:46,2024-07-15 07:46:46
         */
        List<PaymentSettlements> settlementsHistories = new ArrayList<>();
        settlementsHistories.add(PaymentSettlements.builder()
                .paymentKey("tgen_20240715164615N7UM8")
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_COMPLETED"))
                .method(PaymentMethod.CARD)
                .totalAmount(13400)
                .canceledAmount(0) // TODO: 취소 금액 합계 구하는 로직 구현 필요
                .payOutAmount(13400 - 1340)
                .soldDate(Date.valueOf("2024-07-16"))
                .paidOutDate(Date.valueOf("2024-07-18"))
                .build());

        List<PaymentLedger> paymentLedgerList = settlementsHistories.stream().map(PaymentSettlements::toPaymentLedger)
                .toList();
        paymentSettlementsRepository.bulkInsert(settlementsHistories);
        paymentLedgerRepository.bulkInsert(paymentLedgerList);
    }

    @Test
    public void bulkInsert_void_Normal() {
        List<PaymentSettlements> settlementsHistories = new ArrayList<>();
        settlementsHistories.add(PaymentSettlements.builder()
                .paymentKey("xLpgeoO7410238740297423RBKEzMjPJyG")
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
                .method(PaymentMethod.CARD)
                .totalAmount(-99800)
                .canceledAmount(99800) // TODO: 취소 금액 합계 구하는 로직 구현 필요
                .payOutAmount(-97550)
                .soldDate(Date.valueOf("2023-11-25"))
                .paidOutDate(Date.valueOf("2023-11-30"))
                .build());

        settlementsHistories.add(PaymentSettlements.builder()
                .paymentKey("oYwn6qbDZOAQ1239472398vdk4El1Bp0J5")
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
                .method(PaymentMethod.CARD)
                .totalAmount(41500)
                .canceledAmount(0) // TODO: 취소 금액 합계 구하는 로직 구현 필요
                .payOutAmount(40565)
                .soldDate(Date.valueOf("2023-11-25"))
                .paidOutDate(Date.valueOf("2023-11-30"))
                .build());


        paymentSettlementsRepository.bulkInsert(settlementsHistories);
//        PaymentSettlements result = paymentSettlementsRepository.findById("xLpgeoO7410238740297423RBKEzMjPJyG");
//        Assertions.assertEquals("xLpgeoO7410238740297423RBKEzMjPJyG", result.getPaymentKey());
    }

//    @Test
    public void send() throws Exception {
        List<ResponsePaymentSettlements> response = mockTossPayment.requestPaymentSettlement(createPaymentSettlement());
        List<PaymentSettlements> records = response.stream()
                .map(ResponsePaymentSettlements::toEntity)
                .toList();
        boolean result = producer.send(SETTLEMENTS_TOPIC, records);
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
