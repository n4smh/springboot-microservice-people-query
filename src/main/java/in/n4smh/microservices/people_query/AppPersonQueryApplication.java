package in.n4smh.microservices.people_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices")
public class AppPersonQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppPersonQueryApplication.class, args);
	}

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("People Query API")
				.description("APIs to fetch people related details").version("v0.0.1"));
	}

}
