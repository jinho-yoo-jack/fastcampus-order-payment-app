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
    @NotNull
    @NotBlank
    @Min(1)
    private final int itemIdx;

    @NotNull
    @NotBlank
    private final UUID productId;

    @NotNull
    @NotBlank
    private final String productName;

    @NotNull
    @NotBlank
    private final int price;    // 가격

    @NotNull
    @NotBlank
    @Min(1)
    private final int quantity; // 수량

    @NotNull
    @NotBlank
    private final int amounts;  // price * quantity

}
