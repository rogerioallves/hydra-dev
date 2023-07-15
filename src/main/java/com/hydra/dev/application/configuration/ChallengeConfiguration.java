package com.hydra.dev.application.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.hydra.dev.domain.entity")
@EnableJpaRepositories("com.hydra.dev.infrastructure.repository")
public class ChallengeConfiguration {

}
