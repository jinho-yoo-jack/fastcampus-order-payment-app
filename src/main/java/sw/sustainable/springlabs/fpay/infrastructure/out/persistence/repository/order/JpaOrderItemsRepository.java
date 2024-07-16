package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order;


import sw.sustainable.springlabs.fpay.domain.order.OrderItem;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.UUID;

public interface JpaOrderItemsRepository extends JpaBaseRepository<OrderItem, UUID>{
}
