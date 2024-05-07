package sw.sustainable.springlabs.fpay.infrastructure.persistence.repository;


import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.Order;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.JpaBaseRepository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaBaseRepository<Order, UUID> {
}