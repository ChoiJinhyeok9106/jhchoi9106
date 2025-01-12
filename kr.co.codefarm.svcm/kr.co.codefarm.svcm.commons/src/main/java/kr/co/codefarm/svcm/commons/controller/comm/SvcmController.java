package kr.co.codefarm.svcm.commons.controller.comm;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.service.comm.ScvmService;
import kr.co.codefarm.svcm.commons.utils.cipher.RSAUtil;

@RestController
@Controller
public class SvcmController extends BaseController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private ScvmService scvmService;

	@Value("${sysprop.downloadFile}")
	private String _DOWNLOADFILE;

	@Value("${sysprop.downloadFileNm}")
	private String _DOWNLOADFILENM;

	@Value("${spring.profiles}")
	private String profiles;

	@Value("${server.port}")
	private String port;

	@Value("${use-sso}")
	private boolean ssoEnabled;

	@GetMapping(value = "/")
	public ResponseEntity<?> welcome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/index.html"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

	@GetMapping(value = "/publickeys")
	public Map<String, String> publickeys(HttpServletRequest request) throws IOException {
		RSAUtil rsaUtil = new RSAUtil();

		request.getSession().setAttribute("_RSA_PRIVATE_KEY_", rsaUtil.getPrivateKey());

		Map<String, String> retMap = new HashMap<>();
		retMap.put("modulus", rsaUtil.getModulus());
		retMap.put("publicExponent", rsaUtil.getPublicExponent());

		return retMap;
	}

	@GetMapping("/session-info")
	public Map<String, Object> sessioninfo(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		Map<String, Object> userMap = new HashMap<>();
        if (userDetailsImpl != null) {
            userMap.put("USER_GB", userDetailsImpl.getUserGb());
            userMap.put("USER_GROUP_GB", userDetailsImpl.getUserGroupGb());
            userMap.put("USER_ID", userDetailsImpl.getUserId());
            userMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
            userMap.put("EMAIL", userDetailsImpl.getEmail());
            userMap.put("USER_NM", userDetailsImpl.getUserNm());
            userMap.put("DEFAULT_AUTH_CD", userDetailsImpl.getDefaultAuthCd());
            userMap.put("DEFAULT_AUTH_NM", userDetailsImpl.getDefaultAuthNm());
            userMap.put("CORP_NO", userDetailsImpl.getCorpId());
            userMap.put("DEPT_NM", userDetailsImpl.getDeptNm());
            userMap.put("PSTN_NM", userDetailsImpl.getPstnNm());
            userMap.put("TEL_NO", userDetailsImpl.getTelNo());
            userMap.put("HP_NO", userDetailsImpl.getHpNo());
            userMap.put("ZIPNO", userDetailsImpl.getZipno());
            userMap.put("ADDR", userDetailsImpl.getAddr());
            userMap.put("JOIN_DT", userDetailsImpl.getJoinDt());
            userMap.put("USER_STATUS_GB", userDetailsImpl.getUserStatusGb());
            userMap.put("USER_STATUS_GBNM", userDetailsImpl.getUserStatusGbnm());
            userMap.put("USER_PIC", userDetailsImpl.getUserPic());
            userMap.put("ADD_COL1", userDetailsImpl.getAddCol1());
            userMap.put("ADD_COL2", userDetailsImpl.getAddCol2());
            userMap.put("ADD_COL3", userDetailsImpl.getAddCol3());
            userMap.put("AGREE_YN", userDetailsImpl.getAgreeYn());
            userMap.put("SSO_ID", userDetailsImpl.getSsoId());
            userMap.put("BEFORE_USER_ID", userDetailsImpl.getBeforeUserId());
        }
		return userMap;
	}

	@GetMapping("/api/is-session")
	public boolean isSession(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		Map<String, Object> userMap = new HashMap<>();

		if(userDetailsImpl != null) {
			return true;
		}else {
			return false;
		}
	}

	@GetMapping(value = "/agree")
	public void agree(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
		Calendar calendar = Calendar.getInstance();
		int nowYy = calendar.get(calendar.YEAR);
		int nowMm = calendar.get(calendar.MONTH) + 1;
		int nowDd = calendar.get(calendar.DATE);

		String yy = String.valueOf(nowYy);
		String mm = (String.valueOf(nowMm).length() < 2 ? "0":"") + String.valueOf(nowMm);
		String dd = (String.valueOf(nowDd).length() < 2 ? "0":"") + String.valueOf(nowDd);

		String now = yy + mm + dd;

		userDetailsImpl.setAgreeYn("Y");
		userDetailsImpl.setAgreeDt(now);
		scvmService.agree(parameterMap.getMap());
	}
	
	@GetMapping(value = "/terms/service")
	public ModelAndView serviceTerms(ParameterMap parameterMap) {
		return new ModelAndView("../service/additional/html/sub/servicetermshistory.html");
	}
	
	@GetMapping(value = "/terms/privacy")
	public ModelAndView privacyTerms(ParameterMap parameterMap) {
		return new ModelAndView("../service/additional/html/sub/privacytermshistory.html");
	}
}