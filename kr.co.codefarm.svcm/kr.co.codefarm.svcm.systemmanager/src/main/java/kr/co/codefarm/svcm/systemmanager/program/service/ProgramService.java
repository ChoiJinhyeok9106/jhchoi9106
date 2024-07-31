package kr.co.codefarm.svcm.systemmanager.program.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProgramService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.program";

	//Program Service
	public List<Map<String, Object>> getProgramList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectProgramList", paramMap);
		return list;
	}
	
	public Map<String, Object>  insertProgram(Map<String, Object> pProgramRow) {
		Map<String, Object> insProgramRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertProgram", pProgramRow);
		return insProgramRow;
	}
	
	public int updateProgram(Map<String, Object> pProgramRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateProgram", pProgramRow);
		return updCnt;
	}
	
	public int deleteProgram(Map<String, Object> pProgramRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteProgram", pProgramRow);
		return delCnt;
	}
}
