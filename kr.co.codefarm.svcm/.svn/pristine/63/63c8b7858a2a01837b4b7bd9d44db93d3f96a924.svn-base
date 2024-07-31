function EmailApi(){}
var emailApi = new EmailApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * 이메일 발송
 * @param: 발송정보 및 내용
 * @return: 조회 리스트
 ********************************************************************************************/
EmailApi.prototype.emailSend = function(entry, pLoading){
    var loadingYn = pLoading;
	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/commons/mail/send', entry);
}

/********************************************************************************************
 * 이메일 발송
 * @param: 발송정보 및 내용
 * @return: 조회 리스트
 ********************************************************************************************/
EmailApi.prototype.emailFileSend = function(entry, fileKey, pLoading){
    var loadingYn = pLoading;
    entry['FILE_KEY'] = fileKey;
	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/commons/mail/file', entry);
}
