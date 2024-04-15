package sw.sustainable.springlabs.fpay.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullFill;
import sw.sustainable.springlabs.fpay.domain.DomainPaymentRepository;
import sw.sustainable.springlabs.fpay.domain.model.PaymentInfo;

@Service
@RequiredArgsConstructor
public class CardPaymentService implements PaymentFullFill {
    private final DomainPaymentRepository paymentRepository;

    @Override
    @Transactional
    public PaymentInfo paymentFullFill(PaymentInfo paymentInfo) {
        paymentRepository.save(paymentRepository.of(paymentInfo));
        return null;
    }
}
