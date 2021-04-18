package com.brasilservicos.demo;

import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles( "test" )
@SpringBootTest( classes = BrasilServicosApplication.class )
@RunWith( SpringRunner.class )
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
@ContextConfiguration(initializers = { BrasilServicosApplicationTests.DockerPostgreDataSourceInitializer.class })
@Testcontainers
@TestPropertySource( locations = "classpath:application-test.properties" )
public class BrasilServicosApplicationTests {
	public static PostgreSQLContainer<?> postgreDBContainer = new PostgreSQLContainer<>("postgres:11.1");

	static {
		postgreDBContainer.start();
	}

	@Test
	void contextLoads() {
	}

	public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
			TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
					applicationContext,
					System.setProperty("SPRING_DATASOURCE_URL", postgreDBContainer.getJdbcUrl()),
					System.setProperty("SPRING_DATASOURCE_USERNAME", postgreDBContainer.getUsername()),
					System.setProperty("SPRING_DATASOURCE_PASSWORD", postgreDBContainer.getPassword()));
		}
	}

}
