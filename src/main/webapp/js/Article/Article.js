$(function(){
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

	$(".perosonol").on("mousedown",function(){
		 if($(".zhezhao").hasClass("zh")){
		    	$(".tan").animate({"bottom":"-1.3rem"},500)
		  	 	$(".zhezhao").removeClass("zh")
		    }else{
		    	$(".tan").animate({"bottom":"2.7rem"},500)	
		    				$(".zhezhao").addClass("zh")
    }

    	})
 
    	 
})
