package br.com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AddressRegisterApplication{

	public static void main(String[] args) {
		SpringApplication.run(AddressRegisterApplication.class, args);
	}
}
