package sw.sustainable.springlabs.fpay.application.service.card;

import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullFill;

@Service
public class CardPaymentService implements PaymentFullFill {

    @Override
    public boolean paymentFullFill(PaymentResponseMessage message) {

        return false;
    }
}
