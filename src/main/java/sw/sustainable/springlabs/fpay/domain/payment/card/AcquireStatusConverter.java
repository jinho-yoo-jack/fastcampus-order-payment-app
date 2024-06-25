package sw.sustainable.springlabs.fpay.domain.payment.card;

import jakarta.persistence.AttributeConverter;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;

public class AcquireStatusConverter implements AttributeConverter<AcquireStatus, String> {
    @Override
    public String convertToDatabaseColumn(AcquireStatus acquireStatus) {
        return acquireStatus.name();
    }

    @Override
    public AcquireStatus convertToEntityAttribute(String s) {
        return AcquireStatus.valueOf(s);
    }
}
