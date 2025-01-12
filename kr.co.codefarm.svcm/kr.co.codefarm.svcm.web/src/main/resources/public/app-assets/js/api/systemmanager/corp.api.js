function CorpApi() {}

var corpApi = new CorpApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 기업 기관 리스트 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.getCorpList = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.get('/system-manager/corp', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 기업/기관 권한 리스트 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.getAuthList = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.get('/system-manager/corp/auth', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 기업/기관 권한 수정
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.insertAuth = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/system-manager/corp/auth', param);
};

/********************************************************************************************
 * 기업/기관 권한 리스트 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.deleteAuth = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.delete('/system-manager/corp/auth', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 선택한 기업 권한 리스트 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.getCorpAuthList = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.get('/system-manager/corp/corp-auth', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 권한 업데이트
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CorpApi.prototype.updateCorpUserAuth = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.put('/system-manager/corp/corp-user-auth', param);
};