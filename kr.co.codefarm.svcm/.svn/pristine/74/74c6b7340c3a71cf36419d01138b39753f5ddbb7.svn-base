function DeptMgmtApi(){}
var deptMgmtApi = new DeptMgmtApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 부서 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
DeptMgmtApi.prototype.getDeptMgmtList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/deptmgmt/dept-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};


/********************************************************************************************
 * 부서 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
DeptMgmtApi.prototype.deptmgmtInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/deptmgmt/insertDept', entry);
}

/********************************************************************************************
 * 부서 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
DeptMgmtApi.prototype.deptmgmtUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/deptmgmt/updateDept', entry);
}

/********************************************************************************************
 * 부서 중복 체크
 * @param: 중복 체크할 정보(json object)
 * @return: 중복 체크 결과
 ********************************************************************************************/
DeptMgmtApi.prototype.checkDeptMgmt = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/deptmgmt/checkUpdateDept', entry);
}

/********************************************************************************************
 * 부서 미사용 처리
 * @param: 
 * @return: 
 ********************************************************************************************/
DeptMgmtApi.prototype.deptUseNot = function(pDeptCd, pLoading){
	var deptCd = pDeptCd;
	var loadingYn = pLoading;
	
	if(isNull(deptCd)) deptCd = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(deptCd) && deptCd !== '') {
	    return axios.delete('/amanager/deptmgmt/' + pDeptCd);
	} else {
	    console.error('Dept Code is missing');
	}
}

