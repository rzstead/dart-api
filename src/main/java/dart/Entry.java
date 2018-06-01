package dart;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages= {"controllers", "dart"})
@EntityScan(basePackages="models")
@EnableJpaRepositories(basePackages="repos")
public class Entry extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}
}
