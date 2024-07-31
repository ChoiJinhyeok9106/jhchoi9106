$(function() {
	try{
		//왼쪽 서브 메뉴
		var appName = getParameterByName('app', location.href);
		var pageName = getParameterByName('pageName', location.href);
		
	    var snbUI = {
	        click: function(target, speed) {
	            var _self = this,
	                $target = $(target);
	            _self.speed = speed || 300;
	            $target.each(function() {
	                if (findChildren($(this))) {
	                    return;
	                }
	                $(this).addClass('noDepth');
	            });

	            function findChildren(obj) {
	                return obj.find('> ul').length > 0;
	            }
	            
	            $target.on('click', 'a', function(e) {
	                e.stopPropagation();
	                var $this = $(this),
	                    $depthTarget = $this.next(),
	                    $siblings = $this.parent().siblings();

	                $this.parent('li').find('ul li').removeClass('on');
	                $siblings.removeClass('on');
	                $siblings.find('ul').slideUp(250);

	                if ($depthTarget.css('display') == 'none') {
	                    _self.activeOn($this);
	                    $depthTarget.slideDown(_self.speed);
	                } else {
	                    $depthTarget.slideUp(_self.speed);
	                    _self.activeOff($this);
	                }
	                $(this).parent().siblings().children().removeClass('on');
					$(this).addClass('on');
	            })
	        },
	        activeOff: function($target) {
	            $target.parent().removeClass('on');
	        },
	        activeOn: function($target) {
	            $target.parent().addClass('on');
	        }
	    };

	    // Call snbUI
	    $(function() {
	        snbUI.click('#snb li', 300);
	    });

	    //왼쪽 메뉴 반응형 사이즈에서 사라짐
	    var wwidth = $(window).width();
	    if (wwidth <= 1401) {
	        $('#snb').removeClass('on');
	        $('.sub_content').removeClass('on');
	    }
	    if (wwidth >= 1400) {
	    	$('#snb').css({'transition':'.0s'});
	        $('#snb').addClass('on');
	        $('.sub_content').css({'transition':'.0s'});
	        $('.sub_content').addClass('on');
	    }

	    $(window).resize(function() {
	        var wwidth = $(window).width();
	        if (wwidth <= 1401) {
	            $('#snb').removeClass('on');
	            $('.sub_content').removeClass('on');
	        }
	        if (wwidth >= 1400) {
	            $('#snb').addClass('on');
	            $('.sub_content').addClass('on');
	        }
	    })

	    //왼쪽 메뉴 열고 닫기
		$('#sub_header .btn_menu').off('click');
	    $('#sub_header .btn_menu').on('click', function() {
	    	$('#snb').css({'transition':'.4s'});
			$('.sub_content').css({'transition':'.4s'});
	        $('#snb').toggleClass('on');
	        $('.sub_content').toggleClass('on');
	        if ($(window).width() <= 1025) {
	            $('.sub_content').removeClass('on');
	        }
	    });
	    
		$('#snb li').each(function(){
	    	var onclickEvent = $(this).attr('onclick');
	    	
			if(!isNull(onclickEvent) && isNull(pageName) || !isNull(pageName) && onclickEvent == "contentsOpen('"+pageName+"');"){
				$(this).parents('ul').each(function(){
					$(this).show();
				});
				
				$(this).parents('li').each(function(){
					if(isNull($(this).attr('class'))){
						$(this).addClass('on');
					}
				})
				
				if(typeof $(this).attr('onclick') === 'string'){
		    		pageName = $(this).attr('onclick').replace("contentsOpen('","").replace("');","");
		    	}
				contentsOpen(pageName, null, true);
//				$(this).click();
				return false;
			}
		});
	}catch(err){
		return false;
	}
}); 


