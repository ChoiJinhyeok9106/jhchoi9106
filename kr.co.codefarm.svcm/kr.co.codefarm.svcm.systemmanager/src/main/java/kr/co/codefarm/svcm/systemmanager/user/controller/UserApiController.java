package kr.co.codefarm.svcm.systemmanager.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.login.service.ScvmLoginService;
import kr.co.codefarm.svcm.commons.login.service.ScvmUserDetailsServiceImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.map.ServiceMap;
import kr.co.codefarm.svcm.commons.service.mail.EmailService;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import kr.co.codefarm.svcm.commons.utils.cipher.AES128Util;
import kr.co.codefarm.svcm.commons.utils.cipher.RSAUtil;
import kr.co.codefarm.svcm.systemmanager.user.service.UserApiService;

/**
 * @author DGCHOI
 *
 */
@RestController
@RequestMapping(value = "/api/system-manager/user")
public class UserApiController {

	@Value("${spring.profiles.active}")
	private String _PROFILE;
	
	@Value("${use-sso}")
	private boolean ssoEnabled;

	@Autowired
	private UserApiService userApiService;
	
	@Autowired
	private ScvmLoginService loginService;

	@Autowired
	private ScvmUserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/idDouleChk")
	public Object getIdDouleChk(ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		Object user = userApiService.getIdDoubleChk(pUserId);
		return user;
	}
	
	@GetMapping("/corp")
	public List<Map<String, Object>> getCorpList(ParameterMap parameterMap) {
		List<Map<String, Object>> corpList = userApiService.getCorpList(parameterMap.getMap());
		return corpList;
	}
	

