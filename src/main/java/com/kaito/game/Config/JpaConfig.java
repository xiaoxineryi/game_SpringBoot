package com.kaito.game.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@Configuration
@EntityScan(basePackages = "com.kaito.game.dao.entity")
@EnableJpaRepositories(basePackages = "com.kaito.game.dao.repository")
public class JpaConfig {
    
}
