function ApiFarmboard(){}
var apiFarmboard = new ApiFarmboard(); // => 함수로 객체를 생성

/********************************************************************************************
 * 팜보드 메인 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
ApiFarmboard.prototype.farmBoardMainList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboardApi', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
ApiFarmboard.prototype.farmBoardCardDetailList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboardApi/card-detail-list', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
ApiFarmboard.prototype.farmBoardCardAllList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboardApi/card-all-list', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 추가
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
ApiFarmboard.prototype.farmBoardInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboardApi', entry);
}




/********************************************************************************************
 * 팜보드 삭제
 * @param: 삭제 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
ApiFarmboard.prototype.farmBoardDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboardApi/', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}