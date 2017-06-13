package com.srikanth.spring.encryption;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.Assert;

public class CryptoUtilityTest {
	
	@Test
	public void getKeyTest(){
		try {
			Assert.assertNotNull(CryptoUtility.getKey());
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
