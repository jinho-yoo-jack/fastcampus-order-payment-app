package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TossPayment implements PaymentAPIs {
    private final TossPaymentAPIs tossClient;

    @Override
    public ResponsePaymentApproved requestPaymentApprove(PaymentApproved paymentInfo) throws IOException {
        Response<ResponsePaymentApproved> response = tossClient.paymentFullfill(paymentInfo)
                .execute();
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
    public void paymentCancel(PaymentApproved byOrderItemId) {

    }

    @Override
    public void paymentCancelAll(String paymentKey) {

    }

    @Override
    public void settlement(PaymentApproved paymentApproved) {

    }
}
