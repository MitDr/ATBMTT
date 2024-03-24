package com.chap3.DES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DesApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DesApplication.class);
		ApplicationContext context = app.run(args);

		DESController des = context.getBean(DESController.class);
		String[] keys = des.generateKey("17FFCC5ADBF3EA87");

		String plainText = "E36B4C92DE9AD726";
		String encryptedText = des.encrypt(keys, plainText);
		System.out.println("Encrypted Text: " + encryptedText);
	}

}
