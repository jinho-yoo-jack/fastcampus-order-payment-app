package sw.sustainable.springlabs.fpay.infrastructure.persistence.repository;


import org.springframework.data.repository.Repository;

public interface BaseRepository<T, ID> extends Repository<T, ID> {
}
