package lv.nixx.poc.spring6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "lv.nixx.poc.spring6.repository.beta",
        entityManagerFactoryRef = "betaEntityManagerFactory",
        transactionManagerRef = "betaTransactionManager"
)
public class BetaRepoConfig {
}
