package com.example.summaryH2Sql;

import org.h2.tools.Script;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan("com.example.summaryH2Sql")
public class SummaryH2SqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummaryH2SqlApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductRepository repository) {
		return args -> {
			repository.save(new ProductEntity("Apple",new BigDecimal("3.5"), LocalDateTime.now()));
			repository.save(new ProductEntity("Banana", new BigDecimal("2.0"), LocalDateTime.now()));
			repository.save(new ProductEntity("Orange", new BigDecimal("4.0"), LocalDateTime.now()));
		};
	}

}
