$(function(){
		

		document.addEventListener("touchmove",function(e){
    	e.preventDefault();
   				 })
		window.onresize = function () {
				 var h = $(window).height();
				 //console.log(h+','+window.screen.availHeight)
				 var u = navigator.userAgent;
				 if(u.indexOf('Android') > -1|| u.indexOf('Linux') > -1) {
				 if(h <= window.screen.availHeight/2){
				  $('footer').css({'position':'absoult',"margin-top":"-.5rem",'display':'none'});
				 }else{
				  $('footer').css({'position':'relative','display':'block'});
				 }
				 }
				}
//				$('input').on('focus',function(){
//				 $('footer').hide();
//				})
//				$('footer').on('blur',function(){
//				 $('#toNav').show();
//				})
		$(".searchs").on("mousedown",function(){
			$(".taninter").fadeIn(400);
			
		})
		$("#sousuo").on("mousedown",function(){
			$(".taninter").fadeOut(400);

		})
		
	 })