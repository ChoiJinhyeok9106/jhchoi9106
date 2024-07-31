/********************************************************************************************
 * @Writer
 *  조혜린 2023.10.17
 *
 * @Description
 * 	결재 시스템 템플릿
 *
 * @Syntax
 * 	$.get('/assets/cf_component/template/PagingTemplate.html', function(response){
 *		$('head').append(response);
 *	});
 *
 * @Parameters
 *  pagingSet : PagingSet => appListPaging: new PagingSet(pPageCnt, pRowCnt)
 *  type : normal
 ********************************************************************************************/
Vue.component('approvalrpt', {
	props: ['app_gb', 'rptgb', 'pjtno', 'reprt_date', 'approseq', 'mainbssdate'],
	data: function () {
	    return {
	    	userId : '',
			mainList : new Dataset(),
			detailList : new Dataset(),
			treeData : '',
			masterGrid : new Dataset(),
			grid : new Dataset(),
			chkDataList : new Dataset(),
			frontLineDataset : new Dataset(),
			detailSelectedRowkey : '',
			plineRowKey : '',
			mountApproStGb : '01',
			messageModal: new ModalSet('modal-test'),
			frontlineCnt:'',
			approYn: '',
			chkUserNm:'',
			TITLE: '',
			date: '',
			approState: '',
			approStateRsn: '',
			authYn: '',
			approGb: '',
			pjtNm: '프로젝트명',
			prgmNm: '프로그램이름',
			RJCT_RSN: '',
			rpt_gb: '',
			setLine: false,
	    }
	},
	template:`
	<div class="nonsubject-info" id="mainDiv">
	<link rel="stylesheet" type="text/css" href="/service/design/assets/css/ability.css" />
	<link rel="stylesheet" type="text/css" href="/service/gaia/assets/css/workreprt.css"/>
	    <div class="head-box" style="text-align:left">
	    	<div class="form-group right">
				<a href="#!" class="btn type01 size-m" v-if="authYn == '03' && approGb == '02'" @click="fixAgrRpt();">승인</a>
				<a href="#!" class="btn type01 size-m" v-if="authYn == '03' && approGb == '02'" @click="fixDenRptModalOpen();">반려</a>
				<a href="#!" class="btn type01 size-m" @click="moveMain();">메인으로이동</a>
			</div>
	    	<div class="box-type01">
		        <div class="form-group row form-row">
		            <div class="col s12 m6 l6 xl6 pro-info-box" style="padding-top: 10px; padding-left: 30px;">
		                <span class="pro-photo"><img src="/service/design/images/mileage/mileage_cate04.jpg" alt="" /></span>
		                <span class="pro-kind">{{ prgmNm }}</span>
		                <span class="pro-name" style="font-size: 30px;">{{ date|dateFormatter }} {{ rptNm }}</span>
		                <span class="pro-name" style="font-size: 20px; margin-left: 80px;">({{ pjtNm }})</span>
		            </div>
		            <div class="col s12 m6 l6 xl6">
				        <table class="table-type01">
						    <caption>결재라인 표</caption>
						    <colgroup>
						        <col width="15%" />
								<col>
								<col>
						    </colgroup>
						    <thead>
						        <tr>
						        	<th scope="col" v-if="frontLineDataset.data.length > 0">결재상태</th>
						        	<th scope="col" v-if="frontLineDataset.data.length > 0">결재요청</th>
									<th scope="col" v-if="frontLineDataset.data.length > 0" v-for="(item, index) in frontLineDataset.data">{{ item.CD_NM +' '+ item.USER_NM}}</th>
									<th scope="col" v-if="frontLineDataset.data.length == 0">결재자 등록</th>
						        </tr>
						    </thead>
						    <tbody>
						        <tr>
						        	<td v-if="frontLineDataset.data.length > 0 && approState != '반려'"><span style="font-size: 15px;">{{ approState }}</span></td>
						        	<td v-if="frontLineDataset.data.length > 0 && approState == '반려'">
						        		<a href="#!" class="btn type02 size-m center-icon" @click="denRptRsnModalOpen()">{{ approState }}</a>
						        	</td>
						        	<td v-if="frontLineDataset.data.length == 0"><a href="#!"class="icon-search" @click="approvalLineOpen()"></a></td>
						        	<td v-if="frontLineDataset.data.length > 0" style="font-size: 15px;">
						        		<span>{{ frontLineDataset.data[0].INSERT_DTTM|dateFormatter }} <br/> {{ frontLineDataset.data[0].ABBR_NM }} {{ frontLineDataset.data[0].REQ_USER_NM }}</span>
						        	</td>
									<td v-if="frontLineDataset.data.length > 0" style="font-size: 15px;" v-for="(item, index) in frontLineDataset.data">
										<span v-if="item.APPRO_CHK_DTTM != '' && setLine == true"> - </span>
										<span v-if="item.APPRO_CHK_DTTM != '' && setLine == false">{{ item.APPRO_CHK_DTTM|dateFormatter }} {{ item.APPRO_ST_GB }}</span>
										<span v-if="item.APPRO_CHK_DTTM == ''">{{ item.APPRO_ST_GB }}</span>
									</td>
						        </tr>
						    </tbody>
						</table>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="modal-layer-wrap">
			<div id="aprvl_modal" class="modal-layer modal-large">
				<div class="modal-container">
	            	<div class="modal-header">
	            		<p class="modal-title">결재선지정</p>
	            	</div>
	            	<div class="modal-content">
						<div class="form-group row form-row" style="margin-left:180px;">
							<div class="col s12 m6 l3">
								<div id="master-grid" style="width:300px;"></div>
							</div>
							<div class="col s12 m6 l1" style="width:50px; margin-left:20px;"></div>
							<div class="col s12 m6 l1" style="width:50px; margin-left:30px; margin-right:20px;"></div>
							<div class="col s12 m6 l3">
								<div id="pline-grid" style="width:300px;"></div>
							</div>
						</div>
						<div class="form-group row form-row" style="margin-left:180px;">
							<div class="col s12 m6 l3">
							</div>
							<div class="col s12 m6 l3">
							</div>
							<div class="col s12 m6 l3" style="margin-left:10px;">
								<input type="button" value="▼" class="btn type01 size-s" @click="confirmOpen('plineuser')">
								<input type="button" value="개인결재선명 수정" class="btn type01 size-s" @click="fixTitleModalOpen()">
							</div>
						</div>
						<div class="form-group row form-row" style="margin-left:180px;">
							<div class="col s12 m6 l3">
								<div id="detail-grid" style="width:300px;"></div>
							</div>
							<div class="col s12 m6 l1" style="width:50px; margin-left:20px;"></div>
							<div class="col s12 m6 l1" style="width:50px; margin-left:30px; margin-right:20px;">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<input type="button" value="▶" class="btn type01 size-s" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="selectAppro()"><br /><br />
								<input type="button" value="◀" class="btn type01 size-s" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="rmvPlineUser()">
							</div>
							<div class="col s12 m6 l3">
								<div id="plineuser-grid" style="width:300px;"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer center">
						<a href="#!" class="btn type01 size-m" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="messagepoppupcallback('','modal1')">선택</a>
						<a href="#!" class="btn type01 size-m" @click="approvalLineClose()">닫기</a>
	                	<a href="#!" class="btn type01 size-m" @click="confirmOpen('plineUserInsert')">개인결재선 추가</a>
	                	<a href="#!" class="btn type01 size-m" @click="confirmOpen('plineUserDelete')">개인결재선 삭제</a>
	            	</div>
				</div>
			</div>
		</div>
		<div class="modal-layer-wrap">
			<div id="fix_title_modal" class="modal-layer modal-small">
				<div class="modal-container">
	            	<div class="modal-header">
	            		<p class="modal-title">개인결재선명 수정</p>
	            	</div>
	            	<div class="modal-content">
						<div class="box-type04">
							<div class="row form-row">
								<div class="form-group col s12 l12">
									<label class="label-type01">개인결재선명 수정</label>
							        <input type="text" class="size-m" name="titleN1" v-model="TITLE">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer center">
						<a href="#!" class="btn type01 size-m" @click="plineTitleUpdate()">수정</a>
						<a href="#!" class="btn type01 size-m" @click="fixTitleModalClose()">닫기</a>
	            	</div>
				</div>
			</div>
		</div>
		<div class="modal-layer-wrap">
			<div id="den_rpt_modal" class="modal-layer modal-small">
				<div class="modal-container">
	            	<div class="modal-header">
	            		<p class="modal-title">보고서 반려</p>
	            	</div>
	            	<div class="modal-content">
						<div class="box-type04">
							<div class="row form-row">
								<div class="form-group col s12 l12">
									<label class="label-type01">반려 사유</label>
							        <input type="text" class="size-m" name="title" v-model="RJCT_RSN">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer center">
						<a href="#!" class="btn type01 size-m" @click="fixDenRpt()">반려</a>
						<a href="#!" class="btn type01 size-m" @click="fixDenRptModalClose()">닫기</a>
	            	</div>
				</div>
			</div>
		</div>
		<!-- 반려 사유 모달 -->
		<div class="modal-layer-wrap">
			<div id="den_rpt_rsn_modal" class="modal-layer modal-small">
				<div class="modal-container">
	            	<div class="modal-header">
	            		<p class="modal-title">반려 사유</p>
	            	</div>
	            	<div class="modal-content">
						<div class="box-type04">
							<div class="row form-row">
								<div class="form-group col s12 l12">
									<label class="label-type01">반려 사유</label>
							        <input type="text" class="size-m" name="title" v-model="approStateRsn" disabled>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer center">
						<a href="#!" class="btn type01 size-m" @click="denRptRsnModalClose()">닫기</a>
	            	</div>
				</div>
			</div>
		</div>
		<!-- // 반려 사유 모달 -->
		<modal-message :modalset="messageModal" v-on:messagepoppupcallback="messagepoppupcallback"></modal-message>
	</div>
	`,
	//데이터 감시
	watch: {
        'mainList.data': {
            handler: function(val, oldVal) {
                if(this.mainList.enableevent) {
                    var vm = this;
                    val.filter(function(data, rowposition) {
                        return Object.keys(data).some(function(colNm) {
                            var diff = data[colNm] !== vm.mainList.beforeData[rowposition][colNm];
                            if(diff) {
                                vm.mainList.beforeData[rowposition][colNm] = data[colNm];
                            }
                        });
                    });
                }
            },
            deep: true
        },
	},
	filters: {
    	dateFormatter : function (date) {
    		return moment(date).format('YYYY-MM-DD');
    	}
    },
	created: function(){
    	this.getRptInfo();
    },
    mounted: function(){
    	this.userId = this.session.INTERNAL_ID;
    	if(this.approSeq != '0'){
			this.getAprvLineList();
		}
        this.searchApi();
        this.drawDetailGrid();
        this.drawPLineGrid();
        this.drawPLineUserGrid();
    },
    //함수
    methods: {
		// 문서 유형
		getRptInfo: function(){
			var self = this;
			if(this.rptgb == '01'){
				self.rptNm = '작업일보';
			}else if(this.rptgb == '02'){
				self.rptNm = '주간작업보고';
			}else if(this.rptgb == '03'){
				self.rptNm = '월간작업보고';
			}else if(this.rptgb == '04'){
				self.rptNm = '감리일지';
			}else if(this.rptgb == '05'){
				self.rptNm = '감리주간보고';
			}else if(this.rptgb == '06'){
				self.rptNm = '감리월간보고';
			}
			self.date = this.reprt_date;
			self.rpt_gb = this.rptgb;
			var param = {
				pjtNo: this.pjtno
			};
			var returnVal = headcomponentApi.getPjtInfo(param, false);
			returnVal.then(function (response) {
				self.prgmNm = response.data[0].PRGMNM;
				self.pjtNm = response.data[0].PJTNM;
			}).finally(function () {
				if(this.approseq != '0'){
					self.getAuthRpt();
				}
			});
		},
		getAuthRpt: function(){
			var self = this;
			var param = {
				userId: this.session.INTERNAL_ID,
				pjtNo: this.pjtno,
				approSeq: this.approseq
			};
			var returnVal = headcomponentApi.getAuthRpt(param, false);
			returnVal.then(function (response) {
				if(response.data.length == 0){
					self.approGb = '';
					self.authYn = '';
				} else {
					self.approGb = response.data[0].APPRO_GB;
					self.authYn = response.data[0].APPRO_ST_GB;
				}
			});
		},
		fixAgrRpt: function(){
			var self = this;
			var param = {
				userId: this.session.INTERNAL_ID,
				pjtNo: this.pjtno,
				approSeq: this.approseq
			};
			var returnVal = headcomponentApi.fixAgrRpt(param, false);
			returnVal.then(function (response) {
				if(response.data > 0){
					notifySubmit('success', '승인', '승인되었습니다.', 'icon-save');
		    		self.detailgrid.clear();
		    		self.plineusergrid.clear();
					self.getAuthRpt();
					self.getAprvLineList();
					if(self.rpt_gb == '01'){
						self.fixQdb();
					}
				} else {
					notifySubmit('warning', '결재 승인', '결재 승인 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
				notifySubmit('warning', '결재 승인', '결재 승인 실패하였습니다.', 'icon-caution');
			});
		},
		fixQdb: function(){
			var self = this;
			var param = {
				pjtNo: this.pjtno,
				approSeq: this.approseq,
				reportDate: this.reprt_date
			};
			var returnVal = headcomponentApi.fixQdb(param, false);
			returnVal.then(function (response) {
				var data = response.data;
			});
		},
		fixDenRpt: function(){
			var self = this;
			var param = {
				userId: this.session.INTERNAL_ID,
				pjtNo: this.pjtno,
				approSeq: this.approseq,
				rjctRsn: self.RJCT_RSN,
			};
			var returnVal = headcomponentApi.fixDenRpt(param, false);
			returnVal.then(function (response) {
				if(response.data > 0){
					notifySubmit('success', '반려', '반려되었습니다.', 'icon-save');
		    		self.detailgrid.clear();
		    		self.plineusergrid.clear();
					self.getAuthRpt();
					self.getAprvLineList();
					self.fixDenRptModalClose();
				} else {
					notifySubmit('warning', '결재 반려', '결재 반려 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
				notifySubmit('warning', '결재 반려', '결재 반려 실패하였습니다.', 'icon-caution');
			});
		},
		moveMain: function(){
			var self = this;
			if(this.app_gb == 'GIA'){
				if(this.rptgb == '01' || this.rptgb == '02' || this.rptgb == '03'){
					self.mainPage = 'workreprtmain';
				}else if(this.rptgb == '04' || this.rptgb == '05' || this.rptgb == '06'){
					self.mainPage = 'cnstrtnspvsreprtmain';
				}
				contentsOpen('GIA', self.mainPage, {'RPT_GB': self.rpt_gb, 'MAIN_BSS_DATE': this.mainbssdate});
			} else if(this.app_gb == 'CMS'){
				if(this.rptgb == '01' || this.rptgb == '02' || this.rptgb == '03'){
					self.mainPage = 'workreprtmaincms';
				}else if(this.rptgb == '04' || this.rptgb == '05' || this.rptgb == '06'){
					self.mainPage = 'cnstrtnspvsreprtmaincms';
				}
				contentsOpen('CMS', self.mainPage, {'RPT_GB': self.rpt_gb, 'MAIN_BSS_DATE': this.mainbssdate});
			}
		},
		//결재선 팝업 오픈
		approvalLineOpen : function(){
			var self = this;
//			if(self.mountApproStGb != '01') return;
			self.setPLineGridData();
			if(self.frontLineDataset.data.length > 0){
				self.plineusergrid.resetData(self.frontLineDataset.data);
			}
    		modal_open('aprvl_modal');
    	},
    	approvalLineClose : function(){
    		modal_close('aprvl_modal');
    	},
    	//결재선 명 수정 모달
		fixTitleModalOpen : function(){
			var self = this;
			self.plinegrid.focus(0);
    		modal_open('fix_title_modal');
    	},
    	fixTitleModalClose : function(){
    		modal_close('fix_title_modal');
    	},
    	fixDenRptModalOpen : function(){
			var self = this;
    		modal_open('den_rpt_modal');
    	},
    	fixDenRptModalClose : function(){
    		modal_close('den_rpt_modal');
    	},
    	// 결재선 조회
    	getAprvLineList : function(rowKey){
			var self = this;
			var param = {
				pjtNo: this.pjtno,
				approSeq: this.approseq
			};
			var returnVal = headcomponentApi.getAprvLineList(param, false);
			returnVal.then(function(response) {
				self.frontLineDataset.setData(response.data);
				for(var i = 0; i < self.frontLineDataset.data.length; i++){
					if(self.frontLineDataset.data[i].APPRO_ST_GB == '반려'){
						self.approState = '반려';
						self.approStateRsn = self.frontLineDataset.data[i].RJCT_RSN;
						break;
					}else if(self.frontLineDataset.data[i].APPRO_ST_GB == '미결'){
						self.approState = '진행중';
						break;
					}else if(self.frontLineDataset.data[i].APPRO_ST_GB == '작성중'){
						self.approState = '작성중';
						break;
					}else{
						self.approState = '승인';
					}
				}
			}).finally(function () {
				self.$emit('callback', self.frontLineDataset.data);
            });
		},
		setIconClass: function(ynGb, atGb){
			var self = this;
			if(ynGb == 'Y' && atGb == '승인'){
				return 'icon-checkmark';
			}else if(ynGb == 'Y' && atGb == '반려'){
				return 'icon-pause';
			}else{
				return 'icon-minus-circle';
			}
		},
		searchApi: function() {
			var self = this;
			var returnVal = headcomponentApi.deptList('',true);
			returnVal.then(function(response) {
				self.mainList.setData(response.data);
				self.tree(response.data);
			});
        },
		tree: function(pData){
			var self = this;
			var lvObj = {};
			lvObj[1] = pData;
			for(var j=0 ; j < lvObj[1].length ; j++){
				var obj = lvObj[1][j];
				var findIndex = _.findIndex(lvObj[0], {'CD':obj.CD});
				if(findIndex > -1){
					if(!lvObj[0][findIndex]['_children']){
						lvObj[0][findIndex]['_children'] = [];
					}
					lvObj[0][findIndex]['_children'].push(obj);
				}
			}
			self.treeData = lvObj[1];
			this.drawGrid();
		},
        drawGrid: function(){
        	var self = this;
        	var list = [];
			list = [
   				{
   					header: '조직',
   					name: 'CD_NM',
   					//editor: 'text'
   			        sortingType: 'asc',
   			     	width: 280,
   			     	whiteSpace: 'normal'
   				}
			];
			this.grid = new tui.Grid({
	   		    el: document.getElementById('master-grid'),
//	   			scrollX: true,
	   		 	bodyHeight: 200,
	   			selectionUnit: 'row',
	   			columns: list,
	   			columnOptions: {
	   		    	frozenCount: self.wwidth < 1400 ? 0 : 1,
	   		   		resizable: true,
	   		    },
	   		    treeColumnOptions:{
					name:'CD_NM',
					useIcon: true,
					useCascadingCheckbox: true
				},

	   		});
			this.$nextTick(function() {
				this.setGridData();
			});
        },
		setGridData : function(){
			var self = this;
			self.grid.resetData(self.treeData);
			self.masterGrid = self.grid;
			self.$nextTick(function() {
	   			self.gridClick();
		    });
			this.$forceUpdate();
		},
	   	gridClick : function(){
			var self = this;
			self.grid.on('click', function(event) {
		   		var row = event.rowKey;
		   		if(event.targetType == 'cell') {
		   			self.gridCellClick(row,'');
		   		}
            });
		},
		gridCellClick : function(rowKey, columnInfo) {
			var self = this;
			var param = {
				pCD: self.grid.getRow(rowKey).CD,
				pPjtNo: this.pjtno
			};
			var returnVal = headcomponentApi.userList(param, true);
			returnVal.then(function(response) {
				self.detailList.setData(response.data);
			}).finally(function () {
				self.setDetailGridData();
            });
		},
		setDetailGridData : function(){
			var self = this;
			self.detailgrid.resetData(self.detailList.data);
			this.$forceUpdate();
		},
        drawDetailGrid: function(){
        	var self = this;
			this.detailgrid = new tui.Grid({
	   		    el: document.getElementById('detail-grid'),
	   		    rowHeaders: ['checkbox'],
//	   			scrollX: true,
	   		 	bodyHeight: 200,
	   			columns: [
						{
		   					header: '구분',
		   					name: 'CD_NM',
		   					//editor: 'text'
		   			     	width: 120,
		   				},
			   			{
		   					header: '이름',
		   					name: 'USER_NM',
		   					//editor: 'text'
		   			     	width: 120,
		   				}
			   	],
	   			columnOptions: {
	   		    	frozenCount: 2,
	   		   		resizable: false,
	   		    }
	   		});
			self.$nextTick(function() {
	   			self.detailgridClick();
		    });
        },
	   	detailgridClick : function(){
			var self = this;
			self.detailgrid.on('click', function(event) {
		   		var row = event.rowKey;
		   		var columnName = event.columnName;
		   		if(event.targetType == 'cell') {
		   			self.detailgridCellClick(row, columnName);
		   		}
            });
		},
		detailgridCellClick : function(rowKey, columnInfo) {
			var self = this;
			self.detailSelectedRowkey = rowKey;
		},
        drawPLineGrid: function(){
        	var self = this;
			this.plinegrid = new tui.Grid({
	   		    el: document.getElementById('pline-grid'),
	   			scrollX: true,
	   		 	bodyHeight: 200,
	   			columns: [
	   					{
		   					header: '개인결재선',
		   					name: 'APPRO_LINE_TITLE',
//		   					editor: 'text',
		   			        sortingType: 'asc',
		   			        align: 'center',
		   			     	width: 282,
		   			     	whiteSpace: 'normal'
		   				}
	   			],
	   			columnOptions: {
	   		    	frozenCount: 2,
	   		   		resizable: true,
	   		    }
	   		});
			self.$nextTick(function() {
				self.setPLineGridEvent();
	   			self.setPLineGridData();
		    });
        },
        setPLineGridEvent : function(){
			var self = this;
			self.plinegrid.on('click', function(event) {
		   		var row = event.rowKey;
		   		var columnName = event.columnName;
		   		self.TITLE = self.plinegrid.store.data.rawData[0].APPRO_LINE_TITLE;
		   		if(event.targetType == 'cell') {
		   			self.setPlineRowKey(row);
					self.plinegrid.startEditing(row, 'APPRO_LINE_TITLE');
		   		}
            });
        },
        setPlineRowKey: function(rowKey){
        	var self = this;
        	self.plineRowKey = rowKey;
        },
		setPLineGridData : function(){
			var self = this;
			var param = {
				pUserId: self.userId,
				pPjtNo: this.pjtno
			};
			var returnVal = headcomponentApi.pline(param, false);
			returnVal.then(function(response) {
				self.plinegrid.resetData(response.data);
			});
		},
		setPLineUserGridData : function(rowKey){
			var self = this;
			var param = {
				pSeq: self.plinegrid.getRow(rowKey).APPRO_LINE_SEQ,
				pUserId: self.userId
			};
			var returnVal = headcomponentApi.plineuser(param, false);
			returnVal.then(function(response) {
				self.plineusergrid.resetData(response.data);
			});
		},
        drawPLineUserGrid: function(){
        	var self = this;
			this.plineusergrid = new tui.Grid({
	   		    el: document.getElementById('plineuser-grid'),
	   		    rowHeaders: ['checkbox'],
	   			scrollX: true,
	   		 	bodyHeight: 200,
	   			columns: [{
   					header: '구분',
   					name: 'CD_NM',
   					//editor: 'text'
   			        align: 'center',
   			     	width: 70,
   			     	whiteSpace: 'normal'
	   			},
	   			{
   					header: '이름',
   					name: 'USER_NM',
   			        align: 'center',
   			        width:80,
   			     	whiteSpace: 'normal'
   				},
   				{
   					header: '결재구분 ',
   					name: 'APPRO_GB',
   					//editor: 'text'
   			        align: 'center',
   			        width:92,
   			        formatter: 'listItemText',
   			     	editor: {
      			         type: 'select',
      			         options: {
      			        	listItems: [{text:'참조', value:'01'},{text:'결재', value:'02'}],
      			        	autofocus:true,
      			        	autoclose:true,
      			        	showIcon:true,
      			         },
   			     	},
   				}],
   				editingEvent: 'click',
	   			columnOptions: {
	   				frozenCount: 3,
	   		   		resizable: false,
	   		    }
	   		});
			self.$nextTick(function() {
	   			self.plineusergridEventhandler();
		    });
        },
        plineusergridEventhandler : function(){
			var self = this;

			self.plineusergrid.on('click', function(event) {
		   		var row = event.rowKey;
		   		var columnName = event.columnName;
		   		if(event.targetType == 'cell') {
		   			self.plineusergridCellClick(row, columnName);
		   		}
            });
		},
		plineusergridCellClick : function(rowKey, columnInfo) {
			var self = this;
			self.plineusergrid.startEditing(rowKey, 'APPRO_GB');
		},
    	async selectAppro(){
    		var self = this;
    		var resultSync = await self.addPlineUser();
    		if(resultSync) {
    			self.plineusergridCellClick(self.plineusergrid.findRows({USER_NM:self.chkUserNm})[0].rowKey);
    		}
    	},
    	addPlineUser : function(){
    		var self = this;
    		self.chkUserNm = '';
    		var chkRow = 0;
    		var rowKeyNum = 0;
    		if(self.plineusergrid.getRowCount() > 0) rowKeyNum = self.plineusergrid.getRowAt(self.plineusergrid.getRowCount()-1).rowKey+1;
    		chkRow = self.detailgrid.getCheckedRows();
    		if(chkRow.length > 0){
    			for(var i = 0; i < chkRow.length; i++){
	    			if(self.plineusergrid.findRows({USER_NM:chkRow[i].USER_NM}) == ''){
	    				//rowkey 세팅
	    				chkRow[i]['rowKey'] = rowKeyNum+i;
	    				//데이터 세팅
	    				self.plineusergrid.appendRow(chkRow[i]);
	    				if(self.chkUserNm == '') self.chkUserNm = chkRow[i].USER_NM;
	    			}
    			}
    			return true;
    		}
    		else {
    			return false;
    		}
    	},
    	rmvPlineUser : function(){
    		var self = this;
    		self.plineusergrid.removeCheckedRows();
    		self.plineusergrid.focus(0);
//    		self.plineusergrid.focus(0,'OFCPS_NM');
    	},
    	plineNewRow: function(){
    		var self = this;
    		var setOrdNum = 0;
			var params = [];
			self.plineusergrid.focus(0);
			for(var i=0; i < self.plineusergrid.getRowCount(); i++){
    			if(self.plineusergrid.getRow(i).APPRO_GB == '01'){
    				params.push({
					  'USER_ID': self.userId,
					  'APPRO_ID': self.plineusergrid.getRow(i).INTERNAL_ID,
					  'APPRO_ORD': 0,
					  'APPRO_GB': self.plineusergrid.getRow(i).APPRO_GB,
					  'PJT_NO': this.pjtno,
					  'APPRO_AUTH': self.plineusergrid.getRow(i).APPRO_AUTH,
					});
    			}
    			else if(self.plineusergrid.getRow(i).APPRO_GB == '02'){
    				setOrdNum += 1;
    				params.push({
					  'USER_ID': self.userId,
					  'APPRO_ID': self.plineusergrid.getRow(i).INTERNAL_ID,
					  'APPRO_ORD': setOrdNum,
					  'APPRO_GB': self.plineusergrid.getRow(i).APPRO_GB,
					  'PJT_NO': this.pjtno,
					  'APPRO_AUTH': self.plineusergrid.getRow(i).APPRO_AUTH,
					});
    			}
    		}
			var returnVal = headcomponentApi.insertPline(params, self.userId, true);
			returnVal.then(function (response) {
				if(response.data > 0){
					notifySubmit('success', '개인결재선 저장', '개인결재선 생성이 완료되었습니다.', 'icon-trashcan');
					self.setPLineGridData();
				} else {
					notifySubmit('warning', '개인결재선 저장', '개인결재선 생성에 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
				notifySubmit('warning', '개인결재선 저장', '개인결재선 생성에 실패하였습니다.', 'icon-caution');
				self.setPLineGridData();
			});
    	},
    	plineDelRow: function(){
    		var self = this;
    		var param = {
				pSeq: self.plinegrid.getRow(self.plineRowKey).APPRO_LINE_SEQ,
				pUserId: self.userId
			};
			var returnVal = headcomponentApi.removePline(param, true);
			returnVal.then(function (response) {
				if(response.data > 0){
					notifySubmit('success', '개인결재선 삭제', '개인결재선 삭제 완료되었습니다.', 'icon-trashcan');
					self.setPLineGridData();
				} else {
					notifySubmit('warning', '개인결재선 삭제', '개인결재선 삭제에 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
				notifySubmit('warning', '개인결재선 삭제', '개인결재선 삭제 실패하였습니다.', 'icon-caution');
				self.setPLineGridData();
			});
    	},
    	plineTitleUpdate: function(){
    		var self = this;
    		var pSeq = self.plinegrid.getRow(self.plineRowKey).APPRO_LINE_SEQ;
    		var pTitle = self.TITLE;
    		var returnVal = headcomponentApi.fixPlineTitle(pSeq, pTitle, self.userId, true);
			returnVal.then(function (response) {
				if(response.data > 0){
					self.setPLineGridData();
					self.fixTitleModalClose();
					notifySubmit('success', '개인결재선 제목변경', '결재선 제목이 변경되었습니다.', 'icon-trashcan');
					self.TITLE = '';
				} else {
					notifySubmit('warning', '개인결재선 제목변경', '결재선 제목 변경에 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
				notifySubmit('warning', '개인결재선 제목변경', '결재선 제목 변경에 실패하였습니다.', 'icon-caution');
				self.setPLineGridData();
			});
    	},
    	inputAprv: function(){
			var self = this;
    		self.frontLineDataset.clear();
    		self.plineusergrid.focus(0);
    		self.frontLineDataset.setData(self.plineusergrid.getData());
    		var setOrdNum = 0;
    		var params = [];
    		for(var i=0; i < self.frontLineDataset.data.length; i++){
    			if(self.frontLineDataset.data[i]['APPRO_GB'] == '01'){
    				self.frontLineDataset.data[i]['APPRO_ORD'] = 0;
    				params.push({
						'PJT_NO': this.pjtno,
						'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
						'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
						'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
						'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
					});
    			}
    			else if(self.frontLineDataset.data[i]['APPRO_GB'] == '02'){
    				setOrdNum += 1;
    				self.frontLineDataset.data[i]['APPRO_ORD'] = setOrdNum;
    				params.push({
						'PJT_NO': this.pjtno,
						'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
						'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
						'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
						'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
					});
    			}
    		}
    		var returnVal = headcomponentApi.inputAprvLine(params, this.pjtno, this.rptgb, true);
			returnVal.then(function (response) {
				if(response.data > 0){
					notifySubmit('success', '결재선 등록', '결재선 등록 성공하였습니다.', 'icon-trashcan');
					self.setGridData();
		    		self.detailgrid.clear();
		    		self.plineusergrid.clear();
		    		self.getAprvLineList();
				} else {
					notifySubmit('warning', '결재선 등록', '결재선 등록에 실패하였습니다.', 'icon-caution');
				}
			}).finally(function () {
				self.approvalLineClose();
			});
		},
    	confirmOpen : function(btnGb){
			var self = this;
			var rowKey = self.plineRowKey;
			var info = {'rowKey' : rowKey};
			if(btnGb == 'plineuser'){
				this.messageModal.openModal('confirm', '개인결재선 불러오기', '설정 된 결재선이 존재합니다.<br />선택하신 개인결재선으로 대체됩니다.', 'small', 'plineuser', info);
			}
			if(btnGb == 'plineUserInsert'){
				if(self.plineusergrid.getRowCount() > 0){
					this.messageModal.openModal('confirm', '개인결재선 신규', '선택하신 결재자 리스트를<br />개인결재선으로 등록합니다.', 'small', 'plineUserInsert', '');
				}
				else{
					return;
				}
			}
			if(btnGb == 'plineUserDelete'){
				this.messageModal.openModal('confirm', '개인결재선 수정', '선택하신 개인결재선을 삭제 하시겠습니까?', 'small', 'plineUserDelete', '');
			}
			if(btnGb == 'plineUserDelete'){
				this.messageModal.openModal('confirm', '개인결재선 수정', '선택하신 개인결재선을 삭제 하시겠습니까?', 'small', 'plineUserDelete', '');
			}
    	},
    	messagepoppupcallback : function(gb, currentId, customData){
    		if(gb == 'confirm' && currentId == 'plineuser'){
    			this.setPLineUserGridData(customData.rowKey);
    		}
    		else if(gb == 'confirm' && currentId == 'plineUserInsert'){
    			this.plineNewRow();
    		}
    		else if(gb == 'confirm' && currentId == 'plineUserDelete'){
    			this.plineDelRow();
    		}
    		else if(currentId == 'modal1'){
	    		var self = this;
	    		self.frontLineDataset.clear();
	    		self.plineusergrid.focus(0);
	    		self.frontLineDataset.setData(self.plineusergrid.getData());
	    		var setOrdNum = 0;
	    		var params = [];
	    		for(var i=0; i < self.frontLineDataset.data.length; i++){
	    			if(self.frontLineDataset.data[i]['APPRO_GB'] == '01'){
    				self.frontLineDataset.data[i]['APPRO_ORD'] = 0;
    				params.push({
						'PJT_NO': this.pjtno,
						'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
						'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
						'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
						'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
					});
    			}
    			else if(self.frontLineDataset.data[i]['APPRO_GB'] == '02'){
    				setOrdNum += 1;
    				self.frontLineDataset.data[i]['APPRO_ORD'] = setOrdNum;
    				params.push({
						'PJT_NO': this.pjtno,
						'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
						'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
						'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
						'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
					});
    			}
	    		}
	    		self.setGridData();
	    		self.detailgrid.clear();
	    		self.plineusergrid.clear();
	    		self.setLine = true;
	    		this.$emit('callback', params);
	    		modal_close('aprvl_modal');
    		}
    		else{
    			return;
    		}
    	},

    	denRptRsnModalOpen : function(){
    		modal_open('den_rpt_rsn_modal');
    	},
    	denRptRsnModalClose : function(){
    		modal_close('den_rpt_rsn_modal');
    	},



    }
});