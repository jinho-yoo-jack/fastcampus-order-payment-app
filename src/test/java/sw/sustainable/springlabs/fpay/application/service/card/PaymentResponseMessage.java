package sw.sustainable.springlabs.fpay.application.service.card;

public record PaymentResponseMessage(String responseCode, String responseMessage, String orderNo, String cardName,
                                     String cardNo,
                                     String amount) {
}
