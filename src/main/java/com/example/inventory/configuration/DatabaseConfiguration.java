package com.example.inventory.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.example.inventory.repository")
@Slf4j
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public DatabaseConfiguration(@Value("${db.inventory.host}") String host, @Value("${db.inventory.port}") int port,
                                 @Value("${db.inventory.password}") String password,
                                 @Value("${db.inventory.database}") String database, @Value("${db.inventory.username}") String username) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }


    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        log.debug("Connecting to database with host {} database {} port {} user {}",
                host, database, port, username);
        String url = String.format("r2dbc:postgresql://%s:%d/%s", host, port, database);
        return ConnectionFactoryBuilder.withUrl(url)
                .username(username)
                .password(password)
                .configure(opts -> opts.option(ConnectionFactoryOptions.DRIVER, "postgresql"))
                .build();
    }

    @Bean
    public ConnectionFactoryInitializer initializer() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory());
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
}
