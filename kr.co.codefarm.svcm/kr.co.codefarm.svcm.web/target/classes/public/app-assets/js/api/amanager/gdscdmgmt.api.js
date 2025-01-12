function GdsCdMgmtApi(){}
var gdsCdMgmtApi = new GdsCdMgmtApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 물품분류 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
GdsCdMgmtApi.prototype.getAssetClList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/gdscdmgmt/astcl-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
}


/********************************************************************************************
 * 물품규격 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
GdsCdMgmtApi.prototype.getAssetStndList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/gdscdmgmt/aststnd-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    	
    });
}

/********************************************************************************************
 * 물품분류 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
GdsCdMgmtApi.prototype.insertAssetClCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/gdscdmgmt/insertAssetClCd', entry);
}

/********************************************************************************************
 * 물품분류 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
GdsCdMgmtApi.prototype.updateAssetClCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/gdscdmgmt/updateAssetClCd', entry);
}


/********************************************************************************************
 * 물품분류 삭제시 사용여부 확인
 * @param: 사용여부 체크할 정보(json object)
 * @return: 사용여부 체크 결과
 ********************************************************************************************/
GdsCdMgmtApi.prototype.checkUseAssetClCd = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/gdscdmgmt/checkUseAssetClCd', entry);
}

/********************************************************************************************
 * 물품분류 삭제
 * @param: 
 * @return: 
 ********************************************************************************************/
GdsCdMgmtApi.prototype.deleteAssetClCd = function(pAssetClNo, pLoading){
	var assetClNo = pAssetClNo;
	var loadingYn = pLoading;
	
	if(isNull(assetClNo)) assetClNo = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(assetClNo) && assetClNo !== '') {
	    return axios.delete('/amanager/gdscdmgmt/' + pAssetClNo);
	} else {
	    console.error('assetClNo is missing');
	}
}

/********************************************************************************************
 * 물품규격 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
GdsCdMgmtApi.prototype.insertAssetStndCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/gdscdmgmt/insertAssetStndCd', entry);
}

/********************************************************************************************
 * 물품규격 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
GdsCdMgmtApi.prototype.updateAssetStndCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/gdscdmgmt/updateAssetStndCd', entry);
}

/********************************************************************************************
 * 물품규격코드 중복체크
 * @param: 사용여부 체크할 정보(json object)
 * @return: 사용여부 체크 결과
 ********************************************************************************************/
GdsCdMgmtApi.prototype.checkAssetStndList = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/gdscdmgmt/checkAssetStndList', entry);
}

/********************************************************************************************
 * 물품규격 삭제시 사용여부 확인
 * @param: 사용여부 체크할 정보(json object)
 * @return: 사용여부 체크 결과
 ********************************************************************************************/
GdsCdMgmtApi.prototype.checkUseAssetStndCd = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/gdscdmgmt/checkUseAssetStndCd', entry);
}

/********************************************************************************************
 * 물품규격 삭제
 * @param: 
 * @return: 
 ********************************************************************************************/
GdsCdMgmtApi.prototype.deleteAssetStndCd = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/amanager/gdscdmgmt/deleteAssetStndCd',
	{
		//post는 data사용 
		params: pEntry,
		loading: loadingYn
    });

}
