package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.presentation.request.payment.PaymentApproved;

import java.io.IOException;

public interface PaymentFullfillUseCase {
    String paymentApproved(PaymentApproved paymentInfo) throws IOException;
}
