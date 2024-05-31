package sw.sustainable.springlabs.fpay.infrastructure.out.pg.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestPaymentApproved {
    private final String paymentType;
    private final String orderId;
    private final int amount;
}
