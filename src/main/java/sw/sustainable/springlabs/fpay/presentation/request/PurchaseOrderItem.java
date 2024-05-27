package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PurchaseOrderItem {
    @Min(1)
    private final int itemIdx;

    private final UUID productId;

    @NotBlank
    private final String productName;

    private final int price;    // 가격

    @Min(1)
    private final int quantity; // 수량

    private final int amounts;  // price * quantity
}
