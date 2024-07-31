/********************************************************************************************
 * @Writer
 *  박해수 2019.01.26 
 *  
 * @Description
 * 일반 게시판 리스트 위젯 (720px)
 * 
 * @Syntax 
 * 	$.get('/widget/template/community/normal-list.js', function(response){
 *		$('head').append(response); 
 *	});
 * 	<component :is="'normal-list'" :appid="appid" :widgetseq="widgetseq"></component>
 * 
 * @Parameters
 *  appid : 앱 아이디
 *  widgetseq : 위젯 번호
 ********************************************************************************************/ 
Vue.component('normal-list-guide', {
	props: ['appid','widgetseq','thumbnail','parameter'],
	data: function(){
		return {
			appInfo: [],
			widgetInfo: [],
			param: _.cloneDeep(this.parameter),		// 위젯 설정에서 넘겨주는 파라미터 
			mainListLoading: {id:'mainListLoading', val:false},
			widgetData: new Dataset()
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
			this.widgetRefresh();
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
				});
			} catch(err){
			};
		},
		widgetRefresh: function(){
			try{
				this.mainListLoading.val = true;
				if(!isNull(this.param.BOARD_SEQ)){
					var self = this;
					var returnVal = boardApiApi.getWidgetList(this.param.BOARD_SEQ, 4, false);
					
					returnVal.then(function(response) {
						self.widgetData.setData(response.data);
					}).finally(function(){
						self.mainListLoading.val = false;
					});
				}else{
					this.mainListLoading.val = false;
				}
			} catch(err){
			};
		},
		appOpen: function(pPostNo, pBoardSeq, pBoardGb){
			var param = {
				'POST_NO' 	: pPostNo,
				'BOARD_SEQ' : pBoardSeq,
				'BOARD_GB' 	: pBoardGb
			};
			
			if(!isNull(this.param.APP_ID)) {
				parent.appOpen(this.param.APP_ID, this.param.MENU_CD, param);
			}else {
				parent.appOpen(this.appInfo.APP_ID, this.param.MENU_CD, param);
			}
		},
		moveApp: function(menuCd){
			if(!isNull(this.param.APP_ID)) {
				appOpen(this.param.APP_ID, menuCd);
			}else {
				appOpen(this.appInfo.APP_ID, menuCd);
			}
		},
	},
	template:`
		<article class="widget_box" ref="mainListLoading" v-if="thumbnail != true">
            <header class="title_box">
                <p :class="['icon',appInfo.ICON]"></p> 
                <h2 class="widget_tit">{{widgetInfo.WIDGET_NM}}</h2> 
                <p class="widget_info">{{appInfo.APP_NM}}<span>Ver. {{appInfo.APP_VER}}</span></p>
                <a href="#!" class="reload icon-refresh2" @click="widgetRefresh"></a>
            </header> 
            <section class="content_box">
                <div class="inner">
                    <div class="widget-type01" v-if="widgetData.getRowCount() > 0">
                        <ul>
                            <li v-for="(item, index) in widgetData.data">
                            	<a href="#!" @click="appOpen(item.POST_NO, item.BOARD_SEQ, item.BOARD_GB)">
	                                <span class="date-box" v-if="item.INSERT_DTTM"><span class="big">{{item.INSERT_DTTM.substring(6,8)}}</span>{{item.INSERT_DTTM.substring(0,4)}}. {{item.INSERT_DTTM.substring(4, 6)}}</span>
	                                <div class="cont-box">
	                                    <p class="tit">{{item.TITLE}}</p>
	                                    <p class="txt">{{item.CONTENTS}}</p>
	                                    <span class="etc icon-eye">{{item.SEARCH_CNT}}</span><span class="etc icon-realtime">{{item.INSERT_DTTM | capitalize}}</span><span class="writer">{{item.JAKSEONGJA_NM}}</span>
	                                </div>
                                </a>
                            </li>
                        </ul>
                        <div class="btn-line center">
                            <a href="#!" class="btn type02 size-s" @click="moveApp(param.MENU_CD)">{{widgetInfo.WIDGET_NM}} 보기</a>
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
                    <div class="widget-type01">
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
            </section>
        </article>
	`
});
