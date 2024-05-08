package sw.sustainable.springlabs.fpay.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Embeddable
@AllArgsConstructor
@Builder
public class OrderItem {
    private int itemIdx;

    private UUID productId;

    private String productName;

    private int price;

    private String size;

    private int amount;

    private String state;

    protected OrderItem() {}

    public int calculateAmount() {
        return price * amount;
    }
}
