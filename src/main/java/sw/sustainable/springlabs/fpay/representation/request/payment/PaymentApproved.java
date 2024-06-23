package sw.sustainable.springlabs.fpay.representation.request.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentApproved {
//    private String paymentType;
    private final String paymentKey;
    private final String orderId;
    private final String amount;
}
