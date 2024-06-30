package sw.sustainable.springlabs.fpay.domain.payment;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;

@Data
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class TransactionType {
    public static TransactionType convertToTransactionType(ResponsePaymentCommon commonMessage) {
        return switch (commonMessage.getMethod()) {
            case "카드" -> CardPayment.from((ResponsePaymentApproved) commonMessage);
            default ->
                throw new RuntimeException("Unsupported TransactionType method ::: " + commonMessage.getMethod());
        };
    }
}
