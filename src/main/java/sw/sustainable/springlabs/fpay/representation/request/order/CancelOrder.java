package sw.sustainable.springlabs.fpay.representation.request.order;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrder {
    private UUID orderId;
    private int[] itemIdxs;         // itemIdx 정보가 Empty면 전체 취소
    private String cancelReason; // 취소 사유
    private String paymentKey; // 결제 ID
    private int cancellationAmount; // 취소 금액
}
