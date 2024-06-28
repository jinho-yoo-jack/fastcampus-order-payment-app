package sw.sustainable.springlabs.fpay.domain.payment.card;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class PaymentMethod {

    @Column(name = "cancel_amount")
    protected int availableCancelAmount;

    public boolean isCancellableAmountGreaterThan(int cancellationAmount){
        return availableCancelAmount >= cancellationAmount;
    }
}
