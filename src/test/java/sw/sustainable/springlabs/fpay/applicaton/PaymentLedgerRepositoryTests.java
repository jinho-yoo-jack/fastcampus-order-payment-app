package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentLedger;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentMethod;
import sw.sustainable.springlabs.fpay.domain.payment.PaymentStatus;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order.JpaOrderRepository;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.payment.JpaPaymentLedgerRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Slf4j
public class PaymentLedgerRepositoryTests {

    @Autowired
    private JpaPaymentLedgerRepository jpaPaymentLedgerRepository;

    @Test
    public void save_true_PaymentLedger() throws Exception {
        // Given
        PaymentLedger paymentInfo = PaymentLedger.builder()
            .id(1)
            .paymentKey("")
            .method(PaymentMethod.CARD)
            .paymentStatus(PaymentStatus.DONE)
            .totalAmount(3400)
            .balanceAmount(3400)
            .canceledAmount(0)
            .build();

        // When
        PaymentLedger result = jpaPaymentLedgerRepository.save(paymentInfo);

        // Then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(paymentInfo);
    }

}
