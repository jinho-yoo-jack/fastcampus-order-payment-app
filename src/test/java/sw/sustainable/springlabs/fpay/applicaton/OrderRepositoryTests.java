package sw.sustainable.springlabs.fpay.applicaton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository.order.JpaOrderRepository;
import sw.sustainable.springlabs.fpay.representation.request.order.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Slf4j
public class OrderRepositoryTests {

    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    @Test
    public void save_true_NewOrder() throws Exception {
        // Given
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        // When
        Order result = jpaOrderRepository.save(order);

        // Then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(order);
    }

}
