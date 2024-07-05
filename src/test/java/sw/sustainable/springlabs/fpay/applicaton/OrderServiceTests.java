package sw.sustainable.springlabs.fpay.applicaton;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import sw.sustainable.springlabs.fpay.application.service.OrderService;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@Slf4j
public class OrderServiceTests {

    @MockBean
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    public void beforeAll() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void createOrder_NewOrder_ANormalOrderForm() {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        when(orderRepository.save(order)).thenReturn(order);
        Order expectedOrder = orderService.createOrder(newOrder);
        Assertions.assertEquals(expectedOrder, order);

    }

}
