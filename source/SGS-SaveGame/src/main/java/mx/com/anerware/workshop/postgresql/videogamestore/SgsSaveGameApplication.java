package mx.com.anerware.workshop.postgresql.videogamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SgsSaveGameApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SgsSaveGameApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder
			builder) {
		return builder.sources(SgsSaveGameApplication.class);
	}
}
