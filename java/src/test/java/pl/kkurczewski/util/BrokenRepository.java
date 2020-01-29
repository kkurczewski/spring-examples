package pl.kkurczewski.util;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kkurczewski.persistence.dto.Customer;

@Repository
public interface BrokenRepository extends CrudRepository<Customer, Long> {
    // if test is properly isolated this component should be not injected
}