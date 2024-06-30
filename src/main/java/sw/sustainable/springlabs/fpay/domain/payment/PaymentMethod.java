package sw.sustainable.springlabs.fpay.domain.payment;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum PaymentMethod {
    CARD("카드");

    private final String methodName;

    private static final Map<String,String> methodMap = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(PaymentMethod::getMethodName, PaymentMethod::name))
    );

    public static PaymentMethod fromMethodName(String methodName) {
        return PaymentMethod.valueOf(methodMap.get(methodName));
    }

    PaymentMethod(String name) {
        this.methodName = name;
    }

}
