package sw.sustainable.springlabs.fpay.application.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.presentation.request.Orderer;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrder;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

@Slf4j
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTests implements CreateNewOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void createNewOrder() {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order orderCompleted = create(newOrder);
//        log.info("Complete Order -> {}, {}", orderCompleted.getOrderId(), orderCompleted.getOrderedItems());
    }

    @Override
    public Order create(PurchaseOrder newOrder) {
        Order receivedOrder = newOrder.toEntity();
        orderRepository.save(receivedOrder);
        return receivedOrder;
    }
}
