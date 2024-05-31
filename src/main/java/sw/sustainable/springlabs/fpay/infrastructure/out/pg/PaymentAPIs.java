package sw.sustainable.springlabs.fpay.infrastructure.out.pg;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.request.RequestPaymentApproved;

public interface PaymentAPIs {

    @POST("/payments/confirm")
    Call<String> paymentFullfill(@Body RequestPaymentApproved requestMessage);
}
