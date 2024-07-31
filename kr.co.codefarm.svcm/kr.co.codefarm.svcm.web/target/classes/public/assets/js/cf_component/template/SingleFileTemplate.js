/********************************************************************************************
 * @Writer
 *  박해수 2019.01.26
 *
 * @Description
 * 	파일업로드 템플릿
 *
 * @Syntax
 *  $.get('/assets/cf_component/template/SingleFileTemplate.html', function(response){
 *		$('head').append(response);
 *	});
 * 	<single-file-uploader :fileset="Fileset" :type="'img'" v-on:exceluploadcallback="testcallback"></single-file-uploader>
 *
 * @Parameters
 *  fileset : FileSet => file: new FileSet(pSetUrl, pGetUrl, pDelUrl, pUploadPath, pMaxCnt),
 *  type : file, img
 *  filekey : 키 조합(여러개 키를 합칠 경우 #으로 붙임)
 ********************************************************************************************/

Vue.component('single-file-uploader', {
	props: ['fileset', 'type', 'fileid', 'filekey', 'filehangmok', 'modify', 'realdelyn', 'btnvisible'],
	data: function() {
		return {
			fileName: '',
			singleFileMessageModal: new ModalSet('singleFileMessageModal'),
			excelPlaceholder: '',
			messagePopupTF: false,
			mainListLoading: {
				id: 'mainListLoading',
				val: false
			},
			delTargetFile: {}
		};
	},
	template: `
		<div class="form-group" v-if="type == 'file'">
			<div class="input-file-box" v-if="fileset.getFileCnt() == 0" :ref="'mainListLoading'+fileid+filekey+filehangmok">
				<div class="file-input-div">
					<a href="#!" class="btn type02 size-m" v-if="filekey && modify"><span>찾아보기</span></a>
					<input type="file" class="file-input-hidden size-m" @change="fileCompChange($event.target)" title="파일명">
				</div>
				<input id="fileName" class="size-m" readonly="readonly"  title="파일명" :placeholder="!filekey? '저장 후 파일등록이 가능' : modify ? fileset.uploadPercentage > 0 ? fileset.uploadPercentage + '%' : fileset.progressText : fileset.progressText">
			</div>
			<div class="file-upload-type" v-else="fileset.getFileCnt() != 0" style="border:0px; padding: 0px;">
				<div class="upload-list">
			        <div class="list-box" style="height:auto; overflow-y:hidden; margin:0px;">
			            <div class="list-line" v-for="(item, index) in fileset.getFileList()" v-if="index == 0">
			                <span class="file-type">{{getExtension(item.FILE_NM)}}</span>
			                <span class="sel-text">{{item.FILE_NM}}</span>
			                <span class="file-size">{{item.FILE_SIZE > 1000 ? (item.FILE_SIZE/1024).toFixed(2)+'MB' : item.FILE_SIZE+'KB'}}</span>
			                <span class="icon-download"><a href="#!" @click="fileset.download(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, item.FILE_NO, item.FILE_NM, item.FILE_PATH)"></a></span>
			                <span class="icon-close" v-if="modify"><a href="#!" @click="realdelyn != 'N' ? fileset.delFiles(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, null, index) : delFiles(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, null, index)"></a></span>
			       	 	</div>
			        </div>
			    </div>
			</div>
			<modal-message :modalset="singleFileMessageModal" v-on:callback="callback"></modal-message>
		</div>
		
		
		<div class="form-group" v-else-if="type == 'btn'">
			<div class="input-file-btn " v-if="fileset.getFileCnt() == 0" :ref="'mainListLoading'+fileid+filekey+filehangmok">
				<div class="file-input-div ">
					<a href="#!" class="btn type02 size-m" v-if="filekey && modify"><span>찾아보기</span></a>
					<input type="file" class="file-input-hidden size-m" @change="fileCompChange($event.target)" title="파일명">
				</div>
			</div>
			<modal-message :modalset="singleFileMessageModal" v-on:callback="callback"></modal-message>
		</div>
		
		
		
		
		<div class="form-group" v-else-if="type == 'img'">
			<div class="photo-add-wrap">
				<div class="photo-box" :ref="'mainListLoading'+fileid+filekey+filehangmok">
					<div id="photo_image" v-if="fileset.getFileCnt() != 0">
						<img :src="'/upload'+item.FILE_PATH" border="0" alt="" v-for="(item, index) in fileset.getFileList()" v-if="index == 0" />
					</div>
					<!--<div class="progress simple horizontal" v-else>
                        <div class="area">
                            <div :class="{bar:'bar',striped:fileset.striped,animated:'animated'}" style="width: 0%;" :style="{width:fileset.uploadPercentage+'%'}"></div>
                        </div>
                    </div>-->
				</div>
				<div if="modify" class="btn-photo" :for="'cma_file'+filehangmok" v-show="filekey && (btnvisible != null ? btnvisible : true)" :class="{'disabled':fileset.getFileCnt() > 0}">
					<label :for="'cma_file'+filehangmok">사진등록</label>
					<input v-if="fileset.getFileCnt() == 0" type="file" :name="'cma_file'+filehangmok" :id="'cma_file'+filehangmok" accept="image/gif, image/jpeg, image/png" capture="camera" @change="fileCompChange($event.target,this,$('.photo-box'))" :disabled="!filekey"/>
				</div>
				<a href="#!" class="btn-del-photo" v-if="modify" v-show="filekey && (btnvisible != null ? btnvisible : true)" :class="{'disabled':fileset.getFileCnt() == 0}"><span class="icon icon-minus2" @click="fileDelete()">사진삭제</span></a>
			</div>
			<modal-message :modalset="singleFileMessageModal" v-on:callback="callback"></modal-message>
		</div>
		
		<div class="form-group" v-else-if="type == 'excel' && modify">
			<div class="input-file-box" :ref="'mainListLoading'+fileid+filekey+filehangmok">
				<div class="file-input-div">
					<a href="#!" class="btn type02 size-m" v-if="modify"><span>찾아보기</span></a>
					<input v-if="fileset.getFileCnt() == 0" type="file" class="file-input-hidden size-m" @change="excelFileCompChange($event.target)" title="파일명" >
				</div>
				<input id="fileName" class="size-m" readonly="readonly"  title="파일명" :placeholder="excelPlaceholder">
			</div>
			<modal-message :modalset="singleFileMessageModal" v-on:callback="callback"></modal-message>
		</div>
	`,
	watch: {
		'fileset.uploadPercentage': {
			handler: function(val, oldVal) {
				if(val > 0) {
					if(this.fileset.getFileCnt() == 0) {
						this.mainListLoading.val = true;
					}
				}
			}
		},
		'fileset.fileList': {
			handler: function(val, oldVal) {
				if(this.fileset.getFileCnt() > 0) {
					this.mainListLoading.val = false;
				}
			}
		},
		'mainListLoading.val': {
			handler: function(val, oldVal) {
				this.LoadingOverlay(this.$refs['mainListLoading' + this.fileid + this.filekey + this.filehangmok], val);
			}
		}
	},
	mounted: function() {
	}, //함수
	methods: {
		fileCompChange(pTarget,html,$target) {
			if(!pTarget.files.length)
				return;
				
			if(!(this.fileid == 'SMN_DEFAULTINFO' && this.filehangmok.startsWith('logoImg')) && pTarget.files[0].type.includes('image/svg+xml')){
            	alert('svg파일은 업로드가 불가능합니다.');
				if(this.type == 'img'){
					$("#single_img_box").remove();
				}
            	return;
			}
			
			var checkexts = false;
			var checkmimes = false;
			if(!(this.fileid == 'SMN_DEFAULTINFO' && this.filehangmok.startsWith('logoImg')) && this.type == 'img'){
				if (pTarget.files[pTarget.files.length-1].type.includes('image/png') && pTarget.files[pTarget.files.length-1].type.includes('image/jpeg') && pTarget.files[pTarget.files.length-1].type.includes('image/gif')) {
	            	alert('허용되지않은 파일형식입니다.\n jpg, png, gif와같은 형식의 파일만 업로드가가능합니다.');
					$("#single_img_box").remove();
					pTarget.type = '';
					pTarget.type = 'file';
	            	return;
				}
				else{
				    if (!isNull(pTarget.files) && !isNull(pTarget.files[0])) {
				        var reader = new FileReader();
				        	reader.onload = function(e) {
				            $target.css('display', 'block');
				            $target.html('<img  id="single_img_box" src="' + e.target.result + '" border="0" alt="" />');
				        }
				        reader.readAsDataURL(pTarget.files[0]);
				    }					
				}
			}
			else if(this.fileid == 'SMN_DEFAULTINFO' && this.filehangmok.startsWith('logoImg')){
				checkexts = true;
				checkmimes = true;
			}
			else if(this.type != 'img') {
				var files = pTarget.files;
				var extTypes = [
					  'doc'
					, 'docx'
					, 'gif'
					, 'jpeg'
					, 'jpg'
					, 'mp3'
					, 'mp4'
					, 'mpeg'
					, 'png'
					, 'pdf'
					, 'ppt'
					, 'pptx'
					, 'svg'
					, 'txt'
					, 'wav'
					, 'xls'
					, 'xlsx'
					, 'zip'
					, 'hwp'
					, 'hwpx'
				];
				var mimeTypes = [
					  'image/png'
					, 'image/jpeg'
					, 'image/gif'
				    , 'audio'
				    , 'video'
				    , 'application/pdf'
				    , 'application/x-hwp'
				    , 'application/haansofthwp'
				    , 'application/vnd.ms-excel'
				    , 'application/vnd.ms-powerpoint'
				    , 'application/vnd.ms-word'
				    , 'application/vnd.openxmlformats-officedocument'
				    , 'application/x-zip-compressed'
				    , 'application/zip'
				    , 'text/plain'
				    , 'application/msword'
				];
				var fileName = files[0].name;

				extTypes.forEach(function(value) {
					if (fileName.substring(fileName.lastIndexOf('.')+1) == value) {
						cexts = true;
						return;
					}
				});
				mimeTypes.forEach(function(value) {
					if (pTarget.files[pTarget.files.length-1].type.includes(value)) {
						checkmimes = true;
						return;
					}
				});
			}
			
			if(!checkmimes){
				alert('허용되지않은 파일형식입니다.');
				pTarget.type = '';
				pTarget.type = 'file';
				return;
			}
			if(this.type == 'img'){
				if (!pTarget.files[0].type.includes('image')) {
	            	alert('허용되지않은 파일형식입니다.\n이미지파일만 업로드가가능합니다.');
					$("#single_img_box").remove();
					pTarget.type = '';
					pTarget.type = 'file';
	            	return;
				}
				else{
				    if (!isNull(pTarget.files) && !isNull(pTarget.files[0])) {
				        var reader = new FileReader();
				        	reader.onload = function(e) {
				            $target.css('display', 'block');
				            $target.html('<img  id="single_img_box" src="' + e.target.result + '" border="0" alt="" />');
				        }
				        reader.readAsDataURL(pTarget.files[0]);
				    }					
				}
			}
			if(!this.fileset.autoUpload){
				this.fileset.init();
			}

			if(_.size(this.delTargetFile) > 0) {
				this.fileset.delFiles(this.delTargetFile.fileid, this.delTargetFile.filekey, this.delTargetFile.filehangmok, null, null);
			}

			var fileUpload = this.fileset.handleFileUpload(pTarget, this.fileid, this.filekey, this.filehangmok);
			var fileDataCnt = this.fileset.fileData.length; //올릴예정 CNT
			var fileListCnt = this.fileset.fileList.length; //올라간 CNT
			var currentFileCnt = 1; //현재 시도하는 CNT
			var sumCnt = fileDataCnt + fileListCnt + currentFileCnt;

			this.fileName = pTarget.value;
			if(fileUpload == -1) {
				this.singleFileMessageModal.openModal('normal', '파일 업로드', '파일 업로드 가능한 최대 개수를\n초과하였습니다. (' + sumCnt + '/' + this.fileset.maxCnt + ')', 'small', 'fileDelete');
			} else {
				this.$emit('uploadcallback', fileUpload);
				this.$forceUpdate();
			}
		},
		/* 이주데이타 최진혁 수정_업로드된 파일명 표시 */
		excelFileCompChange(pTarget) {
		    if (_.size(this.delTargetFile) > 0) {
		        this.fileset.delFiles(this.delTargetFile.fileid, this.delTargetFile.filekey, this.delTargetFile.filehangmok, this.delTargetFile.fileno, this.delTargetFile.row);
		    }
		
		    var returnData = this.fileset.excelFileUpload(pTarget);
		    var self = this;
		
		    returnData.then(function (response) {
		        var data = response.data.file1;
		        var fileNames = response.fileNames;
		
		        if (isNull(data)) {
		            self.excelPlaceholder = '엑셀업로드 실패';
		        } else {
		            self.excelPlaceholder = `업로드 완료 : ${fileNames.join(", ")}`;
		        }
		        self.mainListLoading.val = false;
		        self.$emit('exceluploadcallback', data);
		    });
		},
		fileDelete() {


			if(this.fileset.getFileCnt() > 0) {
				this.fileset.delFiles(this.fileid, this.filekey, this.filehangmok, null, null, this.fileset.autoUpload);
				this.$emit('filedelcallback');
			} else {
				/*this.singleFileMessageModal.openModal('normal', '파일 삭제', '삭제할 파일이 존재하지 않습니다.', 'small', 'fileDelete');*/
			}
		},
		delFiles(pFileId, pFileKey, pFileHangmok, pFileNo, nRow) {
			this.delTargetFile = {
				'fileid': pFileId,
				'filekey': pFileKey,
				'filehangmok': pFileHangmok,
				'fileno': pFileNo,
				'row': nRow
			};
			this.fileset.fileList = [];
			this.$emit('filedelcallback', {
				'pFileId': pFileId,
				'pFileKey': pFileKey,
				'pFileHangmok': pFileHangmok,
				'pFileNo': pFileNo,
				'nRow': nRow
			});
		},
		callback(pGb, pId) {

		},
		/**
		 * 파일이름에서 확장자명 추출
		 * @param fileName   파일 이름
		 * @return fileExtensiont 파일 확장자명
		 */
		getExtension: function(fileName) {
			try {
				var fileLength = fileName.length;
				var lastDot = fileName.lastIndexOf('.');

				//substring 메서드는 start에서 end까지(end는 포함 안 함) 부분 문자열을 포함하는 문자열을 반환합니다.
				var fileExtension = fileName.substring(lastDot + 1, fileLength);
				return fileExtension;
			} catch(err) {
				return false;
			}
		}
	}
});
