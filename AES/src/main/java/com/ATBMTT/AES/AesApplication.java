package com.ATBMTT.AES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.math.BigInteger;

@SpringBootApplication
public class AesApplication {
	public static void main(String[] args) {

		//SpringApplication.run(AesApplication.class, args);
		SpringApplication app = new SpringApplication(AesApplication.class);
		ApplicationContext context = app.run(args);

		AESController controller = context.getBean(AESController.class);
//		String key =  "2b7e151628aed2a6abf7158809cf4f3c";
//		String plainText ="3243F6A8885A308D313198A2E0370734";
		String key = "C281B1763B140EF7AB12EB2745F1F59F";
		String plainText = "B104AADD3AC293DF787EFD2CF8065925";
		String[] extendKey = controller.extentKey(key);
		System.out.println();
		System.out.println(controller.encrypt(plainText, extendKey));
	}
}

