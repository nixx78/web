package lv.nixx.poc.graphql.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BALANCE_TBL")
@Data
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal amount;

    private String date;

    public BalanceEntity() {
    }

    public BalanceEntity(BigDecimal amount, String date) {
        this.amount = amount;
        this.date = date;
    }
}
