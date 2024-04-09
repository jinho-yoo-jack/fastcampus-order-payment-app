package sw.sustainable.springlabs.fpay.application.port.out.persistence;

import sw.sustainable.springlabs.fpay.application.service.card.PaymentResponseMessage;

public interface RegisterNewTransaction {
    boolean register(PaymentResponseMessage message);
}
