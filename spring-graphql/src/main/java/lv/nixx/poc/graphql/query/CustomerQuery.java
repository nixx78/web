package lv.nixx.poc.graphql.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.SelectedField;
import lv.nixx.poc.graphql.domain.dto.Customer;
import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.CustomerEntity;
import lv.nixx.poc.graphql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerQuery implements GraphQLQueryResolver {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerQuery(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //TODO Implement logic for lazy Application
    public Customer customerDetails(Long customerId, DataFetchingEnvironment environment) {

        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();

        Set<String> selectedFields = selectionSet.getFields()
                .stream()
                .map(SelectedField::getName)
                .collect(Collectors.toSet());

        Optional<CustomerEntity> result;
        if (selectionSet.contains("accounts")) {

            result = customerRepository.findOne((root, query, builder) -> {
                Fetch<Customer, Collection<AccountEntity>> accounts = root.fetch("accounts", JoinType.LEFT);

                if (selectedFields.contains("balance")) {
                    accounts.fetch("balance", JoinType.LEFT);
                }

                return builder.equal(root.get("id"), customerId);
            });

        } else {
            result = customerRepository.findById(customerId);
        }

        return new Customer(result
                .orElseThrow(() -> new IllegalArgumentException("Customer with id:" + customerId + " not found"))
        );
    }

}
