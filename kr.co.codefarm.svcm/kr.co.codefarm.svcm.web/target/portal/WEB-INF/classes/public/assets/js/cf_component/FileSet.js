/********************************************************************************************
 *                                                                                          *                  
 *                                   FileSet Component                                      *
 *                                                                                          * 
 * 작성자   : 박해수                                                                        *
 * 작성일   : 2019.01.23                                                                    *
 * ******************************************************************************************
 * 수정내역 :                                                                                
 *   - 2019.01.23 : 신규작성
 *   - 
 *   -  
 ********************************************************************************************/

/********************************************************************************************
 * 기능목록
 * ** Method  **                                                                                
 * FileSet(pUploadPath, pMaxCnt) : 데이타를 테이블 형태로 저장하는 오브젝트입니다. 
 * uploadFile(pUploadPath): 파일 업로드 시 사용되는 메소드 입니다.
 * getFileList(pFileId, pFileKey, pFileHangmok) : 파일 리스트를 가져오는 메소드 입니다.
 * getFile(pFileId, pFileKey, pFileHangmok, pFileNo) : 특정파일 정보를 가져오는 메소드 입니다.
 * handleFileUpload(files) : input file 컴포넌트에 파일이 등록 될 때마다 this.fileData에 해당 파일정보를 쌓는다.
 * getFileCnt : 현재 저장 된 파일 개수를 가져온다.
 * getFileList : 현재 저장 된 파일 리스트를 가져온다.
 * 
 * ** Property **
 ********************************************************************************************/



/********************************************************************************************
 * @Description
 * 	파일전송을 위한 파일 오브젝트입니다.
 * 
 * @Syntax 
 * 	new FileSet(pServiceAbbrNm, pMaxCnt);
 *  // Create Object
 *  var ds = new FileSet(pServiceAbbrNm, pMaxCnt);
 * 
 * @Parameters
 * pServiceAbbrNm(String) : 서비스 약어(3자리)
 * pMaxCnt(String) : 파일첨부 제한 건수
 * 
 * @return
 * 	FileSet()
 ********************************************************************************************/
var FileSet = function(pServiceAbbrNm, pMaxCnt, pIsUploadFixedName){
	try{
		this.fileData = []; //업로드 할 파일 데이터
		this.maxCnt = 5; //최대개수
		this.uploadPercentage = 0; //업로드 진행%
		this.progressText = ''; //업로드 진행 상태 (텍스트)
		this.fileList = []; //저장 된 파일 리스트
		this.uploadPath = ''; //업로드 파일 경로
		this.striped = true; //업로드 진행상태 시 애니메이션 상태
		this.fileCntPercentage = 0; //파일 카운터 퍼센트
		this.serviceAbbrNm = '';
		this.pIsUploadFixedName = pIsUploadFixedName;
		
		if(!isNull(pServiceAbbrNm)) this.serviceAbbrNm = pServiceAbbrNm;
		if(!isNull(pMaxCnt)) this.maxCnt = pMaxCnt;
		
		if(isNull(this.serviceAbbrNm)){
			return false;
		}
		
		return this;
	}catch(err){
		return false;
	}	
}

/********************************************************************************************
 * @Description
 * 	파일 정보 초기화
 * 
 * @Syntax 
 * 	FileSet.init();
 * 
 * @Parameters
 * 
 * @return
 * 	boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.init = function(){
	try{
		this.fileData = []; //업로드 할 파일 데이터
		this.uploadPercentage = 0; //업로드 진행%
		this.progressText = ''; //업로드 진행 상태 (텍스트)
		this.fileList = []; //저장 된 파일 리스트
		this.striped = true; //업로드 진행상태 시 애니메이션 상태
		this.fileCntPercentage = 0; //파일 카운터 퍼센트
        this.setFileCntUIInit();
        
		return true;
	}catch(err){
		return false;
	}	
}

/********************************************************************************************
 * @Description
 * 	파일 업로드 시 사용되는 메소드 입니다.
 * 
 * @Syntax 
 * 	FileSet.uploadFile(pFileId, pFileKey, pFileHangmok)
 * 
 * @Parameters
 * 	pFileId(String) : 업로드 대상 테이블 명 입니다.
 * 	pFileKey(String) : 업로드 대상 키 조합 입니다.(구분자 : #)
 * 	pFileHangmok(String) : 업로드 될 항목 명입니다.(예)logo, screenshot..) 
 * 
 * @return
 * 	성공여부(Boolean)
 ********************************************************************************************/
