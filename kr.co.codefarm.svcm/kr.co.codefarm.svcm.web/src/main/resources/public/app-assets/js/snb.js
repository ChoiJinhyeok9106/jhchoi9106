$(document).ready(function() {
	try{
		/*openLeft();*/
	}catch(err){
		return false;
	}
});


/********************************************************************************************
 * 좌측 메뉴 부분 오픈
 ********************************************************************************************/
function openLeft(){
	try{
		$.get('/app-html/left.html',function(response){
			$('#snb').html(response); 
		});
	}catch(err){
		return false;
	}
	
}

/********************************************************************************************
 * 메뉴 클릭 시 우측 컨텐츠 영역에 페이지 오픈
 ********************************************************************************************/
function contentsOpen(pServiceCD, pPgmId, pParam, pFirstYn){
	try{
		var currentLocation = self == top ? location.href : parent.globalCurrentUrl ? parent.globalCurrentUrl : parent.location.href;
		var appName = getParameterByName('app', currentLocation);
		var pageName = '/html/error/appMenuNotFound.html';
		var serviceInfo = self == top ? staticVue.serviceCode.data : parent.staticVue.serviceCode.data;
		var serviceFindRow = _.findIndex(serviceInfo, {'service_CD':pServiceCD});
		var contenxt = serviceInfo[serviceFindRow].CONTEXT;
		
		//앱에서 사용할 전역 변수에 넘겨받은 정보 입력
		if(!isNull(pParam)){
			globalParam = typeof pParam == 'string' ?  Function("return "+ pParam)()  : pParam;
		}else{
			globalParam = {};
		}

		if(!isNull(vm)) {
			// destroy the vue listeners, etc
			vm.$destroy();	
		}
		
		$.get({
		    url: '/service/'+contenxt+'/html/sub/'+pPgmId+'.html?app='+appName,
		    type: 'HEAD',
		    async: false,
		    success: function (response) {
		    	$.get('/service/'+contenxt+'/html/sub/'+pPgmId+'.html?app='+appName, function(response){
	    			$('div.sub_cont_wrap').html(response);
	    			$("html, body").stop().animate({scrollTop:0}, 500, 'swing');
				});
		    	
		    	if(pFirstYn != true && !isPc()){
    				$('#sub_header .btn_menu').click();
				}
		    }
		});
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * pushSate 셋팅
 ********************************************************************************************/
function setPushSate(target, state, title, url){
	try{
		if(target == 'parent'){
			parent.window.history.pushState(state, title, url);
		}else if(target == 'self'){
			window.history.pushState(state, title, url);
		}
	}catch(err){
		return false;
	}
}
