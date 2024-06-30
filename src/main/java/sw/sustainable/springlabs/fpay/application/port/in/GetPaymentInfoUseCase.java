package sw.sustainable.springlabs.fpay.application.port.in;

import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;

import java.util.List;

public interface GetPaymentInfoUseCase {
    List<PaymentLedger> getPaymentInfo(String paymentKey);
    PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey);
}
