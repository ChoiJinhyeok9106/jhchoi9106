/********************************************************************************************
 *                                                                                          *                  
 *                         EapprovalHeaderSet Component                                     *
 *                                                                                          * 
 * 작성자   : jgkang                                                                          *
 * 작성일   : 2023.04.19                                                                      *
 * ******************************************************************************************
 * 수정내역 :                                                                                
 *   - 2023.04.19 : 신규작성
 *   - 
 *   -  
 ********************************************************************************************/

/********************************************************************************************
 * 기능목록
 * ** Method  **                                                                                
 * EapprovalHeaderSet(pSize, pCloseBtn, pConfirmBtn, pType) : 기안서의 결재정보 및 결재선을 표기하는 헤더
 * 
 * ** Property **
 ********************************************************************************************/



/********************************************************************************************
 * @Description
 * 	기안서 헤더를 나타내기 위한 데이터 오브젝트입니다.
 * 
 * @Syntax 
 * 	new EapprovalHeaderSet();
 *  // Create Object
 *  var ds = new EapprovalHeaderSet(pId);
 * 
 * @Parameters
 * 
 * 
 * @return
 * EapprovalHeaderSet()
 ********************************************************************************************/
var EapprovalHeaderSet = function(pTitle, ){
	try{
		this.EApprovalTitle = pId;
		this.size = 'small'; //화면 사이즈 (small, middle, large)
		this.confirmBtn = true; //확인버튼
		this.closeBtn = false; //닫기버튼
		this.cancelBtn = false; //확인버튼 
		this.type = 'message'; //normal, blank, confirm, message
		this.title = '';
		this.message = '';
		this.show = false;
		this.currentId = '';
		this.customData = '';
		
		return this;
	}catch(err){
		return false;
	}	
}

/********************************************************************************************
 * @Description
 * 	모달 팝업을 오픈 합니다.
 * 
 * @Syntax 
 * 	ModalSet.openModal(pType, pTitle, pMessage, pSize, pId, pCustomData)
 * 
 * @Parameters
 * pType(String) : 팝업 타입 (message, confirm)
 * pTitle(String) : 팝업 제목
 * pMessage(String) : 팝업내용
 * pSize(String) : 팝업 사이즈 (small, middle, large)
 * pId(String) : 현재 오픈하는 팝업의 아이디
 * pCustomData(String) : 현재 오픈하는 팝업에 전달되는 데이터
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
ModalSet.prototype.openModal = function(pType, pTitle, pMessage, pSize, pId, pCustomData){
	try{
		if(!isNull(pSize)) this.size = pSize;
		if(!isNull(pType)) this.type = pType;
		if(!isNull(pTitle)) this.title = pTitle;
		if(!isNull(pMessage)) this.message = pMessage;
		
		if(this.type == 'message'){
			this.confirmBtn = true;
			this.closeBtn = false;
			this.cancelBtn = false;
		}else if(this.type == 'confirm'){
			this.confirmBtn = true;
			this.closeBtn = false;
			this.cancelBtn = true;
		}else{
			this.confirmBtn = true;
			this.closeBtn = false;
			this.cancelBtn = false;
		}
		
		var modalObj = $('#' + this.id); //레이어의 id를 modalObj변수에 저장
		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
	    else modalObj.css('left', '0px');
		
		$("html").css("overflow", "hidden");
		this.show = true; 
		this.currentId = pId;
		this.customData = pCustomData;
		
		return true;
	}catch(err){
		return true
	}
}

/********************************************************************************************
 * @Description
 * 	모달 팝업을 닫습니다.
 * 
 * @Syntax 
 * 	ModalSet.closeModal(pGb)
 * 
 * @Parameters
 * pGb(String) : 어떤 동작에 의해 닫는지 구분값
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
ModalSet.prototype.closeModal = function(pGb){
	try{
		this.show = false; 
		$("html").css("overflow", "auto");
		
		return pGb;
	}catch(err){
		return false;
	}
}