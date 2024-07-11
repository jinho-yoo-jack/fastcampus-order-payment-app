package sw.sustainable.springlabs.fpay.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.mock.Calls;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.TossPayment;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.TossPaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.method.Card;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class TossPaymentTests {
    @Mock
    private TossPaymentAPIs tossClient;

    @InjectMocks
    private TossPayment tossPayment;

    @Test
    public void requestPaymentApprove_isSuccessful_Normal() throws IOException {
        String orderId = UUID.randomUUID().toString();
        String paymentKey = "tgen_20240605132741Jtkz1";
        PaymentApproved paymentInfo = new PaymentApproved("NORMAL",
                paymentKey, orderId, "3400");

        ResponsePaymentApproved response = ResponsePaymentApproved.builder()
                .orderId(orderId)
                .paymentKey(paymentKey)
                .status("DONE")
                .method("카드")
                .orderName("속이 편한 우유")
                .card(Card.builder()
                        .issuerCode(null)
                        .acquirerCode("41")
                        .number("54287966****112")
                        .approveNo("00000000")
                        .acquireStatus("READY")
                        .build()
                )
                .build();

        when(tossClient.paymentFullfill(paymentInfo))
                .thenReturn(Calls.<ResponsePaymentApproved>response(response));

        ResponsePaymentApproved result = tossPayment.requestPaymentApprove(paymentInfo);
        Mockito.verify(tossClient, Mockito.times(1)).paymentFullfill(paymentInfo);
        Assertions.assertEquals(result, response);

    }

}
