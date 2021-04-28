package com.animalgenetics.repositories;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sql.server.connection")
@Component
public class SqlServerDbConnectionProperties extends DbConnectionProperties {
}
