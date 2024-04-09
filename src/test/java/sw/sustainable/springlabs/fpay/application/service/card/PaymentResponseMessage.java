package sw.sustainable.springlabs.fpay.application.service.card;

import lombok.*;

@Getter
@Setter
public record PaymentResponseMessage(String responseCode, String responseMessage, String orderNo, String cardName,
                                     String cardNo,
                                     String amount) {
}
