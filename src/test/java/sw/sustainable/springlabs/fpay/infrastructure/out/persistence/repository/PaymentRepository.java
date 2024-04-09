package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;

import sw.sustainable.springlabs.fpay.domain.Payment;

import java.util.List;

public interface PaymentRepository extends BaseRepository<Payment, Long> {
    List<Payment> findById(Long customerId);
}
