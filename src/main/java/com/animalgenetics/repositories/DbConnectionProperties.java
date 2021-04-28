package com.animalgenetics.repositories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DbConnectionProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private int minIdle;
    private int maximumPoolSize;
    private long maxLifetime;
    private String validationSql;
}
