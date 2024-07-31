package kr.co.codefarm.svcm.commons.component;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailTemplateComponent {
	
	private Map<String, String> mailTemplates = new HashMap<>();
	private String[] templateFiles = {"mailTemplate1"};
	
	public MailTemplateComponent() throws IOException {
		for (int idx = 0; idx < this.templateFiles.length; idx++) {
			mailTemplates.put(templateFiles[idx], this.readTemplate(templateFiles[idx]).toString());
		}
	}
	
	private StringBuilder readTemplate(String templateFile) {
		StringBuilder mailTemplate = null;
		
		try {
			mailTemplate            = new StringBuilder();
			InputStream inputStream = getClass().getResourceAsStream("/public/assets/mailTemplates/" + templateFile + ".html");
			byte[] buffer           = new byte[4096];
			int readCount           = -1;
			
			while ((readCount = inputStream.read(buffer)) != -1) {
				mailTemplate.append(new String(buffer, 0, readCount));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mailTemplate;
	}
	
	public String getTemplate(String templateName) {
		return mailTemplates.get(templateName);
	}
	
	public String getTemplate(String templateName, Map<String, String> replaceMap) {
		String templateStr = mailTemplates.get(templateName);
		for (String key : replaceMap.keySet()) {
			templateStr = templateStr.replace("{" + key + "}", replaceMap.get(key));
		}
		
		return templateStr;
	}
	
}
