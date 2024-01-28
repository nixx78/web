package lv.nixx.poc.common.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class BetaDBConfig extends AbstractDBConfig {

    private static final String prefix = "beta";

    @Bean(name = prefix + "DataSource")
    @ConfigurationProperties(prefix = "spring.datasource." + prefix)
    @Override
    public DataSource createSource() {
        return super.createSource();
    }

    @Bean(name = prefix + "EntityManagerFactory")
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(prefix + "DataSource") DataSource dataSource,
            @Value("${spring.datasource." + prefix + ".init-file:#{null}}") String initFile,
            @Value("${hibernate.hbm2ddl.auto}") String ddlAuto,
            @Value("${spring.datasource." + prefix + ".packages.to.scan}") String packagesToScan

    ) throws SQLException {
        return super.entityManagerFactory(dataSource, initFile, ddlAuto, packagesToScan);
    }

    @Bean(name = prefix + "TransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier(prefix + "EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
