package kr.co.codefarm.svcm.commons.utils;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.util.Base64Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

/**
 * jwt util
 *
 * Modification Information
 * 2023 05 12 pyk 최초작성
 */

public class JwtUtil {

	private static String _KEY = "6v8CAZ2a5A37W9m80M8b93TPBFRZ5FQL3UBX6UX+zjc=";

	public static void setKey(String key) {
		_KEY = key;
	}

	public static String getToken(Duration durExp, String userId, boolean isSetIssuedAt) {
		SecretKey secretKey = readSecret(_KEY);
		Map<String, Object> payload = new HashMap<>();
		payload.put("usr_id",userId);
		payload.put("login_val", String.valueOf(System.currentTimeMillis())+"-"+EtcUtil.getRandomAlphaNumber(10));

		Date now = new Date();
		Claims claims = Jwts.claims();
		claims.putAll(payload);
		if(isSetIssuedAt) {
			claims.setIssuedAt(now);
		}
		claims.setExpiration(new Date(now.getTime() + durExp.toMillis()));
		JwtBuilder jb = Jwts.builder();
		jb.setClaims(claims);
		jb.signWith(secretKey, SignatureAlgorithm.HS256);
		return jb.compact();
	}

	public static String getToken(String key, Duration durExp, String userId, boolean isSetIssuedAt) {
		SecretKey secretKey = readSecret(key);
		Map<String, Object> payload = new HashMap<>();
		payload.put("usr_id",userId);
		payload.put("login_val", String.valueOf(System.currentTimeMillis())+"-"+EtcUtil.getRandomAlphaNumber(10));

		Date now = new Date();
		Claims claims = Jwts.claims();
		claims.putAll(payload);
		if(isSetIssuedAt) {
			claims.setIssuedAt(now);
		}
		claims.setExpiration(new Date(now.getTime() + durExp.toMillis()));
		JwtBuilder jb = Jwts.builder();
		jb.setClaims(claims);
		jb.signWith(secretKey, SignatureAlgorithm.HS256);
		return jb.compact();
	}

	public static String getToken(SecretKey secretkey, Duration durExp, String userId, boolean isSetIssuedAt) {
		Map<String, Object> payload = new HashMap<>();
		payload.put("usr_id",userId);
		payload.put("login_val", String.valueOf(System.currentTimeMillis())+"-"+EtcUtil.getRandomAlphaNumber(10));

		Date now = new Date();
		Claims claims = Jwts.claims();
		claims.putAll(payload);
		if(isSetIssuedAt) {
			claims.setIssuedAt(now);
		}
		claims.setExpiration(new Date(now.getTime() + durExp.toMillis()));
		JwtBuilder jb = Jwts.builder();
		jb.setClaims(claims);
		jb.signWith(secretkey, SignatureAlgorithm.HS256);
		return jb.compact();
	}

	/**
	 * 토큰 검증
	 */
	public static Jws<Claims> verifyToken(SecretKey secKey, String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(secKey)
			.build()
			.parseClaimsJws(token);
	}


	public static Jws<Claims> verifyToken(String secKey, String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(readSecret(secKey))
			.build()
			.parseClaimsJws(token);
	}


	public static Jws<Claims> verifyToken(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(readSecret(_KEY))
			.build()
			.parseClaimsJws(token);
	}

	public static SecretKey generateSecretKey() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	private static SecretKey readSecret(String secretKey) throws IOException {
//		String base64 = FileUtils.readFileToString(secKeyFile, StandardCharsets.UTF_8.toString());
//		String base64 = FileUtils.readFileToString(secKeyFile, "UTF-8");
		byte[] byteArr = Base64Utils.decodeFromString(secretKey);
		return Keys.hmacShaKeyFor(byteArr);
	}


	public static boolean timeCheck(long checkTime, long baseTime) {
		// 최초 발급 경과 시간
		long dupTime = checkTime - new Date().getTime();
		return dupTime < baseTime;
	}

	public static String makeToken(Map<String, ?> payload, Duration expDuration) {
		SecretKey secretKey = readSecret(_KEY);
		String token = Jwts
			.builder()
			.setClaims(payload)
			.setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime() + expDuration.toMillis()))
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
		return token;
	}

}