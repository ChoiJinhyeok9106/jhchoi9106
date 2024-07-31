function SmsApi(){}
var smsApi = new SmsApi(); // => 함수로 객체를 생성

/********************************************************************************************
 * SMS 발송
 * @param: 발송정보 및 내용
 * @return: 조회 리스트
 * RECV에는 RECV_NUMBER 만 있으면 됨
 ********************************************************************************************/
SmsApi.prototype.smsSend = function(recv, sendNumber, sendMsg, pLoading){
    var loadingYn = pLoading;
	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/commons/sms/send', {
		data: {
			'RECV': Function("return "+recv)(), 
			'SEND_NUMBER': sendNumber,
			'SEND_MSG': sendMsg,
		}
	});
}

/********************************************************************************************
 * SMS 발송
 * @param: 발송정보 및 내용의 데이터셋
 * @return: 조회 리스트
 * RECV에는 RECV_NUMBER 만 있으면 됨
 ********************************************************************************************/
SmsApi.prototype.smsSendList = function(recv, pLoading){
    var loadingYn = pLoading;
	if(isNull(loadingYn)) loadingYn = false;
	return axios.post('/commons/sms/send-list',  Function("return "+recv)());
}
