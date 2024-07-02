package sw.sustainable.springlabs.fpay.domain.model.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentMethodTests {

    @Test
    public void getPaymentMethodByName() {
        Assertions.assertEquals(PaymentMethod.CARD.toString(),
                PaymentMethod.fromMethodName("카드").toString());

        Assertions.assertEquals(PaymentMethod.ACCOUNT.toString(),
                PaymentMethod.fromMethodName("계좌이체").toString());

        Assertions.assertEquals(PaymentMethod.VACCOUNT.toString(),
                PaymentMethod.fromMethodName("가상 계좌이체").toString());
    }
}
