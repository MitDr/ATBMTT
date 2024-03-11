package com.notdemo.nodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NodemoApplication {

	public static void main(String[] args) {

		//ApplicationContext context = SpringApplication.run(NodemoApplication.class, args);
		SpringApplication app = new SpringApplication(NodemoApplication.class);
		app.setAdditionalProfiles("vigenereAutoKey");
		ApplicationContext context = app.run(args);

		EncryptionController encryptionController = context.getBean(EncryptionController.class);
		String plainText = "STILLWATERSR";
		String key = "SAVEFORA";
		String cipherText = encryptionController.encrypt(plainText, key);
		System.out.println("Cypher text:"  + cipherText);
		String decryptedText = encryptionController.decrypt(cipherText, key);
		System.out.println("Decode text:" + decryptedText);

	}
}
