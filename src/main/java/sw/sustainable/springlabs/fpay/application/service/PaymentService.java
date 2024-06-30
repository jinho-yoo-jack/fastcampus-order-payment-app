package sw.sustainable.springlabs.fpay.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw.sustainable.springlabs.fpay.application.port.in.GetPaymentInfoUseCase;
import sw.sustainable.springlabs.fpay.application.port.in.PaymentFullfillUseCase;
import sw.sustainable.springlabs.fpay.domain.api.PaymentAPIs;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.TransactionType;
import sw.sustainable.springlabs.fpay.domain.repository.*;
import sw.sustainable.springlabs.fpay.infrastructure.config.BeanUtils;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.JpaCardPaymentRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.CardTransactionTypeRepository;
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
    private final PaymentLedgerRepository paymentLedgerRepository;
    private TransactionTypeRepository transactionTypeRepository;

    @Transactional
    @Override
    public String paymentApproved(PaymentApproved paymentInfo) throws IOException {
        ResponsePaymentApproved response = paymentAPIs.requestPaymentApprove(paymentInfo);

        if (paymentAPIs.isPaymentApproved(response.getStatus())) {
            Order completedOrder = orderRepository.findById(UUID.fromString(response.getOrderId()));
            completedOrder.orderPaymentFullFill(response.getPaymentKey());
            paymentLedgerRepository.save(response.toPaymentTransactionEntity());
            initPaymentRepository(PaymentMethod.fromMethodName(response.getMethod()));
            transactionTypeRepository.save(TransactionType.convertToTransactionType(response));
        }

        return "fail";
    }

    public PaymentMethod getPaymentMethod(String paymentKey) {
        return paymentLedgerRepository.findAllByPaymentKey(paymentKey).getFirst().getMethod();
    }

    @Override
    public List<PaymentLedger> getPaymentInfo(String paymentKey) {
        return paymentLedgerRepository.findAllByPaymentKey(paymentKey);
    }

    @Override
    public PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey) {
        return paymentLedgerRepository.findOneByPaymentKeyDesc(paymentKey);
    }

    private boolean isNotPaymentRepository() {
        return transactionTypeRepository != null;
    }

    private void initPaymentRepository(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case PaymentMethod.CARD:
                transactionTypeRepository = new CardTransactionTypeRepository((JpaCardPaymentRepository) BeanUtils.getBean(JpaCardPaymentRepository.class));
                break;
            default:
                throw new RuntimeException("Unsupported payment method: " + paymentMethod);
        }
    }

}
