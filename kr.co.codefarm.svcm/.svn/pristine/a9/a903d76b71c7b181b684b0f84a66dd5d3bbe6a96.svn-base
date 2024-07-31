/********************************************************************************************
 * @Writer

 *  박해수 2019.01.26 
 *  
 * @Description
 * 일정관리(840px)
 * 
 * @Syntax 
 * 	$.get('/widget/template/jobsupport/calendar.js', function(response){
 *		$('head').append(response); 
 *	});
 * 	<component :is="'calendar'" :appid="appid" :widgetseq="widgetseq"></component>
 * 
 * @Parameters
 *  appid : 앱 아이디
 *  widgetseq : 위젯 번호
 ********************************************************************************************/ 
Vue.component('calendar', {
	props: ['appid','widgetseq','thumbnail','parameter'],
	data: function(){
		return {
			appInfo: [],
			widgetInfo: [],
			mainListLoading: {id:'mainListLoading', val:false},
			param: _.cloneDeep(this.parameter),		// 위젯 설정에서 넘겨주는 파라미터 
			today: new Date(),
			yyyy: '',
			mm:'',
			dd:'',
			curDate:'',
			calendarList: new Dataset(),
			iljeongList: new Dataset(),
			categoryList: new Dataset(),
			dayIljeongList: []
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
				}).finally(function(){
					self.mainListLoading.val = false;
				});
			} catch(err){
			};
		},
		widgetRefresh: function(){
			var year = this.today.getFullYear();
			var month = this.today.getMonth()+1;
			var day = this.today.getDate();

			this.dd = day;

			if(month<=9)month = '0' + month;
			if(day<=9)day = '0' + day;

			var curYyyyMm = year + "" + month;

			this.yyyy = year;
			this.mm = month;

			this.curDate = year + "" + month + "" + day;

			this.mainListLoading.val = true;

			try{
				var self = this;
				var returnVal = calendarApiApi.calendar(curYyyyMm, false);

				returnVal.then(function(response) {
					self.calendarList.setData(response.data);
					for(var i = 0; i < response.data.length; i++){
						self.calendarList.setColumn(i, 'SUN_COLOR', '');
						self.calendarList.setColumn(i, 'MON_COLOR', '');
						self.calendarList.setColumn(i, 'TUE_COLOR', '');
						self.calendarList.setColumn(i, 'WED_COLOR', '');
						self.calendarList.setColumn(i, 'THU_COLOR', '');
						self.calendarList.setColumn(i, 'FRI_COLOR', '');
						self.calendarList.setColumn(i, 'SAT_COLOR', '');

						self.calendarList.setColumn(i, 'SUN_CLICK', false);
						self.calendarList.setColumn(i, 'MON_CLICK', false);
						self.calendarList.setColumn(i, 'TUE_CLICK', false);
						self.calendarList.setColumn(i, 'WED_CLICK', false);
						self.calendarList.setColumn(i, 'THU_CLICK', false);
						self.calendarList.setColumn(i, 'FRI_CLICK', false);
						self.calendarList.setColumn(i, 'SAT_CLICK', false);
					}

					self.setIljeongList();
				});
			} catch(err){
			};
		},
		setIljeongList: function(){
			try{
				var self = this;
				var returnVal = calendarApiApi.selectCalendar(false);

				returnVal.then(function(response) {
					self.iljeongList.setData(response.data);

					for(var j = 0; j < self.iljeongList.getRowCount(); j++){
						var date = new Date(parseInt(self.iljeongList.getColumn(j, "FR_DTTM")));

						var year = date.getFullYear();
						var month = date.getMonth()+1;
						var day = date.getDate();

						if(month<=9)month = '0' + month;
						if(day<=9)day = '0' + day;

						var frDttm = year + "" + month + "" + day;

						date = new Date(parseInt(self.iljeongList.getColumn(j, "TO_DTTM")));

						year = date.getFullYear();
						month = date.getMonth()+1;
						day = date.getDate();

						if(month<=9)month = '0' + month;
						if(day<=9)day = '0' + day;

						var toDttm = year + "" + month + "" + day;

						self.iljeongList.setColumn(j, 'FR_DTTM', frDttm);
						self.iljeongList.setColumn(j, 'TO_DTTM', toDttm);
					}

					self.categoryList.setData(_.uniqBy(self.iljeongList.data, 'BONIN_CHK'));

					for(var i = 0; i < self.calendarList.getRowCount(); i++){
						self.setCalendar(i, "SUN", self.calendarList.getColumn(i, "SUN"));
						self.setCalendar(i, "MON", self.calendarList.getColumn(i, "MON"));
						self.setCalendar(i, "TUE", self.calendarList.getColumn(i, "TUE"));
						self.setCalendar(i, "WED", self.calendarList.getColumn(i, "WED"));
						self.setCalendar(i, "THU", self.calendarList.getColumn(i, "THU"));
						self.setCalendar(i, "FRI", self.calendarList.getColumn(i, "FRI"));
						self.setCalendar(i, "SAT", self.calendarList.getColumn(i, "SAT"));
					}

					var year = self.today.getFullYear();
					var month = self.today.getMonth()+1;

					if(month<=9)month = '0' + month;

					var curYyyyMm = year + "" + month;

					var selectYyyymm = self.yyyy + ""+ self.mm;

					//변경한 달이 현재 달이랑 같으면 해당 일을 넘겨 조회함
					if(curYyyyMm == selectYyyymm){
						self.setDayIljeong('','',self.dd);
					}else{
						//달 변경 시 일정을 공백으로 만듬
						self.setDayIljeong('','','');
					}
				}).finally(function(){
					self.mainListLoading.val = false;
				});
			} catch(err){
			};
		},
		getYearMonth: function(PrevNext){
			var sYear = this.yyyy;
			var sMonth = this.mm;

			var iYear = parseInt(sYear);
			var iMonth = parseInt(sMonth);

			if(PrevNext== 'Next') iMonth = iMonth + 1;
			else iMonth = iMonth - 1;

			if(iMonth < 1){
				iYear--;
				iMonth = 12;
			}else if(iMonth > 12){
				iYear ++;
				iMonth = 1;
			}

			sYear = '' + iYear;
			if(iMonth < 10) sMonth = '0' + iMonth;
			else sMonth= '' + iMonth;
			var rtnValue = sYear+sMonth;

			this.yyyy = sYear;
			this.mm = sMonth;

			var pYyyymm = this.yyyy + this.mm;

			var returnVal = calendarApiApi.calendar(pYyyymm, false);

			var self = this;

			returnVal.then(function(response){
				self.calendarList.setData(response.data);

				for(var i = 0; i < response.data.length; i++){
					self.calendarList.setColumn(i, 'SUN_COLOR', '');
					self.calendarList.setColumn(i, 'MON_COLOR', '');
					self.calendarList.setColumn(i, 'TUE_COLOR', '');
					self.calendarList.setColumn(i, 'WED_COLOR', '');
					self.calendarList.setColumn(i, 'THU_COLOR', '');
					self.calendarList.setColumn(i, 'FRI_COLOR', '');
					self.calendarList.setColumn(i, 'SAT_COLOR', '');

					self.calendarList.setColumn(i, 'SUN_CLICK', false);
					self.calendarList.setColumn(i, 'MON_CLICK', false);
					self.calendarList.setColumn(i, 'TUE_CLICK', false);
					self.calendarList.setColumn(i, 'WED_CLICK', false);
					self.calendarList.setColumn(i, 'THU_CLICK', false);
					self.calendarList.setColumn(i, 'FRI_CLICK', false);
					self.calendarList.setColumn(i, 'SAT_CLICK', false);
				}

				self.setIljeongList();
			});
		},
		setToday: function(dd){
			if(dd<=9)dd = '0' + dd;

			var valYyyyMmDd = this.yyyy +''+ this.mm + '' + dd;

			if(valYyyyMmDd == this.curDate){
				return false;
			}else{
				return true;
			}
		},
		setCalendar: function(index, pDate, dd){
			var rtnVal = false;
			var pDd = dd;

			if(pDd<=9)pDd = '0' + pDd;

			var date = this.yyyy + '' + this.mm + '' + pDd;

			if(pDd){
				for(var i = 0 ; i < this.iljeongList.getRowCount(); i++){
					var frDd = this.iljeongList.getColumn(i, "FR_DTTM");
					var toDd = this.iljeongList.getColumn(i, "TO_DTTM");

					if(this.iljeongList.getColumn(i, "FR_DTTM") <= date && date <= this.iljeongList.getColumn(i, "TO_DTTM")){
						if(this.calendarList.getColumn(index, pDate+"_COLOR") == ''){
							this.calendarList.setColumn(index, pDate+"_COLOR", this.iljeongList.getColumn(i, "CAL_COLOR"));
						}
						//일정 겹침
						else{
							this.calendarList.setColumn(index, pDate+"_COLOR", 'transparent');
						}
						rtnVal = true;
					}
				}
			}
			return rtnVal;
		},
		setDayIljeong: function(index, week, dd){
			this.dayIljeongList = [];

			var pDd = dd;

			if(pDd<=9)pDd = '0' + pDd;

			var date = this.yyyy + '' + this.mm + '' + pDd;

			//클릭 이벤트의 경우
			if(!isNull(week)){
				for(var i = 0 ; i < this.calendarList.getRowCount(); i++){
					this.calendarList.setColumn(i, "SUN_CLICK", false);
					this.calendarList.setColumn(i, "MON_CLICK", false);
					this.calendarList.setColumn(i, "TUE_CLICK", false);
					this.calendarList.setColumn(i, "WED_CLICK", false);
					this.calendarList.setColumn(i, "THU_CLICK", false);
					this.calendarList.setColumn(i, "FRI_CLICK", false);
					this.calendarList.setColumn(i, "SAT_CLICK", false);
				}

				this.calendarList.setColumn(index, week+"_CLICK", true);
				this.$forceUpdate();
			}

			for(var i = 0 ; i < this.iljeongList.getRowCount(); i++){
				if(this.iljeongList.getColumn(i, "FR_DTTM") <= date && date <= this.iljeongList.getColumn(i, "TO_DTTM")){
					this.dayIljeongList.push(this.iljeongList.data[i]);
				}
			}
		},
		getDateForm: function(pDate){
			var date = '';
			if(!isNull(pDate)){
				date = pDate.substring(0,4) + '-' + pDate.substring(4,6) + '-' + pDate.substring(6,8);
			}
			return date;
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
                <a href="#!" class="reload icon-refresh2" @click="widgetRefresh"></a>
                <a href="#!" class="more icon-arrow-right" @click="moveApp(param.MENU_GB)"></a>
            </header> 
            <section class="content_box">
                <div class="inner">
                    <div class="widget-type14">
                        <div class="calendar-box">                                                            
					        <span class="year"><span class="month">{{mm}}</span>{{yyyy}}</span>
					        <div class="btn-box">
					            <a href="#!" title="이전달" @click="getYearMonth('Prev')"><span class="icon-chevron-left"></span></a>
					            <a href="#!" title="다음달" @click="getYearMonth('Next')"><span class="icon-chevron-right"></span></a>
					        </div>
					        <table class="counsel-calendar-table">
					            <caption>.</caption>
					            <colgroup>
					                <col width="15%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="15%">
					            </colgroup>
					            <thead>
					                <tr>
					                    <th scope="col">S</th>
					                    <th scope="col">M</th>
					                    <th scope="col">T</th>
					                    <th scope="col">W</th>
					                    <th scope="col">T</th>
					                    <th scope="col">F</th>
					                    <th scope="col">S</th>
					                </tr>
					            </thead>
					            <tbody>
					                 <tr v-for="(item, index) in calendarList.data">
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'SUN', item.SUN)">
					                    		<span :class="item.SUN_CLICK == false ? 'date' : 'date ing'" v-if="item.SUN && item.SUN != ''&&setToday(item.SUN)"><span :class="item.SUN_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.SUN_COLOR+';border-color: '+item.SUN_COLOR+';'"></span>{{item.SUN}}</span>
					                    		<span class="date today" v-if="item.SUN && item.SUN != ''&&!setToday(item.SUN)"><span class="dot" :style="'background-color:' +item.SUN_COLOR+';border-color: '+item.SUN_COLOR+';'"></span>{{item.SUN}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'MON', item.MON)">
					                    		<span :class="item.MON_CLICK == false ? 'date' : 'date ing'" v-if="item.MON && item.MON != ''&&setToday(item.MON)"><span :class="item.MON_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.MON_COLOR+';border-color: '+item.MON_COLOR+';'"></span>{{item.MON}}</span>
					                    		<span class="date today" v-if="item.MON && item.MON != ''&&!setToday(item.MON)"><span class="dot" :style="'background-color:' +item.MON_COLOR+';border-color: '+item.MON_COLOR+';'"></span>{{item.MON}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'TUE', item.TUE)">
					                    		<span :class="item.TUE_CLICK == false ? 'date' : 'date ing'" v-if="item.TUE && item.TUE != ''&&setToday(item.TUE)"><span :class="item.TUE_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.TUE_COLOR+';border-color: '+item.TUE_COLOR+';'"></span>{{item.TUE}}</span>
					                    		<span class="date today" v-if="item.TUE && item.TUE != ''&&!setToday(item.TUE)"><span class="dot" :style="'background-color:' +item.TUE_COLOR+';border-color: '+item.TUE_COLOR+';'"></span>{{item.TUE}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'WED', item.WED)">
					                    		<span :class="item.WED_CLICK == false ? 'date' : 'date ing'" v-if="item.WED && item.WED != ''&&setToday(item.WED)"><span :class="item.WED_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.WED_COLOR+';border-color: '+item.WED_COLOR+';'"></span>{{item.WED}}</span>
					                    		<span class="date today" v-if="item.WED && item.WED != ''&&!setToday(item.WED)"><span class="dot" :style="'background-color:' +item.WED_COLOR+';border-color: '+item.WED_COLOR+';'"></span>{{item.WED}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'THU', item.THU)">
					                    		<span :class="item.THU_CLICK == false ? 'date' : 'date ing'" v-if="item.THU && item.THU != ''&&setToday(item.THU)"><span :class="item.THU_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.THU_COLOR+';border-color: '+item.THU_COLOR+';'"></span>{{item.THU}}</span>
					                    		<span class="date today" v-if="item.THU && item.THU != ''&&!setToday(item.THU)"><span class="dot" :style="'background-color:' +item.THU_COLOR+';border-color: '+item.THU_COLOR+';'"></span>{{item.THU}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'FRI', item.FRI)">
					                    		<span :class="item.FRI_CLICK == false ? 'date' : 'date ing'" v-if="item.FRI && item.FRI != ''&&setToday(item.FRI)"><span :class="item.FRI_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.FRI_COLOR+';border-color: '+item.FRI_COLOR+';'"></span>{{item.FRI}}</span>
					                    		<span class="date today" v-if="item.FRI && item.FRI != ''&&!setToday(item.FRI)"><span class="dot" :style="'background-color:' +item.FRI_COLOR+';border-color: '+item.FRI_COLOR+';'"></span>{{item.FRI}}</span>
					                    	</a>
					                    </td>
					                    <td>
					                    	<a href="#!" @click="setDayIljeong(index, 'SAT', item.SAT)">
						                    	<span :class="item.SAT_CLICK == false ? 'date' : 'date ing'" v-if="item.SAT && item.SAT != ''&&setToday(item.SAT)"><span :class="item.SAT_COLOR == 'transparent' ? 'dot more':'dot'" :style="'background-color:' +item.SAT_COLOR+';border-color: '+item.SAT_COLOR+';'"></span>{{item.SAT}}</span>
						                    	<span class="date today" v-if="item.SAT && item.SAT != ''&&!setToday(item.SAT)">{{item.SAT}}</span>
					                    	</a>
					                    </td>
					                </tr>
					            </tbody>
					        </table>				
					        <div class="account-box">
					        	<ul v-for="(cItem, cIndex) in categoryList.data">
					        		<li ><span class="dot" :style="'background-color:' +cItem.CAL_COLOR+';border-color: '+cItem.CAL_COLOR+';'"></span>{{ cItem.CAL_NM }}</li> <!--{{ cItem.CAL_COLOR }}-->
					        	</ul>
					        </div>
                        </div>
                        <perfect-scrollbar class="schedule-box-wrap">
	                        <div class="schedule-box col01" v-for="(iItem, iIndex) in dayIljeongList" v-if="dayIljeongList.length">
	                            <p class="tit" :title="iItem.ILJEONG_NM"><span class="color-bar" :style="'background-color:' + iItem.CAL_COLOR"></span>{{ iItem.ILJEONG_NM }}</p> <!-- iItem.CAL_COLOR -->
	                            <span class="date icon-calendar"">{{ getDateForm(iItem.FR_DTTM) }} ~ {{ getDateForm(iItem.TO_DTTM) }}</span>
	                        </div>
                        </perfect-scrollbar>
                        <div class="schedule-box" v-if="!dayIljeongList.length">
                            <p class="tit">등록된 일정이 없습니다.</p>
                        </div>
                        <div class="btn-line center">
                            <a href="#!" class="btn type02 size-s" @click="moveApp(param.MENU_GB)">일정관리 보기</a>
                        </div>
                    </div>
                </div>
            </section>
        </article>
        <article class="widget_box" v-else-if="thumbnail == true">
            <header class="title_box">
                <p class="icon icon-xcloud025"></p> 
                <h2 class="widget_tit">일정관리</h2> 
                <p class="widget_info">일정관리<span>Ver. 1. 1. 0</span></p>
                <a href="#!" class="reload icon-refresh2"></a>
                <a href="#!" class="more icon-arrow-right" @click="moveApp(param.MENU_GB)"></a>

            </header> 
            <section class="content_box">
                <div class="inner">
                    <div class="widget-type14">
                        <div class="calendar-box">                                                            
					        <span class="year"><span class="month">3</span>2019</span>
					        <div class="btn-box">
					            <a href="#!" title="이전달"><span class="icon-chevron-left"></span></a>
					            <a href="#!" title="다음달"><span class="icon-chevron-right"></span></a>
					        </div>
					        <table class="counsel-calendar-table">
					            <caption></caption>
					            <colgroup>
					                <col width="15%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="14%">
					                <col width="15%">
					            </colgroup>
					            <thead>
					                <tr>
					                    <th scope="col">S</th>
					                    <th scope="col">M</th>
					                    <th scope="col">T</th>
					                    <th scope="col">W</th>
					                    <th scope="col">T</th>
					                    <th scope="col">F</th>
					                    <th scope="col">S</th>
					                </tr>
					            </thead>
					            <tbody>
					                <tr>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td><span class="date">1</span></td>
					                    <td><span class="date">2</span></td>
					                </tr>
					                <tr>
					                    <td><span class="date">3</span></td>
					                    <td><span class="date">4</span></td>
					                    <td><span class="date">5</span></td>
					                    <td><span class="date">6</span></td>
					                    <td><span class="date">7</span></td>
					                    <td><a href="#!"><span class="date on mine">8</span></a></td>
					                    <td><span class="date">9</span></td>
					                </tr>
					                <tr>
					                    <td><span class="date">10</span></td>
					                    <td><span class="date">11</span></td>
					                    <td><a href="#!"><span class="date on haengsa">12</span></a></td>
					                    <td><span class="date">13</span></td>
					                    <td><span class="date">14</span></td>
					                    <td><span class="date">15</span></td>
					                    <td><span class="date">16</span></td>
					                </tr>
					                <tr>
					                    <td><span class="date">17</span></td>
					                    <td><span class="date">18</span></td>
					                    <td><span class="date">19</span></td>
					                    <td><a href="#!"><span class="date on hwakwa">20</span></a></td>
					                    <td><span class="date today">21</span></td>
					                    <td><a href="#!"><span class="date on haksa ing">22</span></a></td>
					                    <td><span class="date">23</span></td>
					                </tr>
					                <tr>
					                    <td><span class="date">24</span></td>
					                    <td><span class="date">25</span></td>
					                    <td><span class="date">26</span></td>
					                    <td><span class="date">27</span></td>
					                    <td><span class="date">28</span></td>
					                    <td><a href="#!"><span class="date on mine">29</span></a></td>
					                    <td><span class="date">30</span></td>
					                </tr>
					                <tr>
					                    <td><span class="date">31</span></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                    <td></td>
					                </tr>
					            </tbody>
					        </table>				
					        <div class="account-box">
					        	<ul>
					        		<li class="hwakwa"></li>
					        		<li class="haengsa"></li>
					        		<li class="haksa"></li>
					        		<li class="mine"></li>
					        	</ul>
					        </div>
					        										    
                        </div>
                        <div class="schedule-box col01">
                            <p class="tit"></p>
                            <span class="date icon-calendar">2019. 01. 15 ~ 2019. 03. 15</span>
                        </div>
                        <div class="schedule-box col02">
                            <p class="tit"></p>
                            <span class="date icon-calendar">2019. 01. 15 ~ 2019. 03. 15</span>
                        </div>
                        <div class="schedule-box col03">
                            <p class="tit"></p>
                            <span class="date icon-calendar">2019. 01. 15 ~ 2019. 03. 15</span>
                        </div>
                        <div class="btn-line center">
                            <a href="#!" class="btn type02 size-s">일정관리 보기</a>
                        </div>
                    </div>
                </div>
            </section>
        </article>
	`
});
