function HaveStatusHistApi(){}
var haveStatusHistApi = new HaveStatusHistApi(); // => 함수로 객체를 생성
/********************************************************************************************
 * 보유 이력 조회 
 * @param: 검색어
 * @return: 조회 리스트
 ********************************************************************************************/
HaveStatusHistApi.prototype.getHaveStatusHistList = function(pEntry, pLoading){
	var loadingYn = pLoading;
	
	if(isNull(loadingYn)) 		loadingYn 		= false;
    
    return axios.get("/amanager/havestatushist/hist-list",
	{
		//post는 data사용 
		params: pEntry,
    	loading: loadingYn
    });
};

