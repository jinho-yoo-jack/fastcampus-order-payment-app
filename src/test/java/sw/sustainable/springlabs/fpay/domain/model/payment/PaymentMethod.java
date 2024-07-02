package sw.sustainable.springlabs.fpay.domain.model.payment;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum PaymentMethod {
    CARD("카드"),
    ACCOUNT("계좌이체"),
    VACCOUNT("가상 계좌이체");

    public static final Map<String, String> methodMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(PaymentMethod::getMethodName, PaymentMethod::name))
    );

    private final String methodName;

    PaymentMethod(String m) {
        this.methodName = m;
    }

    public static PaymentMethod fromMethodName(String methodName) {
        // CODE: Step#4
        return PaymentMethod.valueOf(methodMap.get(methodName));

        // CODE: Step#3
        //if(name.equals("카드"))
        //    return PaymentMethod.CARD;
        //else if(name.equals("계좌이쳬"))
        //    return PaymentMethod.ACCOUNT;
        //else return PaymentMethod.VACCOUNT;

        // CODE: Step#2
        //if(name.equals("카드"))
        //    return PaymentMethod.CARD;
        //else if(name.equals("계좌이쳬"))
        //    return PaymentMethod.ACCOUNT;

        // CODE: Step#1
        // return PaymentMethod.CARD;
    }
}
