package com.example.inventory.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@Configuration
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
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(host)
                .database(database)
                .port(port)
                .password(password)
                .username(username).build());
    }
}
