function UseratyMgmtApi(){}
var useratyMgmtApi = new UseratyMgmtApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 사용자관리 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
UseratyMgmtApi.prototype.getUsrAtyMgmtList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/useratymgmt/user-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 권한 리스트 조회(select) 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
UseratyMgmtApi.prototype.getAuthorCodeList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/useratymgmt/AuthorCode-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 사용자관리 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UseratyMgmtApi.prototype.insertUsrMgmt = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/useratymgmt/insertUsrMgmt', entry);
}

/********************************************************************************************
 * 사용자관리 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UseratyMgmtApi.prototype.updateUsrMgmt = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/useratymgmt/updateUsrMgmt', entry);
}
