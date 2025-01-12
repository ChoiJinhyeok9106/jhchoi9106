/********************************************************************************************
 *                                                                                          *
 *                                   SingleFileSet Component                                      *
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
 * SingleFileSet(pUploadPath) : 데이타를 테이블 형태로 저장하는 오브젝트입니다.
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
 * @Constructor
 * 	new SingleFileSet();
 *  // Create Object
 *  var ds = new SingleFileSet();
 ********************************************************************************************/
var SingleFileSet = function (pServiceAbbrNm, pAutoUpload, pIsUploadFixedName, pFixedFileName, pList) {
  try {
    this.fileData = []; //업로드 할 파일 데이터
    this.maxCnt = 1; //최대개수
    this.uploadPercentage = 0; //업로드 진행%
    this.progressText = "파일을 선택해 주세요"; //업로드 진행 상태 (텍스트)
    this.fileList = []; //저장 된 파일 리스트
    this.uploadPath = ""; //업로드 파일 경로
    this.striped = true; //업로드 진행상태 시 애니메이션 상태
    this.serviceAbbrNm = "";
    this.autoUpload = false; //파일 선택 시 즉시 업로드
    this.list = false; // 파일리스트 이미 존재시 데이터 불러올지 여부 (오브젝트)
    this.isUploadFixedName = pIsUploadFixedName;
    this.fixedFileName = pFixedFileName;

    this.pFileid = null;
    this.pFileKey = null;
    this.pFileHangmok = null;
    if (!isNull(pServiceAbbrNm)) this.serviceAbbrNm = pServiceAbbrNm;
    if (!isNull(pAutoUpload)) this.autoUpload = pAutoUpload;
    if (!isNull(pList)) this.list = pList;

    if (isNull(this.serviceAbbrNm)) {
      return false;
    }

    if (this.list) {
      var self = this;
      var param = {
        serviceAbbrNm: this.serviceAbbrNm,
        fileId: this.list.pFileId,
        fileKey: this.list.pFileKey,
        fileHangmok: this.list.pFileHangmok,
      };
      returnData = axios
        .get("/commons/file", {
          params: param,
        })
        .then(function (response) {
          var data = response.data;
          self.fileList = data;
          return true;
        })
        .catch(function (error) {
          return false;
        });
    }
    return this;
  } catch (err) {
    return false;
  }
};

/********************************************************************************************
 * @Description
 * 	파일 정보 초기화
 *
 * @Syntax
 * 	SingleFileSet.init();
 *
 * @Parameters
 *
 * @return
 * 	boolean : 성공여부
 ********************************************************************************************/
SingleFileSet.prototype.init = function () {
  try {
    this.fileData = []; //업로드 할 파일 데이터
    this.uploadPercentage = 0; //업로드 진행%
    this.progressText = ""; //업로드 진행 상태 (텍스트)
    this.fileList = []; //저장 된 파일 리스트
    this.striped = true; //업로드 진행상태 시 애니메이션 상태

    $("#single_img_box").remove(); //오토 업로드 false일시 init 동작 때 이미지 박스 삭제

    return true;
  } catch (err) {
    return false;
  }
};

/********************************************************************************************
 * @Description
 * 	파일 업로드 시 사용되는 메소드 입니다.
 *
 * @Syntax
 * 	SingleFileSet.uploadFile(pFileId, pFileKey, pFileHangmok)
 *
 * @Parameters
 * 	pUploadPath(String) : 업로드 될 폴더 경로 입니다.
 *
 * @return
 * 	업로드 된 파일 정보 입니다.
 ********************************************************************************************/
