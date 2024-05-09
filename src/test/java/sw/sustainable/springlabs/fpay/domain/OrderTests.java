package sw.sustainable.springlabs.fpay.domain;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.infrastructure.persistence.repository.OrderRepository;

import java.util.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class OrderTests {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void newOrderTest() {
        try {
            UUID newOrderId = Order.generateOrderId();
            OrderItem orderItem = OrderItem.builder()
                    .productId(UUID.randomUUID())
                    .productName("속이 편한 우유 300ml")
                    .price(3230)
                    .amount(10)
                    .size("FREE")
                    .state(OrderStatus.ORDER_COMPLETED)
                    .build();

            Order order = new Order(newOrderId, "유진호", "010-1234-1234", List.of(orderItem));
            orderRepository.save(order);
            Order newOrder = orderRepository.findById(newOrderId).get();
            OrderItem item = newOrder.getItems().get(0);
            log.info("Result -> {}", item);
            UUID actualOrderId = newOrder.getOrderId();
            log.info("actualOrderId -> {}", actualOrderId);
            log.info("newOrderId -> {}", newOrderId);
            Assertions.assertEquals(newOrderId, actualOrderId);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void orderCancelTest(){
        UUID canceledOrderId = UUID.fromString("0b5abb17-caeb-4983-8e6d-8c4dad490bd2");
        Optional<Order> order = orderRepository.findById(canceledOrderId);
        order.ifPresent(value -> orderRepository.delete(value));
    }
}
