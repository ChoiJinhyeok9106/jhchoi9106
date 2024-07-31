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
public class ServiceDto {
	
	@JsonProperty private String SERVICE_CD;
	@JsonProperty private String SERVICE_NM;
	@JsonProperty private String SERVICE_ENM;
	@JsonProperty private String PROTOCOL_GB;
	@JsonProperty private String DOMAIN;
	@JsonProperty private String PORT;
	@JsonProperty private String CONTEXT;
	@JsonProperty private String COMMCODE_USE_YN;

}
