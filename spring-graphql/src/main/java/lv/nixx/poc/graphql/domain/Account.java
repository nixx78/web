package lv.nixx.poc.graphql.domain;

public interface Account {
    long getId();
    String getName();
    Balance getBalance();
}
