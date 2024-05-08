package sw.sustainable.springlabs.fpay.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import sw.sustainable.springlabs.fpay.infrastructure.persistence.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class OrderTests {
    @Autowired
    private OrderRepository orderRepository;

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
                .state(OrderStatus.ORDER_COMPLETED.name())
                .build();

            Order order = new Order(newOrderId,"유진호", "010-1234-1234", List.of(orderItem));
            orderRepository.save(order);
            Iterable<Order> o = orderRepository.findAll();
            log.info("RESULT Order -> {}", o);
//            log.info("RESULT Item -> {}", o.getOrderedItems());

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
