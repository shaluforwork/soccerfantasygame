package com.soccerfantasy.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
//@PropertySource("classpath:/test.properties")
@ComponentScan(basePackages="com.soccerfantasy.app")
public class TestConfig {

}
