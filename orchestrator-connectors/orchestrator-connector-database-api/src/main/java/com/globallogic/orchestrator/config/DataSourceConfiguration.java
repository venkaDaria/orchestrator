package com.globallogic.orchestrator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.login"));
        dataSource.setPassword(env.getProperty("db.password"));

        LOG.debug("DataSource was created");

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource dataSource) {
        LOG.debug("JdbcTemplate was created");
        return new JdbcTemplate(dataSource);
    }
}
