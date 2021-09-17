package lv.nixx.poc.graphql.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationInput {
    private Long customerId;
    private String text;
}
