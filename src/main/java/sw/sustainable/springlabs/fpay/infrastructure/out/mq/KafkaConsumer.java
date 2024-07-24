package sw.sustainable.springlabs.fpay.infrastructure.out.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.RPaymentSettlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.mq.record.Settlements;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.settlements.PaymentSettlementsRepository;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private static final String SETTLEMENTS_TOPIC = "settlements";
    private final PaymentSettlementsRepository paymentSettlementsRepository;

    @KafkaListener(topics = SETTLEMENTS_TOPIC)
    public void receiveSettlements(ConsumerRecord<String, RPaymentSettlements> consumerRecord) {
        log.info("Received payment settlements: {}", consumerRecord.value());
        RPaymentSettlements payload = consumerRecord.value();
        List<Settlements> records = payload.getSettlements();
        List<PaymentSettlements> rows = records.stream().map(record -> PaymentSettlements.builder()
            .paymentKey(record.getPaymentKey())
            .method(PaymentMethod.valueOf(record.getMethod()))
            .paymentStatus(PaymentStatus.valueOf("SETTLEMENTS_REQUESTED"))
            .totalAmount(record.getTotalAmount())
            .payOutAmount(record.getPayOutAmount())
            .canceledAmount(record.getCanceledAmount())
            .soldDate(Date.valueOf(record.getSoldDate()))
            .paidOutDate(Date.valueOf(record.getPaidOutDate()))
            .build()
        ).toList();

        paymentSettlementsRepository.bulkInsert(rows);
    }

}
