package sw.sustainable.springlabs.fpay.domain;

public interface DomainPaymentRepository {
    Payment findById(Long id);
    void save(Payment payment);
    void delete(Payment payment);
}
