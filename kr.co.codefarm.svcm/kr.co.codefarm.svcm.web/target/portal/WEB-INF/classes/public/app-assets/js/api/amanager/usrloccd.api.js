function UsrloccdApi(){}
var usrloccdApi = new UsrloccdApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 사용위치관리 건물,층 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
UsrloccdApi.prototype.getBldgList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/usrloccd/bldg-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 사용위치관리 위치 리스트 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
UsrloccdApi.prototype.getLocList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/usrloccd/loc-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};


/********************************************************************************************
 * 사용위치관리 건물 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.insertBldg = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/usrloccd/insertBldg', entry);
}

/********************************************************************************************
 * 사용위치관리 건물 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.updateBldg = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/usrloccd/updateBldg', entry);
}

/********************************************************************************************
 * 사용위치관리 층 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.insertStair = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/usrloccd/insertStair', entry);
}

/********************************************************************************************
 * 사용위치관리 층 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.updateStair = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/usrloccd/updateStair', entry);
}

/********************************************************************************************
 * 사용위치관리 코드사용확인
 * @param: 중복 체크할 정보(json object)
 * @return: 중복 체크 결과
 ********************************************************************************************/
UsrloccdApi.prototype.checkOrderSeq = function(pEntry, pLoading){
    var entry = pEntry;
    var loadingYn = pLoading;
    
    if(isNull(entry)) entry = {};
    if(isNull(loadingYn)) loadingYn = false;
    
    return axios.post('/amanager/codemgmt/checkOrderSeq', entry);
}

/********************************************************************************************
 * 사용위치관리 위치 입력
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.insertLoc = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/amanager/usrloccd/insertLoc', entry);
}

/********************************************************************************************
 * 사용위치관리 위치 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
UsrloccdApi.prototype.updateLoc = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/amanager/usrloccd/updateLoc', entry);
}
