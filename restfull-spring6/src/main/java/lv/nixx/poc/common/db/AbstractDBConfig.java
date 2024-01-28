package lv.nixx.poc.common.db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static lv.nixx.poc.common.db.DataSourceInit.initDB;

public abstract class AbstractDBConfig {
    public DataSource createSource() {
        return DataSourceBuilder.create().build();
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       String initFile,
                                                                       String ddlAuto,
                                                                       String packagesToScan
    ) throws SQLException {

        initDB(dataSource, initFile);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packagesToScan);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaPropertyMap(properties);

        return em;
    }

    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
