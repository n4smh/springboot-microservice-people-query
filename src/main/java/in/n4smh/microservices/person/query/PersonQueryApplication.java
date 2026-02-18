package in.n4smh.microservices.person.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices.person")
public class PersonQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonQueryApplication.class, args);
	}

}
