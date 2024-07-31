function TransAuthApiApi(){}
var transAuthApiApi = new TransAuthApiApi(); //인스턴스 생성

/********************************************************************************************
 * 다른 홈페이지에 전달한 Auth 정보 생성
 * @param: 로딩 버튼
 * @return: 게시판 목록
 ********************************************************************************************/
TransAuthApiApi.prototype.getAuthInfo = function(linkedSiteName, pLoading){
	var loadingYn = pLoading;

	if(isNull(loadingYn)) loadingYn = false;

	return axios.get('/api/additional/transauth/auth', {
		params: {
			linkedSiteName: linkedSiteName
		},
		loading: loadingYn
	});
};

TransAuthApiApi.prototype.openWindowWithPost = function(url, data){
    var form = document.createElement("form");
    form.target = "_blank";
    form.method = "POST";
    form.action = url;
    form.style.display = "none";

    for (var key in data) {
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = key;
        input.value = data[key];
        form.appendChild(input);
    }

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
};

TransAuthApiApi.prototype.openWindow = function(url, data){
    window.open(url + "?data=" + data, '_blank').focus();
};