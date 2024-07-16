package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order.JpaOrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements CreateNewOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Order createNewOrder(PurchaseOrder newOrder) throws Exception {
        Order order = newOrder.toEntity();
        order.calculateTotalAmount();
        order.verifyHaveAtLeastOneItem();
        order.verifyDuplicateOrderItemId();
        return orderRepository.save(order);
    }
}
