package sw.sustainable.springlabs.fpay.infrastructure.out.persistence.repository;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface JpaBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);
    <S extends T> void save(S entity);
    boolean deleteById(ID id);
}
