package sw.sustainable.springlabs.fpay.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentSettlementsUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.SendSettlementsInfoUseCase;

@RestController
@RequestMapping("/settlements")
@RequiredArgsConstructor
@Slf4j
public class SettlementsController {
    private final PaymentSettlementsUseCase paymentSettlementsUseCase;
    private final SendSettlementsInfoUseCase sendSettlementsInfoUseCase;

    @GetMapping
    public boolean fetchSettlements() throws Exception {
        paymentSettlementsUseCase.getPaymentSettlements();
        return true;
    }

    @GetMapping("/produce")
    public boolean produceSettlements() throws Exception {
        sendSettlementsInfoUseCase.send();
        return true;
    }
}
