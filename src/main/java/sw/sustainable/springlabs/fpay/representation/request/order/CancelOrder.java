package sw.sustainable.springlabs.fpay.representation.request.order;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrder {
    private UUID orderId;
    private int itemIdx;
    private String paymentKey;
    private int cancellationAmount;
}
