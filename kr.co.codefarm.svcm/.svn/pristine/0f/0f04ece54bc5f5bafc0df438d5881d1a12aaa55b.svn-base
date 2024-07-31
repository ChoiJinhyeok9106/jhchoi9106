package kr.co.codefarm.svcm.systemmanager.user.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.map.ServiceMap;
import kr.co.codefarm.svcm.commons.service.mail.EmailService;
import kr.co.codefarm.svcm.commons.utils.cipher.AES128Util;
import kr.co.codefarm.svcm.systemmanager.user.service.UserService;

/**
 * @author ASJ
 *
 */
@RestController
@RequestMapping(value = "/system-manager/user")
public class UserController  {
	
	@Value("${spring.profiles.active}")
	private String _PROFILE;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	/* 사용자 조회 */
	@GetMapping("")
	public List<Map<String, Object>> getUserList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		List<Map<String, Object>> list = userService.getUserList(parameterMap.getMap());
		return list;
	}
	
	/* 사용자 조회 */
	@GetMapping("/corp")
	public List<Map<String, Object>> getCorpUserList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		List<Map<String, Object>> list = userService.getCorpUserList(parameterMap.getMap());
		return list;
	}

	/* 사용자 조회 */
	@GetMapping("/sign")
	public List<Map<String, Object>> getUserSignList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = userService.getUserSignList(parameterMap.getMap());
		return list;
	}
	
	/* 사용자 조회 */
	@GetMapping("/sign-corp")
	public List<Map<String, Object>> getUserCorpSignList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		List<Map<String, Object>> list = userService.getUserCorpSignList(parameterMap.getMap());
		return list;
	}
	
	/* get User */
	@GetMapping("/id")
	public Object getUserSort(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = userDetailsImpl.getInternalId();
		Object user = userService.getUser(pUserId);
		return user;
	}
	

	@GetMapping("/sendKey")
	public Object sendKey(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) throws MessagingException, NoSuchAlgorithmException{
		char[] charSet = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9'
		};
		int pos                     = 0;
		StringBuilder stringBuilder = new StringBuilder();
		Random rand = SecureRandom.getInstanceStrong();
		for (int idx = 0; idx < 6; idx++) {
			pos = rand.nextInt(charSet.length);
			stringBuilder.append(charSet[pos]);
		}
		String key = stringBuilder.toString();
		String[] to = new String[1];
		to[0] = userDetailsImpl.getEmail();
		String from = "no-reply@codefarm.co.kr";
		String subject = "[인증번호] CDF PLATFORM";
		String content = "<h2>인증번호는 발송 후 3분 이내 입력시에만 유효합니다.</h2><br>인증번호 : <b>" + key + "</b>";
		emailService.sendMail(from, to, subject, content);;
		if(_PROFILE.equals("dev")) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("@ KEY : "+ key +" @");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		JSONObject ret = new JSONObject();
		ret.put("KEY", key);
		ret.put("EX", System.currentTimeMillis() + 1000*60*3);
		return AES128Util.enc(ret.toString());
	}

	/* 사용자 아이디 중복 체크 */
	@GetMapping("/useridchk")
	public List<Map<String, Object>> getUserIdChk(ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");

		List<Map<String, Object>> list = userService.getUserIdChk(pUserId);
		return list;
	}

	/* 사용자 삽입 */
	@PostMapping("")
	public Object insertUser(ParameterMap parameterMap) {
		int insCnt = userService.insertUser(parameterMap.getMap());
		return insCnt;
	}
	


	/* 가입 승인  */
	@Transactional
	@PutMapping("/admission")
	public Object userSignAdmission(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) throws MessagingException {
		if(userDetailsImpl.getDefaultAuthCd().equals("S0001") || userDetailsImpl.getDefaultAuthCd().equals("S9999") || userDetailsImpl.getUserId().equals("codefarm@codefarm.co.kr")) {
			int updCnt = userService.userSignAdmission(parameterMap.getMap());

			String url = (("01".equals(ServiceMap.get("SMN").get("PROTOCOL_GB")) ? "http":"https") + "://" + ServiceMap.get("SMN").get("DOMAIN") + ":" + ServiceMap.get("SMN").get("PORT")) + "/html/main/login.html";
			String[] to = new String[1];
			to[0] = parameterMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[가입승인] CDF PLATFORM";
			String content = "<h2>회원가입이 승인되었습니다</h2><br>바로가기 : <a href=\"" + url + "\">CDF PLATFORM</a>";
			emailService.sendMail(from, to, subject, content);;
			return updCnt;
		} else {
			return null;
		}
	}
	
	/* 가입 반려  */
	@Transactional
	@PutMapping("/reject")
	public Object rejectUser(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) throws MessagingException {
		if(userDetailsImpl.getDefaultAuthCd().equals("S0001") || userDetailsImpl.getDefaultAuthCd().equals("S9999") || userDetailsImpl.getUserId().equals("codefarm@codefarm.co.kr")) {
			int updCnt = userService.rejectUser(parameterMap.getMap());
			
			String[] to = new String[1];
			to[0] = parameterMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[가입반려] CDF PLATFORM";
			String content = "<h2>회원가입이 반려되었습니다</h2><br>회원가입 반려사유를 확인하시고 다시신청해주세요.<br><h4>반려사유</h4>" + parameterMap.get("MSG");
			emailService.sendMail(from, to, subject, content);;
			return updCnt;
		} else {
			return null;
		}
	}
	
	/* 가입 승인  */
	@Transactional
	@PutMapping("/admission-corp")
	public Object userCorpSignAdmission(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) throws MessagingException {
		if(userDetailsImpl.getDefaultAuthCd().equals("S0002")) {
			int updCnt = userService.userCorpSignAdmission(parameterMap.getMap());
			
			String url = (("01".equals(ServiceMap.get("SMN").get("PROTOCOL_GB")) ? "http":"https") + "://" + ServiceMap.get("SMN").get("DOMAIN") + ":" + ServiceMap.get("SMN").get("PORT")) + "/html/main/login.html";
			String[] to = new String[1];
			to[0] = parameterMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[가입승인] CDF PLATFORM";
			String content = "<h2>회원가입이 승인되었습니다</h2><br>바로가기 : <a href=\"" + url + "\">CDF PLATFORM</a>";
			if(!_PROFILE.equals("dev")) {
				emailService.sendMail(from, to, subject, content);
			}
			return updCnt;
		} else {
			return null;
		}
	}
	
	/* 가입 반려  */
	@Transactional
	@PutMapping("/reject-corp")
	public Object rejectCorpUser(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) throws MessagingException {
		if(userDetailsImpl.getDefaultAuthCd().equals("S0002")) {
			int updCnt = userService.rejectCorpUser(parameterMap.getMap());
			
			String[] to = new String[1];
			to[0] = parameterMap.get("USER_ID").toString();
			String from = "no-reply@codefarm.co.kr";
			String subject = "[가입반려] CDF PLATFORM";
			String content = "<h2>회원가입이 반려되었습니다</h2><br>회원가입 반려사유를 확인하시고 다시신청해주세요.<br><h4>반려사유</h4>" + parameterMap.get("MSG");

			if(!_PROFILE.equals("dev")) {
				emailService.sendMail(from, to, subject, content);
			}
			return updCnt;
		} else {
			return null;
		}
	}

	/* 사용자 갱신 */
	@PutMapping("")
	public Object updateUser(ParameterMap parameterMap) {
		int insCnt = userService.updateUser(parameterMap.getMap());
		return insCnt;
	}
	
	/* 사용자 삭제 */
	@DeleteMapping("/{userId}")
	public Object deleteUser(@PathVariable(value="userId") String userId, ParameterMap parameterMap) {
		if(userId.isEmpty()) {
			/* serviceId is not null*/
		}
		int delCnt = userService.deleteUser(userId);
		return delCnt;
	}

	/*update User pic*/
	@PutMapping("/pic")
	public Object updateUserPic(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int insCnt = 0;

		if(pUserId.equals((String) parameterMap.get("USER_ID"))) {
			insCnt = userService.updateUserPic(parameterMap.getMap());
		}
		return insCnt;
	}

	/* get User  Sort */
	@GetMapping("/sort")
	public Object getUserSort(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		String pSortGb = (String) parameterMap.get("pSortGb");

		Object userSort = userService.getUserSort(pUserId, pSortGb);
		return userSort;
	}

	/*insert User  Sort*/
	@PostMapping("/sort")
	public int insertUserSort(ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		String pSortGb = (String) parameterMap.get("pSortGb");
		int userSortRow = userService.insertUserSort(pUserId, pSortGb, parameterMap.getMap());
		return userSortRow;
	}

	/*update User  Sort*/
	@PutMapping("/sort")
	public Object updateUserSort(ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		String pSortGb = (String) parameterMap.get("pSortGb");
		int insCnt = userService.updateUserSort(pUserId, pSortGb, parameterMap.getMap());
		return insCnt;
	}

	/*delete User  Sort*/
	@DeleteMapping("/sort/{userId}")
	public Object deleteUserSort(@PathVariable(value="userId") String userId, ParameterMap parameterMap) {
		String pSortGb = (String) parameterMap.get("pSortGb");
		if(userId.isEmpty()) {
			/* appId is not null*/
		}
		int delCnt = userService.deleteUserSort(userId, pSortGb);
		return delCnt;
	}

	/* get User  Sort */
	@GetMapping("/account")
	public Object getUserAccount(ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		Object userSort = userService.getUserAccount(pUserId);
		return userSort;
	}

}
