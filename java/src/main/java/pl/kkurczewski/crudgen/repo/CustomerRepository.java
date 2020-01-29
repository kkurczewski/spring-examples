package pl.kkurczewski.crudgen.repo;

import pl.kkurczewski.crudgen.dto.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}