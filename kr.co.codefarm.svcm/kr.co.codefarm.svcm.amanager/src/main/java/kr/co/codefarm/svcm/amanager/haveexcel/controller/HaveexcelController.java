/**
 * 
 */
package kr.co.codefarm.svcm.amanager.haveexcel.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import kr.co.codefarm.svcm.amanager.haveexcel.service.HaveexcelService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(value = "/amanager/haveexcel")
public class HaveexcelController {

	@Autowired
	private HaveexcelService HaveexcelService;

	// 조회
	@GetMapping("/haveexcel-list")
	public List<Map<String, Object>> getHaveExcelList(ParameterMap parameterMap) {
		return HaveexcelService.getHaveExcelList(parameterMap.getMap());
	}
	
	// 엑셀업로드 리스트 삭제
	@DeleteMapping("/deleteHaveExcel/{agencyId}")
	public ResponseEntity<String> deleteUploadList(@PathVariable(value = "agencyId") String agencyId, ParameterMap parameterMap) {
		int delCnt = HaveexcelService.deleteHaveExcel(agencyId);
		if (delCnt > 0) {
			return ResponseEntity.ok("OK"); // 성공적으로 삭제된 경우 "OK" 반환
		} else {
			return ResponseEntity.badRequest().body("FAIL"); // 삭제 실패 시 "FAIL" 반환
		}
	}

	/* Excel File Upload -> Insert(Excel04) */
	@PostMapping("/insert-haveexcel")
	public ResponseEntity<?> insertService(@RequestBody List<Map<String, Object>> requestDataList) {
		try {
			int totalRows = requestDataList.size();

			if (totalRows == 0) {
				return ResponseEntity.badRequest().body("입력된 데이터가 없습니다.");
			}

			if (totalRows >= 5002) {
				return ResponseEntity.badRequest().body("5000행까지만 자산정보를 입력해주세요");
			}

			// 첫 번째 요청 데이터 가져오기
			Map<String, Object> firstData = requestDataList.get(0);

			// 필수 체크: 엑셀 구조 확인
			if (firstData == null || firstData.isEmpty()) {
				return ResponseEntity.badRequest().body("엑셀 구조가 맞지 않습니다. (헤더가 비어 있습니다.)");
			}

			// 헤더 구조 확인
			if (!("순번".equals(firstData.get("A")) && "RFID자산번호".equals(firstData.get("B"))
					&& "기관자산번호".equals(firstData.get("C")) && "물품분류번호".equals(firstData.get("D"))
					&& "물품규격번호".equals(firstData.get("E")) && "물품명".equals(firstData.get("F"))
					&& "규격명".equals(firstData.get("G")) && "태그종류".equals(firstData.get("H"))
					&& "취득구분".equals(firstData.get("I")) && "회계구분".equals(firstData.get("J"))
					&& "물품구분".equals(firstData.get("K")) && "구입일자".equals(firstData.get("L"))
					&& "보유부서".equals(firstData.get("M")) && "삭제여부".equals(firstData.get("N"))
					&& "사용위치".equals(firstData.get("O")) && "구입단가".equals(firstData.get("P"))
					&& "구입수량".equals(firstData.get("Q")) && "물품대장종류".equals(firstData.get("R"))
					&& "정리일자".equals(firstData.get("S")) && "제조사명".equals(firstData.get("T"))
					&& "구매처명".equals(firstData.get("U")) && "비고".equals(firstData.get("V")))) {
				return ResponseEntity.badRequest().body("엑셀 구조가 맞지 않습니다. (필수 항목명 확인 필요)");
			}

			// 배열 순회 하며 데이터 입력
			for (int r = 1; r < requestDataList.size(); r++) {
				Map<String, Object> jsonData = requestDataList.get(r);

				ParameterMap parameterMap = new ParameterMap();
				Map<String, Object> paramMap = new HashMap<>();

				paramMap.put("NUM", jsonData.get("A")); // A col - 순번
				paramMap.put("ASSET_MGMT_NO", getStringValue(jsonData.get("B"))); // B col - RFID 자산번호
				paramMap.put("INST_ASSET_MGMT_NO", getStringValue(jsonData.get("C"))); // C col - 기관자산번호
				paramMap.put("ASSET_CL_NO", getStringValue(jsonData.get("D"))); // D col - 물품분류번호
				paramMap.put("ASSET_STND_NO", getStringValue(jsonData.get("E"))); // E col - 물품규격번호
				paramMap.put("ASSET_CL_NM", getStringValue(jsonData.get("F"))); // F col - 물품명
				paramMap.put("ASSET_STND_NM", getStringValue(jsonData.get("G"))); // G col - 규격명
				paramMap.put("TAG_KIND_CD", getStringValue(jsonData.get("H"))); // H col - 태그종류
				paramMap.put("ACQ_SE_CD", getStringValue(jsonData.get("I"))); // I col - 취득구분
				paramMap.put("ACNTS_SE_CD", getStringValue(jsonData.get("J"))); // J col - 회계구분
				paramMap.put("ASSET_ITEM_SE_CD", getStringValue(jsonData.get("K"))); // K col - 물품구분
				paramMap.put("BUY_DT", getStringValue(jsonData.get("L"))); // L col - 구입일자
				paramMap.put("DEPT_CD", getStringValue(jsonData.get("M"))); // M col - 보유부서
				paramMap.put("DTL_YN", getStringValue(jsonData.get("N"))); // N col - 삭제여부
				paramMap.put("USE_LOC_NM", getStringValue(jsonData.get("O"))); // O col - 사용위치
				paramMap.put("BUY_UPRICE", getStringValue(jsonData.get("P"))); // P col - 구입단가
				paramMap.put("BUY_QTY", getStringValue(jsonData.get("Q"))); // Q col - 구입수량
				paramMap.put("GDS_RGST_KD_CD", getStringValue(jsonData.get("R"))); // R col - 물품대장종류
				paramMap.put("ARN_DT", getStringValue(jsonData.get("S"))); // S col - 정리일자
				paramMap.put("PRODT_CORPO_NM", getStringValue(jsonData.get("T"))); // T col - 제조사명
				paramMap.put("BUY_LOC_NM", getStringValue(jsonData.get("U"))); // U col - 구매처명
				paramMap.put("RMK", getStringValue(jsonData.get("V"))); // V col - 비고

				paramMap.put("ERR_YN", "N");
				paramMap.put("ERR_CN", "검증전");

				paramMap.put("EXCEL_ROW_NO", String.valueOf(r + 1));
				parameterMap.setMap(paramMap);

				int insCnt = HaveexcelService.insertHaveExcel(parameterMap.getMap());

				if (insCnt <= 0) {
					return ResponseEntity.badRequest().body("자산 정보 입력에 실패하였습니다.");
				}
			}

			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Excel Upload 에러 발생: " + e.getMessage());
		}
	}

