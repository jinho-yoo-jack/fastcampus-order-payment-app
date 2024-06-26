package sw.sustainable.springlabs.fpay.application.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Response;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullfillUseCase;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.payment.Payment;
import sw.sustainable.springlabs.fpay.domain.payment.card.CardPayment;
import sw.sustainable.springlabs.fpay.domain.payment.card.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentMethodRepository;
import sw.sustainable.springlabs.fpay.domain.repository.PaymentRepository;
import sw.sustainable.springlabs.fpay.infrastructure.config.BeanUtils;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.CardPaymentRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.JpaCardPaymentRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.PaymentAPIs;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.response.payment.method.Card;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentFullfillUseCase {
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
        ResponsePaymentApproved response = requestPaymentApproved(paymentInfo);
        String status = response.getStatus();

        if (paymentApproved(status)) {
            Order completedOrder = orderRepository.findById(UUID.fromString(response.getOrderId()));
            completedOrder.orderPaymentFullFill();
            paymentMethodRepository.save(Payment.toEntity(response));
            initPaymentRepository(response.getMethod());
            paymentRepository.save(CardPayment.toEntity(response));
        }

        return "fail";
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

    private boolean paymentApproved(String status) {
        return "DONE".equalsIgnoreCase(status);
    }

    private ResponsePaymentApproved requestPaymentApproved(PaymentApproved paymentInfo) throws IOException {
        Response<ResponsePaymentApproved> response = paymentAPIs.paymentFullfill(paymentInfo)
                .execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new IOException(response.message());
    }

}
