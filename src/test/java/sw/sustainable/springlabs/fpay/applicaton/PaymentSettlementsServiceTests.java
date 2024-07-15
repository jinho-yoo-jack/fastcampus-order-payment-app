package sw.sustainable.springlabs.fpay.applicaton;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentSettlementsRepository;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest
public class PaymentSettlementsServiceTests {
    @Autowired
    private PaymentSettlementsRepository paymentSettlementsRepository;

    @Autowired
    private PaymentLedgerRepository paymentLedgerRepository;

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
                .method(PaymentMethod.fromMethodName("카드"))
                .totalAmount(-99800)
                .canceledAmount(99800) // TODO: 취소 금액 합계 구하는 로직 구현 필요
                .payOutAmount(-97550)
                .soldDate(Date.valueOf("2023-11-25"))
                .paidOutDate(Date.valueOf("2023-11-30"))
                .build());

        settlementsHistories.add(PaymentSettlements.builder()
                .paymentKey("oYwn6qbDZOAQ1239472398vdk4El1Bp0J5")
                .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
                .method(PaymentMethod.fromMethodName("카드"))
                .totalAmount(41500)
                .canceledAmount(0) // TODO: 취소 금액 합계 구하는 로직 구현 필요
                .payOutAmount(40565)
                .soldDate(Date.valueOf("2023-11-25"))
                .paidOutDate(Date.valueOf("2023-11-30"))
                .build());


        paymentSettlementsRepository.bulkInsert(settlementsHistories);
    }
}
