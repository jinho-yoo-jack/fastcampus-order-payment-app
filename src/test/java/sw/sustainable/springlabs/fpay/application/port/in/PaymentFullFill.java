package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.application.service.card.PaymentResponseMessage;

public interface PaymentFullFill {
    boolean paymentFullFill(PaymentResponseMessage message);
}
