package sw.sustainable.springlabs.fpay.domain.model.order;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;

@Converter(autoApply = true)
@Slf4j
public class OrderStatusConverter implements AttributeConverter<sw.sustainable.springlabs.fpay.domain.order.OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(sw.sustainable.springlabs.fpay.domain.order.OrderStatus orderStatus) {
        return orderStatus.name();
    }

    @Override
    public sw.sustainable.springlabs.fpay.domain.order.OrderStatus convertToEntityAttribute(String s) {
        return OrderStatus.valueOf(s);
    }
}
