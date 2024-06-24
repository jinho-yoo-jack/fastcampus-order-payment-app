package sw.sustainable.springlabs.fpay.representation.request.payment;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class PaymentApproved {
    private final String paymentType;
    private final String paymentKey;
    private final String orderId;
    private final String amount;
}
