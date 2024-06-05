package sw.sustainable.springlabs.fpay.presentation.request.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentApproved {
    private final String paymentKey;
    private final String orderId;
    private final int amount;
}
