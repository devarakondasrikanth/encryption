package com.srikanth.spring.encryption;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.codec.Base64;
import java.security.SecureRandom;


public class StandAlone {
	
	public static void main(String[] args) throws Exception {
    
        String clean = "This is a secret text";        
        String encrypted = encrypt(clean);
        System.out.println("Encrypted text is "+new String(encrypted));
        String decrypted = decrypt(encrypted);
        System.out.println("Dencrypted text is "+decrypted);
    }

    public static String encrypt(String plainText) throws Exception {
        byte[] clean = plainText.getBytes();
        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = (SecretKeySpec) CryptoUtility.getKey();
        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);
        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
        //Base64 Encoding
        return new String(Base64.encode(encryptedIVAndText));
    }

    public static String decrypt(String encryptedText) throws Exception {
        int ivSize = 16;
        //Base64 Decoding
        byte[] encryptedIvTextBytes = Base64.decode(encryptedText.getBytes());
        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);
        SecretKeySpec secretKeySpec = (SecretKeySpec) CryptoUtility.getKey();
        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}
