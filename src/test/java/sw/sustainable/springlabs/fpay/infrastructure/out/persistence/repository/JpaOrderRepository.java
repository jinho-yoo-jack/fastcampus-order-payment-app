package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.order.Order;

import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<Order, UUID> {
}