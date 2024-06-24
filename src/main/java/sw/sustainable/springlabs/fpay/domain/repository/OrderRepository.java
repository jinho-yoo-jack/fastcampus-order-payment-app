package sw.sustainable.springlabs.fpay.domain.repository;

import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;
import sw.sustainable.springlabs.fpay.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order findById(UUID id);
    Order save(Order newOrder);
    void saveOrderItems(List<OrderItem> newOrderItems);
    boolean removeAll(UUID id);
    Order updateOrderStatus(Order order, OrderStatus orderStatus);
}
