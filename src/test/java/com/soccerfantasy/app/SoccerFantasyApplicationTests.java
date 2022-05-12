package com.soccerfantasy.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class SoccerFantasyApplicationTests {

	@Test
	void contextLoads() {
	}

}
