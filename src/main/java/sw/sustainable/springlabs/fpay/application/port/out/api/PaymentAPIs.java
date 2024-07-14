package sw.sustainable.springlabs.fpay.application.port.out.api;

import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.io.IOException;
import java.util.List;

public interface PaymentAPIs {
    ResponsePaymentApproved requestPaymentApprove(PaymentApproved requestMessage) throws IOException;
    boolean isPaymentApproved(String status);
    ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException;
    List<ResponsePaymentSettlements> requestPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException;
}
