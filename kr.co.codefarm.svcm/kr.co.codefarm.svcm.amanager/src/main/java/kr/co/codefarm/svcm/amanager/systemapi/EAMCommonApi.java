package kr.co.codefarm.svcm.amanager.systemapi;

import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.api.dto.CodeDto;
import kr.co.codefarm.svcm.commons.api.manager.ICommonAPI;
import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ManagedAPI(service="eam")
public class EAMCommonApi implements ICommonAPI {
	
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.systemapi";
	

	@Override
	public List<CodeDto> addSelectCodesHandler(Map<String, Object> parameterMap) {
		List<CodeDto> retList = new ArrayList<>();
		List<Map<String, Object>> selList = commonDao.selectList(DEFAULT_MAPPER_PATH+".selectEamCommCodes", parameterMap);
		
		for (Map<String, Object> map : selList) {
			CodeDto codeDto = (CodeDto) ObjectUtil.convertMapToObject(map, new CodeDto());
			retList.add(codeDto);
		}
		
		return retList;
	}
	
	@Override
	public CodeDto addSelectCodeHandler(Map<String, Object> parameterMap) {
		CodeDto codeDto = (CodeDto) commonDao.selectObject (DEFAULT_MAPPER_PATH+".selectEamCommCode", parameterMap);
		return codeDto;
	}
	
	@Override
	public Object addDeleteCodeHandler(CodeDto parameterObj) {
		Map<String, Object> codeMap = (Map<String, Object>) ObjectUtil.convertObjectToMap(parameterObj);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH+".deleteEamCommCode", codeMap);
		return delCnt; 
	}
	
	@Override
	public Object addUpdateCodeHandler(CodeDto parameterObj) { 
		Map<String, Object> codeMap = (Map<String, Object>) ObjectUtil.convertObjectToMap(parameterObj);
		int updCnt = commonDao.delete(DEFAULT_MAPPER_PATH+".updateEamCommCode", codeMap);
		return updCnt; 
	}
	
	@Override
	public Object addInsertCodeHandler(CodeDto parameterObj) { 
		Map<String, Object> codeMap = (Map<String, Object>) ObjectUtil.convertObjectToMap(parameterObj);
		int insCnt = commonDao.delete(DEFAULT_MAPPER_PATH+".insertEamCommCode", codeMap);
		return insCnt; 
	}
}
