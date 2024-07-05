package sw.sustainable.springlabs.fpay.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.GetOrderInfoUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentCancelUseCase;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements CreateNewOrderUseCase, GetOrderInfoUseCase {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(PurchaseOrder newOrder) {
        return orderRepository.save(newOrder.toEntity());
    }

    @Transactional
    @Override
    public Order getOrderInfo(UUID orderId) {
        return orderRepository.findById(orderId);
    }

}
