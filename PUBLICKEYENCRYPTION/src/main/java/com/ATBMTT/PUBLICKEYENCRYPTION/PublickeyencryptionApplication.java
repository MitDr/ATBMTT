package com.ATBMTT.PUBLICKEYENCRYPTION;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PublickeyencryptionApplication {

	public static void main(String[] args) {

		//SpringApplication.run(PublickeyencryptionApplication.class, args);
		ApplicationContext context = SpringApplication.run(PublickeyencryptionApplication.class, args);
  		PUBLICKEYENCRYPTIONController controller = context.getBean(PUBLICKEYENCRYPTIONController.class);
  		//controller.DifficultyHellman(7879, 3, 524, 214); //Chuẩn

//		controller.RSA_1(37,53,47,41);
//  		controller.RSA_1();
  		//controller.RSA_2(37,53,47,41);
// 		controller.ElGamal(809,3,57,150,270);
 		//controller.DSA(23,11,6,8,9,10);
	}

}
