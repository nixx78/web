package lv.nixx.poc.graphql.domain.dto;

import lv.nixx.poc.graphql.domain.entity.ApplicationEntity;

public class Application {

    private final ApplicationEntity applicationEntity;

    public Application(ApplicationEntity applicationEntity) {
        this.applicationEntity = applicationEntity;
    }

    public Long getId() {
        return applicationEntity.getId();
    }

    public String getText() {
        return applicationEntity.getText();
    }
}
