package sw.sustainable.springlabs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sw.sustainable.springlabs.fpay.domain.OrderStatus;

@Slf4j
public class EnumTests {
    @Test
    public void enumTest1(){
        System.out.println(OrderStatus.ORDER_COMPLETED.name());

        log.info("RESULT -> {}","ORDER_COMPLETED".equals(OrderStatus.ORDER_COMPLETED.name()));
        OrderStatus orderStatus = OrderStatus.ORDER_COMPLETED;
        orderStatus.valueOf("ORDER_COMPLETED");
    }
}
