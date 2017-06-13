package com.srikanth.spring.encryption;

import org.junit.Test;
import org.junit.Assert;

public class BouncycastleCryptoTest {
	
	private String inputString ="No Body can see me now";
	private BouncycastleCrypto bc = new BouncycastleCryptoImpl();
	@Test
	public void encryptionTest(){
		try {
			Assert.assertNotNull(bc.encrypt(inputString));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void decryptionTest(){
		String encryptedStr;
		try {
			encryptedStr = bc.encrypt(inputString);
			Assert.assertEquals(inputString, bc.decrypt(encryptedStr));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
}