	/* JSON 데이터 전처리 메서드 추가 */
	private void preprocessJsonData(Map<String, Object> jsonData) {
		for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
			if (entry.getValue() instanceof String) {
				String value = (String) entry.getValue();
				if (value != null && value.endsWith("\"")) {
					// 큰따옴표로 끝나는 경우, 이스케이프 처리
					jsonData.put(entry.getKey(), StringUtils.replace(value, "\"", "\\\""));
				}
			}
		}
	}

	/* 데이터 입력시 공백 제거 */
	private String getStringValue(Object obj) {
		if (obj == null) {
			return null;
		} else {
			String value = obj.toString();
			return value.trim(); // 양쪽 공백 제거
		}
	}
	
	/* 필수항목 검증 */
	@PutMapping("/updateMdtr")
	public Object updateMdtr(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.updateMdtr(parameterMap.getMap());
		return insCnt;
	}
	
	/* 자산관리번호 update */
	@PutMapping("/updateAssetMgmtNo")
	public Object updateAssetMgmtNo(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.updateAssetMgmtNo(parameterMap.getMap());
		return insCnt;
	}
	
	/* 물품분류번호 생성 */
	@PostMapping("/insertAsstClCd")
	public Object insertAsstClcd(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.insertAsstClCd(parameterMap.getMap());
		return insCnt;
	}
	
	/* 물품규격번호 생성 */
	@PostMapping("/insertAsstStndCd")
	public Object insertAsstStndcd(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.insertAsstStndCd(parameterMap.getMap());
		return insCnt;
	}
	
	/* 자산 반영 이력현황 삭제 */
	@DeleteMapping("/deleteAcqDtlHist/{agencyId}")
	public Object deleteAcqDtlHist(@PathVariable(value="agencyId") String agencyId, ParameterMap parameterMap) {
		int delCnt = HaveexcelService.deleteAcqDtlHist(agencyId);
		return delCnt;
	}
	
	/* 자산 반영 발급현황 삭제 */
	@DeleteMapping("/deletePrtIssu/{agencyId}")
	public Object deletePrtIssu(@PathVariable(value="agencyId") String agencyId, ParameterMap parameterMap) {
		int delCnt = HaveexcelService.deletePrtIssu(agencyId);
		return delCnt;
	}
	
	/* 자산 반영 보유현황 삭제 */
	@DeleteMapping("/deleteAcqDtl/{agencyId}")
	public Object deleteAcqDtl(@PathVariable(value="agencyId") String agencyId, ParameterMap parameterMap) {
		int delCnt = HaveexcelService.deleteAcqDtl(agencyId);
		return delCnt;
	}
	
	/* 자산 반영 취득관리 입력 */
	@PostMapping("/insertAcqDtl")
	public Object insertAcqDtl(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.insertAcqDtl(parameterMap.getMap());
		return insCnt;
	}
	
	/* 자산 반영 취득관리 입력 */
	@PostMapping("/insertAcq")
	public Object insertAcq(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.insertAcq(parameterMap.getMap());
		return insCnt;
	}
	
	/* 자산 반영 보유현황 수정 */
	@PutMapping("/updateAcqDtl")
	public Object updateAcqDtl(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.updateAcqDtl(parameterMap.getMap());
		return insCnt;
	}
	
	/* 자산 반영 반영여부 수정 */
	@PutMapping("/updateRflcYn")
	public Object updateRflcYn(ParameterMap parameterMap) {
		int insCnt = HaveexcelService.updateRflcYn(parameterMap.getMap());
		return insCnt;
	}


}
