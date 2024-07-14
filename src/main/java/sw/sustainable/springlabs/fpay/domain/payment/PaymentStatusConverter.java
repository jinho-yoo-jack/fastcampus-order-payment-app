package sw.sustainable.springlabs.fpay.domain.payment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter(autoApply = true)
@Slf4j
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        return status.name();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String s) {
        return PaymentStatus.valueOf(s);
    }
}
