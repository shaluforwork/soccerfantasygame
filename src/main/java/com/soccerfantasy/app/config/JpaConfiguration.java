package com.soccerfantasy.app.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories (
  basePackages = {"com.soccerfantasy.app"},
  entityManagerFactoryRef = "fantasySoccerEntityManagerFactory",
  transactionManagerRef = "fantasySoccerTransactionManager"
)
public class JpaConfiguration {

	@Bean
    public LocalContainerEntityManagerFactoryBean fantasySoccerEntityManagerFactory (
      @Qualifier("fantasySoccerDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages("com.soccerfantasy.app")
          .build();
    }

    @Bean
    public PlatformTransactionManager fantasySoccerTransactionManager(
      @Qualifier("fantasySoccerEntityManagerFactory") LocalContainerEntityManagerFactoryBean fantasySoccerEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(fantasySoccerEntityManagerFactory.getObject()));
    }
}
