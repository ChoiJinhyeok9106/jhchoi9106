function AcqMgmtApi(){}
var acqMgmtApi = new AcqMgmtApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 자산 취득 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
AcqMgmtApi.prototype.getAsstAcqMgmtList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/acqmgmt/acq-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 자산 취득 추가
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
AcqMgmtApi.prototype.assetAcqInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/acqmgmt/insertAcq', entry);
}

/********************************************************************************************
 * 자산 취득 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
AcqMgmtApi.prototype.assetAcqUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/acqmgmt/updateAcq', entry);
}

/********************************************************************************************
 * 자산 취득 삭제
 * @param: 자산취득번호
 * @return: 처리건수
 ********************************************************************************************/
AcqMgmtApi.prototype.assetAcqDelete = function(pAssetAcqNo, pLoading){
	var assetAcqNo = pAssetAcqNo;
	var loadingYn = pLoading;
	
	if(isNull(assetAcqNo)) assetAcqNo = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(assetAcqNo) && assetAcqNo !== '') {
	    return axios.delete('/amanager/acqmgmt/' + pAssetAcqNo);
	} else {
	    console.error('Asset Acquisition Number is missing');
	}
}

/********************************************************************************************
 * 자산 취득 → 보유 현황 반영
 * @param: 반영 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
AcqMgmtApi.prototype.assetDtlInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/acqmgmt/insertDtl', entry);
}


/********************************************************************************************
 * 자산 반영 여부 업데이트
 * @param: 반영 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
AcqMgmtApi.prototype.assetAcqRflcAtUpdate = function(pAssetAcqNo, pLoading){
	var assetAcqNo = pAssetAcqNo;
	var loadingYn = pLoading;
	
	if(isNull(assetAcqNo)) assetAcqNo = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/acqmgmt/' + pAssetAcqNo );
}

