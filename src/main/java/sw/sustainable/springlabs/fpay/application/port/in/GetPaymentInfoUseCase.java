package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;

import java.util.UUID;

public interface GetPaymentInfoUseCase {
    PaymentMethod getPaymentMethodInfo(String method, String paymentKey);
}
