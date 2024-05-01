package sw.sustainable.springlabs.fpay.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class PaymentTests {
    @Test
    public void enumTest(){
        OrderStatus status = OrderStatus.FULLFILL;
        log.info("OrderStatus - {}", status);
        log.info("OrderStatus name - {}", status.name());
        log.info("OrderStatus is equal FULLFILL of String - {}", "FULLFILL".equals(status.name()));
    }
}
