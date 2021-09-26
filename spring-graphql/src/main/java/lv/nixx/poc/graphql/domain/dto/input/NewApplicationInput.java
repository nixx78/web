package lv.nixx.poc.graphql.domain.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewApplicationInput {
    private Long id;
    private Long customerId;
    private String text;
}
