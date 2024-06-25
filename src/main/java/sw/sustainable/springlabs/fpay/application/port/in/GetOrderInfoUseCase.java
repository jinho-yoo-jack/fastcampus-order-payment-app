package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.order.Order;

import java.util.UUID;

public interface GetOrderInfoUseCase {
    Order getOrderInfo(UUID orderId);
}
