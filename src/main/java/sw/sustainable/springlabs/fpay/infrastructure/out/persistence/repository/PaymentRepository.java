package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sw.sustainable.springlabs.fpay.domain.Payment;

@Repository
public interface PaymentRepository extends JpaBaseRepository<Payment, String> {
}
