package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;

import java.io.IOException;

public interface PaymentCancelUseCase {
    boolean paymentCancel(CancelOrder cancelRequestMessage) throws IOException;
}
