function CodeMgmtApi(){}
var codeMgmtApi = new CodeMgmtApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 공통코드 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CodeMgmtApi.prototype.getCodeMgmtList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/codemgmt/code-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 공통코드 리스트 상세 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CodeMgmtApi.prototype.getCodeMgmtListDetail = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/codemgmt/codedetl-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 정렬순서 중복 체크
 * @param: 중복 체크할 정보(json object)
 * @return: 중복 체크 결과
 ********************************************************************************************/
CodeMgmtApi.prototype.checkOrderSeq = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/codemgmt/checkOrderSeq', entry);
}

/********************************************************************************************
 * 상세코드 중복 체크
 * @param: 중복 체크할 정보(json object)
 * @return: 중복 체크 결과
 ********************************************************************************************/
CodeMgmtApi.prototype.checkCodeList = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/codemgmt/checkCodeList', entry);
}

/********************************************************************************************
 * 공통상세코드 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
CodeMgmtApi.prototype.insertCodeMgmtDetail = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/codemgmt/insertCode', entry);
}

/********************************************************************************************
 * 공통상세코드 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
CodeMgmtApi.prototype.updateCodeMgmtDetail = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/codemgmt/updateCode', entry);
}
