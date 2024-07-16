package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order;


import org.springframework.data.jpa.repository.JpaRepository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.UUID;

public interface JpaOrderRepository extends JpaBaseRepository<Order, UUID>{
}
