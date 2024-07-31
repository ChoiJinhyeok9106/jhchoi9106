function SubscribeApi() {}

var subscribeApi = new SubscribeApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 구독 정보 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.getSubscribeList = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.get('/system-manager/subscribe', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 구독 정보 신청
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.subscription = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/system-manager/subscribe', param);
};

/********************************************************************************************
 * 구독 정보 신청
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.cancelSubscription = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.delete('/system-manager/subscribe', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};

/********************************************************************************************
 * 구독 정보 신청
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.getSubscriptionList = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.get('/system-manager/subscribe/subscription', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};


/********************************************************************************************
 * 구독 정보 신청
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.acceptService = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/system-manager/subscribe/apply', param);
};

/********************************************************************************************
 * 구독 정보 신청
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
SubscribeApi.prototype.rejectService = function(param, pLoading) {
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;
	return axios.delete('/system-manager/subscribe/apply', {
		//post는 data사용 
		params: param,
		loading: loadingYn
	});
};