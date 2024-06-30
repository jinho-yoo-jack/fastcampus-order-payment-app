package sw.sustainable.springlabs.fpay;

import org.junit.jupiter.api.Test;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;

public class EnumTests {
    @Test
    public void enumTest(){
        PaymentMethod p = PaymentMethod.CARD;
        System.out.println(p);
        System.out.println(p.getMethodName());
        System.out.printf(PaymentMethod.valueOf("CARD").toString());
        System.out.printf(PaymentMethod.fromMethodName("카드").toString());
    }
}
