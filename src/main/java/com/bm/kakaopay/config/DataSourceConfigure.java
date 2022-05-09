package com.bm.kakaopay.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory"
        , transactionManagerRef="configTransactionManager"
        , basePackages={"com.bm.kakaopay.repository"})
public class DataSourceConfigure {

    @Autowired
    @Qualifier("dataSource")
    private DataSource datasource;

    /**
     * @param builder
     * @return
     */
    @Primary
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean configEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(datasource)
                .packages("com.bm.kakaopay")
                .persistenceUnit("domain")
                .build();

    }


    /**
     * @param builder
     * @return
     */
    @Primary
    @Bean("configTransactionManager")
    public PlatformTransactionManager configTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(configEntityManagerFactory(builder).getObject());

    }
}

