function CommonSearchApi(){}
var commonSearchApi = new CommonSearchApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 보유 부서 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CommonSearchApi.prototype.getDeptList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/common/dept-list",
	{
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 부서 트리 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CommonSearchApi.prototype.getDeptTreeList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/common/depttree-list",
	{
		params: pEntry,
    	loading: loadingYn
    });
};


/********************************************************************************************
 * 물품 목록 번호 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CommonSearchApi.prototype.getAsstNoList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/common/assetno-list",
	{
		params: pEntry,
    	loading: loadingYn
    });
};

/********************************************************************************************
 * 사용자 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
CommonSearchApi.prototype.getUsrList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/common/usr-list",
	{
		params: pEntry,
    	loading: loadingYn
    });
};