SingleFileSet.prototype.uploadFile = function (pFileId, pFileKey, pFileHangmok, pMessageYn) {
  try {
    let formData = new FormData();
    var uploadCount = 1;
    var returnData = null;
    var SingleFileSet = this;

    if (this.fileData.length == 0) {
      this.uploadPercentage = 0;
      this.progressText = "파일이 존재하지 않습니다.";
      return;
    }

    if (isNull(pFileId)) {
      return false;
    }
    if (isNull(pFileKey)) {
      return false;
    }
    if (isNull(pFileHangmok)) {
      return false;
    }

    if (isNull(pMessageYn)) {
      pMessageYn = true;
    }

    formData.append("serviceAbbrNm", this.serviceAbbrNm);
    formData.append("upload_path", this.serviceAbbrNm + "/" + pFileId + "/" + pFileHangmok);
    formData.append("fileId", pFileId);
    formData.append("fileKey", pFileKey);
    formData.append("fileHangmok", pFileHangmok);

    if (this.isUploadFixedName == true) {
      formData.append("upload_fixed_name", this.fixedFileName);
    }

    for (var i = 0; i < this.fileData.length; i++) {
      var fileObj = this.fileData[i];

      if (!isNull(fileObj)) {
        formData.append("file" + uploadCount, fileObj);
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
    this.progressText = "0 %";
    return returnData = axios
      .post("/commons/file/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
        onUploadProgress: function (progressEvent) {
          var per = parseInt(Math.round((progressEvent.loaded * 100) / progressEvent.total));
          this.progressText = per + " %";

          if (per >= 100) {
            this.striped = false;
            if (pMessageYn) {
              notifySubmit("success", "파일 업로드", "업로드가 완료되었습니다.", "icon-caution");
            }
          }

          this.uploadPercentage = per;
        }.bind(this),
      })
      .then(function (response) {
        var data = response.data;
		if(data['ERROR']){
			alert(data['ERROR']);
			throw data.ERROR;
		}
		
        SingleFileSet.fileList = data;
        SingleFileSet.fileData = [];
        SingleFileSet.progressText = "업로드 완료";
        if(pFileId == 'SMN_USER_V' && pFileHangmok == 'USER_PIC'){
			var ret = getSession();
			if(ret){
				ret.finally(function(){
					vmHeader.$forceUpdate();
				});
			}
			
		}
      })
      .catch(function (error) {
		SingleFileSet.fileData = [];
		SingleFileSet.uploadCount = SingleFileSet.fileList.length;
		SingleFileSet.uploadPercentage = 0;
		SingleFileSet.progressText = '업로드 실패';
      });
  } catch (err) {
    return false;
  }
};

/********************************************************************************************
 * @Description
 * 	엑셀파일 업로드 시 사용되는 메소드 입니다.
 *
 * @Syntax
 * 	SingleFileSet.excelFileUpload(pTarget)
 *
 * @Parameters
 * 	pTarget(evnet.target.files) : 업로드 할 파일정보
 *
 * @return
 * 	엑셀파일을 Array로 변경하여 리턴합니다.
 ********************************************************************************************/
/* 이주데이타 최진혁 수정_업로드된 파일명 표시 */
SingleFileSet.prototype.excelFileUpload = function (pTarget) {
    try {
        var files = pTarget.files;
        if (files.length > 0) {
            let formData = new FormData();
            var returnData = null;
            var uploadCount = 1;
            var fileNames = [];

            for (var i = 0; i < files.length; i++) {
                var fileObj = files[i];
                if (!isNull(fileObj)) {
                    formData.append("file" + uploadCount, fileObj);
                    fileNames.push(fileObj.name); // 파일명을 배열에 추가
                    uploadCount++;
                }
            }

            this.progressText = "0 %";
            returnData = axios
                .post("/commons/file/upload/excel", formData, {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                    onUploadProgress: function (progressEvent) {
                        var per = parseInt(Math.round((progressEvent.loaded * 100) / progressEvent.total));
                        this.progressText = per + " %";

                        if (per >= 100) {
                            this.striped = false;
                        }

                        this.uploadPercentage = per;
                    }.bind(this),
                })
                .then(function (response) {
                    // 파일명도 반환 데이터에 포함
                    return { data: response.data, fileNames: fileNames };
                })
                .catch(function (err) {
                    console.error(err); // 오류 출력
                });

            pTarget.type = "";
            pTarget.type = "file";

            this.progressText = `업로드 완료: ${fileNames.join(", ")}`; // 업로드된 파일명을 progressText에 추가

            return returnData;
        }
    } catch (err) {
        console.error(err); // 오류 출력
        return false;
    }
};

/********************************************************************************************
 * @Description
 * 	특정파일 정보를 가져오는 메소드 입니다.
 *
 * @Syntax
 * 	SingleFileSet.getFile(pFileId, pFileKey, pFileHangmok, pFileNo)
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
SingleFileSet.prototype.getFiles = function (pFileId, pFileKey, pFileHangmok, pFileNo) {
  try {
    var returnData = null;
    var self = this;

    if (isNull(pFileId)) {
      return false;
    }
    if (isNull(pFileKey)) {
      return false;
    }
    if (isNull(pFileHangmok)) {
      return false;
    }

    this.init();
    returnData = axios
      .get("/commons/file", {
        //post는 data사용
        params: {
          serviceAbbrNm: this.serviceAbbrNm,
          fileId: pFileId,
          fileKey: pFileKey,
          fileHangmok: pFileHangmok,
          fileNo: pFileNo,
        },
        loading: false,
      })
      .then(function (response) {
        var data = response.data;
        self.fileList = data;
        return true;
      })
      .catch(function (error) {
        return false;
      });
  } catch (err) {
    return false;
  }
};

/********************************************************************************************
 * @Description
 * 	파일을 삭제 한다.
 *
 * @Syntax
 * 	SingleFileSet.delFiles(pFileId, pFileKey, pFileHangmok, pFileNo, nRow)
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
SingleFileSet.prototype.delFiles = function (pFileId, pFileKey, pFileHangmok, pFileNo, nRow, messageYn) {
  try {
    var returnData = null;
    var self = this;

    if (isNull(pFileId)) {
      return false;
    }
    if (isNull(pFileKey)) {
      return false;
    }
    if (isNull(pFileHangmok)) {
      return false;
    }
    var p = {
      serviceAbbrNm: this.serviceAbbrNm,
      fileId: pFileId,
      fileKey: pFileKey,
      fileHangmok: pFileHangmok,
      fileNo: pFileNo,
    };
    returnData = axios
      .delete("/commons/file", {
        //post는 data사용
        params: {
          serviceAbbrNm: this.serviceAbbrNm,
          fileId: pFileId,
          fileKey: pFileKey,
          fileHangmok: pFileHangmok,
          fileNo: pFileNo,
        },
        loading: false,
      })
      .then(function (response) {
        if (!isNull(nRow)) {
          self.fileList.splice(nRow, 1);
        } else {
          self.fileList = [];
        }
        self.uploadPercentage = 0;
        
        if (messageYn != false) notifySubmit("success", "파일 삭제", "파일삭제가 완료되었습니다.", "icon-caution");
        if(self.autoUpload) $("#single_img_box").remove();
        
        if(pFileId == 'SMN_USER_V' && pFileHangmok == 'USER_PIC'){
			var ret = getSession();
			if(ret){
				ret.finally(function(){
					vmHeader.$forceUpdate();
				});
			}
			
		}
        return true;
      })
      .catch(function (error) {
        return false;
      });
  } catch (err) {
    return false;
  }
};

/********************************************************************************************
 * @Description
 * 	input file 컴포넌트에 파일이 등록 될 때마다 this.fileData에 해당 파일정보를 쌓는다.
 *
 * @Syntax
 * 	SingleFileSet.handleFileUpload(target, pFileid, pFileKey, pFileHangmok)
 *
 * @Parameters
 * target(evnet.target.files) : 업로드 할 파일정보
 * pFileId(String) : 파일아이디(테이블 명)
 * pFileKey(String) :  파일키(해당테이블의 PK조합)
 * pFileHangmok(String) : 사용자 지정 항목 (ex) logo, img, filelist 등)
 *
 * @return
 *
 ********************************************************************************************/
SingleFileSet.prototype.handleFileUpload = function (target, pFileid, pFileKey, pFileHangmok) {
  try {
    var files = target.files;

    if (files.length > 0) {
      var fileDataCnt = this.fileData.length; //올릴예정 CNT
      var fileListCnt = this.fileList.length; //올라간 CNT
      var currentFileCnt = files.length; //현재 시도하는 CNT
      var sumCnt = fileDataCnt + fileListCnt + currentFileCnt;

      if (sumCnt > this.maxCnt) {
        target.type = "";
        target.type = "file";
        //				alert('파일 업로드 가능한 최대 개수를\n초과하였습니다. ('+sumCnt+'/'+this.maxCnt+')');
        return -1;
      }

      this.uploadPercentage = 0;
      this.progressText = "";

      for (var i = 0; i < files.length; i++) {
        var fileObj = files[i];
        this.fileData.push(fileObj);
        this.progressText = "업로드 대기 : " + this.fileData.length + "개";
      }
      //es6 문법제거
      /*
			for(let fileObj of files){
				this.fileData.push(fileObj);
				this.progressText = '업로드 대기 : '+this.fileData.length+'개';
			}
			*/
      //			target.type = '';
      //			target.type = 'file';

      if (isNull(pFileid)) {
        return false;
      }
      if (isNull(pFileKey)) {
        return false;
      }
      if (isNull(pFileHangmok)) {
        return false;
      }

      if (this.autoUpload) {
        this.uploadFile(pFileid, pFileKey, pFileHangmok);
      } else {
        this.pFileid = pFileid;
        this.pFileKey = pFileKey;
        this.pFileHangmok = pFileHangmok;
      }
    } else {
      return false;
    }
    return true;
  } catch (err) {
    return false;
  }
};

SingleFileSet.prototype.conUpload = function () {
  this.uploadFile(this.pFileid, this.pFileKey, this.pFileHangmok, false);
};

/********************************************************************************************
 * @Description
 * 	현재 저장 된 파일 개수를 리턴한다.
 *
 * @Syntax
 * 	SingleFileSet.getFileCnt(files)
 *
 * @Parameters
 *
 * @return
 * fileCnt(Integer) : 파일 개수
 ********************************************************************************************/
SingleFileSet.prototype.getFileCnt = function () {
  try {
    return this.fileList.length;
  } catch (err) {
    return -1;
  }
};

/********************************************************************************************
 * @Description
 * 	현재 저장 된 파일 리스트를 가져온다.
 *
 * @Syntax
 * 	SingleFileSet.getFileList(files)
 *
 * @Parameters
 *
 * @return
 * fileList(Object) : 파일 리스트
 ********************************************************************************************/
SingleFileSet.prototype.getFileList = function () {
  try {
    return this.fileList;
  } catch (err) {
    return -1;
  }
};

/********************************************************************************************
 * @Description
 * 	파일을 다운로드 한다.
 *
 * @Syntax
 * 	SingleFileSet.download(pFilename, pFilePath)
 *
 * @Parameters
 * pFilename(String) : 실제 파일명
 * pFilePath(String) : upload폴더 하위 파일 패스
 *
 * @return
 * fileList(Object) : 파일 리스트
 ********************************************************************************************/
SingleFileSet.prototype.download = function (pFileId, pFileKey, pFileHangmok, pFileNo, pFilename, pFilePath) {
  try {
    axios({
      url: "/upload" + pFilePath,
      method: "GET",
      responseType: "blob", // important
    }).then(function (response) {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", pFilename);
      document.body.appendChild(link);

      if (navigator.appVersion.toString().indexOf(".NET") > 0) window.navigator.msSaveBlob(new Blob([response.data]), pFilename);
      else link.click();
      return true;
    });
  } catch (err) {
    return false;
  }
};
