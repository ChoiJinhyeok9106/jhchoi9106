package kr.co.codefarm.svcm.commons.utils.cipher;

import org.apache.commons.codec.CharEncoding;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES128Util {
	
	private static volatile AES128Util _INSTANCE;
	
	private static String _SECRET_KEY = "www.Codefarm.co.kr";
	private static byte[] _IV_128KEY  = new byte[16]; 

	public static AES128Util getInstance() {
		if (_INSTANCE == null) {
			synchronized (AES128Util.class) {
				if (_INSTANCE == null) {
					_INSTANCE = new AES128Util();
				}
			}
		}
		
		return _INSTANCE;
	}
	
	public static String enc(String plainText) {
		String encryptedStr = "";
		
		try {
			System.arraycopy(_SECRET_KEY.getBytes(CharEncoding.UTF_8), 0, _IV_128KEY, 0, 16);
			
			SecretKey secretKey = new SecretKeySpec(_IV_128KEY, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(_IV_128KEY));
		
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(CharEncoding.UTF_8));			
			encryptedStr = new String(Base64.getUrlEncoder().encodeToString(encryptedBytes));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encryptedStr;
	}
	
	public static String dec(String cipherText) {
		String decryptedStr = "";
		
		try {
			System.arraycopy(_SECRET_KEY.getBytes(CharEncoding.UTF_8), 0, _IV_128KEY, 0, 16);
			
			SecretKey secretKey = new SecretKeySpec(_IV_128KEY, "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");			
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(_IV_128KEY));
		
			byte[] decryptedBytes = Base64.getUrlDecoder().decode(cipherText.getBytes());
			decryptedStr = new String(cipher.doFinal(decryptedBytes), CharEncoding.UTF_8);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return decryptedStr;
	}

}
