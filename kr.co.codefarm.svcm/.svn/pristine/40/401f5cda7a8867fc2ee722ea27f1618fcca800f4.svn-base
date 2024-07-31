/********************************************************************************************
 * @Writer 
 *  윤수영 2022. 06. 10. 
 *  
 * @Description
 * 	모달 카카오톡 전송
 * 
 * @Syntax
 *  $.get('/app-assets/popup/template/emailpopupTemplate.html', function(response){
 *		$('head').append(response);
 *	});
 * 	<popup-kakao ref="popupkakao"></popup-kakao>
 *
 * @Parameters
 *
 ********************************************************************************************/

Vue.component('popup-kakao', {
	props: ['id','searchyn','templateid','paramlist'],
	data: function () {
	    return {
	    	show: false,
	    	searchShow : true,
	    	mainListInput: new Dataset(),
			mainListSearch: new Dataset(),
		    mainListLoading: {id:'mainListLoading', val:false},
		    messageModal: new ModalSet('kakao-default-modal'),	// confirm 모달
		    isFirst: true,
			pDate : '',
			pDept : '',
			pName : '',
			pEopmu : '',
			reservationYn : false,
			reservTime : '',
			smsOrKakao : '01',
			selectKakaoKey :'',
			selectKakaoCont :'',
			selectKakaoParam : [],
			sendKakaoArr : [],
			responseString : '',
			receiveKakaoTemplate : new Dataset(),
			kakaoTemplate: [],
			kakaoTemplateSelect:[],
			kakaoTemplateParam: [],
	    }
 	},
	template:`
		<div class="modal-layer-wrap on" v-show="show" id="mainListLoading">
			<div :id="'template-popup-kakao-'+id" class="modal-layer modal-xlarge" style="display:none;">
				<div class="modal-container">
					<div class="modal-header">
						<p class="modal-title">카카오톡 전송</p>
						<div class="btn-r">
							<a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideKakaoPopup"><span class="icon-exit"></span></a>
						</div>
					</div>
					<div class="modal-content">
						<div class="row form-row">
							<label class="label-type01">전송방식</label>		
							<div class="radio size-s">
								<input type="radio" name="radio" id="radio01" v-model='smsOrKakao' value="01" @click="smsKakaoChg('01')" :disabled="templateid">
								<label for="radio01">SMS</label>
								<i class="radio-icon"></i>
							</div>
							<div class="radio size-s">
								<input type="radio" name="radio" id="radio02" v-model='smsOrKakao' value="02" @click="smsKakaoChg('02')" :disabled="templateid">
								<label for="radio02">KaKao</label>
								<i class="radio-icon"></i>
							</div>
						</div>
						<div class="row form-row" v-if="searchShow">
							<div class="form-group col s12 l4">
								<label class="label-type01">기관</label>		
								<input type="text" class="size-m" v-model="pDept">
							</div>
							<div class="form-group col s12 l4">
								<label class="label-type01">성명/아이디</label>		
								<input type="text" class="size-m" v-model="pName">
							</div>
							<div class="form-group col s12 l4">
								<label class="label-type01">업무구분</label>		
								<input type="text" class="size-m" v-model="pEopmu">
							</div>
						</div>
						<div class="row form-row right" v-if="searchShow">
								<input type="button" value="검색" class="btn type02 size-m" @click="searchUser()">
						</div>
						<div class="row form-row">
							<div class="form-group col s12 l6">
								<div class="row form-row">
									<label class="label-type01">발송내용</label>
								</div>
								<div class="row form-row">
									<select class="size-m" v-model="selectKakaoKey" @change="setKaKaoTemplate(selectKakaoKey)" :disabled="templateid">
										<option value="" v-if="smsOrKakao=='01'">직접입력</option>
										<option v-for="item in kakaoTemplateSelect" :value="item.KEY">{{item.TITLE}}</option>
									</select>
								</div>
								<div class="row form-row">
									<textarea style="height: 230px" v-model="selectKakaoCont" :disabled="selectKakaoKey != ''" >
									</textarea>
								</div>					
							</div>
							
							<div class="form-group col s12 l1">
							</div>
														
							<div class="form-group col s12 l5" v-if="searchShow">
								
								<label class="label-type01">조회 대상</label>
								
								<table class="table-type01">
									<colgroup>
											<col width="10%"/>
											<col width="25%"/>
											<col width="25%"/>
											<col width="40%"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">소속</th>
											<th scope="col">성명</th>
											<th scope="col">핸드폰번호</th>
										</tr>
									</thead>
								</table>
								<div style="height: 200px; overflow-y: scroll; margin-top:-1px;">
									<table class="table-type01">
										<colgroup>
											<col width="10%"/>
											<col width="25%"/>
											<col width="25%"/>
											<col width="40%"/>
										</colgroup>
										<tbody v-if="mainListSearch.getRowCount() > 0">
											<tr v-for="(item,idx) in mainListSearch.data">
											  <td><input type="checkbox" name ="searchCheckBox"></td>
											  <td>{{item.DEPT_NM}}</td>
											  <td>{{item.USER_NM}}</td>
											  <td>{{item.HP_NO}}</td>
											  <td style="display: none">{{idx}}</td>
											</tr>
										</tbody>
										<tbody v-else>
											<tr>
											  <td colspan="100">데이터가 없습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row form-row" v-if="searchShow">
							<div class="form-group col s12 l9"></div>
							<div class="form-group col s12 l3">
								<input type="button" value="▼" class="size-m" @click="checkSearch()">
								<input type="button" value="▲" class="size-m" @click="checkInput()">
							</div>
						</div>
						<div class="row form-row">
							<div class="form-group col s12 l6">
								<div class="row form-row">
									<label class="label-type01">치환구분</label>
								</div>
								<div class="row form-row">
									<table class="table-type01">
										<colgroup>
												<col width="25%"/>
												<col width="25%"/>
												<col width="50%"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col">변수명</th>
												<th scope="col">치환구분</th>
												<th scope="col">내용</th>
											</tr>
										</thead>
										<tbody v-if="selectKakaoParam.length > 0">
											<tr v-for="(item, idx) in selectKakaoParam">
											  <td>{{item.paramName}}</td>
											  <td>
											  	<select v-model="item.paramGb">
											  		<option value="01">직접입력</option>
											  		<option value="02">소속</option>
											  		<option value="03">성명</option>
												</select>
											  </td>
											  <td>
											  	<div v-show="item.paramGb=='01'"><input type="text" v-model="item.paramCont"></div>
											  </td>
											</tr>
										</tbody>
										<tbody v-else>
											<tr>
											  <td colspan="100">입력가능한 파라메터가 없습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="form-group col s12 l1">
							</div>
							<div class="form-group col s12 l5">
							<label class="label-type01">발송 대상</label>
								<table class="table-type01">
									<colgroup>
											<col width="10%"/>
											<col width="25%"/>
											<col width="25%"/>
											<col width="40%"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">소속</th>
											<th scope="col">성명</th>
											<th scope="col">핸드폰번호</th>
										</tr>
									</thead>
								</table>
								<div style="height: 200px; overflow-y: scroll; margin-top:-1px;">
									<table class="table-type01">
										<colgroup>
											<col width="10%"/>
											<col width="25%"/>
											<col width="25%"/>
											<col width="40%"/>
										</colgroup>
										<tbody v-if="mainListInput.getRowCount() > 0">
											<tr v-for="(item,idx) in mainListInput.data">
											  <td><input type="checkbox" name="inputCheckBox"></td>
											  <td>{{item.DEPT_NM}}</td>
											  <td>{{item.USER_NM}}</td>
											  <td>{{item.HP_NO}}</td>
											  <td style="display: none">{{idx}}</td>
											</tr>
										</tbody>
										<tbody v-else>
											<tr>
											  <td colspan="100">데이터가 없습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row form-row">
							<div class="form-group col s12 l12 col-inline right">
								<div class="checkbox size-s type02">
									<input type="checkbox" id="reservation" @change="reservationChg">
									<label for="reservation">예약발송</label>
									<i class="icon-check"></i>
								</div>
								<input :disabled="!reservationYn" type="datetime-local" class="size-m" v-model="reservTime">
								<input type="button" class="btn type02 size-m" value="전송" @click="makeKaKaoSendArr()">
							</div>				
						</div>
					</div>						
				</div>
			</div>
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
    //마운트 된 시점수행
    mounted: function(){

		var self = this;
		self.reservTime = '';
		self.reservationYn = false;
		if(this.templateid){
			self.smsOrKakao = '02';
			self.getKakaoTemplate();
		}
		else{
			self.getSmsTemplate();
		}
    },
    //함수
    methods: {
		reservationChg: function () {
			if(this.reservationYn){
				this.reservTime='';
			}

			this.reservationYn = !this.reservationYn
		},
		initPopup: function(idList){
			var self = this;
			self.searchShow = this.searchyn == 'F' ? false : true;
			self.selectKakaoKey = '';
			if(this.templateid){
				self.selectKakaoKey = this.templateid;
				self.setKaKaoTemplate(this.selectKakaoKey);
			}
			try{
				if(idList){
					var inputUser = '';
					for (var i = 0; i < idList.length; i++) {
						inputUser += idList[i]+',';
					}

					var params = {
						pInputUserStr : inputUser,
						pDept : self.pDept,
						pName : self.pName,
						pEopmu : self.pEopmu,
					};
					
					var params = {
						pInputUserStr : inputUser
					};

					var returnVal = kakaoApi.getParamUser(params, true);

					returnVal.then(function (response) {
						for(var i = 0 ; i < response.data.length ; i++){
							self.mainListInput.addRow(response.data[i]);	
						}
					}).finally(function () {
						self.mainListSearch.sort('USER_NM');
					});
				}
				if(this.isFirst){
					if(!idList)
						this.mainListInput.clear();
					
					this.mainListSearch.clear();
					this.pName = '';
					this.$forceUpdate();
					this.isFirst = false;
				}
			}catch(err){
				return false;
			}
		},
		showKakaoPopup: function(idList){
    		try{
	    		var modalObj = $('#template-popup-kakao-'+this.id); //레이어의 id를 modalObj변수에 저장

	    		modalObj.css('display', 'block');

	    		if(!idList)
	    			this.initPopup();
	    		else
	    			this.initPopup(idList);
	    			
				if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
	    	    else modalObj.css('left', '0px');

	    		this.show = true;
    		}catch(err){
    			return false;
    		}
    	},
		hideKakaoPopup: function(){
			try{
				this.show = false;
				this.isFirst = true;
			}catch(err){
				return false;
			}
		},
		searchUser: function () {
			var self = this;
			var inputUser = '';

			if(self.mainListInput.getRowCount()>0) {
				for (var i = 0; i < self.mainListInput.getRowCount(); i++) {
					inputUser += self.mainListInput.data[i].HP_NO+',';
				}
			}

			var params = {
				pInputUserStr : inputUser,
				pDept : self.pDept,
				pName : self.pName,
				pEopmu : self.pEopmu,
			};

			var returnVal = kakaoApi.getUser(params, true);

			returnVal.then(function (response) {
				if (response.status == 200) {
					self.mainListSearch.setData(response.data);
				}else{
					self.mainListSearch = null;
				}
			}).finally(function () {
				self.mainListSearch.sort('USER_NM');
			});
		},
		checkSearch: function () {
			var self = this;
			var rowData = [];
			var tdArr = [];
			var delRow = [];
			var checkbox = $("input[name=searchCheckBox]:checked");

			// 체크된 체크박스 값을 가져온다
			checkbox.each(function(i) {

				// checkbox.parent() : checkbox의 부모는 <td>이다.
				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();

				// 체크된 row의 모든 값을 배열에 담는다.
				rowData.push(tr.text());

				// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
				var dept = td.eq(1).text();
				var name = td.eq(2).text();
				var hpno = td.eq(3).text();
				var idx = td.eq(4).text();

				delRow.push(idx);

				tdArr[i] = {
					DEPT_NM : dept,
					USER_NM : name,
					HP_NO : hpno,
				};
				self.mainListInput.addRow(tdArr[i]);
			});
			$("input:checkbox[name='searchCheckBox']").prop("checked", false);

			for(var i=delRow.length; i>0; i--){
				self.mainListSearch.deleteRowData(delRow[i-1]);
			}
			self.mainListInput.sort('USER_NM');
		},
		checkInput: function () {
			var self = this;
			var rowData = [];
			var tdArr = [];
			var delRow = [];
			var checkbox = $("input[name=inputCheckBox]:checked");

			// 체크된 체크박스 값을 가져온다
			checkbox.each(function(i) {

				// checkbox.parent() : checkbox의 부모는 <td>이다.
				// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();

				// 체크된 row의 모든 값을 배열에 담는다.
				rowData.push(tr.text());

				// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
				var dept = td.eq(1).text();
				var name = td.eq(2).text();
				var hpno = td.eq(3).text();
				var idx = td.eq(4).text();

				delRow.push(idx);

				tdArr[i] = {
					DEPT_NM : dept,
					USER_NM : name,
					HP_NO : hpno,
				};
				self.mainListSearch.addRow(tdArr[i]);
			});
			$("input:checkbox[name='inputCheckBox']").prop("checked", false);

			for(var i=delRow.length; i>0; i--){
				self.mainListInput.deleteRowData(delRow[i-1]);
			}
			self.mainListSearch.sort('USER_NM');
		},
		setKaKaoTemplate : function (tmpKey) {
			var self = this;
			if(tmpKey == ''){
				self.selectKakaoCont = '';
				self.selectKakaoParam = [];
			}else{
				for(var i=0; i<self.kakaoTemplate.length; i++){
					if(self.kakaoTemplate[i].KEY==tmpKey){
						self.selectKakaoCont = self.kakaoTemplate[i].CONTENTS;
						self.setKaKaoParam(tmpKey);
						return;
					}
				}
			}
		},
		setKaKaoParam : function (tmpKey) {
			var self=this;
			self.selectKakaoParam = [];
			for(var i=0; i<self.kakaoTemplateParam.length; i++){
				if(self.kakaoTemplateParam[i].KEY==tmpKey){
					for(var j=0; j<self.kakaoTemplateParam[i].PARAMS.length; j++){
						self.selectKakaoParam.push({
							paramName : self.kakaoTemplateParam[i].PARAMS[j],
							paramGb : self.paramlist[j] ? self.paramlist[j] : '01',
							parmaCont : '',
						})
					}
					return;
				}
			}
		},
		makeKaKaoSendArr : async function () {
			var self = this;

			var successCnt =0;
			var failCnt =0;

			for(var i=0; i<self.mainListInput.data.length; i++){

				var kakaoRemakeVal = '';
				var smsRemakeVal = self.selectKakaoCont;

				for(var j=0; j<self.selectKakaoParam.length; j++){

					if(self.smsOrKakao=='01'){



						if(self.selectKakaoParam[j].paramGb=='01'){
							smsRemakeVal = smsRemakeVal.replace(self.selectKakaoParam[j].paramName,self.selectKakaoParam[j].paramCont);
						}else if(self.selectKakaoParam[j].paramGb=='02'){
							smsRemakeVal = smsRemakeVal.replace(self.selectKakaoParam[j].paramName,self.mainListInput.data[i].DEPT_NM);
						}else if(self.selectKakaoParam[j].paramGb=='03'){
							smsRemakeVal = smsRemakeVal.replace(self.selectKakaoParam[j].paramName,self.mainListInput.data[i].USER_NM);
						}

					}else if(self.smsOrKakao=='02'){

						if(self.selectKakaoParam[j].paramGb=='01'){
							if(kakaoRemakeVal ==''){
								kakaoRemakeVal += '{"'+self.selectKakaoParam[j].paramName+'":"'+self.selectKakaoParam[j].paramCont+'"'
							}else{
								kakaoRemakeVal += ',"'+self.selectKakaoParam[j].paramName+'":"'+self.selectKakaoParam[j].paramCont+'"'
							}
						}else if(self.selectKakaoParam[j].paramGb=='02'){
							if(kakaoRemakeVal ==''){
								kakaoRemakeVal += '{"'+self.selectKakaoParam[j].paramName+'":"'+self.mainListInput.data[i].DEPT_NM+'"'
							}else{
								kakaoRemakeVal += ',"'+self.selectKakaoParam[j].paramName+'":"'+self.mainListInput.data[i].DEPT_NM+'"'
							}
						}else if(self.selectKakaoParam[j].paramGb=='03'){
							if(kakaoRemakeVal ==''){
								kakaoRemakeVal += '{"'+self.selectKakaoParam[j].paramName+'":"'+self.mainListInput.data[i].USER_NM+'"'
							}else{
								kakaoRemakeVal += ',"'+self.selectKakaoParam[j].paramName+'":"'+self.mainListInput.data[i].USER_NM+'"'
							}
						}
					}
					kakaoRemakeVal += '}'
				}

				if(self.smsOrKakao =='01') {
					var params = {
						IN_TO : self.mainListInput.data[i],
						IN_VAR : smsRemakeVal,
						IN_TIME : self.reservTime,
					};

					var returnVal = solApiApi.sendSms(params);

					returnVal.then(function(response) {
						if(response.data.OUT_CD == '1'){
							successCnt++;
						}else{
							failCnt++;
						}
					}).finally(function () {
						if((successCnt+failCnt)==self.mainListInput.data.length){
							if(successCnt>0) notifySubmit('success', '문자 발송', successCnt+'건 성공적으로 발송되었습니다.', 'icon-save');
							if(failCnt>0) notifySubmit('success', '문자 발송', failCnt+'건 발송에 실패하였습니다.', 'icon-save');
							self.hideKakaoPopup();
						}
					});
				}

				else if(self.smsOrKakao =='02') {
					var params = {
						IN_TO : self.mainListInput.data[i],
						IN_ID : self.selectKakaoKey,
						IN_TIME : self.reservTime,
						IN_VAR : kakaoRemakeVal,
					};
					var returnVal = solApiApi.sendKakao(params);

					returnVal.then(function(response) {
						if(response.data.OUT_CD == '1'){
							successCnt++;
						}else{
							failCnt++;
						}
					}).finally(function () {
						if((successCnt+failCnt)==self.mainListInput.data.length){
							if(successCnt>0) notifySubmit('success', '문자 발송', successCnt+'건 성공적으로 발송되었습니다.', 'icon-save');
							if(failCnt>0) notifySubmit('success', '문자 발송', failCnt+'건 발송에 실패하였습니다.', 'icon-save');
							self.hideKakaoPopup();
						}
					});
				}
			}

		},

		getSmsTemplate:function () {
			var self = this;
			var returnVal = solApiApi.getSmsTemp();

			returnVal.then(function (response) {
				self.receiveKakaoTemplate.setData(response.data);
			}).finally(function () {
				self.makeSmsTempCont();
			});
		},

		getKakaoTemplate:function () {
			var self = this;
			var returnVal = solApiApi.getKakaoTemp();

			returnVal.then(function (response) {
				self.receiveKakaoTemplate.setData(response.data);
			}).finally(function () {
				self.makeKakaoTempCont();
			});
		},

		makeKakaoTempCont : function () {
			var self = this;
			self.kakaoTemplate =[];

			for(var i=0; i<self.receiveKakaoTemplate.data[0].templateList.length; i++){
				self.kakaoTemplate.push({
					KEY : self.receiveKakaoTemplate.data[0].templateList[i].templateId,
					CONTENTS : self.receiveKakaoTemplate.data[0].templateList[i].content,
				})
			}
			self.makeKaKaoTempSelect();
		},
		makeKaKaoTempSelect : function () {
			var self = this;
			self.kakaoTemplateSelect =[];

			for(var i=0; i<self.receiveKakaoTemplate.data[0].templateList.length; i++){
				self.kakaoTemplateSelect.push({
					KEY : self.receiveKakaoTemplate.data[0].templateList[i].templateId,
					TITLE : self.receiveKakaoTemplate.data[0].templateList[i].name,
				})
			}
			self.makeKaKaoTempParam();
		},
		makeKaKaoTempParam : function () {
			var self = this;

			for(var i=0; i<self.receiveKakaoTemplate.data[0].templateList.length; i++){

				var templateParam = [];
				var paramCnt = self.receiveKakaoTemplate.data[0].templateList[i].content.split('#{').length - 1;
				var startidx = '';
				var endidx = '';

				for(var j=0; j<paramCnt; j++) {
					if (j == 0) {
						startidx = self.receiveKakaoTemplate.data[0].templateList[i].content.indexOf('#{');
						endidx = self.receiveKakaoTemplate.data[0].templateList[i].content.indexOf('}')
						var param = self.receiveKakaoTemplate.data[0].templateList[i].content.substring(startidx, endidx + 1);
						templateParam.push(param);
					} else {
						startidx = self.receiveKakaoTemplate.data[0].templateList[i].content.indexOf('#{',endidx+1);
						endidx = self.receiveKakaoTemplate.data[0].templateList[i].content.indexOf('}',endidx+1)
						var param = self.receiveKakaoTemplate.data[0].templateList[i].content.substring(startidx, endidx + 1);
						templateParam.push(param);
					}
				}
				self.kakaoTemplateParam.push({
					KEY : self.receiveKakaoTemplate.data[0].templateList[i].templateId,
					PARAMS : templateParam,
				})
			}
		},

		makeSmsTempCont : function () {
			var self = this;
			self.kakaoTemplate =[];

			for(var i=0; i<self.receiveKakaoTemplate.data[0].presets.length; i++){
				self.kakaoTemplate.push({
					KEY : self.receiveKakaoTemplate.data[0].presets[i].presetId,
					CONTENTS : self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text,
				})
			}
			self.makeSmsTempSelect();
		},
		makeSmsTempSelect : function () {
			var self = this;
			self.kakaoTemplateSelect =[];

			for(var i=0; i<self.receiveKakaoTemplate.data[0].presets.length; i++){
				self.kakaoTemplateSelect.push({
					KEY : self.receiveKakaoTemplate.data[0].presets[i].presetId,
					TITLE : self.receiveKakaoTemplate.data[0].presets[i].preset.title,
				})
			}
			self.makeSmsTempParam();
		},
		makeSmsTempParam : function () {
			var self = this;

			for(var i=0; i<self.receiveKakaoTemplate.data[0].presets.length; i++){

				var templateParam = [];
				var paramCnt = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.split('#{').length - 1;
				var startidx = '';
				var endidx = '';

				for(var j=0; j<paramCnt; j++) {
					if (j == 0) {
						startidx = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.indexOf('#{');
						endidx = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.indexOf('}')
						var param = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.substring(startidx, endidx + 1);
						templateParam.push(param);
					} else {
						startidx = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.indexOf('#{',endidx+1);
						endidx = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.indexOf('}',endidx+1)
						var param = self.receiveKakaoTemplate.data[0].presets[i].preset.preset.text.substring(startidx, endidx + 1);
						templateParam.push(param);
					}
				}
				self.kakaoTemplateParam.push({
					KEY : self.receiveKakaoTemplate.data[0].presets[i].presetId,
					PARAMS : templateParam,
				})
			}
		},
		smsKakaoChg : function (no) {
			var self = this;
			self.kakaoTemplate = [];
			self.kakaoTemplateSelect = [];
			self.kakaoTemplateParam = [];
			self.selectKakaoCont = '';
			self.selectKakaoParam= [];

			if(no=='01') 		self.getSmsTemplate();
			else if(no=='02')	self.getKakaoTemplate();
		}

    }
});

