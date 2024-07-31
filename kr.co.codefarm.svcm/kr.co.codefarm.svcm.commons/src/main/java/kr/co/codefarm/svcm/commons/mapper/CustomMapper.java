package kr.co.codefarm.svcm.commons.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	
	public CustomMapper() {
		getSerializerProvider().setNullValueSerializer(new NullToEmptySerializer());
	}
	
}
