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
public class AlarmDto {
	
	@JsonProperty private String ALARM_SEQ;
	@JsonProperty private String API_SEQ;
	@JsonProperty private String ALARM_GB;
	@JsonProperty private String ALARM_DETAIL_GB;
	@JsonProperty private String ALARM_DAESANG_GB;
	@JsonProperty private String DAESANGJA_COLUMN_ID;
	@JsonProperty private String ALARM_CHK_GB;
	@JsonProperty private String TITLE;
	@JsonProperty private String TEXT;
	@JsonProperty private String SERVICE_CD;
	@JsonProperty private String API_NM;
	@JsonProperty private String API_URL;
	@JsonProperty private String METHOD_GB;
	@JsonProperty private String REST_API_DESC;
	@JsonProperty private String REMARK;
	
}
