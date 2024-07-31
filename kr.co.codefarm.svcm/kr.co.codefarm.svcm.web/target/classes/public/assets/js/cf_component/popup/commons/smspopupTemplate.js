/********************************************************************************************
 * @Writer
 *  박해수 2019.02.09
 *
 * @Description
 * 	모달 SMS 검색 템플릿
 *
 * @Syntax
 *  $.get('/app-assets/popup/template/smspopupTemplate.html', function(response){
 *		$('head').append(response);
 *	});
 * 	<popup-sms ref="popupsms"></popup-sms>
 *
 * @Parameters
 *
 ********************************************************************************************/

Vue.component('popup-sms', {
	props: ['id','recvnumbers','sendnumber','modifiable','mode'],
	data: function () {
	    return {
	    	show: false,
	    	smsMode: this.mode == 'MMS' ? 'MMS' : 'SMS',
	    	maxLength: this.mode == 'MMS' ? 2000 : 90,
	    	sendNumber: telnoformat(this.sendnumber),
	    	lovValue: '',
	    	sendMsg: '',
	    	smsDatalist: new Dataset(),
	    	recipientTagSet: new Tagset(),
		    mainListLoading: {id:'mainListLoading', val:false},
		    messageModal: new ModalSet('sms-default-modal'),	// confirm 모달
		    isFirst: true,
	    }
 	},
	template:`
		<div>
			<transition name="fade">
				<div class="modal-layer-wrap on" v-show="show" id="mainListLoading">
			    <div :id="'template-popup-sms-'+id" class="modal-layer modal-small" style="display:block;">
			        <div class="modal-container">
			            <div class="modal-header">
		                    <p class="modal-title">{{smsMode}} 보내기</p>
		                    <div class="btn-r">
		                    	<a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideSmsPopup"><span class="icon-exit"></span></a>
		                	</div>
		                </div>
		                <div class="modal-content">
		                	<!--<header id="header">
				                <h1>{{smsMode}}</h1>
				            </header>-->
				            <div class="sms_contents">
				                <div class="take-wrap">
				                    <h2>받는 사람</h2>
				                    <span class=total>총 {{smsDatalist.getRowCount()}}명</span>
				                    <div class="take-people-box">
				                        <div class="take-people-inner">
				                        	<template v-for="(item, index) in smsDatalist.getData()">
				                            	<span class="sel-people">{{item.RECV_NAME}}<span class="tel">{{item.RECV_NUMBER}}</span><a href="#!" class="icon-minus-circle" @click="removeNumber(index)"></a></span>
				                            </template>
				                        </div>
				                    </div>
				                </div>
				                <div class="message-wrap">
				                    <span class="byte"><span class="first" :class="{'invalid':sendMsgLength > maxLength}">{{sendMsgLength}}</span>/<span class="last">{{maxLength}}</span>byte</span>
				                    <div class="message-box">
				                        <textarea class="message-inner" name="sendMsg" contenteditable="true" placeholder="메시지를 입력하세요" data-vv-as="메시지 " v-model="sendMsg" v-validate="'required'" v-bind:class="{'invalid': errors.has('sendMsg')}"></textarea>
				                    </div>
				                    <invalid-message :visible="true" :name="'sendMsg'" :verror="errors"/>
				                </div>
				                <div class="send-wrap">
				                    <h2>보내는 사람</h2>
				                    <input type="text" name="sendNumber" :value="sendNumber" class="send-tel form-plaintxt" placeholder="전화번호 입력" data-vv-as="보내는 사람 " v-validate="'required'" v-bind:class="{'invalid': errors.has('sendNumber')}" readonly/>
				                </div>
				                <invalid-message :visible="true" :name="'sendNumber'" :verror="errors"/>
				                <div class="btn-wrap">
				                    <a href="#!" class="btn-send" @click="sendSms">보내기</a>
				                </div>
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
	    'sendMsg' : {
	    	handler: function (val, oldVal) {
	    		/*this.sendMsg = val.cut(this.maxLength);*/
			}
	    },
	},
	computed: {
		sendMsgLength : function() {
			return this.sendMsg.bytes();
		},
	},
	created: function() {
		try{
			this.newRow();
		}catch(err){
			return false;
		}
	},
    //마운트 된 시점수행
    mounted: function(){

    },
    //함수
    methods: {
    	initPopup: function(){
    		try{
    			if(this.isFirst){
    				this.smsDatalist.clear();
    				this.sendMsg = '';
        			this.newRow();
        			this.$validator.reset();
        			this.$forceUpdate();
        			this.isFirst = false;
    			}
    		}catch(err){
    			return false;
    		}
    	},
    	showSmsPopup: function(){
    		try{
    			if(this.defaultInfo.SMS_USE_YN != 'Y'){
    				notifySubmit('error', self.smsMode+' 전송', '시스템에서 메시지 전송을 지원하지 않습니다.', 'icon-save');
    				return false;
    			}

    			this.sendNumber = telnoformat(this.sendnumber);

    			if(isNull(this.sendNumber)){
    				this.sendNumber = telnoformat(this.defaultInfo.SMS_SEND_NO);
    			}
	    		var modalObj = $('#template-popup-sms-'+this.id); //레이어의 id를 modalObj변수에 저장
	    		this.initPopup();
	    		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
	    	    else modalObj.css('left', '0px');
	    		this.show = true;
    		}catch(err){
    			return false;
    		}
    	},
    	hideSmsPopup: function(){
    		try{
    			this.show = false;
    			this.isFirst = true;
    		}catch(err){
    			return false;
    		}
    	},
    	newRow: function(){
    		try{
	        	var addObj = {
	        		'RECV_NAME' : '',
	        		'RECV_NUMBER' : '',
	        	};

	        	if(!isNull(this.recvnumbers)){
	        		var recvnumbers = this.recvnumbers.split(',');

	        		for(var i=0 ; i < recvnumbers.length ; i++){
	        			var recvinfo = recvnumbers[i].split(';');
	        			var recvname = recvinfo[0];
	        			var recvnumber = recvinfo[1];
	        			this.smsDatalist.addRow( {'RECV_NAME' : recvname, 'RECV_NUMBER' : telnoformat(recvnumber)} );
	        		}
		   	 	}else{
//			   	 	var newRowPosition = this.smsDatalist.addRow(addObj);
//		            return newRowPosition;
		   	 	}
    		}catch(err){
    			return false;
    		}
        },
        removeNumber: function(index) {
        	this.smsDatalist.deleteRow(index);
        },
        sendSms: function(){
        	try{
	        	var validate = this.$validator.validateAll();
	            var self = this;

	            validate.then(function(response) {
	            	if(self.sendMsgLength > self.maxLength){
	            		notifySubmit('warning', self.smsMode+' 전송', self.maxLength+'KB를 초과하였습니다.', 'icon-save');
	            	}else{
	            		if(response){
		                	self.messagePopup('SEND_SMS', null)
		                }
	            	}
	            });
        	}catch(err){
    			return false;
    		}
        },
        realSendSms: function(){
        	try{
	            var self = this;
	        	self.mainListLoading.val = true;
	        	var returnVal = smsApi.smsSend(self.smsDatalist.getData(), this.sendNumber, this.sendMsg, false);

				if(!isNull(returnVal)){
					returnVal.then(function(response) {
	                    var data = response.data;

	                    if(data){
	                         notifySubmit('success', '메시지 전송', '메시지가 전송되었습니다.', 'icon-save');
	                         self.hideSmsPopup();
	                    }else{
	                        notifySubmit('warning', '메시지 전송', '메시지 전송에 실패하였습니다.', 'icon-caution');
	                    }
					}).finally(function(){
						self.mainListLoading.val = false;
					});
				}
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
		    		case 'SEND_SMS':
		    			title = '메시지 전송';
		    			cont = "작성 된 메시지를 전송하시겠습니까?<br />(전송 된 메시지는 회수가 불가능합니다.)";
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
		    		case 'SEND_SMS':
		    			this.realSendSms();
		    			break;
		    	}
	    	}catch(err){
    			return false;
    		}
	    },
    }
});
