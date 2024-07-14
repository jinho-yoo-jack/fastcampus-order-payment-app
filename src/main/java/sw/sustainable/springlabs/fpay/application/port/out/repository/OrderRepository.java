package sw.sustainable.springlabs.fpay.application.port.out.repository;

import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;

import java.util.UUID;

public interface OrderRepository {
    Order findById(UUID id);
    Order save(Order newOrder);
    boolean removeAll(UUID id);
}
