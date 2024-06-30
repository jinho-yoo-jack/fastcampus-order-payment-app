package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.data.repository.*;

import java.util.Optional;

@NoRepositoryBean
public interface JpaBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);
    <S extends T> T save(T entity);
    boolean deleteById(ID id);
}
