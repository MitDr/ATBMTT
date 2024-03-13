package com.notdemo.nodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NodemoApplication {

	public static void main(String[] args) {

		//ApplicationContext context = SpringApplication.run(NodemoApplication.class, args);
		SpringApplication app = new SpringApplication(NodemoApplication.class);
		app.setAdditionalProfiles("CeasarCipher");	//Nhập tên bean của loại mã hóa muốn sử dụng
		ApplicationContext context = app.run(args);

		EncryptionController encryptionController = context.getBean(EncryptionController.class);
		String plainText = "WHENINROMEDOASTHER"; // Nhập cipherText mã hóa
		String key = "4";	//Nhập key

		//Mã hóa plainText
		String cipherText = encryptionController.encrypt(plainText, key);
		System.out.println("Cypher text:"  + cipherText);

		//Giải mã cipherText
		String decryptedText = encryptionController.decrypt(cipherText, key);
		System.out.println("Decode text:" + decryptedText);

	}
}
