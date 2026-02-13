package in.n4smh.microservices.person_query;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.jdbc.health.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices")
public class PersonQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonQueryApplication.class, args);
	}

	@Bean
	OpenAPI springShopOpenAPI(BuildProperties buildProperties) {
		return new OpenAPI().info(new Info().title(buildProperties.getName())
				.description(buildProperties.get("description")).version(buildProperties.getVersion()));
	}

	@Bean
	public DataSourceHealthIndicator dataSourceHealthIndicator(DataSource dataSource) {
		log.info("dataSourceHealthIndicator");
		return new DataSourceHealthIndicator(dataSource);
	}

	@Bean
	public HealthIndicator dbHealthIndicator(DataSource dataSource) {
		log.info("dbHealthIndicator");
		return new DataSourceHealthIndicator(dataSource);
	}
}
