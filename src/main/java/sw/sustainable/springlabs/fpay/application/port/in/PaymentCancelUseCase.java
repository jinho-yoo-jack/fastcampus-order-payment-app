package sw.sustainable.springlabs.fpay.application.port.in;

import java.util.UUID;

public interface PaymentCancelUseCase {
    void paymentCancel(UUID paymentId);
}
