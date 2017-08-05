$(function(){
	$(".answers b").on("mousedown",function(){
	
	 	$(".tix").fadeIn(300)
	 })
	 $(".bttn").on("mousedown",function(){
	 	$(".tix_").fadeIn(300)
	 	$(".tix").fadeOut(300)
	 })
	 $(".tix_").on("mousedown",function(){
	 	$(".tix_").fadeOut(300)
})
})
