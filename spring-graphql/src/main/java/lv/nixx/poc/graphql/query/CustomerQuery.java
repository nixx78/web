package lv.nixx.poc.graphql.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lv.nixx.poc.graphql.domain.dto.Customer;
import lv.nixx.poc.graphql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerQuery implements GraphQLQueryResolver {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerQuery(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer customerDetails(Long customerId) {
        return new Customer(customerRepository.findById(customerId)
                .orElseThrow( ()-> new IllegalArgumentException("Customer with id:" + customerId + " not found"))
        );
    }

}
