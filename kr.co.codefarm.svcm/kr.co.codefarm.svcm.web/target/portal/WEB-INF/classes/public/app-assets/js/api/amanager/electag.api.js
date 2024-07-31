function ElectagApi(){}
var electagApi = new ElectagApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 전자태그발급요청현황 조회
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/


ElectagApi.prototype.getElecTagReqStatusList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/electag/elecReq-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 전자태그발급요청현황 조회
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/


ElectagApi.prototype.getElecTagIssueStatusList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/electag/elecIssu-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 전자태그발급요청현황상세 조회
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/


ElectagApi.prototype.getElecTagIssueStatusDetlQry = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/electag/elecIssuDetl-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 태그발급현황 태그발급 업데이트
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/


ElectagApi.prototype.updateTaPrtIssu = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/electag/updateTa', entry);
}