package com.srikanth.spring.encryption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBeanTest {	
	
	private final Log log = LogFactory.getLog(getClass());
	
	//This bean read the encrypted text from the properties file and writes it back to console
	@Value("${variable.secret-var}")
	private String secValue;
	
	@Value("${inputString}")
	private String orgText;
	
	//BCryptPasswordEncoder class in spring will not have the decode method
	//Added some additional functionality to actually decode the password and return it back
	@Autowired
    BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	TextEncryptor textEncrypt;
	
	@Test
	public void encryptTest(){		
		Assert.notNull(bcryptEncoder.encode(orgText),"Encrypting the text value");
		log.info("Encrypted the text value "+bcryptEncoder.encode(orgText));
	}
	
	@Test
	public void decryptTest(){
		String encryptedText = bcryptEncoder.encode(orgText);
		Assert.isTrue(bcryptEncoder.matches(orgText, encryptedText),"Encrypted text is decrypted successfully");
		log.info("Successfully decrypted the encrypted value");
	}
	
	
}
