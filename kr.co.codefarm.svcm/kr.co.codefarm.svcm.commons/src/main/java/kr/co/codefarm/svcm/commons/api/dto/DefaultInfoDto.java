/**
 * 
 */
package kr.co.codefarm.svcm.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dki_s
 *
 */
@Getter @Setter
public class DefaultInfoDto {
	
	@JsonProperty private String SITE_SEQ;
	@JsonProperty private String SITE_NM;
	@JsonProperty private String BIZNO;
	@JsonProperty private String CORPNO;
	@JsonProperty private String CO_NM;
	@JsonProperty private String CO_ENM;
	@JsonProperty private String ADDR1;
	@JsonProperty private String ADDR2;
	@JsonProperty private String ZIPNO;
	@JsonProperty private String EOPTAE;
	@JsonProperty private String JONGMOK;
	@JsonProperty private String COPYRIGHT;
	@JsonProperty private String HOEWON_JOIN_URL;
//	@JsonProperty private String GROUPWARE_SESSION_INSERT_URL;
//	@JsonProperty private String GROUPWARE_EMAIL_LINK_URL;
//	@JsonProperty private String GROUPWARE_EMAIL_LIST_URL;
//	@JsonProperty private String GROUPWARE_EMAIL_CNT_URL;
	@JsonProperty private String PWD_FIND_URL;
	@JsonProperty private String SEND_MAIL;
	@JsonProperty private String SEND_NM;
	@JsonProperty private String DEFAULT_MAIL_TITLE;
	@JsonProperty private String SMS_USE_YN;
	@JsonProperty private String SMS_SEND_NO;
	@JsonProperty private String TEL_NO;
	@JsonProperty private String FAX_NO;
	@JsonProperty private String REMARK;
	@JsonProperty private String USE_YN;
	@JsonProperty private String DAEPYO_YN;
	@JsonProperty private String AGREE_ITEM1_NM;
	@JsonProperty private String AGREE_ITEM1_PILSU_YN;
	@JsonProperty private String AGREE_ITEM1_USE_YN;
	@JsonProperty private String AGREE_ITEM1_CONTENTS;
	@JsonProperty private String AGREE_ITEM2_NM;
	@JsonProperty private String AGREE_ITEM2_PILSU_YN;
	@JsonProperty private String AGREE_ITEM2_USE_YN;
	@JsonProperty private String AGREE_ITEM2_CONTENTS;
	@JsonProperty private String AGREE_ITEM3_NM;
	@JsonProperty private String AGREE_ITEM3_PILSU_YN;
	@JsonProperty private String AGREE_ITEM3_USE_YN;
	@JsonProperty private String AGREE_ITEM3_CONTENTS;
	@JsonProperty private String AGREE_ITEM4_NM;
	@JsonProperty private String AGREE_ITEM4_PILSU_YN;
	@JsonProperty private String AGREE_ITEM4_USE_YN;
	@JsonProperty private String AGREE_ITEM4_CONTENTS;
	@JsonProperty private String LOGO1;
	@JsonProperty private String LOGO2;
	@JsonProperty private String LOGO;
	@JsonProperty private String BG;
	@JsonProperty private String EMAIL_LOGO;
	@JsonProperty private String INFO_AGREE_TERM;
	
}
