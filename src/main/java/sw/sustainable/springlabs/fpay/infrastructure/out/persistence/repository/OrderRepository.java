package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements sw.sustainable.springlabs.fpay.domain.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    private final JpaOrderItemsRepository jpaOrderItemsRepository;

    @Override
    public Order findById(UUID id) {
        return jpaOrderRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Order save(Order newOrder) {
        return jpaOrderRepository.save(newOrder);
    }

    @Override
    public void saveOrderItems(List<OrderItem> orderItems) {
        orderItems.forEach(jpaOrderItemsRepository::save);
    }

    @Override
    public boolean removeAll(UUID id) {
        return jpaOrderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        return order.update(orderStatus);
    }
}
