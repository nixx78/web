package lv.nixx.poc.graphql.domain.dto;

import lv.nixx.poc.graphql.domain.entity.CustomerEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class Customer {

    private final CustomerEntity customerEntity;

    public Customer(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Long getId() {
        return customerEntity.getId();
    }

    public String getName() {
        return customerEntity.getName();
    }

    public String getSurname() {
        return customerEntity.getSurname();
    }

    public String getPCode() {
        return customerEntity.getPCode();
    }

    public Collection<Account> getAccounts() {
        return customerEntity.getAccounts()
                .stream()
                .map(Account::new)
                .collect(Collectors.toList());
    }

    public Collection<Application> getApplications() {
        return customerEntity.getApplications()
                .stream()
                .map(Application::new)
                .collect(Collectors.toList());
    }

}
