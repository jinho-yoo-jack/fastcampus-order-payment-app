package sw.sustainable.springlabs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class EnumTests {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void enumTest1(){
        OrderStatus status = OrderStatus.ORDER_COMPLETED;
        log.info("Getter of Enum -> {}", status.getCode());
        log.info("Enum.valueOf() -> {}", OrderStatus.valueOf(status.name()));
    }

    @Test
    public void newOrderTest(){
        try {
            UUID newOrderId = Order.generateOrderId();
            OrderItem orderItem = OrderItem.builder()
                    .productId(UUID.randomUUID())
                    .productName("속이 편한 우유")
                    .price(3230)
                    .amount(10)
                    .size("FREE")
                    .state(OrderStatus.ORDER_COMPLETED)
                    .build();

            Order order = new Order(newOrderId,"유진호", "010-1234-1234", List.of(orderItem));
            orderRepository.save(order);
//            Order newOrder = orderRepository.findById(newOrderId).get();
//            log.info("Result -> {}", newOrder.toString());
//            UUID actualOrderId =  newOrder.getOrderId();
//            Assertions.assertEquals(newOrderId, actualOrderId);

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
