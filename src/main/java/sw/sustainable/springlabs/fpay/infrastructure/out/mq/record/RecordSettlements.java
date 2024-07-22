package sw.sustainable.springlabs.fpay.infrastructure.out.mq.record;

import jakarta.persistence.*;
import lombok.*;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethodConverter;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatusConverter;
import sw.sustainable.springlabs.fpay.domain.settlements.PaymentSettlements;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecordSettlements {
    private int id;

    private String paymentKey;

    private String method;

    private String paymentStatus;

    private int totalAmount;

    private int payOutAmount;

    private int canceledAmount;

    private String soldDate;

    private String paidOutDate;

    public static RecordSettlements from(PaymentSettlements paymentSettlements) {
        return RecordSettlements.builder()
                .id(paymentSettlements.getId())
                .paymentKey(paymentSettlements.getPaymentKey())
                .paymentStatus(paymentSettlements.getPaymentStatus().toString())
                .totalAmount(paymentSettlements.getTotalAmount())
                .payOutAmount(paymentSettlements.getPayOutAmount())
                .canceledAmount(paymentSettlements.getCanceledAmount())
                .soldDate(paymentSettlements.getSoldDate().toString())
                .paidOutDate(paymentSettlements.getPaidOutDate().toString())
                .build();
    }
}
