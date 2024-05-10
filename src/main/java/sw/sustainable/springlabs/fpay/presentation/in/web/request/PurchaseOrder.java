package sw.sustainable.springlabs.fpay.presentation.in.web.request;

import jakarta.validation.constraints.*;
import lombok.*;
import sw.sustainable.springlabs.fpay.domain.model.Order;
import sw.sustainable.springlabs.fpay.domain.model.OrderItem;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PurchaseOrder {
    @NotNull
    @NotBlank
    private final Orderer orderer;

    @Size(min = 1)
    private final List<PurchaseOrderItem> newlyOrderedItem;

    private List<OrderItem> convert2OrderItems() {
        return null;
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item){
        return OrderItem.builder()
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .size()
                .build();

    }

    public Order toEntity(){
        return null;
    }
}
