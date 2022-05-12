package com.soccerfantasy.app.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfiguration {

	@Bean
    @Profile({ "dev", "test" })
	@ConfigurationProperties("spring.datasource.fantasysoccer")
	public DataSourceProperties fantasySoccerDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
    @Profile({ "dev", "test" })
	@ConfigurationProperties("spring.datasource.fantasysoccer.hikari")
	public DataSource fantasySoccerDataSource() {
		return fantasySoccerDataSourceProperties().initializeDataSourceBuilder().build();
	}
}