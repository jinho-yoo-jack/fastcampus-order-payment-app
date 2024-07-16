package sw.sustainable.springlabs.fpay.domain.payment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter(autoApply = true)
@Slf4j
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        log.info("Converting payment method {} -> {}", paymentMethod, paymentMethod.getMethodName());
        return paymentMethod.getMethodName();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String s) {
        log.info("Converting payment method {} -> {}", s,PaymentMethod.fromMethodName(s));
        return PaymentMethod.fromMethodName(s);
    }
}
