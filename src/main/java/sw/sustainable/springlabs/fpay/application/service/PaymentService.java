package sw.sustainable.springlabs.fpay.application.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullFillUseCase;
import sw.sustainable.springlabs.fpay.application.port.out.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.application.port.out.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.PaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.application.port.out.repository.TransactionTypeRepository;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.ResponsePaymentApproved;
import sw.sustainable.springlabs.fpay.representation.request.payment.PaymentApproved;

import java.beans.Transient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentFullFillUseCase {
    private final PaymentAPIs tossPayment;
    private final OrderRepository orderRepository;
    private final PaymentLedgerRepository paymentLedgerRepository;
    private final Set<TransactionTypeRepository> transactionTypeRepositorySet;
    private final Map<String, TransactionTypeRepository> transactionTypeRepositories = new HashMap<>();

    private TransactionTypeRepository transactionTypeRepository;

    @PostConstruct
    public void init() {
        for (TransactionTypeRepository transactionTypeRepository : transactionTypeRepositorySet) {
            // card, account
            String paymentMethodType = transactionTypeRepository.getClass().getSimpleName().split("TransactionTypeRepository")[0].toLowerCase();
            transactionTypeRepositories.put(paymentMethodType, transactionTypeRepository);
            log.info("TransactionTypeRepository: {}", paymentMethodType);
        }
    }

    @Transactional
    @Override
    public String paymentApproved(PaymentApproved paymentApproved) throws IOException {
        verifyOrderIsCompleted(UUID.fromString(paymentApproved.getOrderId()));
        ResponsePaymentApproved response = tossPayment.requestPaymentApprove(paymentApproved);

        if (tossPayment.isPaymentApproved(response.getStatus())) {
            Order completedOrder = orderRepository.findById(UUID.fromString(response.getOrderId()));
            completedOrder.orderPaymentFullFill(response.getPaymentKey());
            paymentLedgerRepository.save(response.toPaymentTransactionEntity());
            PaymentMethod method = PaymentMethod.fromMethodName(response.getMethod());
            transactionTypeRepository = transactionTypeRepositories.get(method.toString().toLowerCase());
            transactionTypeRepository.save(TransactionType.convertToTransactionType(response));

            return "success";
        }

        return "fail";
    }

    private void verifyOrderIsCompleted(UUID orderId) throws IllegalArgumentException {
        OrderStatus orderStatus = orderRepository.findById(orderId).getStatus();
        if (!OrderStatus.ORDER_COMPLETED.equals(orderStatus))
            throw new IllegalArgumentException("Order is not completed");
    }
}
