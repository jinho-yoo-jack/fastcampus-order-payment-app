package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.UUID;

@Repository
public interface JpaOrderItemsRepository extends JpaBaseRepository<OrderItem, UUID> {
}
