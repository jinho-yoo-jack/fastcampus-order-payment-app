package sw.sustainable.springlabs.fpay.representation.request.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentApproved {
    private final String paymentKey;
    private final String orderId;
    private final int amount;
}
