package com.ATBMTT.Modulo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ATBMTT.Modulo")
public class ModuloApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ModuloApplication.class);
		app.setAdditionalProfiles("chineseRemainderTheorem");
		ApplicationContext context = app.run(args);

		ModuloController moduloController = context.getBean(ModuloController.class);

		System.out.println(moduloController.solve());
		//Exponentitaiion
		//System.out.println(moduloController.solveRecurr(499,6337, 6337));
	}

}
