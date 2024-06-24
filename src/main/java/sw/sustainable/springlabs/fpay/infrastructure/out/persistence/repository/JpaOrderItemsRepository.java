package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;

import java.util.UUID;

@Repository
public interface JpaOrderItemsRepository extends JpaBaseRepository<OrderItem, UUID> {
}