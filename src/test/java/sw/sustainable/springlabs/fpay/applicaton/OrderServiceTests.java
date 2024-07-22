package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sw.sustainable.springlabs.fpay.application.service.OrderService;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

//    @Test
    public void createOrder_NewOrder_ANormalOrderForm() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        Mockito.when(orderRepository.save(any()))
            .thenReturn(order);

        Order completedOrder = orderService.createOrder(newOrder);

        Mockito.verify(orderRepository, Mockito.times(1)).save(any());
        Assertions.assertEquals(order, completedOrder);
    }

}
