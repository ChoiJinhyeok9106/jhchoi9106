package kr.co.codefarm.svcm.commons.utils.cipher;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class RSAUtil {
	
	private PrivateKey _privateKey;
	private PublicKey _publicKey;
	private KeyFactory _keyFactory;
	private int _keySize = 2048;
	
	public RSAUtil() {
		try {
			SecureRandom secureRandom         = new SecureRandom();
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(this._keySize, secureRandom);
			
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			
			this._keyFactory = KeyFactory.getInstance("RSA");
			this._privateKey = keyPair.getPrivate();
			this._publicKey  = keyPair.getPublic();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public PrivateKey getPrivateKey() {
		return this._privateKey;
	}
	
	public PublicKey getPublicKey() {
		return this._publicKey;
	}
	
	public String getModulus() {
		try {
			RSAPublicKeySpec rsaPublicKeySpec = (RSAPublicKeySpec) this._keyFactory.getKeySpec(this._publicKey, RSAPublicKeySpec.class);
			return rsaPublicKeySpec.getModulus().toString(16);
		} catch(InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPublicExponent() {
		try {
			RSAPublicKeySpec rsaPublicKeySpec = (RSAPublicKeySpec) this._keyFactory.getKeySpec(this._publicKey, RSAPublicKeySpec.class);
			return rsaPublicKeySpec.getPublicExponent().toString(16);
		} catch(InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String enc(String plainText) {
		String cipherText = "";
		
		try {
			byte[] plainBytes = plainText.getBytes(CharEncoding.UTF_8);
			
			if (plainBytes.length <= 245) {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, this._publicKey);
				
				cipherText = new String(Base64.encodeBase64(cipher.doFinal(plainBytes)));
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cipherText;
	}
	
	public static String decryptionUsingModulusAndExponent(PrivateKey privateKey, String cipherText) {
		String plainText      = "";
		
		if (privateKey != null) {
			if (cipherText != null && cipherText.length() % 2 == 0) {
				byte[] encryptedBytes = new byte[cipherText.length() / 2];
				
				for (int idx = 0; idx < cipherText.length(); idx += 2) {
					byte value = (byte) Integer.parseInt(cipherText.substring(idx, idx + 2), 16);
					encryptedBytes[(int) Math.floor(idx / 2)] = value;
				}
				
				try {
					Cipher cipher = Cipher.getInstance("RSA");
					cipher.init(Cipher.DECRYPT_MODE, privateKey);

					plainText = new String(cipher.doFinal(encryptedBytes), CharEncoding.UTF_8);
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return plainText;
	}
	
	public static String decryptionUsingPublicKey(PrivateKey privateKey, String cipherText) {
		String plainText      = "";
		
		if (privateKey != null) {
			if (cipherText != null) {
				try {
					Cipher cipher = Cipher.getInstance("RSA");
					cipher.init(Cipher.DECRYPT_MODE, privateKey);

					plainText = new String(cipher.doFinal(Base64.decodeBase64(cipherText.getBytes())), CharEncoding.UTF_8);
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return plainText;
	}

}