FileSet.prototype.uploadFile = function(pFileId, pFileKey, pFileHangmok){
	try{
		let formData = new FormData();
        var uploadCount = 1;
        var returnData = null;
        var fileSet = this;
        
        if(this.fileData.length == 0){
        	this.uploadPercentage = 0;
        	this.progressText = '파일이 존재하지 않습니다.';
        	return;
        }
		
        if(isNull(pFileId)){
        	return false;
        } 
        if(isNull(pFileKey)){
        	return false;
        }
        if(isNull(pFileHangmok)){
        	return false;
        }
        
        formData.append('serviceAbbrNm', this.serviceAbbrNm);
        formData.append('upload_path', this.serviceAbbrNm + '\\' + pFileId + '\\' + pFileHangmok);
        formData.append('fileId', pFileId);
        formData.append('fileKey', pFileKey);
        formData.append('fileHangmok', pFileHangmok);
        
        if(this.pIsUploadFixedName == true){
        	formData.append('upload_fixed_name', pFileHangmok);
        }
        
        for(var i=0 ; i < this.fileData.length ; i++){
        	var fileObj = this.fileData[i];
        	
        	if(!isNull(fileObj)){
        		formData.append('file'+uploadCount, fileObj);	
        		uploadCount++;
        	}
        }
        //2019.02.27 박해수 : es6문법 제거
        /*
        for(let fileObj of this.fileData){
        	if(!isNull(fileObj)){
        		formData.append('file'+uploadCount, fileObj);	
        		uploadCount++;
        	}
        }
        */
        this.progressText = '0 %';
        returnData = axios.post('/commons/file/upload',
    		formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                onUploadProgress: function( progressEvent ) {
                	var per = parseInt( Math.round( ( progressEvent.loaded * 100 ) / progressEvent.total ) );
                	this.progressText = per + ' %';
                	
                	if(per >= 100){
                		this.striped = false;
                	}
                	
                    this.uploadPercentage = per;
                }.bind(this)
            }
        ).then(function(response) {
			var data = response.data;
			if(data['ERROR']){
				alert(data['ERROR']);
				throw data.ERROR;
			}
				
			fileSet.fileList = data;
			fileSet.fileData = [];
			fileSet.progressText = '업로드 완료';
    		return true;
		}).catch(function(error) {
			fileSet.fileData = [];
			fileSet.uploadCount = fileSet.fileList.length;
			fileSet.uploadPercentage = 0;
			fileSet.progressText = '업로드 실패';
			fileSet.calcFileCntPercentage();
			fileSet.setFileCntUIInit();
			return false;
		});
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * @Description
 * 	파일 리스트를 가져오는 메소드 입니다.
 * 
 * @Syntax 
 * 	FileSet.getFile(pFileId, pFileKey, pFileHangmok, pFileNo)
 * 
 * @Parameters
 * pFileId(String) : 파일아이디(테이블 명)
 * pFileKey(String) :  파일키(해당테이블의 PK조합)
 * pFileHangmok(String) : 사용자 지정 항목 (ex) logo, img, filelist 등)
 * pFileNo(Integer) : 파일 순번
 * 
 * @return
 * 	파일리스트
 ********************************************************************************************/
