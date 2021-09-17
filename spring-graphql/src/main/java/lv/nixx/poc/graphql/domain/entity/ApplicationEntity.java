package lv.nixx.poc.graphql.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "APPLICATION_TBL")
@Data
@Accessors(chain = true)
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    private String text;
}
