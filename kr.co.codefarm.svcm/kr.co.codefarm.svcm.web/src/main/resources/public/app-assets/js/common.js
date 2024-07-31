Vue.mixin({
	data: function () {
		return {
		};
	},
	created: function () {
	},
	methods: {
		makeComma : function(str) {
			 str = String(str);
			 return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		},
		reportUrl : function(filePath,paramObj){
			var host = location.host;
			var reportHost = 'report.kren.kr';
			if(!(host == 'www.kren.kr' || host == 'kren.kr' || host == 'kren.ne.kr')) reportHost = 'report.dev.kren.kr';
			var url = 'https://'+reportHost+'/ubi4/ubihtml.jsp';
			url += '?file='+filePath;
			if(Object.keys(paramObj).length > 0) url += '&arg=';
			var cnt = 0;
			for ( var key in paramObj) {
				if(cnt == 0) url += key+'%23'+paramObj[key];
				else url += '%23'+key+'%23'+paramObj[key];
				cnt++;
			}
			
			return url;
		}
	}
});
