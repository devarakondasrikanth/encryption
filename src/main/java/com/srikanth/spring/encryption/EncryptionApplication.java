package com.srikanth.spring.encryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootApplication
public class EncryptionApplication {	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EncryptionApplication.class, args);
		
	}
	
}
