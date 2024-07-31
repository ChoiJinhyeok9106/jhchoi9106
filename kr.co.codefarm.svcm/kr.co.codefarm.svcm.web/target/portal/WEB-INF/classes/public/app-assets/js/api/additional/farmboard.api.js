function FarmboardApi(){}
var farmboardApi = new FarmboardApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 초대 카운트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.invitedCnt = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/invited-cnt', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 메인 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardMainList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard', 
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
FarmboardApi.prototype.farmBoardInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard', entry);
}

/********************************************************************************************
 * 팜보드 수정
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard', entry);
}

/********************************************************************************************
 * 팜보드 삭제
 * @param: 삭제 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 탈퇴
 * @param: 삭제 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardSecession = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/secession', 
	{
		//post는 data사용 
		params: JSON.parse(JSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 라벨 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.labelList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/label', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 라벨 삭제
 * @param: 삭제 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.labelDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/label', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 초대하기
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.sendInvitation = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/send-invitation', entry);
}

/********************************************************************************************
 * 참여자 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardChamyeojaList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/chamyeoja', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 리스트 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardList = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/list', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 리스트 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/list', entry);
}

/********************************************************************************************
 * 팜보드 리스트 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/list', entry);
}

/********************************************************************************************
 * 팜보드 리스트 순서 변경 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListOrdUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/list-ord', entry);
}

/********************************************************************************************
 * 팜보드 리스트 삭제
 * @param: 삭제할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/list', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCard = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/card', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardLabel = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/card-label', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/card', entry);
}

/********************************************************************************************
 * 팜보드 카드 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardListOrdUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card-ord', entry);
}

/********************************************************************************************
 * 팜보드 카드 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card', entry);
}

/********************************************************************************************
 * 팜보드 카드 삭제
 * @param: 삭제할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/card', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 상세 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetail = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/card-detail', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 체크리스트 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardChkListInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/card-check-list', entry);
}

/********************************************************************************************
 * 팜보드 카드 체크리스트 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardChkListUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card-check-list', entry);
}

/********************************************************************************************
 * 팜보드 카드 상세 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/card-detail', entry);
}

/********************************************************************************************
 * 팜보드 카드 상세 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card-detail', entry);
}

/********************************************************************************************
 * 팜보드 카드 대표 이미지 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDaePyoImgUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card-daepyo-img', entry);
}


/********************************************************************************************
 * 팜보드 카드 상세 삭제
 * @param: 삭제할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/card-detail', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 상세 항목 조회
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailItem = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/card-detail-item', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}


/********************************************************************************************
* 팜보드 카드 상세 항목 신규 
* @param: 등록할 정보
* @return: 처리건수
********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailItemInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/card-detail-item', entry);
}

/********************************************************************************************
* 팜보드 카드 상세 항목 수정 
* @param: 수정할 정보
* @return: 처리건수
********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailItemUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/card-detail-item', entry);
}

/********************************************************************************************
* 팜보드 카드 상세 항목 삭제
* @param: 삭제할 정보
* @return: 처리건수
********************************************************************************************/
FarmboardApi.prototype.farmBoardCardDetailItemDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/card-detail-item', 
	{
		//post는 data사용 
		params: param,
	 	loading: loadingYn
	 });
}

/********************************************************************************************
 * 팜보드 리스트 참여자 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListChamyeoja = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/list-chamyeoja', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 리스트 참여자 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListChamyeojaInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/list-chamyeoja', entry);
}

/********************************************************************************************
 * 팜보드 리스트 참여자 수정 
 * @param: 수정할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListChamyeojaUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/list-chamyeoja', entry);
}

/********************************************************************************************
 * 팜보드 리스트 참여자 삭제
 * @param: 삭제할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardListChamyeojaDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/list-chamyeoja', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 참여자 정보 수정
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardChamyeojaUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/chamyeoja', entry);
}

/********************************************************************************************
 * 참여자 정보 삭제
 * @param: 삭제 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardChamyeojaDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/chamyeoja', 
	{
		//post는 data사용 
		params: JSON.parse(JpSON.stringify(param)),
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 참여자 북마크 정보 수정
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.bookmarkUpdate = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/bookmark', entry);
}

/********************************************************************************************
 * 초대 답변 저장
 * @param: 등록 할 정보(json object)
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.answInvitiation = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.put('/additional/farmboard/answ-invitiation', entry);
}

/********************************************************************************************
 * 팜보드 카드 참여자 조회 
 * @param: 검색구분, 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardChamyeoja = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.get('/additional/farmboard/card-chamyeoja', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

/********************************************************************************************
 * 팜보드 카드 참여자 신규 
 * @param: 등록할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardChamyeojaInsert = function(pEntry, pLoading){
	var entry = pEntry;
	var loadingYn = pLoading;
	
	if(isNull(entry)) entry = {};
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.post('/additional/farmboard/card-chamyeoja', entry);
}

/********************************************************************************************
 * 팜보드 카드 참여자 삭제
 * @param: 삭제할 정보
 * @return: 처리건수
 ********************************************************************************************/
FarmboardApi.prototype.farmBoardCardChamyeojaDelete = function(param, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) loadingYn = false;
	
	return axios.delete('/additional/farmboard/card-chamyeoja', 
	{
		//post는 data사용 
		params: param,
    	loading: loadingYn
    });
}