FileSet.prototype.getFiles = function(pFileId, pFileKey, pFileHangmok, pFileNo){
	try{
		var returnData = null;
		var self = this;
		
        if(isNull(pFileId)){
        	return false;
        } 
        if(isNull(pFileKey)){
        	return false;
        }
        if(isNull(pFileHangmok)){
        	return false;
        }
        
        this.init();
        returnData = axios.get('/commons/file', 
		{
			//post는 data사용 
			params: {
				serviceAbbrNm: this.serviceAbbrNm,
				fileId: pFileId,
				fileKey: pFileKey,
				fileHangmok: pFileHangmok,
				fileNo: pFileNo
	    	},
	    	loading: false
	    }).then(function(response) {
			var data = response.data;
			
			self.fileList = data;
		});
        
        return returnData;
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * @Description
 * 	파일을 삭제 합니다.
 * 
 * @Syntax 
 * 	FileSet.delFiles(pFileId, pFileKey, pFileHangmok, pFileNo, nRow)
 * 
 * @Parameters
 * pFileId(String) : 파일아이디(테이블 명)
 * pFileKey(String) :  파일키(해당테이블의 PK조합)
 * pFileHangmok(String) : 사용자 지정 항목 (ex) logo, img, filelist 등)
 * pFileNo(Integer) : 파일 순번
 * nRow : 삭제 할 파일 행
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.delFiles = function(pFileId, pFileKey, pFileHangmok, pFileNo, nRow){
	try{
		var returnData = null;
		var self = this;
		
        if(isNull(pFileId)){
        	return false;
        } 
        if(isNull(pFileKey)){
        	return false;
        }
        if(isNull(pFileHangmok)){
        	return false;
        }
        
        returnData = axios.delete('/commons/file', 
		{
			//post는 data사용 
			params: {
				serviceAbbrNm: this.serviceAbbrNm,
				fileId: pFileId,
				fileKey: pFileKey,
				fileHangmok: pFileHangmok,
				fileNo: pFileNo
	    	},
	    	loading: false
	    }).then(function(response) {
	    	self.fileList.splice(nRow, 1);
	    	self.uploadPercentage = 0;
	    	self.calcFileCntPercentage();
	    	self.setFileCntUIInit();
	        return true;
		}).catch(function(error) {
			return false;
		});
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * @Description
 * 	input file 컴포넌트에 파일이 등록 될 때마다 this.fileData에 해당 파일정보를 쌓습니다.
 * 
 * @Syntax 
 * 	FileSet.handleFileUpload(pTarget)
 * 
 * @Parameters
 * files(evnet.target.files) : 업로드 할 파일정보
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.handleFileUpload = function(pTarget){
	try{
		var files = pTarget.files;
		if(files.length > 0){
			var fileDataCnt = this.fileData.length; //올릴예정 CNT
			var fileListCnt = this.fileList.length; //올라간 CNT
			var currentFileCnt = files.length; //현재 시도하는 CNT
			var sumCnt = fileDataCnt+fileListCnt+currentFileCnt;
			
			if(sumCnt > this.maxCnt){
				pTarget.type = '';
				pTarget.type = 'file';
//				alert('파일 업로드 가능한 최대 개수를\n초과하였습니다. ('+sumCnt+'/'+this.maxCnt+')');
				return -1;
			}
						
			this.uploadPercentage = 0;
			this.progressText = '';
			
			for(var i=0 ; i < files.length ; i++){
				var fileObj = files[i];
				
				this.fileData.push(fileObj);
				this.progressText = '업로드 대기 : '+this.fileData.length+'개';
			}
			//es6 문법 제거
			/*
			for(let fileObj of files){
				this.fileData.push(fileObj);
				this.progressText = '업로드 대기 : '+this.fileData.length+'개';
			}
			*/
			pTarget.type = '';
			pTarget.type = 'file';
			
			this.calcFileCntPercentage();
			this.setFileCntUIInit();
		}else{
			return false;
		}
		return true;
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * @Description
 * 	input file 컴포넌트에 파일이 드래그 시작될 때 실행되는 이벤트 입니다.
 * 
 * @Syntax 
 * 	FileSet.handleDragOver(pTarget)
 * 
 * @Parameters
 * files($evnet.target) : 업로드 할 Object
 * 
 * @return
 * 	boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.handleDragOver = function(pTarget){
	try{
		var divObj = $(pTarget).parents('div.upload-viewer:eq(0)');
		
		if($(divObj).hasClass("invalid") != true) {
			$(divObj).addClass('invalid');
			return true;
		}
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	input file 컴포넌트에 파일이 드래그 벗어날때 실행되는 이벤트 입니다.
 * 
 * @Syntax 
 * 	FileSet.handleDragLeave(pTarget)
 * 
 * @Parameters
 * files($evnet.target) : 업로드 할 Object
 * 
 * @return
 * 	boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.handleDragLeave = function(pTarget){
	try{
		var divObj = $(pTarget).parents('div.upload-viewer:eq(0)');
		
		if($(divObj).hasClass("invalid") === true) {
			$(divObj).removeClass('invalid');
			return true;
		}
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	현재 저장 된 파일 개수를 리턴합니다.
 * 
 * @Syntax 
 * 	FileSet.getFileCnt()
 * 
 * @Parameters
 * 
 * @return
 * fileCnt(Integer) : 파일 개수
 ********************************************************************************************/
FileSet.prototype.getFileCnt = function(){
	try{
		return this.fileList.length;
	}catch(err){
		return -1;
	}
}

/********************************************************************************************
 * @Description
 * 	현재 저장 된 파일 및 저장하려고 하는 파일개수를 합하여 리턴합니다.
 * 
 * @Syntax 
 * 	FileSet.getFileCurrentCnt()
 * 
 * @Parameters
 * 
 * @return
 * fileCnt(Integer) : 파일 개수
 ********************************************************************************************/
FileSet.prototype.getFileCurrentCnt = function(){
	try{
		return this.fileData.length + this.fileList.length;
	}catch(err){
		return -1;
	}
}

/********************************************************************************************
 * @Description
 * 	현재 저장 된 파일 리스트를 리턴합니다.
 * 
 * @Syntax 
 * 	FileSet.getFileList()
 * 
 * @Parameters
 * 
 * @return
 * fileList(Object) : 파일 리스트
 ********************************************************************************************/
FileSet.prototype.getFileList = function(){
	try{
		return this.fileList;
	}catch(err){
		return -1;
	}
}

/********************************************************************************************
 * @Description
 * 	파일업로드 최대개수와, 현재개수를 나타내는 UI스크립트 호출 합니다.
 * 
 * @Syntax 
 * 	FileSet.setFileCntUIInit()
 * 
 * @Parameters
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.setFileCntUIInit = function(){
	try{
		//progress circle
		$('.circlechart').circlechart(this.fileCntPercentage); // Initialization
		return true;
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	이미지 파입 업로드 컴포넌트 일 경우 이미지 마우스 오버 이벤트 입니다.
 * 
 * @Syntax 
 * 	FileSet.imgMouseover(pTarget)
 * 
 * @Parameters
 *  target(Object) : img 감싸고 있는 Object
 *  
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.imgMouseover = function(pTarget){
	try{
		//@mouseover="imgMouseover"
	    $(pTarget).find('.hidden-layer').stop().fadeIn();
	    return true;
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	이미지 파입 업로드 컴포넌트 일 경우 이미지 마우스 리브 이벤트 입니다.
 * 
 * @Syntax 
 * 	FileSet.imgMouseleave(pTarget)
 * 
 * @Parameters
 * target(Object) : img 감싸고 있는 Object
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.imgMouseleave = function(pTarget){
	try{
		$(pTarget).find('.hidden-layer').stop().fadeOut();
		return true;
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	파일최대개수 기준 현재개수 퍼센테이지 값을 설정 합니다.
 * 
 * @Syntax 
 * 	FileSet.calcFileCntPercentage()
 * 
 * @Parameters
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.calcFileCntPercentage = function(){
	
	try{
		this.fileCntPercentage = (this.fileData.length + this.fileList.length) * 100 / this.maxCnt;
		return true;
	}catch(err){
		return false;
	}
}

/********************************************************************************************
 * @Description
 * 	파일을 다운로드 한다.
 * 
 * @Syntax 
 * 	FileSet.download(pFileId, pFileKey, pFileHangmok, pFileNo, pFilename, pFilePath)
 * 
 * @Parameters
 * pFileId(String) : 파일아이디(테이블 명)
 * pFileKey(String) :  파일키(해당테이블의 PK조합)
 * pFileHangmok(String) : 사용자 지정 항목 (ex) logo, img, filelist 등)
 * pFileNo(Integer) : 파일 순번
 * pFilename(Integer) : 파일명
 * pFilePath(Integer) : 파일경로
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.download = function(pFileId, pFileKey, pFileHangmok, pFileNo, pFilename, pFilePath){
	try{
		var downloadCall = axios.get('/upload'+pFilePath, 
		{
	    	responseType: 'blob',
	    });
		
		downloadCall.then(function(response) {
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement('a');
			
			link.href = url;
			link.setAttribute('download', pFilename);
			document.body.appendChild(link);

			if (navigator.appVersion.toString().indexOf('.NET') > -1 || navigator.appVersion.toString().indexOf('MSIE') > -1)
		        window.navigator.msSaveBlob(new Blob([response.data]), pFilename);
			else link.click();
			
			return true;
		});
	}catch(err){
		return false;
	}
}


/********************************************************************************************
 * @Description
 * 	파일 최대개수를 변경 합니다.
 * 
 * @Syntax 
 * 	FileSet.setMaxCnt(pCnt)
 * 
 * @Parameters
 * pCnt(Integer) : 최대 개수
 * 
 * @return
 * boolean : 성공여부
 ********************************************************************************************/
FileSet.prototype.setMaxCnt = function(pCnt){
	try{
		this.maxCnt = parseInt(pCnt,10);
		return true;
	}catch(err){
		return false;
	}
}