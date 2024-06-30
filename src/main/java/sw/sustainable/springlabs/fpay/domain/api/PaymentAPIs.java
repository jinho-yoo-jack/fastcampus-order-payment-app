package sw.sustainable.springlabs.fpay.domain.api;

import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;

import java.io.IOException;

public interface PaymentAPIs {
    ResponsePaymentApproved requestPaymentApprove(PaymentApproved requestMessage) throws IOException;
    boolean isPaymentApproved(String status);
    ResponsePaymentCancel paymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException;
    void paymentCancelAll(String paymentKey);
    void settlement(PaymentApproved paymentApproved);
}
