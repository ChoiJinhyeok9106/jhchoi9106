/********************************************************************************************
 * @Writer
 *  주우진 2022.04.18
 *  
 * @Description
 * 	모달 사용자 권한별 검색 템플릿
 * 
 * @Syntax
 *  $.get('/app-assets/popup/template/userauthpopupTemplate.html', function(response){
 *		$('head').append(response); 
 *	});
 * 	<popup-userauth ref="popupuser"></popup-userauth>
 * 
 * @Parameters
 *  
 ********************************************************************************************/ 
 
Vue.component('popup-userauth', {
	props: ['type','id','lovcomp','searchargv','setting','auth'],
	data: function () {
	    return {
	    	show: false,
	    	userList: new Dataset(),
	    	userListPaging: new PagingSet(5, 5),
	    	lovValue: '',
	    	pAuthCd: 'S0002',
	    	pSearchArgv :'',
		    detailSearch: false,
		    listLoading: {id:'listLoading', val:false},
		    firstMessage: true,
		    authList : []
	    }
 	},
	template:`
		<div>
			<div class="btn-input" v-if="lovcomp">
				<a href="#!" class="btn type02 size-m center-icon" @click="showUserPopup()"><span class="icon-search"></span></a>
				<input type="text" class="size-m" v-on:keyup.13="showUserPopup();" v-model="lovValue">
			</div>
			<transition name="fade">
				<div class="modal-layer-wrap on" v-show="show">
			    <div :id="'template-popup-user-'+id" class="modal-layer modal-large" style="display:block;">
			        <div class="modal-container">
			            <div class="modal-header">
		                    <p class="modal-title">사용자 검색</p>
		                </div>
		                <div class="modal-content">
		                    <div class="row">
		                        <div class="col s12">
		                        	<div class="box-type03 col-inline">
										<label for="InputText02" class="label-type00 size-m icon-search">검색</label>
								        <select name="pAuthCd" class="size-m" id="pAuthCd" v-model="pAuthCd" data-vv-as="권한 구분" v-validate="'required'" v-bind:class="{'invalid':errors.has('pAuthCd')}" :disabled="auth">
							                <option v-for="(item, index) in authList" :value="item.AUTH_CD">{{item.AUTH_NM}}</option>
							            </select>
							            <input type="text" name="pSearchArgv" class="size-m" id="pSearchArgv" v-model="pSearchArgv" :placeholder="searchPlaceHolder('text')" @keyup.13="setUserList"  data-vv-as="사용자 명/아이디 " v-validate="searchPlaceHolder('validate')" v-bind:class="{'invalid':errors.has('pSearchArgv')}">
								        <input type="button" value="검색" class="btn type01 size-m" @click="setUserList">
								    </div>
									<div class="col s12 l12">
									    <span class="board-info-txt size-m">총 <span>{{userListPaging.totalCnt}}</span>건의 사용자가 있습니다. (<span>{{userListPaging.getCurrentPage()}}</span> / {{userListPaging.getTotalPage()}})</span>
									</div>
				            		<div class="col s12 m12 xl12">
				            			<table class="table-type01 response" id="listLoading" summary="사용자 아이디, 사용자 명, 부서 코드, 권한 코드, 성별 구분, 생년월일, 사용 여부를 조회 항목별 순서대로 안내하는 표입니다">
				            				<caption>서비스 목록</caption>
				            				<colgroup>
				            					<col width="30%" />
				            					<col width="20%" />
				            					<col width="50%" />
				            				</colgroup>
				            				<thead>
				            					<tr>
				            						<th scope="col">아이디</th>
				            						<th scope="col">이름</th>
				            						<th scope="col">소속</th>
				            					</tr>
				            				</thead>
				            				<tbody v-if="userList.getRowCount() > 0">		
				            					<tr v-for="(item, index) in userList.data" v-if="userList.getRowType(index) != 8" @dblclick="selectRow(item)" style="cursor:pointer;">
				            						<td data-header="아이디">{{item.USER_ID}}</td>
				            						<td data-header="이름">{{item.USER_NM}}</td>
				            						<td data-header="소속">{{item.DEPT_NM}}</td>
				            					</tr>
				            				</tbody>
											<tbody v-else>
												<tr>
													<td data-header="검색결과 없음" colspan="100">검색결과가 없습니다.</td>
												</tr>
											</tbody>
				            			</table>
				            			<paging :pagingset="userListPaging" :type="'normal'"></paging>
				            		</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="btn-r">
		                    <a href="#!" class="cbtn" style="display: inline-block;" title="닫기" @click="hideUserPopup()"><span class="icon-exit"></span></a>
		                </div>
		            </div>
		        </div>
			    </div>
			</transition>
		</div>
	`,
	//데이터 감시
	watch: {
		'userListPaging.currentPage': {
			handler: function (val, oldVal) {
				//this.setUserList()
				this.userList.pageChange(val, this.userListPaging.getRowCnt());
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
        //this.setUserList();
    	this.setAuthList();
    },
    //함수
    methods: {
    	showUserPopup: function(){
    		if(this.auth){
    			this.pAuthCd = this.auth;
    		}
    		var modalObj = $('#template-popup-user-'+this.id); //레이어의 id를 modalObj변수에 저장
    		if (modalObj.outerWidth() < $(document).width()) modalObj.css('margin-left', '-' + modalObj.outerWidth() / 2 + 'px');
    	    else modalObj.css('left', '0px');
    		//$("html").css("overflow", "hidden");
    		this.pSearchArgv = this.lovValue;
    		this.show = true;
    		this.firstMessage = true;
    		if(!isNull(this.pSearchArgv)) this.setUserList();
    	},
    	hideUserPopup: function(){
    		this.show = false;
    	},
    	selectRow: function(item){
    		if(this.setting){
    			this.lovValue = item[this.setting];
    		}
    		this.$emit('callback', item);
    		this.show = false;
    	},
        setAuthList: function(){
			var self = this;
			var returnVal = authApiApi.getAllAuthList({}, false);
			returnVal.then(function(response) {
				self.authList = response.data;
			});
        },
        /* 사용자 리스트 조회 */
        setUserList: function(){
    		var validate = this.$validator.validateAll();
			var self = this;
			
			validate.then(function(response) {
				if(response && self.show){
					self.listLoading.val = true;
					var param = {
						pAuthCd : self.pAuthCd,
						pSearchArgv : self.pSearchArgv
					};
					
					var returnVal = userApiApi.userByAuth(param, false);
					returnVal.then(function(response) {
						self.userList.setRawData(response.data, self.userListPaging.getRowCnt());
						if(response.data.length > 0){
							self.userListPaging.setTotalCnt(response.data.length);
						}else{
							self.userListPaging.setTotalCnt(0);
						}
					}).finally(function(){
						self.listLoading.val = false;
						if(self.firstMessage){
							notifySubmit('info', '팝업검색', '선택하려는 대상을 더블클릭 해주세요', 'icon-message');
							self.firstMessage = false;
						}
					});
				}
			});
        },
        searchPlaceHolder: function(pGb) {
	    	if(pGb == 'text'){
		    	return '사용자 명/아이디';
	    	}else if(pGb == 'validate'){
	    		return 'required|min:2';
	    	}
	    },
    }
});
