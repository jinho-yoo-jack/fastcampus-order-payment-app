package sw.sustainable.springlabs.fpay.presentation.in.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import sw.sustainable.springlabs.fpay.application.service.card.PaymentResponseMessage;

@Data
@RequiredArgsConstructor
public class FullFillRequestMessage {
    private final String paymentType;
    private final PaymentResponseMessage paymentResponseMessage;;
}
