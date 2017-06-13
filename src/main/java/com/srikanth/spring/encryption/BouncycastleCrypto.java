package com.srikanth.spring.encryption;


public interface BouncycastleCrypto {
	
	public String encrypt(String val) throws Exception;
	public String decrypt(String val) throws Exception;

}
