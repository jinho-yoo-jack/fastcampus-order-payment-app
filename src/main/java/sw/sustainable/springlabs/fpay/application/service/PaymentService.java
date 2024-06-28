package sw.sustainable.springlabs.fpay.application.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw.sustainable.springlabs.fpay.application.port.in.GetPaymentInfoUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullfillUseCase;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.repository.*;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentFullfillUseCase, GetPaymentInfoUseCase {
    private final PaymentAPIs paymentAPIs;
    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final Set<PaymentRepository> paymentRepositoriesSet;

    private Map<String, PaymentRepository> paymentRepositoriesInfo = new HashMap<>();
    private PaymentRepository paymentRepository;

    @PostConstruct
    public void init() {
        for (PaymentRepository paymentRepository : paymentRepositoriesSet) {
            String key = paymentRepository.getClass().getSimpleName().split("\\$\\$")[0];
            paymentRepositoriesInfo.put(key, paymentRepository);
        }
    }

    @Transactional
    @Override
    public String paymentApproved(PaymentApproved paymentInfo) throws IOException {
        ResponsePaymentApproved response = paymentAPIs.requestPaymentApprove(paymentInfo);

        if (paymentAPIs.isPaymentApproved(response.getStatus())) {
            Order completedOrder = orderRepository.findById(UUID.fromString(response.getOrderId()));
            completedOrder.orderPaymentFullFill();
            paymentMethodRepository.save(Payment.toEntity(response));
            initPaymentRepository(response.getMethod());
            paymentRepository.save(CardPayment.from(response));
        }

        return "fail";
    }

    public String getPaymentMethod(String paymentKey){
        return paymentMethodRepository.findById(paymentKey).getMethod();
    }

    @Override
    public PaymentMethod getPaymentMethodInfo(String method, String paymentKey) {
        initPaymentRepository(method);
        if(isNotPaymentRepository()){
           return paymentRepository.findById(paymentKey);
        }
        return null;
    }

    private boolean isNotPaymentRepository() {
        return paymentRepository != null;
    }

    private void initPaymentRepository(String paymentMethod) {
        switch (paymentMethod) {
            case "카드":
                paymentRepository = paymentRepositoriesInfo.get("CardPaymentRepository");
                break;
            case "가상계좌":
                paymentRepository = paymentRepositoriesInfo.get(paymentMethod);
                break;
            default:
                throw new RuntimeException("Unsupported payment method: " + paymentMethod);
        }
    }

}
