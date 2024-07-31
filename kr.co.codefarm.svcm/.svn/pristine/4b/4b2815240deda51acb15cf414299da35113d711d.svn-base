
var PykUtilObject = function(){

function getCodeName(codeList, code){
	if(code == null){
		code = '';
	}
	var it = _.find(codeList, {code: code});// pyk code
	return it != null ? it.name : '';// pyk code
}

var dateutil = (function(){

	// 년월 배열 만들기
	var makeYmList = function(stYmd, edYmd){
		var stYm = stYmd.substr(0, 6);
		var edYm = edYmd.substr(0, 6);
		var ymList = [];
		for(var date = fromYmd(stYm+'01'), ym = getYm(date); ym <= edYm; addMonth(date, 1), ym = getYm(date)){
			ymList.push(ym);
		}
		return ymList;
	};

	var fromYmd = function(ymd){
		var len1 = ymd.length;
		var yy = Number(ymd.substr(0, 4));
		var MM = len1 >= 6 ? Number(ymd.substr(4, 2))-1 : 0;
		var dd = len1 >= 8 ? Number(ymd.substr(6, 2)) : 1;
		var hh = len1 >= 10 ? Number(ymd.substr(8, 2)) : 0;
		var mm = len1 >= 12 ? Number(ymd.substr(10, 2)) : 0;
		var ss = len1 >= 14 ? Number(ymd.substr(12, 2)) : 0;
		var SSS = len1 >= 17 ? Number(ymd.substr(14, 3)) : 0;
		var date1 = new Date(yy, MM, dd, hh, mm, ss, SSS);
		return date1;
	};

	var getYm = function(mydate){
		return String(mydate.getFullYear())+_.padStart(mydate.getMonth()+1, 2, '0');
	};

	// 말일
	var getLastDateOfMonth = function(mydate, gbn){
		var date = new Date(mydate);
		date.setDate(1);
		date.setMonth(date.getMonth()+1);
		date.setDate(date.getDate()-1);
		return gbn == 'date'?date:date.getDate();
	};

	var addMonth = function(mydate, n){
		var tmpDate = mydate.getDate();
		mydate.setMonth(mydate.getMonth()+n);
		if(mydate.getDate() != tmpDate){
			mydate.setMonth(mydate.getMonth() - 1);
			mydate.setDate(getLastDateOfMonth(mydate));
		}
	};

	return {
		makeYmList
	};
})();//dateutil

var arrayutil = (function(){
	var sumItemFn = function(r, it, props){
		_.each(props, function(prop){
			r[prop] = +Big(r[prop]||0).plus(it[prop]||0);
		});
	};
	var makeCondSumItem = function(list1, condFn, sumPropList){
		return _.reduce(list1, function(r, it){
			if(condFn == null || condFn(it)){
				sumItemFn(r, it, sumPropList);
			}
			return r;
		}, {});
	};

	return {
		makeCondSumItem
	};
})();//arrayutil

var TreeUtil = function(ss){
	var ss = _.extend({
		propLevel: 'level',
		propId: 'id',
		propPid: 'pid',
		propChildren: 'children',
		limitLevel: 5,
		rootCondFunc: function(it){
			return ! it.pid;
		},
		isSetHelperProp: false
	}, ss)
	var {propLevel, propId, propPid, propChildren, limitLevel, rootCondFunc, isSetHelperProp} = ss;

	// var setArrayPidByLevel = function(levelArray){
	// 	var pids = [];
	// 	_.each(levelArray, function(it, idx){
	// 		it[propId] = idx;
	// 		var level = it[propLevel];
	// 		if(pids[level - 1] != null)it[propPid] = pids[level - 1];
	// 		pids[level] = it[propId];
	// 		it[propLevel] = level;
	// 	});
	// };

	// id pid 로 레벨 찾기
	var findLevel = function(idArray, it){
		var checkIt = it;
		var level = 1;
		var limitLevel = limitLevel || 5;
		var rootCondFunc = rootCondFunc || function(it){
			return ! it[propPid];
		};
		for(;true;){
			if(rootCondFunc && rootCondFunc(checkIt)){
				return {valid: true, level: level};
			}
			var pid = checkIt[propPid];
			if(! pid)return {valid: true, level: level};
			var searchParentMap = {};
			searchParentMap[propId] = pid;
			var parentItem = _.find(idArray, searchParentMap);// searchParentMap 예 = {id: pid}
			if(parentItem == null)return {valid: false, msg: '레벨을 찾을 수 없습니다.'};
			level++;
			if(level > limitLevel){
				return {valid: false, msg: '레벨이 '+limitLevel+'가 초과합니다.'};
			}
			checkIt = parentItem;
		}
	};
	// id pid 로 level 셋
	var setLevelByIdPid = function(idArray){
		_.each(idArray, function(it){
			var result = findLevel(idArray, it);
			it[propLevel] = result.valid && result.level;
		});
	};
	var makeTreeByIdPid = function(idArray){
		var idArray = _.cloneDeep(idArray);// 복사해서 사용하자
		// 먼저 _level 넣고
		setLevelByIdPid(idArray);
		var gbPid = _.groupBy(idArray, propPid);// group by pid
		var treeArray = [];
		_.each(idArray, function(it){
			var id = it[propId];
			var children = gbPid[id];
			if(children)it[propChildren] = children;
			// 루트에 1레벨 item 만 넣기
			var myLevel = it[propLevel];
			if(myLevel == 1 || myLevel == null)treeArray.push(it);
		});
		return treeArray;
	};
	var makeTreeToLevelArray = function(treeArray){
		var levelArray = [];
		var _id = 0;
		var pushToArray = function(treeArray, level, parentIt){
			_.each(treeArray, function(it){
				var children = it[propChildren];
				// var levelIt = $.extend({_level: level, _id: _id++, _pid: parentIt ? parentIt._id : null}, it);
				var levelIt = $.extend({}, it);
				levelIt[propLevel] = level;
				delete levelIt[propChildren];
				levelArray.push(levelIt);
				if(children && children.length){
					pushToArray(children, level + 1, levelIt);
				}
			});
		};
		pushToArray(treeArray, 1, null);
		return levelArray;
	};
	// level array to hierachy
	var makeLevelToTreeArray = function(levelArray){
		var pitems = [{[propChildren]: []}];
		_.each(levelArray, function(it, idx){
			it = $.extend({}, it);
			// if(isSetHelperProp)it._id = idx;
			var level = it[propLevel];
			// if(isSetHelperProp)it._level = level;
			if(pitems[level - 1][propChildren] == null){
				pitems[level - 1][propChildren] = [];
			}
			pitems[level - 1][propChildren].push(it);
			pitems[level] = it;
			// if(isSetHelperProp)it._pid = pitems[level - 1]._id;
		});
		return pitems[0][propChildren];
	};

	return {
		makeTreeByIdPid,
		makeTreeToLevelArray,
		makeLevelToTreeArray
	};

};
_.extend(this, {
	getCodeName,
	dateutil,
	arrayutil,
	TreeUtil
});

};//PykUtilObject
window._pu = new PykUtilObject();
