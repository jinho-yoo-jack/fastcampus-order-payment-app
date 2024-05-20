package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.presentation.in.web.request.PurchaseOrder;

public interface CreateNewOrderUseCase {
    Order create(PurchaseOrder newOrder);
}
