package sw.sustainable.springlabs.fpay.infrastructure.out.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.util.List;

@Component
@Slf4j
public class KafkaConsumer {
    private final static String SETTLEMENTS_TOPIC = "settlements";

    // record 를 수신하기 위한 consumer 설정
    @KafkaListener(topics = SETTLEMENTS_TOPIC)
    public void receive(ConsumerRecord<String, List<PaymentSettlements>> consumerRecord) {
        List<PaymentSettlements> payload = consumerRecord.value();
        log.info("received payload = {}", payload.toString());
    }
}
