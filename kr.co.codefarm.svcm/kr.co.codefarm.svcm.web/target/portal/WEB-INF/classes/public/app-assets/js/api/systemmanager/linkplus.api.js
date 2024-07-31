function LinkplusApi(){}
var linkplusApi = new LinkplusApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 링크플러스 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
LinkplusApi.prototype.linkpluss = function(pParam, pLoading){
    var loadingYn = pLoading;
	if(isNull(loadingYn)) loadingYn = false;
    
	return axios.get('/system-manager/link-plus', 
	{
		params: JSON.parse(JSON.stringify(pParam)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 링크플러스  추가
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
LinkplusApi.prototype.linkplusInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/system-manager/link-plus', entry);
}

/********************************************************************************************
 * 링크플러스  수정
 * @param: 수정 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
LinkplusApi.prototype.linkplusUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/system-manager/link-plus', entry);
}

/********************************************************************************************
 * 링크플러스  삭제
 * @param: Alarm pk
 * @return: 처리건수
 ********************************************************************************************/
LinkplusApi.prototype.linkplusDelete = function(pEntry, pLoading){
	var loadingYn = pLoading;
	if(isNull(loadingYn)) loadingYn = false;
	return axios.delete('/system-manager/link-plus', {
		params: {
			'LINK_SEQ':pEntry.LINK_SEQ,
		},
    	loading: loadingYn
	});
}
