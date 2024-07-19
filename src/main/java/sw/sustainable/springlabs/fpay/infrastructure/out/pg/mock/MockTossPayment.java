package sw.sustainable.springlabs.fpay.infrastructure.out.pg.mock;

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
public class MockTossPayment implements PaymentAPIs {
    private final MockTossPaymentAPIs mockTossClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(PaymentApproved paymentInfo) throws IOException {
        return null;
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    @Override
    public ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException {
        return null;
    }

    @Override
    public List<ResponsePaymentSettlements> requestPaymentSettlement(PaymentSettlement paymentSettlement) throws IOException {
        String startDate = paymentSettlement.getStartDate();
        String endDate = paymentSettlement.getEndDate();
        int page = paymentSettlement.getPage();
        int size = paymentSettlement.getSize();

        Response<List<ResponsePaymentSettlements>> response = mockTossClient.paymentSettlements().execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty())  {
            return response.body();
        }

        throw new IOException(response.message());
    }
}
