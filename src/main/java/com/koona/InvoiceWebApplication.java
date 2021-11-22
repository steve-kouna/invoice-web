package com.koona;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvoiceWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(InvoiceWebApplication.class, args);
	}

	@Bean
	public Hibernate5Module dataTypeHibernateModule() {
		return new Hibernate5Module();
	}

}
