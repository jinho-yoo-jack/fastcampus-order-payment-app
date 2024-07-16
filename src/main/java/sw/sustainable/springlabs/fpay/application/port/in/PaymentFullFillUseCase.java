package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;

public interface PaymentFullFillUseCase {
    String paymentApproved(PaymentApproved paymentApproved) throws IOException;
}
