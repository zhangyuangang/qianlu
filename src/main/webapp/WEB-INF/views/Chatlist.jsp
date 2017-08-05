<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>消息</title>
		<link rel="stylesheet" href="../../css/Reset/reset.css" />
		<link rel="stylesheet" href="../../css/Chat/Chatlist.css" />
		<link rel="stylesheet" href="../../css/Pluglib/swiper.min.css" />
		<script type="text/javascript" src="../../js/jquery.min.js" ></script>
		<script type="text/javascript" src="../../js/Chat/Chatlist.js" ></script>
	</head>
	<style>
	.swiper-container {
        width: 90%;
        height: 200px;
        margin: 20px auto;
    }
    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;     
    }
	.more{width: 90%; margin: 0 auto; font-size: 1rem; margin-top: 1rem;font-family: "微软雅黑";}
	.more a{float:right;}
	</style>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
			 <h2 class="more"><span class="salej">大白</span><a href="/user/toUserList.html">更多</a></h2>
				<div class="swiper-container swiper-container-horizontal swiper-container-free-mode">
					<div class="swiper-wrapper">
						
					</div>
				</div>
				
				
				
				
				
            	<div class="sale">
            		  <ul>
              				
              				
              			</ul>
            	</div>
            	</div>
            		</div>
            		<!--footer-->
            			<footer>
 		<div class="home as" data-ripple>
 		<a href="/home.html?from=singlemessage&isappinstalled=0">
 			<span class="img"></span>
 			<span class="nav" >前路</span>
 			</a>
 		</div>
 		<div class="chat as" data-ripple>
 			<a href="/user/toChatList.html?from=singlemessage&isappinstalled=0">
	 			<span class="img"></span>
	 			<span class="nav">交谈</span>
 			</a>
 		</div>
 		<div class="person as" data-ripple>
 			<a href="/user/toUserCenter.html?from=singlemessage&isappinstalled=0">
 				<span class="img"></span>
 				<span class="nav">主页</span>
 			</a>
 		</div>
 	</footer>
 	
	</body>
	<script src="../../js/iscroll-min.js"></script>
	<script src="../../js/swiper-3.3.1.min.js"></script>
		<script type="text/javascript">
				
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
                preventDefault: false
                
			})
			document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    			})
			$.ajax({
				url:'/user/getUserMsgList.html',
				type:'GET',
				data:{
					curPage:0
				},
				success: function(msg){
					for(var i = 0; i < msg.data.length; i++){
						var str = ""
						str +=
							'<li>'+
              					'<div class="bigmsg attr-title" title="'+ msg.data[i].tuserId +'" name="'+ msg.data[i].id +'">'+           							
              						'<div class="bigpic">'+
              							'<img src="'+ msg.data[i].theadImg +'" width="100%">'+
              						'</div>'+
              						'<div class="bigtxt">'+
              							'<h3>'+ msg.data[i].tusername +'</h3>'+
              							'<p>'+ msg.data[i].lastest +'</p>'+
             						'</div>'+             						
              					'</div>'+
              				'</li>'+
              				'<div class="kong"></div>'+
              				'<span class="line"></span>'
						$(".sale ul").append(str)
						iscroll.refresh()
					}
					
					$(".sale li").on("mousedown",function(){
							var TuserId = $(this).find(".bigmsg").attr("title")
							var Name = $(this).find(".bigmsg").attr("name")
							location.href = "/chat.html?selfId="+${BIRDS_USER.id}+'&tId='+TuserId+'&id='+Name				
						})
					
					
				}
			})
			$.ajax({
                url:'/user/getUserList.html',
                type:'GET',
                data:{
					isPaging:1,
					pageSize:10,
					curPage:0
					},
                dataType:'json',
				cache:true,
				ansy:true,
                success:function(msg){
					console.log(msg)
                	if(msg.code == "1"){
						//正常业务逻辑
						var str = ""
						for(var i=0; i<msg.data.length; i++){
							str +=
									'<div class="swiper-slide">'+
										'<div class="wra_img"><img src="'+ msg.data[i].headImage +'" width="100%"/></div>'+
										'<p>'+ msg.data[i].nickname +'</p>'+
										'<p>'+ msg.data[i].companyName +'</p>'+
										'<p>'+ msg.data[i].occupationName +'</p>'+
										'<div class="talk" title="'+ msg.data[i].userId +'" >交谈</div>'+
									'</div>'

						}
          				$(".swiper-wrapper").append(str)
						var swiper = new Swiper('.swiper-container', {
							pagination: '.swiper-pagination',
							slidesPerView: 2,
							paginationClickable: true,
							spaceBetween: 20,
							freeMode: true
						});
						$(".talk").on("mousedown",function(){
							var userId = $(this).attr("title")
							location.href = "/user/toUserCenter.html?userId=" + userId + "&from=singlemessage&isappinstalled=0"
						})							
					} else{
						console.log(msg.info);
					}
                },
				error:function(){
					console.log("失败")
				}
			
            });	
	</script>
</html>
