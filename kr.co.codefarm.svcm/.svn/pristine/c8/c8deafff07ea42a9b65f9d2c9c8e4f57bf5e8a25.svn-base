/********************************************************************************************
 * @Writer
 *  박해수 2019.01.26 
 *  
 * @Description
 * 일반 탭형태 게시판 리스트 위젯 (720px)
 * 
 * @Syntax 
 * 	$.get('/widget/template/community/normal-tap-list.js', function(response){
 *		$('head').append(response); 
 *	});
 * 	<component :is="'normal-tap-list'" :appid="appid" :widgetseq="widgetseq"></component>
 * 
 * @Parameters
 *  appid : 앱 아이디
 *  widgetseq : 위젯 번호
 ********************************************************************************************/ 
Vue.component('normal-tap-list', {
	props: ['appid','widgetseq','thumbnail','parameter'],
	data: function(){
		return {
			appInfo: [],
			widgetInfo: [],
			param: _.cloneDeep(this.parameter),		// 위젯 설정에서 넘겨주는 파라미터 
			mainListLoading: {id:'mainListLoading', val:false},
			widgetData: new Dataset(),
			seq: null,									// 게시판 순번
			menuCd: null,								// 게시판 구분 -> 메뉴코드
			tabGb: null									// 탭 구분
				
		}
	},
    //데이터 감시
    watch: {
    	'mainListLoading.val': {
	    	handler: function (val, oldVal) {
				this.LoadingOverlay($(this.$refs[this.mainListLoading.id]), val);
			}
	    },
    },
	filters: {
		  capitalize: function (value) {
		    if (!value) return ''
		    value = value.toString()
		    return value.substr(0,4)+'. '+value.substr(4,2)+'. '+value.substr(6,2)
		  }
	},
	mounted: function(){
		//앱에서 사용할 전역 변수에 넘겨받은 정보 입력
		if(!isNull(this.param)){
			this.param = typeof this.param == 'string' ? Function("return "+this.param)() : this.param;
		}else{
			this.param = {};
		}

		if(this.thumbnail != true){
			this.setAppInfo();
			this.setWidgetInfo();
			this.widgetRefresh('/');
		}
    },
	methods: {
		setAppInfo: function(){
			try{
				var self = this;
				var returnVal = appApi.appId(self.appid, false);
				
				returnVal.then(function(response) {
					self.appInfo = response.data;
				});
			} catch(err){
			};
		},
		setWidgetInfo: function(){
			try{
				var self = this;
				var returnVal = widgetApi.widgetId(self.appid, self.widgetseq, false);
				
				returnVal.then(function(response) {
					self.widgetInfo = response.data;
				}).finally(function(){
					self.mainListLoading.val = false;
				});
			} catch(err){
			};
		},
		widgetRefresh: function(pBoardSeq){
			try{
				this.mainListLoading.val = true;
				var boardSeq = pBoardSeq.split('/')[0];	// 게시판 순번
				var menuCd = pBoardSeq.split('/')[1];	// 게시판 구분
				this.tabGb = pBoardSeq.split('/')[2];	// 탭 구분
				
				if(isNull(boardSeq)) boardSeq = isNull(this.param.BOARD_SEQ1) ? (isNull(this.param.BOARD_SEQ2) ? this.param.BOARD_SEQ3 : this.param.BOARD_SEQ2) : this.param.BOARD_SEQ1;
				if(isNull(menuCd)) menuCd = isNull(this.param.MENU_CD1) ? (isNull(this.param.MENU_CD2) ? this.param.MENU_CD3 : this.param.MENU_CD2) : this.param.MENU_CD1;
				
				if(!isNull(boardSeq)){
					var self = this;
					var returnVal = boardApiApi.getWidgetList(boardSeq, 3, false);
					
					returnVal.then(function(response) {
						self.widgetData.setData(response.data);
						self.seq = boardSeq;
						self.menuCd = menuCd;
					}).finally(function(){
						self.tapControllScript();
						self.mainListLoading.val = false;
					});
				}else{
					this.mainListLoading.val = false;
				}
			} catch(err){
			};
		},
		tapControllScript: function(){
			this.$nextTick(function() {
				if(isNull(this.tabGb)){
					$(".tab-content").hide();								// 탭 내용 있을때의 div 숨기기 
	                $(".widget-type-none").hide();							// 탭 내용 없을때의 div 숨기기
					$(".tab-content:first").show();							// 탭 내용 있을때의 첫번째 div 나타내기
					$(".widget-type-none:first").show();					// 탭 내용 없을때의 첫번째 div 나타내기
					$(".tab-type01>div>div>a:first").addClass("active");
				}
	            $(".tab-type01 .tab-inner div a").click(function() {
	                $(".tab-type01 .tab-inner div a").removeClass("active");
	                $(this).addClass("active");
	                $(".tab-content").hide();
	                $(".widget-type-none").hide();
	                var activeTab = $(this).parent().attr("rel");
	                $("#"+activeTab).fadeIn();
	                $("#no-cont-"+activeTab).fadeIn();
	            });
			});
		},
		appOpen: function(pPostNo, pBoardSeq, pBoardGb){
			var param = {
				'POST_NO' 	: pPostNo,
				'BOARD_SEQ' : pBoardSeq,
				'BOARD_GB' 	: pBoardGb
			};
			parent.appOpen(this.appInfo.APP_ID, this.menuCd, param);
		},
		moveApp: function(menuCd){
			appOpen(this.appInfo.APP_ID, menuCd);
		},
	},
	template:`
		<article class="widget_box" ref="mainListLoading" v-if="thumbnail != true">
            <header class="title_box">
                <p :class="['icon',appInfo.ICON]"></p> 
                <h2 class="widget_tit">{{widgetInfo.WIDGET_NM}}</h2> 
                <p class="widget_info">{{appInfo.APP_NM}}<span>Ver. {{appInfo.APP_VER}}</span></p>
                <a href="#!" class="reload icon-refresh2" @click="widgetRefresh(seq+'/'+menuCd+'/'+tabGb)"></a>
            </header> 
            <section class="content_box">
                <div class="inner">
                    <div class="widget-type02" v-if="param.BOARD_SEQ1 || param.BOARD_SEQ2 || param.BOARD_SEQ3">
                        <div class="tab-outter">
                            <div class="tab-type01">
                                <div class="tab-inner">
                                    <div rel="tab1"><a href="#!" v-if="param.BOARD_SEQ1" @click="widgetRefresh(param.BOARD_SEQ1+'/'+param.MENU_CD1+'/tab1')">{{this.param.BOARD_NM1}}</a></div>
                                    <div rel="tab2"><a href="#!" v-if="param.BOARD_SEQ2" @click="widgetRefresh(param.BOARD_SEQ2+'/'+param.MENU_CD2+'/tab2')">{{this.param.BOARD_NM2}}</a></div>
                                    <div rel="tab3"><a href="#!" v-if="param.BOARD_SEQ3" @click="widgetRefresh(param.BOARD_SEQ3+'/'+param.MENU_CD3+'/tab3')">{{this.param.BOARD_NM3}}</a></div>                                                    
                                </div>
                            </div>
                        </div>
                        <!-- 모바일일때 셀렉트박스 -->
                        <select id="exampleFormControlInput2" class="sel-mobile size-m" @change="widgetRefresh($event.target.value, $event)">
                            <option v-if="param.BOARD_SEQ1" :value="param.BOARD_SEQ1+'/'+param.MENU_CD1+'/tab1'">{{this.param.BOARD_NM1}}</option>
                            <option v-if="param.BOARD_SEQ2" :value="param.BOARD_SEQ2+'/'+param.MENU_CD2+'/tab2'">{{this.param.BOARD_NM2}}</option>
                            <option v-if="param.BOARD_SEQ3" :value="param.BOARD_SEQ3+'/'+param.MENU_CD3+'/tab3'">{{this.param.BOARD_NM3}}</option>
                        </select>
                        <div class="tab-container">
                            <div id="tab1" class="tab-content" v-if="widgetData.getRowCount() > 0">
                                <ul>
		                            <li v-for="(item, index) in widgetData.data">
		                            	<a href="#!" @click="appOpen(item.POST_NO, item.BOARD_SEQ, item.BOARD_GB)">
			                                <span class="date-box"><span class="big">{{item.INSERT_DTTM.substring(6,8)}}</span>{{item.INSERT_DTTM.substring(0,4)}}. {{item.INSERT_DTTM.substring(4, 6)}}</span>
			                                <div class="cont-box">
			                                    <p class="tit">{{item.TITLE}}</p>
			                                    <p class="txt">{{item.CONTENTS}}</p>
			                                    <span class="etc icon-eye">{{item.SEARCH_CNT}}</span><span class="etc icon-realtime">{{item.INSERT_DTTM | capitalize}}</span><span class="writer">{{item.JAKSEONGJA_NM}}</span>
			                                </div>
		                                </a>
		                            </li>
		                        </ul>
		                        <div class="btn-line center">
                            		<a href="#!" class="btn type02 size-s" @click="moveApp(menuCd)">커뮤니티 보기</a>
		                        </div>
                            </div>
                            <div id="no-cont-tab1" class="widget-type-none" v-else>
                                <div class="no-cont">현재 등록된 게시물이 없습니다.</div>                                                     
                            </div>
                        </div>
                        <div class="tab-container">
                            <div id="tab2" class="tab-content" v-if="widgetData.getRowCount() > 0">
                                <ul>
		                            <li v-for="(item, index) in widgetData.data">
		                            	<a href="#!" @click="appOpen(item.POST_NO, item.BOARD_SEQ, item.BOARD_GB)">
			                                <span class="date-box"><span class="big">{{item.INSERT_DTTM.substring(6,8)}}</span>{{item.INSERT_DTTM.substring(0,4)}}. {{item.INSERT_DTTM.substring(4, 6)}}</span>
			                                <div class="cont-box">
			                                    <p class="tit">{{item.TITLE}}</p>
			                                    <p class="txt">{{item.CONTENTS}}</p>
			                                    <span class="etc icon-eye">{{item.SEARCH_CNT}}</span><span class="etc icon-realtime">{{item.INSERT_DTTM | capitalize}}</span><span class="writer">{{item.JAKSEONGJA_NM}}</span>
			                                </div>
		                                </a>
		                            </li>
		                        </ul>
		                        <div class="btn-line center">
                            		<a href="#!" class="btn type02 size-s" @click="moveApp(menuCd)">커뮤니티 보기</a>
		                        </div>
                            </div>
                            <div id="no-cont-tab2" class="widget-type-none" v-else>
                                <div class="no-cont">현재 등록된 게시물이 없습니다.</div>                                                     
                            </div>
                        </div>
                        <div class="tab-container">
                            <div id="tab3" class="tab-content" v-if="widgetData.getRowCount() > 0">
                                <ul>
		                            <li v-for="(item, index) in widgetData.data">
		                            	<a href="#!" @click="appOpen(item.POST_NO, item.BOARD_SEQ, item.BOARD_GB)">
			                                <span class="date-box"><span class="big">{{item.INSERT_DTTM.substring(6,8)}}</span>{{item.INSERT_DTTM.substring(0,4)}}. {{item.INSERT_DTTM.substring(4, 6)}}</span>
			                                <div class="cont-box">
			                                    <p class="tit">{{item.TITLE}}</p>
			                                    <p class="txt">{{item.CONTENTS}}</p>
			                                    <span class="etc icon-eye">{{item.SEARCH_CNT}}</span><span class="etc icon-realtime">{{item.INSERT_DTTM | capitalize}}</span><span class="writer">{{item.JAKSEONGJA_NM}}</span>
			                                </div>
		                                </a>
		                            </li>
		                        </ul>
		                        <div class="btn-line center">
                            		<a href="#!" class="btn type02 size-s" @click="moveApp(menuCd)">커뮤니티 보기</a>
		                        </div>
                            </div>
                            <div id="no-cont-tab3" class="widget-type-none" v-else>
                                <div class="no-cont">현재 등록된 게시물이 없습니다.</div>                                                     
                            </div>
                        </div>
                    </div>
                    <div class="widget-type21" v-else>
						<div class="no-cont">현재 등록된 게시물이 없습니다.</div>  
                    </div>
                </div>
            </section>
        </article>
        <article class="widget_box" v-else-if="thumbnail == true">
            <header class="title_box">
                <p class="icon icon-xcloud023"></p> 
                <h2 class="widget_tit">커뮤니티</h2> 
                <p class="widget_info">커뮤니티<span>Ver. 1. 1. 0</span></p>
                <a href="#!" class="reload icon-refresh2"></a>
            </header> 
            <section class="content_box">
                <div class="inner">
                    <div class="widget-type02">
                        <div class="tab-outter">
                            <div class="tab-type01">
                                <div class="tab-inner">
                                    <div rel="tab1"><a href="#!" class="active">첫번째 탭</a></div>
                                    <div rel="tab2"><a href="#!">두번째 탭</a></div>
                                    <div rel="tab3"><a href="#!">세번째 탭</a></div>                                                    
                                </div>
                            </div>
                        </div>
                        <!-- 모바일일때 셀렉트박스 -->
                        <select id="exampleFormControlInput2" class="sel-mobile size-m">
                            <option value="">첫번째 탭</option>
                            <option value="">두번째 탭</option>
                            <option value="">세번째 탭</option>
                        </select>
                        <!-- 모바일일때 셀렉트박스 -->
                        <div class="tab-container">
                            <div id="tab1" class="tab-content">
                                <ul>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>                                                            
                                </ul>
                                <div class="btn-line center">
                                    <a href="#!" class="btn type02 size-s">커뮤니티 보기</a>
                                </div>
                            </div>
                        </div>
                        <div class="tab-container">
                            <div id="tab2" class="tab-content">
                                <ul>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit">2</p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>                                                            
                                </ul>
                                <div class="btn-line center">
                                    <a href="#!" class="btn type02 size-s">커뮤니티 보기</a>
                                </div>
                            </div>
                        </div>
                        <div class="tab-container">
                            <div id="tab3" class="tab-content">
                                <ul>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit">3</p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>
                                    <li><a href="#!">
                                        <span class="date-box"><span class="big">30</span>2019. 12</span>
                                        <div class="cont-box">
                                            <p class="tit"></p>
                                            <p class="txt"></p>
                                            <span class="etc icon-eye">687</span><span class="etc icon-realtime">2019. 01. 02</span><span class="writer"></span>
                                        </div></a>
                                    </li>                                                            
                                </ul>
                                <div class="btn-line center">
                                    <a href="#!" class="btn type02 size-s">커뮤니티 보기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </article>
	`
});
