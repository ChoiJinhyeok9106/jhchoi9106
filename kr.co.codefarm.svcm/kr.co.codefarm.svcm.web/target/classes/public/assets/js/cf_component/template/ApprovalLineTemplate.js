/********************************************************************************************
 * @Writer
 *  조혜린 2023.10.17
 *
 * @Description
 *  결재 시스템 템플릿
 *
 * @Syntax
 *  <approvalline :rptgb="rptgb" v-on:callback="approvalCallback" ref="aprvlLine"></approval>
 *
 *  v-on:callback="approvalCallback" : 결재 목록 선택 후 호출 되는 부모창 콜백 함수
 *  ref="aprvlLine" : 부모창에서 결재라인 선택 팝업 호출 함수
 *
 * @Parameters
 * rptgb : 결재구분(01: 작업일보, 02: 주간작업보고, 03: 월간작업보고, 04: 감리일지, 05: 감리주간보고, 06: 감리월간보고, 07: 계약, 08: 예정공정표, 09: 대금청구)
 * chicergstyn : Y - 결재 목록 선택 시 바로 승인 요청, N - 결재 목록만 선택(승인요청 별도 처리)
 *
 *
 ********************************************************************************************/
Vue.component('approvalline', {
	props: ['rptgb', 'chicergstyn'],
	data: function () {
		return {
			pjtNo: '',
			userId: '',
			mountApproStGb: '01',
			detailSelectedRowkey: '',
			TITLE: '',
			treeData: '',
			chkUserNm:'',
			plineRowKey : '',
			grid: new Dataset(),
			mainList: new Dataset(),
			detailList: new Dataset(),
			frontLineDataset: new Dataset(),
			masterGrid: new Dataset(),
			messageModal: new ModalSet('approvalline-modal'),
		}
	},
	template:`
		<div class="nonsubject-info">
			<link rel="stylesheet" type="text/css" href="/service/design/assets/css/ability.css" />
			<link rel="stylesheet" type="text/css" href="/service/gaia/assets/css/workreprt.css" />

			<div class="modal-layer-wrap">
				<div id="approvalline-modal" class="modal-layer modal-large">
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
								<div class="col s12 m6 l3"></div>
								<div class="col s12 m6 l3"></div>
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
								<div class="col s12 m6 l1" style="width:50px; margin-left:30px; margin-right:20px; padding-top: 80px;">
									<input type="button" value="▶" class="btn type01 size-s" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="selectAppro()">
									<input type="button" value="◀" class="btn type01 size-s" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="rmvPlineUser()" style="margin-top: 5px !important;">
								</div>
								<div class="col s12 m6 l3">
									<div id="plineuser-grid" style="width:300px;"></div>
								</div>
							</div>
						</div>
						<div class="modal-footer center">
							<a href="#!" class="btn type01 size-m" v-if="mountApproStGb == '01' || mountApproStGb == '04'" @click="messagepoppupcallback('', 'selPline')">선택</a>
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
			<modal-message :modalset="messageModal" v-on:messagepoppupcallback="messagepoppupcallback"></modal-message>
		</div>
	`,
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
		}
	},
	mounted: function() {
		this.userId = this.session.INTERNAL_ID;

		// 결재선 조직 그리드 생성 및 조회
		this.searchApi();

		// 조직에 따른 유저 그리드 생성
		this.drawDetailGrid();

		// 개인 결재선 그리드 생성
		this.drawPLineGrid();

		// 선택결재선 그리드 생성
		this.drawPLineUserGrid();
	},
	methods: {
		// 결재선 팝업 오픈
		approvalLineOpen: function(pjtNo) {
			var self = this;

			self.pjtNo = pjtNo;
			self.setPLineGridData();

			if(self.frontLineDataset.data.length > 0) {
				self.plineusergrid.resetData(self.frontLineDataset.data);
			}

			modal_open('approvalline-modal');
		},
		approvalLineClose: function() {
			modal_close('approvalline-modal');
		},
		// 결재선 명 수정 모달
		fixTitleModalOpen: function() {
			this.plinegrid.focus(0);

			modal_open('fix_title_modal');
		},
		fixTitleModalClose: function() {
			modal_close('fix_title_modal');
		},
		setPLineGridData: function() {
			var self = this;
			var param = {
				pUserId: self.userId,
				pPjtNo: self.pjtNo
			};

			var returnVal = headcomponentApi.pline(param, false);
			returnVal.then(function(response) {
				self.plinegrid.resetData(response.data);
			});
		},
		searchApi: function() {
			var self = this;
			var returnVal = headcomponentApi.deptList('', true);
			returnVal.then(function(response) {
				self.mainList.setData(response.data);
				self.tree(response.data);
			});
		},
		tree: function(pData) {
			var self = this;
			var lvObj = {};

			lvObj[1] = pData;
			for(var j=0; j < lvObj[1].length; j++) {
				var obj = lvObj[1][j];
				var findIndex = _.findIndex(lvObj[0], {'CD': obj.CD});
				if(findIndex > -1) {
					if(!lvObj[0][findIndex]['_children']) {
						lvObj[0][findIndex]['_children'] = [];
					}

					lvObj[0][findIndex]['_children'].push(obj);
				}
			}

			self.treeData = lvObj[1];
			self.drawGrid();
		},
		drawGrid: function(){
			var self = this;
			var list = [];

			list = [
				{
					header: '조직',
					name: 'CD_NM',
					sortingType: 'asc',
					width: 280,
					whiteSpace: 'normal'
				}
			];

			this.grid = new tui.Grid({
				el: document.getElementById('master-grid'),
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

			self.$nextTick(function() {
				self.setGridData();
			});
		},
		setGridData: function() {
			var self = this;
			self.grid.resetData(self.treeData);
			self.masterGrid = self.grid;
			self.$nextTick(function() {
				self.gridClick();
			});

			self.$forceUpdate();
		},
		gridClick: function() {
			var self = this;
			self.grid.on('click', function(event) {
				var row = event.rowKey;
				if(event.targetType == 'cell') {
					self.gridCellClick(row,'');
				}
			});
		},
		gridCellClick: function(rowKey, columnInfo) {
			var self = this;
			var param = {
				pCD: self.grid.getRow(rowKey).CD,
				pPjtNo: self.pjtNo
			};

			var returnVal = headcomponentApi.userList(param, true);
			returnVal.then(function(response) {
				self.detailList.setData(response.data);
			}).finally(function () {
				self.setDetailGridData();
			});
		},
		setDetailGridData: function() {
			var self = this;
			self.detailgrid.resetData(self.detailList.data);
			self.$forceUpdate();
		},
		drawDetailGrid: function() {
			var self = this;
			this.detailgrid = new tui.Grid({
				el: document.getElementById('detail-grid'),
				rowHeaders: ['checkbox'],
				bodyHeight: 200,
				columns: [
					{
						header: '구분',
						name: 'CD_NM',
						width: 120,
					},
					{
						header: '이름',
						name: 'USER_NM',
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
		detailgridClick: function() {
			var self = this;
			self.detailgrid.on('click', function(event) {
				var row = event.rowKey;
				var columnName = event.columnName;

				if(event.targetType == 'cell') {
						self.detailgridCellClick(row, columnName);
				}
			});
		},
		detailgridCellClick: function(rowKey, columnInfo) {
			this.detailSelectedRowkey = rowKey;
		},
		drawPLineGrid: function() {
			var self = this;
			this.plinegrid = new tui.Grid({
				el: document.getElementById('pline-grid'),
				scrollX: true,
				bodyHeight: 200,
				columns: [
					{
						header: '개인결재선',
						name: 'APPRO_LINE_TITLE',
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
		setPLineGridEvent: function() {
			var self = this;
			self.plinegrid.on('click', function(event) {
				var row = event.rowKey;

				self.TITLE = self.plinegrid.store.data.rawData[0].APPRO_LINE_TITLE;

				if(event.targetType == 'cell') {
					self.setPlineRowKey(row);
					self.plinegrid.startEditing(row, 'APPRO_LINE_TITLE');
				}
			});
		},
		setPlineRowKey: function(rowKey) {
			this.plineRowKey = rowKey;
		},
		setPLineGridData: function() {
			var self = this;
			var param = {
				pUserId: self.userId,
				pPjtNo: self.pjtNo
			};

			var returnVal = headcomponentApi.pline(param, false);
			returnVal.then(function(response) {
				self.plinegrid.resetData(response.data);
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
					align: 'center',
					width: 70,
					whiteSpace: 'normal'
				},
				{
					header: '이름',
					name: 'USER_NM',
					align: 'center',
					width: 80,
					whiteSpace: 'normal'
				},
				{
					header: '결재구분 ',
					name: 'APPRO_GB',
					align: 'center',
					width: 92,
					formatter: 'listItemText',
					editor: {
						type: 'select',
						options: {
							listItems: [{text:'참조', value:'01'},{text:'결재', value:'02'}],
							autofocus: true,
							autoclose: true,
							showIcon: true,
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
		plineusergridEventhandler: function() {
			var self = this;

			self.plineusergrid.on('click', function(event) {
				var row = event.rowKey;
				var columnName = event.columnName;

				if(event.targetType == 'cell') {
					self.plineusergridCellClick(row, columnName);
				}
			});
		},
		plineusergridCellClick: function(rowKey, columnInfo) {
			this.plineusergrid.startEditing(rowKey, 'APPRO_GB');
		},
		setPLineUserGridData: function(rowKey) {
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
		async selectAppro() {
			var self = this;
			var resultSync = await self.addPlineUser();

			if(resultSync) {
				self.plineusergridCellClick(self.plineusergrid.findRows({USER_NM: self.chkUserNm})[0].rowKey);
			}
		},
		rmvPlineUser: function() {
			var self = this;
			self.plineusergrid.removeCheckedRows();
			self.plineusergrid.focus(0);
		},
		addPlineUser : function(){
			var self = this;
			var chkRow = 0;
			var rowKeyNum = 0;

			self.chkUserNm = '';

			if(self.plineusergrid.getRowCount() > 0) rowKeyNum = self.plineusergrid.getRowAt(self.plineusergrid.getRowCount() - 1).rowKey + 1;

			chkRow = self.detailgrid.getCheckedRows();
			if(chkRow.length > 0) {
				for(var i = 0; i < chkRow.length; i++) {
					if(self.plineusergrid.findRows({USER_NM:chkRow[i].USER_NM}) == '') {
						// rowkey 세팅
						chkRow[i]['rowKey'] = rowKeyNum + i;

						// 데이터 세팅
						self.plineusergrid.appendRow(chkRow[i]);

						if(self.chkUserNm == '') self.chkUserNm = chkRow[i].USER_NM;
					}
				}

				return true;
			} else {
				return false;
			}
		},
		plineNewRow: function(){
			var self = this;
			var setOrdNum = 0;
			var params = [];

			self.plineusergrid.focus(0);

			for(var i=0; i < self.plineusergrid.getRowCount(); i++) {
				if(self.plineusergrid.getRow(i).APPRO_GB == '01') {
					params.push({
						'USER_ID': self.userId,
						'APPRO_ID': self.plineusergrid.getRow(i).INTERNAL_ID,
						'APPRO_ORD': 0,
						'APPRO_GB': self.plineusergrid.getRow(i).APPRO_GB,
						'PJT_NO': self.pjtNo,
						'APPRO_AUTH': self.plineusergrid.getRow(i).APPRO_AUTH,
					});
				}
				else if(self.plineusergrid.getRow(i).APPRO_GB == '02') {
					setOrdNum += 1;
					params.push({
						'USER_ID': self.userId,
						'APPRO_ID': self.plineusergrid.getRow(i).INTERNAL_ID,
						'APPRO_ORD': setOrdNum,
						'APPRO_GB': self.plineusergrid.getRow(i).APPRO_GB,
						'PJT_NO': self.pjtNo,
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
		plineDelRow: function() {
			var self = this;
			var param = {
				pSeq: self.plinegrid.getRow(self.plineRowKey).APPRO_LINE_SEQ,
				pUserId: self.userId
			};

			var returnVal = headcomponentApi.removePline(param, true);
			returnVal.then(function (response) {
				if(response.data > 0) {
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
		plineTitleUpdate: function() {
			var self = this;
			var pSeq = self.plinegrid.getRow(self.plineRowKey).APPRO_LINE_SEQ;
			var pTitle = self.TITLE;
			var returnVal = headcomponentApi.fixPlineTitle(pSeq, pTitle, self.userId, true);
			returnVal.then(function (response) {
				if(response.data > 0) {
					self.setPLineGridData();
					self.fixTitleModalClose();
					self.TITLE = '';

					notifySubmit('success', '개인결재선 제목변경', '결재선 제목이 변경되었습니다.', 'icon-trashcan');
				} else {
					notifySubmit('warning', '개인결재선 제목변경', '결재선 제목 변경에 실패하였습니다.', 'icon-caution');
				}
			}).catch(function (error) {
					notifySubmit('warning', '개인결재선 제목변경', '결재선 제목 변경에 실패하였습니다.', 'icon-caution');
					self.setPLineGridData();
			});
		},
		messagepoppupcallback: function(gb, currentId, customData) {
			var self = this;

			if(gb == 'confirm' && currentId == 'plineuser') {								// 개인결재선 결재 목록에 추가
				self.setPLineUserGridData(customData.rowKey);
			} else if(gb == 'confirm' && currentId == 'plineUserInsert') {	// 개인결재선 추가
				self.plineNewRow();
			} else if(gb == 'confirm' && currentId == 'plineUserDelete') {	// 개인결재선 삭제
				self.plineDelRow();
			} else if(currentId == 'selPline') {														// 선택한 결재 목록 전달
					self.frontLineDataset.clear();
					self.plineusergrid.focus(0);
					self.frontLineDataset.setData(self.plineusergrid.getData());

					var setOrdNum = 0;
					var params = [];
					for(var i=0; i < self.frontLineDataset.data.length; i++) {
						if(self.frontLineDataset.data[i]['APPRO_GB'] == '01') {					// 참조
							self.frontLineDataset.data[i]['APPRO_ORD'] = 0;

							params.push({
								'PJT_NO': self.pjtNo,
								'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
								'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
								'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
								'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
							});
						} else if(self.frontLineDataset.data[i]['APPRO_GB'] == '02') {	// 결재
							setOrdNum += 1;
							self.frontLineDataset.data[i]['APPRO_ORD'] = setOrdNum;

							params.push({
								'PJT_NO': self.pjtNo,
								'APPRO_ID': self.frontLineDataset.data[i].INTERNAL_ID,
								'APPRO_ORD': self.frontLineDataset.data[i].APPRO_ORD,
								'APPRO_AUTH': self.frontLineDataset.data[i].APPRO_AUTH,
								'APPRO_GB': self.frontLineDataset.data[i].APPRO_GB,
							});
						}
					}

					if(self.chicergstyn == 'Y') {
						self.inputAprv(params);
					} else {
						self.setGridData();
						self.detailgrid.clear();
						self.plineusergrid.clear();

						self.$emit('callback', params);
					}

					modal_close('approvalline-modal');
			} else {
				return;
			}
		},
		inputAprv: function(pData) {
			var self = this;

			if(!isNull(pData) && pData.length > 0) {
				var returnVal = headcomponentApi.inputAprvLine(pData, self.pjtNo, self.rptgb, true);
				returnVal.then(function (response) {
					if(!isNull(response.data) && !isNull(response.data.rtnData)) {
						if(self.chicergstyn != 'Y') {
							notifySubmit('success', '결재선 등록', '결재선 등록 성공하였습니다.', 'icon-trashcan');
						}

						self.setGridData();
						self.detailgrid.clear();
						self.plineusergrid.clear();

						self.$emit('callback', response.data.rtnData);
					} else {
						notifySubmit('warning', '결재선 등록', '결재선 등록에 실패하였습니다.', 'icon-caution');
					}
				}).finally(function () {
					self.approvalLineClose();
				});
			}
		},
		confirmOpen: function(btnGb) {
			var self = this;
			var rowKey = self.plineRowKey;
			var info = {'rowKey': rowKey};

			if(btnGb == 'plineuser') {
				self.messageModal.openModal('confirm', '개인결재선 불러오기', '설정 된 결재선이 존재합니다.<br />선택하신 개인결재선으로 대체됩니다.', 'small', 'plineuser', info);
			}

			if(btnGb == 'plineUserInsert') {
				if(self.plineusergrid.getRowCount() > 0) {
					self.messageModal.openModal('confirm', '개인결재선 신규', '선택하신 결재자 리스트를<br />개인결재선으로 등록합니다.', 'small', 'plineUserInsert', '');
				} else {
					return;
				}
			}

			if(btnGb == 'plineUserDelete') {
				self.messageModal.openModal('confirm', '개인결재선 수정', '선택하신 개인결재선을 삭제 하시겠습니까?', 'small', 'plineUserDelete', '');
			}
		}
	}
});