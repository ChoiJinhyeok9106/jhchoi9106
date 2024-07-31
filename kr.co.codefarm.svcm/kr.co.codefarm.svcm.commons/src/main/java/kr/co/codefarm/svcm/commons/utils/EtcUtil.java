package kr.co.codefarm.svcm.commons.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class EtcUtil {
	
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_CLIENT_IP"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getRemoteAddr(); 
		}
		
		return ip;
	}
	
	public static String getRandomString(int len) {
		try {
			char[] charSet = new char[] {
					'0', '1', '2', '3', '4', '5', '6', '7', '8', 
					'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 
					'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
					'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
					'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
					'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ')', 
					'!', '@', '#', '$', '%', '^', '&', '*', '('
			};
			
			int pos                     = 0;
			StringBuilder stringBuilder = new StringBuilder();
			Random rand = SecureRandom.getInstanceStrong();
			for (int idx = 0; idx < len; idx++) {
				pos = rand.nextInt(charSet.length);
				stringBuilder.append(charSet[pos]);
			}
			
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static String getRandomAlphaNumber(int len) {
		try {
			char[] charSet = new char[] {
					'0', '1', '2', '3', '4', '5', '6', '7', '8', 
					'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 
					'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
					'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
					'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
					'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
			};
			
			int pos                     = 0;
			StringBuilder stringBuilder = new StringBuilder();
			Random rand = SecureRandom.getInstanceStrong();
			for (int idx = 0; idx < len; idx++) {
				pos = rand.nextInt(charSet.length);
				stringBuilder.append(charSet[pos]);
			}
			
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
