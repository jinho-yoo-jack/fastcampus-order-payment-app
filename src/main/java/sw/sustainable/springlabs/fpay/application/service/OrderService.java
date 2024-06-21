package sw.sustainable.springlabs.fpay.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.PurchaseOrder;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateNewOrderUseCase {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order create(PurchaseOrder newOrder) {
        Order receivedOrder = newOrder.toEntity();
        orderRepository.save(receivedOrder);
        return receivedOrder;
    }
}
