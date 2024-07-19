package sw.sustainable.springlabs.fpay.infrastructure.out.pg.mock;

import retrofit2.Call;
import retrofit2.http.GET;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentSettlements;

import java.util.List;

public interface MockTossPaymentAPIs {
    @GET("settlements")
    Call<List<ResponsePaymentSettlements>> paymentSettlements();
}