/********************************************************************************************
 * @Writer
 *  주우진 2022.04.12 
 *  
 * @Description
 * 	모달 기관 검색 템플릿
 * 
 * @Syntax
 *  $.get('/app-assets/popup/template/corppopupTemplate.html', function(response){
 *		$('head').append(response); 
 *	});
 * 	<popup-corp ref="popupcorp"></popup-corp>
 * 
 * @Parameters
 *  
 ********************************************************************************************/ 
 
Vue.component('popup-corp', {
	props: ['type','id','lovcomp','setting','option','base'],
	data: function () {
	    return {
	    	show: false,
	    	corpList: new Dataset(),
	    	corpListPaging: new PagingSet(5, 5),
	    	lovValue: '',
		    detailSearch: false,
		    listLoading: {id:'listLoading', val:false},
		    firstMessage: true,
		    pSearchArgv: '',
	    }
 	},
	template:`
		<div>
			<div class="btn-input" v-if="lovcomp">
				<a href="#!" class="btn type02 size-m center-icon" @click="showCorpPopup()"><span class="icon-search"></span></a>
				<input type="text" class="size-m" v-on:keyup.13="showCorpPopup();" v-model="lovValue">
			</div>
			<transition name="fade">
				<div class="modal-layer-wrap on" v-show="show">
			    <div :id="'template-popup-corp-'+id" class="modal-layer modal-large" style="display:block;">
			        <div class="modal-container">
			            <div class="modal-header">
		                    <p class="modal-title">기관 검색</p>
		                </div>
		                <div class="modal-content">
		                    <div class="row">
		                        <div class="col s12 l12">
		                        	<div class="box-type03">
									    <div class="col s12 l12 col-inline left">
											<label for="InputText02" class="label-type00 size-m icon-search">검색</label>
								   			<input type="text" name="pSearchArgv" class="size-m" id="pSearchArgv" v-model="pSearchArgv" placeholder="사업자등록번호/기관명검색" @keyup.13="setCorpList">
									        <input type="button" value="검색" class="btn type01 size-m" @click="setCorpList">
									    </div>
								    </div>
									<div class="col s12 l12 col-form-txt">
										<span class="board-info-txt size-m f-left">총 <span>{{corpListPaging.totalCnt}}</span>건의 기관이 있습니다. (<span>{{corpListPaging.getCurrentPage()}}</span> / {{corpListPaging.getTotalPage()}})</span>
                        				<small class="form-txt f-right">선택할 기관을 더블클릭해주세요</small>
									</div>
				            		<div class="col s12 m12 xl12">
				            			<table class="table-type01 response" id="listLoading">
				            				<colgroup>
				            					<col width="20%"/>
				            					<col width="40%"/>
				            					<col width="20%"/>
				            					<col width="20%"/>
				            				</colgroup>
				            				<thead>
				            					<tr>
				            						<th scope="col">사업자등록번호</th>
				            						<th scope="col">기관명</th>
				            						<th scope="col">대표자명</th>
				            						<th scope="col">전화번호</th>
				            					</tr>
				            				</thead>
				            				<tbody v-if="corpList.getRowCount() > 0">		
				            					<tr v-for="(item, index) in corpList.data" v-if="corpList.getRowType(index) != 8" @dblclick="selectRow(item)" style="cursor:pointer;">
				            						<td class="left" data-header="사업자등록번호">{{item.CORP_BIZ_NO.replaceAll('-','').replace(/([0-9]{3})([0-9]{2})([0-9]{5})$/, '$1-$2-$3')}}</td>
				            						<td class="left" data-header="기관명">{{item.CORP_NM}}</td>
				            						<td class="center" data-header="대표자명">{{item.CORP_CEO_NM}}</td>
				            						<td class="center" data-header="전화번호">{{item.CORP_TEL_NO.replaceAll('-','').replace(/([0-9]{2,3})([0-9]{3,4})([0-9]{4})$/, '$1-$2-$3')}}</td>
				            					</tr>
				            				</tbody>
											<tbody v-else>
												<tr>
													<td data-header="검색결과 없음" colspan="100">검색결과가 없습니다.</td>
												</tr>
											</tbody>
				            			</table>
				            			<paging :pagingset="corpListPaging" :type="'normal'"></paging>
				            		</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="btn-r">
		                    <a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideCorpPopup()"><span class="icon-exit"></span></a>
		                </div>
		            </div>
		        </div>
			    </div>
			</transition>
		</div>
	`,
	//데이터 감시
	watch: {
		'corpListPaging.currentPage': {
			handler: function (val, oldVal) {
				//this.setCorpList()
				this.corpList.pageChange(val, this.corpListPaging.getRowCnt());
			}
	    },
	    'listLoading.val': {
	    	handler: function (val, oldVal) {
				this.LoadingOverlay($('#'+this.listLoading.id), val);
			}
	    },
	},
    //마운트 된 시점수행
    mounted: function(){
        //this.setCorpList();
    },
    //함수
    methods: {
    	showCorpPopup: function(){
    		var modalObj = $('#template-popup-corp-'+this.id); //레이어의 id를 modalObj변수에 저장
    		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
    	    else modalObj.css('left', '0px');
    		this.pSearchArgv = this.lovValue;
    		this.show = true;
    		this.firstMessage = true;
    		if(!isNull(this.pSearchArgv)) this.setCorpList();
    	},
    	hideCorpPopup: function(){
    		this.show = false;
    	},
    	selectRow: function(item){
    		if(this.setting){
    			this.lovValue = item[this.setting];
    		}
    		this.$emit('callback', item);
    		this.show = false;
    	},
        /* 사용자 리스트 조회 */
        setCorpList: function(){
    		var validate = this.$validator.validateAll();
			var self = this;
			
			validate.then(function(response) {
				if(response && self.show){
					self.listLoading.val = true;
					var param = { 
						pSearchArgv : self.pSearchArgv,
					}
					
					var returnVal = userApiApi.getCorpList(param, false);
					returnVal.then(function(response) {
						self.corpList.setRawData(response.data, self.corpListPaging.getRowCnt());
						if(response.data.length > 0){
							self.corpListPaging.setTotalCnt(response.data.length);
						}else{
							self.corpListPaging.setTotalCnt(0);
						}
					}).finally(function(){
						self.listLoading.val = false;
					});
				}
			});
        },
    }
});