function HaveExcelApi(){}
var haveExcelApi = new HaveExcelApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 엑셀 업로드 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
HaveExcelApi.prototype.getHaveExcelList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 	loadingYn 	= false;
    
    return axios.get("/amanager/haveexcel/haveexcel-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 엑셀업로드 삭제
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.deleteHaveExcel = function(pAgencyId, pLoading){
	var agencyId = pAgencyId;
	var loadingYn = pLoading;
	
	if(isNull(agencyId)) agencyId = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(agencyId) && agencyId !== '') {
	    return axios.delete('/amanager/haveexcel/deleteHaveExcel/' + pAgencyId);
	} else {
	    console.error('Agency Id is missing');
	}
}

/********************************************************************************************
 * 엑셀 업로드 입력 
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.insertHaveExcel = async function(pEntry) {
    var entry = pEntry;


    if (isNull(entry)) entry = {};

    try {
        const response = await axios.post('/amanager/haveexcel/insert-haveexcel', entry, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

        return response.data;
    } catch (error) {
        throw error;
    }
};

/********************************************************************************************
 * 엑셀 업로드 검증
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.updateMdtr = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/haveexcel/updateMdtr', entry);
}

/********************************************************************************************
 * 엑셀 업로드 검증(자산관리번호 변경)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.updateAssetMgmtNo = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/haveexcel/updateAssetMgmtNo', entry);
}

/********************************************************************************************
 * 엑셀 업로드 검증(물품분류번호 생성)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.insertAsstClCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/haveexcel/insertAsstClCd', entry);
}

/********************************************************************************************
 * 엑셀 업로드 검증(물품분류번호 생성)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.insertAsstStndCd = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/haveexcel/insertAsstStndCd', entry);
}

/********************************************************************************************
 * 자산 보유 반영(이력현황 삭제)
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.deleteAcqDtlHist = function(pAgencyId, pLoading){
	var agencyId = pAgencyId;
	var loadingYn = pLoading;
	
	if(isNull(agencyId)) agencyId = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(agencyId) && agencyId !== '') {
	    return axios.delete('/amanager/haveexcel/deleteAcqDtlHist/' + pAgencyId);
	} else {
	    console.error('Agency Id is missing');
	}
}

/********************************************************************************************
 * 자산 보유 반영(발급현황 삭제)
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.deletePrtIssu = function(pAgencyId, pLoading){
	var agencyId = pAgencyId;
	var loadingYn = pLoading;
	
	if(isNull(agencyId)) agencyId = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(agencyId) && agencyId !== '') {
	    return axios.delete('/amanager/haveexcel/deletePrtIssu/' + pAgencyId);
	} else {
	    console.error('Agency Id is missing');
	}
}

/********************************************************************************************
 * 자산 보유 반영(보유현황 삭제)
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.deleteAcqDtl = function(pAgencyId, pLoading){
	var agencyId = pAgencyId;
	var loadingYn = pLoading;
	
	if(isNull(agencyId)) agencyId = '';
	if(isNull(loadingYn)) loadingYn = false;
	
	if (!isNull(agencyId) && agencyId !== '') {
	    return axios.delete('/amanager/haveexcel/deleteAcqDtl/' + pAgencyId);
	} else {
	    console.error('Agency Id is missing');
	}
}

/********************************************************************************************
 * 자산 보유 반영(보유현황 입력)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.insertAcqDtl = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/haveexcel/insertAcqDtl', entry);
}

/********************************************************************************************
 * 자산 보유 반영(취득관리 입력)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.insertAcq = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/haveexcel/insertAcq', entry);
}

/********************************************************************************************
 * 자산 보유 반영(보유현황 수정)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.updateAcqDtl = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/haveexcel/updateAcqDtl', entry);
}

/********************************************************************************************
 * 자산 보유 반영(반영여부 수정)
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
HaveExcelApi.prototype.updateRflcYn = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/haveexcel/updateRflcYn', entry);
}





