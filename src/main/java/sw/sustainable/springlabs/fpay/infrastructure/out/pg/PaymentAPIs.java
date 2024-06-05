package sw.sustainable.springlabs.fpay.infrastructure.out.pg;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.presentation.request.payment.PaymentApproved;

import java.util.Optional;

public interface PaymentAPIs {

    @POST("payments/confirm")
    Call<ResponsePaymentApproved> paymentFullfill(@Body PaymentApproved requestMessage);
}
