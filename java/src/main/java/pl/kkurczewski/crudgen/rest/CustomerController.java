package pl.kkurczewski.crudgen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kkurczewski.crudgen.dto.Customer;
import pl.kkurczewski.crudgen.repo.CustomerRepository;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public long addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @GetMapping
    public Flux<Customer> getCustomers() {
        return Flux.fromIterable(customerRepository.findAll());
    }

}
