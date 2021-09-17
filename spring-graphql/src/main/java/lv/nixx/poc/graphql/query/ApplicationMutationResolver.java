package lv.nixx.poc.graphql.query;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lv.nixx.poc.graphql.domain.dto.Application;
import lv.nixx.poc.graphql.domain.dto.ApplicationInput;
import lv.nixx.poc.graphql.domain.entity.ApplicationEntity;
import lv.nixx.poc.graphql.domain.entity.CustomerEntity;
import lv.nixx.poc.graphql.repository.ApplicationRepository;
import lv.nixx.poc.graphql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMutationResolver implements GraphQLMutationResolver {

    private final ApplicationRepository appRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ApplicationMutationResolver(ApplicationRepository appRepository, CustomerRepository customerRepository) {
        this.appRepository = appRepository;
        this.customerRepository = customerRepository;
    }

    public Application newApplication(ApplicationInput input) {
        Long customerId = input.getCustomerId();

        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer: " + customerId + " not exists"));

        ApplicationEntity created = appRepository.save(new ApplicationEntity()
                .setCustomer(customer)
                .setText(input.getText()));

        return new Application(created);
    }

}
