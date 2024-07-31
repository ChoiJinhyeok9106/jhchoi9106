/********************************************************************************************
 * @Writer
 *  박해수 2019.02.09
 *
 * @Description
 * 	모달 이메일 검색 템플릿
 *
 * @Syntax
 *  $.get('/app-assets/popup/template/emailpopupTemplate.html', function(response){
 *		$('head').append(response);
 *	});
 * 	<popup-email ref="popupemail"></popup-email>
 *
 * @Parameters
 *
 ********************************************************************************************/

Vue.component('popup-email', {
	props: ['id','lovcomp','email','modifiable','fixedsender','sender','placeholder'],
	data: function () {
	    return {
	    	show: false,
	    	lovValue: '',
	    	editor: '',
	    	editorText: '',
	    	emailDatalist: new Dataset(),
	    	recipientTagSet: new Tagset(),
		    mainListLoading: {id:'mainListLoading', val:false},
		    messageModal: new ModalSet('email-default-modal'),	// confirm 모달
		    isFirst: true,
	    }
 	},
	template:`
		<div>
			<div class="btn-input" v-if="lovcomp">
				<a href="#!" class="btn type02 size-m center-icon" @click="showEmailPopup()"><span class="icon-search"></span></a>
				<input type="text" class="size-m" v-on:keyup.13="showEmailPopup();" v-model="lovValue">
			</div>
			<transition name="fade">
				<div class="modal-layer-wrap on" v-show="show" id="mainListLoading">
			    <div :id="'template-popup-email-'+id" class="modal-layer modal-xlarge" style="display:none;">
			        <div class="modal-container">
			            <div class="modal-header">
		                    <p class="modal-title">이메일 작성</p>
		                    <div class="btn-r">
		                    	<a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideEmailPopup"><span class="icon-exit"></span></a>
		                	</div>
		                </div>
		                <div class="modal-content" v-if="emailDatalist.currentRow">
		                    <!-- 게시판 쓰기페이지 -->
							<div class="box-type03 board-write-page">
								<div class="row form-row">
									<div class="form-group col s12 l2 col-label">
										<label for="recipient" class="label-type01 size-m">받는 사람</label>
									</div>
									<div class="form-group col s12 l10">
										<tag-input ref="recipient" :id="'recipient'" :modifiable="modifiable" name="recipient" :tagset="recipientTagSet" :rowposition="0" :placeholder="'이메일 주소 작성 후 엔터키를 입력해주세요.'" :datavvas="'받는 사람 '" :validate="'required|email'" :invalidclass="errors.has('recipientHidden')?'invalid':''" :width="'100%'"></tag-input>
										<input type="hidden" id="recipientHidden" class="size-m" name="recipientHidden" data-vv-as="받는 사람 " v-model.trim="emailDatalist.currentRow.TO" v-validate="'required'">
										<invalid-message :visible="true" :name="'recipientHidden'" :verror="errors"/>
									</div>
								</div>
								<div class="row form-row">
									<div class="form-group col s12 l2 col-label">
										<label for="sender" class="label-type01 size-m">보내는 사람</label>
									</div>
									<div class="form-group col s12 l10">
										<input type="text" id="sender" name="sender" class="size-m" v-model="emailDatalist.currentRow.FROM" placeholder="보내는 사람 예) example@example.com " data-vv-as="보내는 사람 이메일 주소 " v-validate="'required|email'" v-bind:class="{'invalid': errors.has('sender')}" :disabled="fixedsender">
										<invalid-message :visible="true" :name="'sender'" :verror="errors"/>
									</div>
								</div>
								<div class="row form-row">
									<div class="form-group col s12 l2 col-label">
										<label for="title" class="label-type01 size-m">제목</label>
									</div>
									<div class="form-group col s12 l10">
										<input type="text" id="title" name="title" class="size-m" v-model="emailDatalist.currentRow.TITLE" placeholder="제목" data-vv-as="제목 " v-validate="'required|max:300'" v-bind:class="{'invalid': errors.has('title')}">
										<invalid-message :visible="true" :name="'title'" :verror="errors"/>
									</div>
								</div>
								<div class="row form-row">
									<div class="form-group col s12 l2 col-label">
										<label for="emailEditorText" class="label-type01 size-m">내용</label>
									</div>
									<div class="form-group col s12 l10">
										<div class="form-inner-box" id="emailEditorText" v-bind:class="{'invalid': errors.has('emailEditorText')}">

										</div>
										<input type="hidden" id="emailEditorText" class="size-m" name="emailEditorText" data-vv-as="내용 " v-model.trim="emailDatalist.currentRow.CONTENTS" v-validate="'required'">
										<invalid-message :visible="true" :name="'emailEditorText'" :verror="errors"/>
									</div>
								</div>
							</div>
							<div class="col-inline btn-line right">
								<a href="#!" class="btn type02 size-m" @click="sendMail">전송</a>
								<a href="#!" class="btn type01 size-m" @click="hideEmailPopup">닫기</a>
							</div>
		                </div>
		            </div>
		        </div>
			    </div>
			</transition>
			<modal-message :modalset="messageModal" v-on:messagepoppupcallback="messagePopupCallback"></modal-message>
		</div>
	`,
	//데이터 감시
	watch: {
	    'mainListLoading.val': {
	    	handler: function (val, oldVal) {
				this.LoadingOverlay($('#'+this.mainListLoading.id), val);
			}
	    },
	},
	created: function() {
		try{
			var newRowPosition = this.newRow();
	   	 	this.recipientTagSet.init(this.emailDatalist, 'TO', newRowPosition, 'recipientHidden', true);
		}catch(err){
			return false;
		}

	},
    //마운트 된 시점수행
    mounted: function(){
    	this.makeEditor();
    },
    //함수
    methods: {
    	initPopup: function(){
    		try{
    			if(this.isFirst){
    				this.emailDatalist.clear();
        			this.newRow();
        			this.editor.setValue('');
        			this.$refs.recipient.init();
        			this.$validator.reset();
        			this.$forceUpdate();
        			this.isFirst = false;
    			}
    		}catch(err){
    			return false;
    		}
    	},
    	showEmailPopup: function(){
    		try{
	    		var modalObj = $('#template-popup-email-'+this.id); //레이어의 id를 modalObj변수에 저장

	    		modalObj.css('display', 'block');

	    		this.initPopup();
	    		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
	    	    else modalObj.css('left', '0px');

	    		this.show = true;
    		}catch(err){
    			return false;
    		}
    	},
    	hideEmailPopup: function(){
    		try{
    			this.show = false;
    			this.isFirst = true;

	    		modalObj.css('display', 'none');
    		}catch(err){
    			return false;
    		}
    	},
    	newRow: function(){
    		try{
	        	var addObj = {
	        		'TO' : '',
	        		'FROM' : this.sender ? this.sender : '',
	        		'TITLE' : '',
	        		'CONTENTS' : '',
	        	};

	        	if(!isNull(this.email)){
	        		addObj['TO'] = this.email;
		   	 	}

	            var newRowPosition = this.emailDatalist.addRow(addObj);
	            return newRowPosition;
    		}catch(err){
    			return false;
    		}
        },
        sendMail: function(){
        	try{
	        	this.setEmailContetsToDataset();
	        	var validate = this.$validator.validateAll();
	            var self = this;

	            validate.then(function(response) {
	                if(response){
	                	self.messagePopup('SEND_EMAIL', null)
	                }
	            });
        	}catch(err){
    			return false;
    		}
        },
        realSendMail: function(){
        	try{
	            var self = this;
	        	self.mainListLoading.val = true;
	        	var returnVal = emailApi.emailSend(self.emailDatalist.currentRow, false);

				if(!isNull(returnVal)){
					returnVal.then(function(response) {
	                    var data = response.data;

	                    if(data){
	                         notifySubmit('success', '이메일 발송', '이메일이 성공적으로 발송되었습니다.', 'icon-save');
	                         self.hideEmailPopup();
	                    }else{
	                        notifySubmit('warning', '이메일 발송', '이메일 발송에 실패하였습니다.', 'icon-caution');
	                    }
					}).finally(function(){
						self.mainListLoading.val = false;
					});
				}
        	}catch(err){
    			return false;
    		}
        },
        setEmailContetsToDataset: function() {
        	try{
        		this.emailDatalist.setColumn(this.emailDatalist.rowposition, 'CONTENTS', this.editor.getHtml());
        	}catch(err){
    			return false;
    		}
    	},
        makeEditor: function() {
        	try{
	        	this.editor = new toastui.Editor({
	    	   	    el: document.querySelector('#emailEditorText'),
	    	   	    language: 'ko_KR',
	    	   	    initialEditType: 'wysiwyg',
	    	        exts: ['colorSyntax'],
	    	        placeholder: this.placeholder ? this.placeholder : '이메일 내용을 작성해주세요.',
	    	   	    //previewStyle: 'vertical',
	    	   	    //height: '300px',
	    	   	});


        	}catch(err){
    			return false;
    		}
        },
		/* 저장할때 메세지창 띄우기*/
        messagePopup: function(pId, pData) {
        	try{
	            var title = '확인';
	            var cont = '해당 작업을 수행하시겠습니까?';
	            var pGb = 'confirm';

	            switch(pId) {
		    		case 'SEND_EMAIL':
		    			title = '이메일 발송';
		    			cont = "작성 된 이메일을 발송하시겠습니까?<br />(발송 된 이메일은 회수가 불가능합니다.)";
	                    break;
		    	}

	            this.messageModal.openModal(pGb, title, cont, 'small', pId, pData);
        	}catch(err){
    			return false;
    		}
        },
	    messagePopupCallback: function(pGb, pId, pData) {	//confirm 확인 callback함수 (확인/취소 등 구분값, confirm 호출 구분 값, 전달 데이터)
	    	try{
		    	if(pGb!='confirm') return;

		    	switch(pId) {
		    		case 'SEND_EMAIL':
		    			this.realSendMail();
		    			break;
		    	}
	    	}catch(err){
    			return false;
    		}
	    },
    }
});
