package sw.sustainable.springlabs.fpay.infrastructure.out.pg.mock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.TossPaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentSettlement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MockPayment implements PaymentAPIs {
    private final MockPaymentAPIs mockClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(PaymentApproved requestMessage) throws IOException {
        return null;
    }

    @Override
    public boolean isPaymentApproved(String status) {
        return false;
    }

    @Override
    public ResponsePaymentCancel requestPaymentCancel(String paymentKey, PaymentCancel cancelMessage) throws IOException {
        return null;
    }

    @Override
    public List<ResponsePaymentSettlements> requestPaymentSettlement() throws IOException {

        Response<List<ResponsePaymentSettlements>> response = mockClient.paymentSettlements().execute();
        if(response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
            return response.body();
        }
        throw new IOException(response.message());

    }
}
