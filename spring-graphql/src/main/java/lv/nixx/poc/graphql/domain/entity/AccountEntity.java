package lv.nixx.poc.graphql.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TBL")
@Data
@Accessors(chain = true)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, targetEntity = BalanceEntity.class)
    private BalanceEntity balance;

}