	@Transactional
	@PostMapping("/systemUserJoin")
	public Map<String, Object> insertSystemUser(HttpServletRequest request, ParameterMap parameterMap) throws NoSuchAlgorithmException, MessagingException, IOException {
		PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
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
//		Random rand = SecureRandom.getInstanceStrong();
//		for (int idx = 0; idx < 20; idx++) {
//			pos = rand.nextInt(charSet.length);
//			stringBuilder.append(charSet[pos]);
//		}
		for (int idx = 0; idx < 20; idx++) {
			pos = (int) (charSet.length * Math.random());
			stringBuilder.append(charSet[pos]);
		}
		String key = stringBuilder.toString();
		parameterMap.put("AUTH_KEY", key);
		parameterMap.put("PWD", RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("PWD").toString()));
		parameterMap.put("CORP_BIZ_NO", parameterMap.get("CORP_BIZ_NO").toString().replace("-", ""));
		parameterMap.put("CORP_TEL_NO", parameterMap.get("CORP_TEL_NO").toString().replace("-", ""));
		parameterMap.put("TEL_NO", parameterMap.get("TEL_NO").toString().replace("-", ""));
		parameterMap.put("HP_NO", parameterMap.get("HP_NO").toString().replace("-", ""));
		if(userApiService.getCorpDbCheck(parameterMap.getMap()) < 1) {
			Map<String, Object> retMap = userApiService.insertUserApply(parameterMap.getMap());
			int	userRow = userApiService.insertUserAgreeRes(parameterMap.getMap());
			
			if(retMap.containsKey("CORP_ID")) {
				parameterMap.put("CORP_ID", retMap.get("CORP_ID"));
				String url = (("01".equals(ServiceMap.get("SMN").get("PROTOCOL_GB")) ? "http":"https") + "://" + ServiceMap.get("SMN").get("DOMAIN") + ":" + ServiceMap.get("SMN").get("PORT"));
				String[] to = new String[1];
				to[0] = parameterMap.get("USER_ID").toString();
				String from = "no-reply@codefarm.co.kr";
				String subject = "[메일인증] CDF PLATFORM";

		        ClassPathResource resource = new ClassPathResource("email/email_application.html");
		        InputStreamReader input =  new InputStreamReader(resource.getInputStream());
		        BufferedReader br = new BufferedReader(input);
		        
		        StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				result = result.replaceAll("##IMAGE_URL##", url + "/images/login/");
				result = result.replaceAll("##AUTH_URL##", url + "/html/main/auth.html");
				result = result.replaceAll("##AUTH_KEY##", key);
		        input.close();
		        br.close();
		        String content = result;
		        System.out.println(content);
//				String content = "<h2>회원가입 신청진행중입니다.</h2><br><h4>인증키 : " + key + "</h4><br>아래링크를 통해 인증키를 삽입해주세요.<br><a href=\"" + url + "\">메일 본인인증하러가기</a>";
				emailService.sendMail(from, to, subject, content);
				if(_PROFILE.equals("local")) {
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					System.out.println("@ KEY : "+ key +" @");
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				}
			}
			return retMap;
		}else {
			Map<String, Object> retMap = new HashMap<>();
			retMap.put("MSG","DUP");
			return retMap;
		}
	}
	
	@PostMapping("/loginValidCheck")
	public Object loginValidCheck(HttpServletRequest request, ParameterMap parameterMap) throws MessagingException {
		PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
		Map<String, Object> requestMap = new HashMap<>();
		if (privateKey == null) {
			return "암호화 모듈 오류입니다.";
		}
		String id = RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("UID").toString());
		String pw = RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("UPW").toString());
		ScvmUserDetailsImpl user = (ScvmUserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(id, pw);
		if (user == null || user.getUserId() == null) {
			return "잘못된 로그인ID 또는 비밀번호 입니다.";
		}
		else if(Integer.parseInt(user.getLoginFailCnt()) >= 5) {
			return "로그인 5회 실패로 로그인이 제한되었습니다.\n[비밀번호 분실]에서 비밀번호를 재생성하시기 바랍니다.";
		}	
		else if (user != null && user.getUserId() != null && "N".equals(user.getPassword())) {
			Map<String, Object> param = new HashMap<>();
			param.put("USER_ID",   id);
			param.put("SYSTEM_IP", EtcUtil.getIP(request));
			param.put("ACCESS_YN", "N");
			loginService.setAccount(param);
			if(Integer.parseInt(user.getLoginFailCnt()) + 1 >= 5) {
				return "로그인 5회 실패로 로그인이 제한되었습니다.\n[비밀번호 분실]에서 비밀번호를 재생성하시기 바랍니다.";
			}else {
				return "잘못된 로그인ID 또는 비밀번호 입니다.";
			}
		}else {
			return "PASS";
		}
	}

	@PostMapping("/sendKey")
	public Object sendKey(HttpServletRequest request, ParameterMap parameterMap) throws MessagingException {
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("UID", parameterMap.get("uid").toString());
		try {
			if(userApiService.userCheck(requestMap) > 0) {
				char[] charSet = new char[] {
						'0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9'
				};
				int pos                     = 0;
				StringBuilder stringBuilder = new StringBuilder();
//				Random rand = SecureRandom.getInstanceStrong();
//				for (int idx = 0; idx < 6; idx++) {
//					pos = rand.nextInt(charSet.length);
//					stringBuilder.append(charSet[pos]);
//				}
				for (int idx = 0; idx < 6; idx++) {
					pos = (int) (charSet.length * Math.random());
					stringBuilder.append(charSet[pos]);
				}
				String key = stringBuilder.toString();
				String[] to = new String[1];
				to[0] = parameterMap.get("uid").toString();
				String from = "no-reply@codefarm.co.kr";
				String subject = "[로그인 2차인증] CDF PLATFORM";
				String content = "<h2>인증번호는 발송 후 3분 이내 입력시에만 유효합니다.</h2><br>인증번호 : <b>" + key + "</b>";
				emailService.sendMail(from, to, subject, content);
				if(_PROFILE.equals("local")) {
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					System.out.println("@ KEY : "+ key +" @");
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				}
				JSONObject ret = new JSONObject();
				ret.put("KEY", key);
				ret.put("EX", System.currentTimeMillis() + 1000*60*3);
				return AES128Util.enc(ret.toString());
			}else {
				return -1;
			}
		}catch(RuntimeException e) {
			System.out.println(e.getClass().getCanonicalName().toString().split("[.]")[e.getClass().getCanonicalName().toString().split("[.]").length-1] + " Occured");
			return -1;
		}
		
	}
	
	@Transactional
	@PostMapping("/userJoin")
	public Map<String, Object> insertUser(HttpServletRequest request, ParameterMap parameterMap) throws NoSuchAlgorithmException, MessagingException, IOException {
		PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");		
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
//		RHEL 시큐어랜덤오류발생
//		Random rand = SecureRandom.getInstanceStrong();
//		for (int idx = 0; idx < 20; idx++) {
//			pos = rand.nextInt(charSet.length);
//			stringBuilder.append(charSet[pos]);
//		}
		for (int idx = 0; idx < 20; idx++) {
			pos = (int) (charSet.length * Math.random());
			stringBuilder.append(charSet[pos]);
		}
		String key = stringBuilder.toString();
		parameterMap.put("AUTH_KEY", key);
		parameterMap.put("PWD", RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("PWD").toString()));
		parameterMap.put("TEL_NO", parameterMap.get("TEL_NO").toString().replace("-", ""));
		parameterMap.put("HP_NO", parameterMap.get("HP_NO").toString().replace("-", ""));
		Map<String, Object> retMap = userApiService.insertCorpUserApply(parameterMap.getMap());
		int	userRow = userApiService.insertUserAgreeRes(parameterMap.getMap());
		
		if(retMap.containsKey("CORP_ID")) {
			parameterMap.put("CORP_ID", retMap.get("CORP_ID"));
			String url = (("01".equals(ServiceMap.get("SMN").get("PROTOCOL_GB")) ? "http":"https") + "://" + ServiceMap.get("SMN").get("DOMAIN") + ":" + ServiceMap.get("SMN").get("PORT"));
			String[] to = new String[1];
			to[0] = parameterMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[메일인증] CDF PLATFORM";

	        ClassPathResource resource = new ClassPathResource("email/email_application.html");
	        InputStreamReader input =  new InputStreamReader(resource.getInputStream());
	        BufferedReader br = new BufferedReader(input);
	        
	        StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String result = sb.toString();
			result = result.replaceAll("##IMAGE_URL##", url + "/images/login/");
			result = result.replaceAll("##AUTH_URL##", url + "/html/main/auth.html");
			result = result.replaceAll("##AUTH_KEY##", key);
	        input.close();
	        br.close();
	        String content = result;
	        System.out.println(content);
//			String content = "<h2>회원가입 신청진행중입니다.</h2><br><h4>인증키 : " + key + "</h4><br>아래링크를 통해 인증키를 삽입해주세요.<br><a href=\"" + url + "\">메일 본인인증하러가기</a>";
			emailService.sendMail(from, to, subject, content);
			if(_PROFILE.equals("local")) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("@ KEY : "+ key +" @");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
			if(_PROFILE.equals("dev")) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("@ KEY : "+ key +" @");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
		}
		return retMap;
	}
	
	/* 사용자 갱신 */
	@PutMapping("")
	public Object updateUser(ParameterMap parameterMap) {
		int insCnt = userApiService.updateUser(parameterMap.getMap());
		return insCnt;
	}
	
	/* 아이디 찾기 */
	@GetMapping("/find/id")
	public Object findId(ParameterMap parameterMap) {
		return userApiService.findId(parameterMap.getMap());
	}

	/* 비밀번호 찾기 */
	@PostMapping("/find/pw")
	public Object findPw(HttpServletRequest request, ParameterMap parameterMap) throws MessagingException, NoSuchAlgorithmException, IOException {
		PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");		
		Map<String, Object> retMap = new HashMap<>();

        retMap = (Map<String, Object>) userApiService.findPw(parameterMap.getMap());
        
        if(retMap != null) {
    		retMap.put("PWD", RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("PWD").toString()));
    		retMap.put("SPWD", RSAUtil.decryptionUsingModulusAndExponent(privateKey, parameterMap.get("SPWD").toString()));
        	userApiService.updatePw(retMap);
			String url = (("01".equals(ServiceMap.get("SMN").get("PROTOCOL_GB")) ? "http":"https") + "://" + ServiceMap.get("SMN").get("DOMAIN") + ":" + ServiceMap.get("SMN").get("PORT"));
			String[] to = new String[1];
			to[0] = retMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[비밀번호찾기] CDF PLATFORM";

			String content = "<h2>비밀번호가 변경되었습니다..</h2><br><h4>변경된 임시비밀번호 : " + retMap.get("SPWD") + "</h4><br><a href=\"" + url + "\">로그인 하러가기</a>";
			emailService.sendMail(from, to, subject, content);

			if(_PROFILE.equals("local")) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("PWD : "+ retMap.get("SPWD"));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
        }

		return retMap;
	}

	/* 비밀번호 변경 */
	@GetMapping("/change/pw")
	public Object changePw(ParameterMap parameterMap) {
		parameterMap.put("SYSTEM_ID", "findpw");
		userApiService.changePw(parameterMap.getMap());
		return parameterMap.getMap();
	}

	/* 메일인증  */
	@PostMapping("/certified")
	public Map<String, Object> mailAuth(ParameterMap parameterMap) {
		Map<String, Object> returnMap = new HashMap<>();
		int updCnt = (int) userApiService.mailAuth(parameterMap.getMap());
		if(updCnt > 0){
			returnMap.put("MESSAGE","SUCCESS");
		}
		else {
			returnMap.put("MESSAGE","FAIL");
		}
		return returnMap;
	}

}
