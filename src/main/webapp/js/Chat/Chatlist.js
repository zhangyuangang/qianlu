$(function(){
			$(".as").on("mousedown",function(){
			var index = $(this).index()
			$(".nav").eq(index).css("color","#fc8f2a").parents().siblings(".as").find(".nav").css("color","#7c7c7c")
		})
		$(".home").on("mouseover",function(){
			location.href = "../../index.html"
			$(".home img").attr("src","../../img/qianluhome1.png")
			$(".chat img").attr("src","../../img/chat.png")
			$(".person img").attr("src","../../img/my.png")
		})
		$(".chat").on("mouseover",function(){
			$(".home img").attr("src","../../img/qianluhome.png")
			$(".chat img").attr("src","../../img/chat1.png")
			$(".person img").attr("src","../../img/my.png")
		})
		$(".person").on("mouseover",function(){
			location.href = "person/pbigb.html"
			$(".home img").attr("src","../../img/qianluhome.png")
			$(".chat img").attr("src","../../img/chat.png")
			$(".person img").attr("src","../../img/my1.png")
		})
})
