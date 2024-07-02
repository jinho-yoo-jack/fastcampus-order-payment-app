package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentCancel;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentCancel;

public interface TossPaymentAPIs {
    @POST("payments/confirm")
    Call<ResponsePaymentApproved> paymentFullfill(@Body PaymentApproved requestMessage);

    @POST("payments/{paymentKey}/cancel")
    Call<ResponsePaymentCancel> paymentCancel(@Path("paymentKey") String paymentKey, @Body PaymentCancel requestMessage);
}
