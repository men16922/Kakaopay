package com.bm.kakaopay.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 서비스에 필요한 빈 설정
 */
@Slf4j
@Configuration
public class ServiceConfigure {

    @Primary
    @Bean(name="dataSource")
    @Qualifier("dataSource")
    public DataSource dataSource(DataSourceProperties properties) {
        DataSourceBuilder<?> factory = DataSourceBuilder
                .create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword());

        log.info("JDBC DRIVER :" + properties.getDriverClassName());
        HikariDataSource dataSource = (HikariDataSource) factory.build();
        dataSource.setPoolName("H2_DB");
        dataSource.setMinimumIdle(1);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }


}