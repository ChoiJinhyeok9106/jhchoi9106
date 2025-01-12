function HaveStatusApi(){}
var haveStatusApi = new HaveStatusApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 자산 보유 현황 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
HaveStatusApi.prototype.getHaveStatusList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/havestatus/havestatus-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 보유 현황 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveStatusApi.prototype.assetHaveUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/havestatus/updateHave', entry);
}

/********************************************************************************************
 * 전자 태그 발급 요청
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveStatusApi.prototype.insertTaPrtIssuReq = function(pAssetMgmtNo, pLoading) {
	var assetMgmtNo = pAssetMgmtNo;
	var loadingYn = pLoading;
	
	var entry = {
		ASSET_MGMT_NO: assetMgmtNo
	};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/havestatus/insertTa', entry);
}

/********************************************************************************************
 * 전자 태그 발급 취소
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveStatusApi.prototype.updateTaPrtIsuuReqcncl = function(pEntry, pLoading) {
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/havestatus/updatecnclTa', entry);
}

/********************************************************************************************
 * 사용 위치 조회
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
HaveStatusApi.prototype.getUseLoc = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/havestatus/useloc-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};






