package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order.JpaOrderRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements sw.sustainable.springlabs.fpay.domain.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order findById(UUID id) {
        return jpaOrderRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Order save(Order newOrder) {
        jpaOrderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public boolean removeAll(UUID id) {
        return jpaOrderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        return null;
//        return order.update(orderStatus);
    }
}
