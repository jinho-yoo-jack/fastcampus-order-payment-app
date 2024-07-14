package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.order.Order;

import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order findById(UUID id) {
        return jpaOrderRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("OrderId not found"));
    }

    @Override
    public Order save(Order newOrder) {
        return jpaOrderRepository.save(newOrder);
    }

    @Override
    public boolean removeAll(UUID id) {
        return jpaOrderRepository.deleteById(id);
    }

}
