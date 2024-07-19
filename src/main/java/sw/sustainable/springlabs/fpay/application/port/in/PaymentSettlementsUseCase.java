package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.io.IOException;

public interface PaymentSettlementsUseCase {
    void getPaymentSettlements() throws Exception;
}
