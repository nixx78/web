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
public class AlphaDBConfig extends AbstractDBConfig {

    private static final String prefix = "alpha";

    @Bean(name = prefix + "DataSource")
    @ConfigurationProperties(prefix = "db." + prefix)
    @Override
    public DataSource createSource() {
        return super.createSource();
    }

    @Bean(name = prefix + "EntityManagerFactory")
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(prefix + "DataSource") DataSource dataSource,
            @Value("${db." + prefix + ".init-file:#{null}}") String initFile,
            @Value("${hibernate.hbm2ddl.auto}") String ddlAuto,
            @Value("${db." + prefix + ".packages.to.scan}") String packagesToScan

    ) throws SQLException {
        return super.entityManagerFactory(dataSource, initFile, ddlAuto, packagesToScan);
    }

    @Bean(name = prefix + "TransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier(prefix + "EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
