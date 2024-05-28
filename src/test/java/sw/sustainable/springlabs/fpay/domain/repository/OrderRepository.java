package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;

import java.util.UUID;

public interface OrderRepository {
    Order findById(UUID id);
    Order save(Order newOrder);
    boolean removeAll(UUID id);
    Order updateOrderStatus(Order order, OrderStatus orderStatus);
}
