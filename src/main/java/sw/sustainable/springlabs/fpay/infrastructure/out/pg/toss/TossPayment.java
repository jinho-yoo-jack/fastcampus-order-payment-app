package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentAPIs {
    private final TossPaymentAPIs tossClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(PaymentApproved paymentInfo) throws IOException {
        Response<ResponsePaymentApproved> response = tossClient.paymentFullfill(paymentInfo).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    @Override
    public ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException {
        Response<ResponsePaymentCancel> response = tossClient.paymentCancel(paymentKey, cancelMessage).execute();
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new IOException(response.message());
    }

    @Override
    public List<ResponsePaymentSettlements> requestPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException {
        String startDate = paymentSettlement.getStartDate();
        String endDate = paymentSettlement.getEndDate();
        int page = paymentSettlement.getPage();
        int size = paymentSettlement.getSize();

        Response<List<ResponsePaymentSettlements>> response = tossClient.paymentSettlements(startDate, endDate, page, size).execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty())  {
            return response.body();
        }

        throw new IOException(response.message());
    }
}
