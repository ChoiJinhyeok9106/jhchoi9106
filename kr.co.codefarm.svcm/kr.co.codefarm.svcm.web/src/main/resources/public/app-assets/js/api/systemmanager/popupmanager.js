function Popupmanager(){}
var popupmanager = new Popupmanager(); // => 함수로 객체를 생성
/********************************************************************************************
 * 공지 리스트 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
Popupmanager.prototype.searchPopup = function(paramObj, pLoading){
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/system-manager/popupmanager',
	{
		//post는 data사용 
		params: paramObj,
		loading: loadingYn
    });
}

/********************************************************************************************
 * 공지 추가
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
Popupmanager.prototype.popupInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/system-manager/popupmanager', entry);
}

/********************************************************************************************
 * 공지 수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
Popupmanager.prototype.popupUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/system-manager/popupmanager', entry);
}

/********************************************************************************************
 * 사용여부 변경
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
Popupmanager.prototype.useYnToggle = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;

	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;

	return axios.put('/system-manager/popupmanager/useYnToggle', entry);
}

/********************************************************************************************
 * 공지  삭제
 * @param: 사이트 순번
 * @return: 처리건수
 ********************************************************************************************/
Popupmanager.prototype.popupDelete = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.delete('/system-manager/popupmanager',
		{
			//post는 data사용
			params: entry,
			loading: loadingYn
		});
}