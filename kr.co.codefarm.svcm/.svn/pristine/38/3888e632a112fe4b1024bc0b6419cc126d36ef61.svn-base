/********************************************************************************************
 * @Writer
 *  박해수 2019.01.26
 *
 * @Description
 * 	파일업로드 템플릿
 *
 * @Syntax
 *  $.get('/assets/cf_component/template/MultiFileTemplate.html', function(response){
 *		$('head').append(response); 
 *	});
 * 	<file-uploader :fileset="screenshotFile" :type="'img'"></file-uploader>
 *
 * @Parameters
 *  fileset : FileSet => screenshotFile: new FileSet(pSetUrl, pGetUrl, pDelUrl, pUploadPath, pMaxCnt),
 *  type : list, img
 *  filekey : 키 조합(여러개 키를 합칠 경우 #으로 붙임)
 ********************************************************************************************/

Vue.component('file-uploader', {
	props: ['fileset', 'type', 'fileid', 'filekey', 'filehangmok', 'title', 'modify', 'floatnone', 'imgwidth', 'uploadbtn'],
	data: function(){
		return{
			mouseOver: -1,
			multiFileMessageModal: new ModalSet('multiFileMessageModal'),
			uploadbtnyn: false
		}
	},
	template:`
		<div class="form-group">
			<label for="exampleFormControlInput1" class="label-type01">{{title}}</label>
	        <div class="file-upload-type" :class="{'pd-none':floatnone}">
	            <div class="upload-viewer" v-if="modify" :class="{'float-none':floatnone}">
	                <span class="circlechart-text01">{{fileset.getFileCurrentCnt()}}</span><span class="circlechart-text02">{{fileset.maxCnt}}</span>
	                <div class="circlechart"></div>
	                <div class="info-text" v-show="filekey">파일을 <span>드래그 앤 드롭</span> 하거나 파일찾기를 눌러 선택한 후 업로드하세요</div>
	                <div class="info-text" v-show="!filekey">저장 후 <span>파일 등록을</span> 하실 수 있습니다.</div>
	                <div class="btn-line"><input :disabled="!filekey" type="button" value="파일찾기" onclick="$('#files').click();" class="btn type01 size-m"><input v-if="uploadbtnyn" type="button" :disabled="!filekey" value="업로드" class="btn type02 size-m" @click="fileset.uploadFile(fileid, filekey, filehangmok)"></div>
	                <div class="progress-zone">
	                    <div class="percent">{{fileset.uploadPercentage}}%</div>
	                    <div class="statue-txt">{{fileset.progressText}}</div>
	                    <div class="progress simple horizontal">
	                        <div class="area">
	                            <div :class="{bar:'bar',striped:fileset.striped,animated:'animated'}" style="width: 0%;" :style="{width:fileset.uploadPercentage+'%'}"></div>
	                        </div>
	                    </div>
	                </div>
	                <input :disabled="!filekey" type="file" id="files" multiple @change="fileCompChange($event.target)" @drop="fileset.handleFileUpload($event.target); fileset.handleDragLeave($event.target);" @dragover="fileset.handleDragOver($event.target)" @dragleave="fileset.handleDragLeave($event.target)"/>
	            </div>
	            <div class="upload-list" :class="{'float-none':floatnone}">
	                <span class="tit">파일목록</span>
	                <span class="total">Total <span>{{fileset.getFileCnt()}}</span></span>
                	<perfect-scrollbar class="list-box">
	                    <div class="list-line-photo" v-show="type == 'img'" v-for="(item, index) in fileset.getFileList()" @mouseover="mouseOver = index" @mouseleave="mouseOver = -1" :style="{'width':imgwidth+'px'}">
	                    	<transition name="fade">
		                        <div class="hidden-layer" v-if="mouseOver == index" :style="{'width':imgwidth+'px'}">
		                            <div class="btn-box">
		                                <a href="#!" v-if="modify"><span class="icon icon-close" @click="fileset.delFiles(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, item.FILE_NO, index)"></span></a>
		                                <a href="#!"><span class="icon icon-download" @click="fileset.download(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, item.FILE_NO, item.FILE_NM, item.FILE_PATH)"></span></a>
		                            </div>
		                        </div>
	                        </transition>
	                        <img :src="'/upload'+item.FILE_PATH" alt="item.FILE_NM" :style="{'width':imgwidth+'px'}"/>
	                    </div>
	                    <div class="list-line" v-if="type == 'list'" v-for="(item, index) in fileset.getFileList()">
	                        <span class="file-type">{{getExtension(item.FILE_NM)}}</span>
	                        <span class="sel-text">{{item.FILE_NM}}</span>
	                        <span class="file-size">{{item.FILE_SIZE > 1000 ? (item.FILE_SIZE/1024).toFixed(2)+'MB' : item.FILE_SIZE+'KB'}}</span>
	                        <span class="icon-download"><a href="#!" @click="fileset.download(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, item.FILE_NO, item.FILE_NM, item.FILE_PATH)"></a></span>
	                        <span class="icon-close" v-if="modify"><a href="#!" @click="fileset.delFiles(item.FILE_ID, item.FILE_KEY, item.FILE_HANGMOK, item.FILE_NO, index); onchange()"></a></span>
                   	 	</div>
               	 	</perfect-scrollbar>
	            </div>
	        </div>
	        <modal-message :modalset="multiFileMessageModal" v-on:callback="callback"></modal-message>
	    </div>
	`,
	mounted: function() {
		if(!isNull(this.uploadbtn)){
			this.uploadbtnyn = this.uploadbtn;
		}
	},
	//함수
	methods: {
		/**
		 * 파일 컴포넌트 변경 이벤트
		 * @param pTarget 파일 컴포넌트
		 * @return null
		 */
		fileCompChange: function(pTarget){
			try{
				if(!pTarget.files.length){
					pTarget.type = '';
					pTarget.type = 'file';
					return;
				}
					
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
				var checkmimes = true;
				var checkexts = true;
				pTarget.files.forEach(function(file) {
					
					var cmimes = false;
					var cexts = false;
					var fileName = file.name;
					extTypes.forEach(function(value) {
						if (fileName.substring(fileName.lastIndexOf('.')+1) == value) {
							cexts = true;
							return;
						}
					});
					
					
					if (this.type == 'img' && pTarget.files[pTarget.files.length-1].type.includes('image/png') && pTarget.files[pTarget.files.length-1].type.includes('image/jpeg') && pTarget.files[pTarget.files.length-1].type.includes('image/gif')) {
						cmimes = true;
					}
					else if(this.type != 'img') {
						mimeTypes.forEach(function(value) {
							if (file.type.includes(value)) {
								cmimes = true;
								return;
							}
						});
					}
					if(!cmimes){
						checkmimes = false;
						return;
					}
					if(!cexts){
						checkexts = false;
						return;
					}
				});
				
				if(!checkmimes || !checkexts){
					alert('허용되지않은 파일형식입니다.');
					pTarget.type = '';
					pTarget.type = 'file';
					this.fileset.fileData = [];
					return;
				}

				var fileUpload = this.fileset.handleFileUpload(pTarget);
				var fileDataCnt = this.fileset.fileData.length; //올릴예정 CNT
				var fileListCnt = this.fileset.fileList.length; //올라간 CNT
				var currentFileCnt = files.length; //현재 시도하는 CNT
				var sumCnt = fileDataCnt+fileListCnt+currentFileCnt;

				if(fileUpload == -1){
					this.multiFileMessageModal.openModal('normal', '파일 업로드', '파일 업로드 가능한 최대 개수를\n초과하였습니다. ('+sumCnt+'/'+this.fileset.maxCnt+')', 'small', 'fileDelete');
				}

				this.onchange();
				return true;
			}catch(err){
				return false;
			}
		},
		callback: function(pGb, pId){
		},
		onchange: function() {
			try{
				this.$emit('onchange');
			}catch(err){
				return false;
			}
		},
		/**
		 * 파일이름에서 확장자명 추출
		 * @param fileName   파일 이름
		 * @return fileExtensiont 파일 확장자명
		 */
		getExtension: function(fileName){
			try{
				var fileLength = fileName.length;
				var lastDot = fileName.lastIndexOf('.');

				//substring 메서드는 start에서 end까지(end는 포함 안 함) 부분 문자열을 포함하는 문자열을 반환합니다.
				var fileExtension = fileName.substring(lastDot+1, fileLength);
				return fileExtension;
			}catch(err){
				return false;
			}
		},
	}
});
