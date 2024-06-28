package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

public interface TossPaymentAPIs {

    @POST("payments/confirm")
    Call<ResponsePaymentApproved> paymentFullfill(@Body PaymentApproved requestMessage);

}
