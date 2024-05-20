package sw.sustainable.springlabs.fpay.infrastructure.persistence.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.model.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
}