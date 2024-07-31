/**
 * 
 */
package kr.co.codefarm.svcm.commons.controller.mail;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.service.file.FileService;
import kr.co.codefarm.svcm.commons.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author wjjoo
 *
 */
@RestController
@RequestMapping(value = "/commons/mail")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private FileService fileService;

	@Value("${sysprop.uploadDir}")
	private String UPLOAD_DIR;
	
	@Value("${homedir}")
	private String HOME_DIR;

	/*get File*/
	@PostMapping("/send")
	public boolean SendEmail(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String toList 	= (String) parameterMap.get("TO");
//		String from 	= (String)parameterMap.get("FROM");
		String from 	= "no-reply@codefarm.co.kr";
		String title 	= (String)parameterMap.get("TITLE");
		String contents = (String)parameterMap.get("CONTENTS");
		String userNm 	= "XCloud";
		
		//2022-06-17 wjjoo 현재 필요없는기능
		// 메일을 보내는 사람이 세션이 없는 경우에는 메일 보내는 사람의 이메일 주소 값을 넣어줌
//		if(userDetailsImpl != null) {
//			userNm = userDetailsImpl.getUserNm();
//		}else {
//			userNm = from;
//		}
		
		StringTokenizer tokens = new StringTokenizer(toList, "," );
		List<String> sendList = new ArrayList<String>();

		for(int i = 1; tokens.hasMoreElements(); i++ ){ 
			sendList.add(tokens.nextToken());
		}
		
        // List -> String[]
        String[] emails = sendList.toArray(new String[sendList.size()]);

		try {
			emailService.sendMail(userNm, from, emails, title, contents);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@PostMapping("/file")
	public boolean sendMailFile(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String toList 	= (String) parameterMap.get("TO");
		String from 	= (String)parameterMap.get("FROM");
		String title 	= (String)parameterMap.get("TITLE");
		String contents = (String)parameterMap.get("CONTENTS");
		String fileKey = (String)parameterMap.get("FILE_KEY");		
		List<Map<String, Object>> fileList = fileService.getFileList("SMN", "SMN", fileKey, "cheombufile","");
		String userNm 	= "XCloud";
		String fileNm[] = new String[fileList.size()];
		String filePath[] = new String[fileList.size()];
		for(int i=0; i<fileList.size();i++) {
			fileNm[i] = (String) fileList.get(i).get("FILE_NM");
			filePath[i] = (String) fileList.get(i).get("FILE_PATH");
			filePath[i] = this.HOME_DIR + "/" + this.UPLOAD_DIR + filePath[i];
			filePath[i].replace("\\", File.separator).replace("/", File.separator);
		}
		
		StringTokenizer tokens = new StringTokenizer(toList, "," );
		List<String> sendList = new ArrayList<String>();

		for(int i = 1; tokens.hasMoreElements(); i++ ){ 
			sendList.add(tokens.nextToken());
		}
		
        // List -> String[]
        String[] emails = sendList.toArray(new String[sendList.size()]);

		try {
			emailService.sendMail(userNm, from, emails, title, contents, fileNm, filePath);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}