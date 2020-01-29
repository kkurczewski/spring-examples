package pl.kkurczewski.crudgen;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import pl.kkurczewski.crudgen.dto.Customer;
import pl.kkurczewski.crudgen.repo.CustomerRepository;
import pl.kkurczewski.util.BrokenRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
@Commit
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
@EntityScan(basePackageClasses = Customer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryInMemoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired(required = false)
    private BrokenRepository brokenRepository;

    @Test
    void shouldNotInjectBrokenRepositoryBean() {
        assertNull(brokenRepository);
    }

    @Order(1)
    @Test
    void shouldAddCustomer() {
        Customer customer = new Customer("Cave", "Johnson");
        Customer savedCustomer = customerRepository.save(customer);

        assertSoftly(soft -> {
            soft.assertThat(savedCustomer.getId()).isEqualTo(1);
            soft.assertThat(savedCustomer.getFirstName()).isEqualTo("Cave");
            soft.assertThat(savedCustomer.getLastName()).isEqualTo("Johnson");
        });
    }

    @Order(2)
    @Test
    void shouldReturnEmptyResult() {
        assertThat(customerRepository.findAll())
                .first()
                .satisfies(customer -> assertSoftly(soft -> {
                    soft.assertThat(customer.getId()).isEqualTo(1);
                    soft.assertThat(customer.getFirstName()).isEqualTo("Cave");
                    soft.assertThat(customer.getLastName()).isEqualTo("Johnson");
                }));
    }
}
