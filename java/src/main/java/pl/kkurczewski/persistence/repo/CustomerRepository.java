package pl.kkurczewski.persistence.repo;

import pl.kkurczewski.persistence.dto.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}