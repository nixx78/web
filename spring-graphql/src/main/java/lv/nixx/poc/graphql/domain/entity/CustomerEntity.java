package lv.nixx.poc.graphql.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "CUSTOMER_TBL")
@Data
@Accessors(chain = true)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pCode;
    private String name;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = AccountEntity.class, mappedBy = "customer")
    private Collection<AccountEntity> accounts;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = ApplicationEntity.class, mappedBy = "customer")
    private Collection<ApplicationEntity> applications;


}
