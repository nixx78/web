package lv.nixx.poc.graphql.query;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lv.nixx.poc.graphql.domain.dto.Application;
import lv.nixx.poc.graphql.domain.dto.ApplicationInput;
import lv.nixx.poc.graphql.domain.entity.ApplicationEntity;
import lv.nixx.poc.graphql.domain.entity.CustomerEntity;
import lv.nixx.poc.graphql.repository.ApplicationRepository;
import lv.nixx.poc.graphql.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationMutationResolver implements GraphQLMutationResolver {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMutationResolver.class);

    private final ApplicationRepository appRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ApplicationMutationResolver(ApplicationRepository appRepository, CustomerRepository customerRepository) {
        this.appRepository = appRepository;
        this.customerRepository = customerRepository;
    }

    public Application newApplication(ApplicationInput input) {
        Long customerId = input.getCustomerId();

        LOG.info("Try to add application for customer id [{}]", customerId);

        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer: " + customerId + " not exists"));

        ApplicationEntity created = appRepository.save(new ApplicationEntity()
                .setCustomer(customer)
                .setText(input.getText()));

        LOG.info("Application with id [{}] created", created.getId());

        return new Application(created);
    }

    public Application updateApplication(ApplicationInput input) {

        final Long appId = input.getId();
        LOG.info("Try to update application with id [{}]", appId);

        Optional<ApplicationEntity> existingOpt = appRepository.findById(appId);
        if (existingOpt.isPresent()) {
            ApplicationEntity applicationEntity = existingOpt.get();
            applicationEntity.setText(input.getText());

            ApplicationEntity saved = appRepository.save(applicationEntity);

            LOG.info("Application with id updated [{}]", appId);
            return new Application(saved);
        } else {
            throw new IllegalArgumentException("Application with id [" + appId + "]not exists");
        }
    }

    public Application deleteApplication(Long id) {

        LOG.info("Try to delete application with id [{}]", id);

        Optional<ApplicationEntity> existingAppOpt = appRepository.findById(id);
        if (existingAppOpt.isPresent()) {
            ApplicationEntity existing = existingAppOpt.get();
            appRepository.delete(existing);
            LOG.info("Application with id [{}] deleted successfully", id);
            return new Application(existing);
        } else {
            throw new IllegalArgumentException("Application with id [" + id + "not exists");
        }
    }

}
