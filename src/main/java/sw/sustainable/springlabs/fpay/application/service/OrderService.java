package sw.sustainable.springlabs.fpay.application.service;

import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.presentation.in.web.request.PurchaseOrder;

@Service
public class OrderService implements CreateNewOrderUseCase {
    private OrderRepository orderRepository;

    @Override
    public Order create(PurchaseOrder newOrder) {
        return null;
    }
}
