package com.jotacode.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // Toca hacerlo antes de que se haga el escaneo de los componentes
@EnableJpaAuditing // Es para que se pueda usar la auditoria de los campos de la base de datos
public class PizzeriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApiApplication.class, args);
	}

}
