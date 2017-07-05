package com.globallogic.orchestrator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration  {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("orchestrator.connector.database.driverClassName"));
        dataSource.setUrl(env.getProperty("orchestrator.connector.database.url"));
        dataSource.setUsername(env.getProperty("orchestrator.connector.database.login"));
        dataSource.setPassword(env.getProperty("orchestrator.connector.database.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
