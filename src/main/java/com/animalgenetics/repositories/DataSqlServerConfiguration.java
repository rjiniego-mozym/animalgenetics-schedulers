package com.animalgenetics.repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(transactionManagerRef = "sqlServerTransactionManager", entityManagerFactoryRef = "sqlServerEntityManagerFactory")
@Profile("!mock")
public class DataSqlServerConfiguration {

    public static final String SQL_SERVER_DATA_SOURCE = "sqlServerDataSource";

    @Autowired
    private SqlServerDbConnectionProperties sqlServerDbConnectionProperties;

    @Bean
    @Profile(value = { "!frenemy" })
    public DataSource sqlServerDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(sqlServerDbConnectionProperties.getUrl());
        dataSource.setUsername(sqlServerDbConnectionProperties.getUsername());
        dataSource.setPassword(sqlServerDbConnectionProperties.getPassword());
        dataSource.setMinimumIdle(sqlServerDbConnectionProperties.getMinIdle());
        dataSource.setDriverClassName(sqlServerDbConnectionProperties.getDriverClassName());
        dataSource.setMaxLifetime(sqlServerDbConnectionProperties.getMaxLifetime());
        dataSource.setMaximumPoolSize(sqlServerDbConnectionProperties.getMaximumPoolSize());
        dataSource.setPoolName(SQL_SERVER_DATA_SOURCE);
        dataSource.setRegisterMbeans(true);
        dataSource.setAllowPoolSuspension(true);
        return dataSource;
    }

    @Bean
    @Profile(value = { "frenemy" })
    public DataSource mockSqlServerDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    @Profile(value = { "!frenemy" })
    public LocalContainerEntityManagerFactoryBean sqlServerEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(sqlServerDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        factoryBean.setJpaProperties(properties);
        updateEntityManagerFactoryBean(factoryBean);
        return factoryBean;
    }

    @Bean (name="sqlServerEntityManagerFactory")
    @Profile(value = { "frenemy" })
    public LocalContainerEntityManagerFactoryBean mockSqlServerEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(mockSqlServerDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        factoryBean.setJpaProperties(properties);
        updateEntityManagerFactoryBean(factoryBean);
        return factoryBean;
    }

    @Bean
    @Profile(value = { "!frenemy" })
    public PlatformTransactionManager sqlServerTransactionManager() {
        return new JpaTransactionManager(sqlServerEntityManagerFactory().getObject());
    }

    @Bean (name="sqlServerTransactionManager")
    @Profile(value = { "frenemy" })
    public PlatformTransactionManager mockSqlServerTransactionManager() {
        return new JpaTransactionManager(mockSqlServerEntityManagerFactory().getObject());
    }

    private void updateEntityManagerFactoryBean(LocalContainerEntityManagerFactoryBean factoryBean) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(this.getClass().getPackage().getName());
        factoryBean.setPersistenceUnitName(SQL_SERVER_DATA_SOURCE);
    }
}
