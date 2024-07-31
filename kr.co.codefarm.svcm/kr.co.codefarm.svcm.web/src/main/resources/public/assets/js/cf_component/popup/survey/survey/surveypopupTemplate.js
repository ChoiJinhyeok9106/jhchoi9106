/********************************************************************************************
 * @Writer
 *  박해수 2019.02.09 
 *  
 * @Description
 * 	모달 설문지 검색 템플릿
 * 
 * @Syntax
 *  $.get('/app-assets/popup/template/surveypopupTemplate.html', function(response){
 *		$('head').append(response); 
 *	});
 * 	<popup-survey ref="popupsurvey"></popup-survey>
 * 
 * @Parameters
 *  
 ********************************************************************************************/ 
 
Vue.component('popup-survey', {
	props: ['type','id','lovcomp','surveycategory', 'seolmuntypegb','searchargv','addsurveyset'],
	data: function () {
	    return {
	    	show: false,
	    	surveyList: new Dataset(),
	    	surveyListPaging: new PagingSet(5, 5),
	    	lovValue: '',
	    	pCategorySeq: this.surveycategory ? this.surveycategory : '99',
			pUseYn: '',
			pSeolmunTypeGb: this.seolmuntypegb ? this.seolmuntypegb : '', 
			pSearchType: '1',
	    	pSearchArgv: this.searchargv ? this.searchargv : '',
		    mainListLoading: {id:'mainListLoading', val:false},
		    surveyCodeSet: new CustomcodeSet('/additional/survey/category', {'pUseYn':'Y'}),
		    firstMessage: true,
	    }
 	},
	template:`
		<div>
			<div class="btn-input" v-if="lovcomp">
				<a href="#!" class="btn type02 size-m center-icon" @click="showSurveyPopup()"><span class="icon-search"></span></a>
				<input type="text" class="size-m" v-on:keyup.13="showSurveyPopup();" v-model="lovValue">
			</div>
			<transition name="fade">
				<div class="modal-layer-wrap on" v-show="show">
			    <div :id="'template-popup-survey-'+id" class="modal-layer modal-large" style="display:block;">
			        <div class="modal-container">
			            <div class="modal-header">
		                    <p class="modal-title">{{surveycategory ? '['+surveyCodeSet.getCustomcodeNm('CATEGORY_SEQ', surveycategory, 'CATEGORY_NM')+'] ' : ''}}설문지 검색</p>
		                </div>
		                <div class="modal-content">
		                    <div class="row">
		                        <div class="col s12">
		                        	<div class="box-type03 col-inline">
										<label for="InputText02" class="label-type00 size-m icon-search">검색</label>
								        <select v-if="pCategorySeq" name="pCategorySeq" class="size-m" id="pCategorySeq" v-model="pCategorySeq" data-vv-as="카테고리 " v-validate="'required'" v-bind:class="{'invalid':errors.has('pCategorySeq')}">
							                <option v-for="(item, index) in surveyCodeSet.getCustomcode('CATEGORY_SEQ', 'CATEGORY_NM', 'select')" :value="item.CD">{{item.CD_NM}}</option>
							            </select>
							            <select v-if="pSeolmunTypeGb" class="size-m" id="seolmunTypeGb" name="seolmunTypeGb" v-model="pSeolmunTypeGb" data-vv-as="설문 유형 구분 " v-validate="'required'" v-bind:class="{'invalid': errors.has('seolmunTypeGb')}" disabled="disabled">
					                    	<option v-for="(item, index) in addCommcode.getCommcode('ADD', 'SEOLMUN_TYPE_GB', 'blank', 'CD_NM', 'N')" :value="item.CD">{{item.CD_NM}}</option>
										</select>
							            <select name="pUseYn" class="size-m" id="pUseYn" v-model="pUseYn" data-vv-as="사용여부">
							                <option value="">- 사용여부 -</option>
							                <option value="Y">예</option>
							                <option value="N">아니오</option>
							            </select>
							   			<input type="text" name="pSearchArgv" class="size-m" id="pSearchArgv" v-model="pSearchArgv" placeholder="설문지명" @keyup.13="setSurveyList">
								        <input type="button" value="검색" class="btn type01 size-m" @click="setSurveyList">
								        <div class="f-right">
								            
								        </div>
								    </div>
									<div class="col s12 l12">
										<div class="col s12 l6">
											<span class="board-info-txt size-m">총 <span>{{surveyListPaging.totalCnt}}</span>건의 공개 된 설문지가 있습니다. (<span>{{surveyListPaging.getCurrentPage()}}</span> / {{surveyListPaging.getTotalPage()}})</span>
										</div>
									</div>
				            		<div class="col s12 m12 xl12">
				            			<table class="table-type01 response" id="mainListLoading" summary="카테고리, 설문지명, 설문기간, 익명여부, 사용여부, 공개여부 를 조회 항목별 순서대로 안내하는 표입니다">
				            				<caption>서비스 목록</caption>
				            				<colgroup>
				            					<col /> <!-- 설문지명 -->
				            					<col width="13%" /> <!-- 설문 유형 구분 -->
				            					<col width="10%" /> <!-- 작성자 -->
				            					<col width="27%" /> <!-- 설문기간 -->
				            					<col width="10%" /> <!-- 익명여부 -->
				            					<col width="10%" /> <!-- 사용여부 -->
				            					<col width="6%" /> <!-- 보기 -->
				            				</colgroup>
				            				<thead>
				            					<tr>
				            						<th scope="col" class="left">설문지명</th>
				            						<th scope="col">설문유형구분</th>
				            						<th scope="col">작성자</th>
				            						<th scope="col">설문기간</th>
				            						<th scope="col">익명여부</th>
				            						<th scope="col">사용여부</th>
				            						<th scope="col">보기</th>
				            					</tr>
				            				</thead>
				            				<tbody>		
				            					<tr v-for="(item, index) in surveyList.data" v-if="surveyList.getRowType(index) != 8" @dblclick="selectRow(item)" style="cursor:pointer;">
				            						<td class="left" data-header="설문지명">{{item.SEOLMUNJI_NM}}</td>
				            						<td data-header="설문유형구분">{{addCommcode.getCommcodeNm('ADD','SEOLMUN_TYPE_GB',item.SEOLMUN_TYPE_GB,'CD_NM')}}</td>
				            						<td data-header="작성자">{{item.INSERT_NM}}</td>
				            						<td data-header="설문기간">{{mask('YYYY-MM-DD',item.FR_DT) + ' ' + mask('HH:mm',item.FR_TM) + ' ~ ' + mask('YYYY-MM-DD',item.TO_DT) + ' ' + mask('HH:mm',item.TO_TM)}}</td>
				            						<td data-header="익명여부"><a href="#!" :class="{'btn-table-type02':item.IKMYEONG_YN == 'Y', 'btn-table-type01':item.IKMYEONG_YN == 'N'}"><span :class="{'icon-checkmark':item.IKMYEONG_YN == 'Y', 'icon-minus-circle':item.IKMYEONG_YN == 'N'}"></span></a></td>
				            						<td data-header="사용여부"><a href="#!" :class="{'btn-table-type02':item.USE_YN == 'Y', 'btn-table-type01':item.USE_YN == 'N'}"><span :class="{'icon-checkmark':item.USE_YN == 'Y', 'icon-minus-circle':item.USE_YN == 'N'}"></span></a></td>
				            						<td data-header="미리보기"><a href="#!" class="btn-table-type02" @click="makeServeyPreviewUrl(item.SEOLMUNJI_SEQ)"><span class="icon-preview"></span></a></td>
				            					</tr>
				            				</tbody>
				            			</table>
				            			<paging :pagingset="surveyListPaging" :type="'normal'"></paging>
				            		</div>
				            		<div class="col s12 l12" v-if="addsurveyset != false">
										<div class="col s12 l12 right">
											<span class="board-info-txt size-m icon icon-caution3">&nbsp; 적합한 설문지가 없으신가요? 설문지를 직접 작성하실수 있습니다. <a href="/html/main/index.html?app=survey&menu=02000000" target="_blank" class="btn type01 size-s left-icon icon-corner-left-up" >설문지 작성</a></span>
										</div>
									</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="btn-r">
		                    <a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideSurveyPopup()"><span class="icon-exit"></span></a>
		                </div>
		            </div>
		        </div>
			    </div>
			</transition>
		</div>
	`,
	//데이터 감시
	watch: {
		'surveyListPaging.currentPage': {
			handler: function (val, oldVal) {
				this.surveyList.pageChange(val, this.surveyListPaging.getRowCnt());
			}
	    },
	    'mainListLoading.val': {
	    	handler: function (val, oldVal) {
				this.LoadingOverlay($('#'+this.mainListLoading.id), val);
			}
	    },
	},
    //마운트 된 시점수행
    mounted: function(){
//        this.setSurveyList();
    },
    //함수
    methods: {
    	showSurveyPopup: function(){
    		var modalObj = $('#template-popup-survey-'+this.id); //레이어의 id를 modalObj변수에 저장
    		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
    	    else modalObj.css('left', '0px');
    		//$("html").css("overflow", "hidden");
    		this.pSearchArgv = this.lovValue;
    		this.show = true;
    		this.firstMessage = true;
    		if(!isNull(this.pSearchArgv)) this.setSurveyList();
    	},
    	hideSurveyPopup: function(){
    		this.show = false;
    	},
    	selectRow: function(item){
    		this.$emit('callback', item);
    		this.show = false;
    	},
        /* 설문지 리스트 조회 */
        setSurveyList: function(){
    		var validate = this.$validator.validateAll();
			var self = this;
			
			validate.then(function(response) {
				if(response && self.show){
					self.mainListLoading.val = true;
					var param = {
						pCategorySeq: self.pCategorySeq,
						pSeolmunTypeGb: self.pSeolmunTypeGb,
						pUseYn: self.pUseYn,
						pSearchArgv: self.pSearchArgv, 
						pSearchType: self.pSearchType,
						pPageNo: self.surveyListPaging.currentPage, 
						pRowCount: self.surveyListPaging.getRowCnt(),
						pUsePopupTemplate: 'Y',
					}
					
					var returnVal = surveyApi.seolmunGroup(param, false);
					returnVal.then(function(response) {
						self.surveyList.setRawData(response.data, self.surveyListPaging.getRowCnt());
						if(response.data.length > 0){
							self.surveyListPaging.setTotalCnt(response.data.length);
						}else{
							self.surveyListPaging.setTotalCnt(0);
						}
					}).finally(function(){
						self.mainListLoading.val = false;
						if(self.firstMessage){
							notifySubmit('info', '팝업검색', '선택하려는 대상을 더블클릭 해주세요', 'icon-message');
							self.firstMessage = false;
						}
					});
				}
			});
        },
        makeServeyPreviewUrl: function(pSeolmunjiSeq) {
			var returnVal = surveyApi.getSeolmunInfoEnc(pSeolmunjiSeq,'','',true, true); 
			
			returnVal.then(function(response){
				window.open('/survey/' + response.data, '_blank');
			});
		},
    }
});
