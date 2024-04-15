package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.model.PaymentInfo;

public interface PaymentFullFill {
    PaymentInfo paymentFullFill(PaymentInfo paymentInfo);
}
