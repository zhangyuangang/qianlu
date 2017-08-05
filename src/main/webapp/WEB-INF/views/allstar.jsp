<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>大白</title>
		<link rel="stylesheet" href="../../css/Reset/reset.css" />
		<link rel="stylesheet" href="../../css/Allstar/allstar.css" />
		<script type="text/javascript" src="../../js/jquery.min.js" ></script>
		<script type="text/javascript" src="../../js/Allstar/allstar.js" ></script>
		<script type="text/javascript" src="../../js/swiper-3.3.1.min.js" ></script>
		<script type="text/javascript" src="../../js/iscroll-min.js"></script>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
			<div class="search">
				<div type="text" id="search" class="search_" ></div>
			</div>
			
            	<!--xiaoshou-->        	
            	<div class="box">
				</div>
            	<div class="box1">
				<ul></ul>
				</div>
            	</div>
            		</div>
		<div class="search_tans">
				<div class="search_tans_gangwei">
					<h2>热门岗位</h2>
					<span name="1">营销</span>
					<span name="2">产品</span>
					<span name="4">管理</span>
					<span name="8">财务</span>
					<span name="16">技术</span>	
					<span name="0">其他</span>	
				</div>
				<div class="line"></div>
				<div class="search_tans_hangye">
					<h2>热门行业</h2>
					<span>IT互联网</span>
					<span>金融快消</span>
					<span>大智造</span>
					<span>其他</span>
				</div>
				<div class="line"></div>
				<div class="search_tans_hotcp">
					<h2>热门公司</h2>
					<span>腾讯</span>
					<span>百度</span>
					<span>阿里巴巴</span>
					<span>华为</span>
					<span>蘑菇街</span>	
					<span>google</span>	
					<span>其他</span>
				</div>
				<div class="but">搜索</div>
				<div class="defid">取消</div>
			</div>			
     <!--footer-->
    <footer>
 		<div class="home as">
 		<a href="/home.html?from=singlemessage&isappinstalled=0">
 			<span class="img"></span>
 			<span class="nav">前路</span>
 			</a>
 		</div>
 		<div class="chat as">
 			<a href="/user/toChatList.html?from=singlemessage&isappinstalled=0">
	 			<span class="img"></span>
	 			<span class="nav">交谈</span>
 			</a>
 		</div>
 		<div class="person as">
 			<a href="/user/toUserCenter.html?from=singlemessage&isappinstalled=0">
 				<span class="img"></span>
 				<span class="nav">主页</span>
 			</a>
 		</div>
 	</footer>
 	
	</body>
		<script type="text/javascript">
		$(".defid").on("mousedown",function(){
			$(".search_tans").fadeOut(600)
			$(".scroll").css("height","auto")
			$(".wrapper").css("height","auto")
		})
			$("#search").on("mousedown",function(){
				$(".search_tans").fadeIn(600)
				$(".scroll").css("height","100%")
				$(".wrapper").css("height","100%")
			})
			$(".search_tans_gangwei span").on("mousedown",function(){
				var index = $(this).index()
					if($(this).hasClass("vk")){
						$(".search_tans_gangwei span").eq(index-1).removeClass("vk")
					}else{
						$(".search_tans_gangwei span").eq(index-1).addClass("vk")
					}
					if($(".search_tans_gangwei .vk").length>1){
					    $(".search_tans_gangwei span").eq(index-1).removeClass("vk")	
					 }
			})
			$(".search_tans_hangye span").on("mousedown",function(){
				var index = $(this).index()
					if($(this).hasClass("vk")){
						$(".search_tans_hangye span").eq(index-1).removeClass("vk")
					}else{
						$(".search_tans_hangye span").eq(index-1).addClass("vk")
					}
					if($(".search_tans_hangye  .vk").length>1){
					    $(".search_tans_hangye span").eq(index-1).removeClass("vk")	
					 }
			})
			$(".search_tans_hotcp span").on("mousedown",function(){
				var index = $(this).index()
					if($(this).hasClass("vk")){
						$(".search_tans_hotcp span").eq(index-1).removeClass("vk")
					}else{
						$(".search_tans_hotcp span").eq(index-1).addClass("vk")
					}
					if($(".search_tans_hotcp  .vk").length>1){
					    $(".search_tans_hotcp span").eq(index-1).removeClass("vk")	
					 }
			})
			
			
			
			//搜索
			$(".but").on("mousedown",function(){
				var occtype = $(".search_tans_gangwei .vk").attr("name")
			var tradename = $(".search_tans_hangye .vk").html()
			var companyname = $(".search_tans_hotcp .vk").html()
				console.log(occtype)
			console.log(tradename)
			console.log(companyname)
				$.ajax({
					url:'/user/searchByKeywords.html',
					type:'GET',
					data:{
						occType:occtype,
						tradeName:tradename,
						companyName:companyname,
					},
					success:function(msg){
						//User(occtype)
						console.log(msg.date)
						$(".box").empty()
						var str = "";
						for(var i = 0; i < msg.date.length; i++){																		
										str += 												
											'<li>'+
												'<div class="bigmsg" title="'+ msg.date[i].userId +'">'+																	
													'<div class="bigpic">'+
														'<img src="'+ msg.date[i].headImage +'" width="100%">'+
													'</div>'+
													'<div class="bigtxt">'+
														'<h3><span class="name">'+ msg.date[i].nickname +'</span><span class="zhiwei">'+ msg.date[i].occupationName +'</span></h3>'+
														'<p>'+
															'<span>'+ msg.date[i].companyName +'</span>'+
														'</p>'+
													'</div>'+              						
												'</div>'+
												'<a href="##">交谈</a>'+
											'</li>'											
						}
						$(".box1 ul").append(str)
						$(".search_tans").fadeOut(600)
						$(".scroll").css("height","auto")
						$(".wrapper").css("height","auto")
						iscroll.refresh()	
					}
				})
				
			})
			
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
                preventDefault: false
                
			})
			$.ajax({
						url:'/user/getUserForType.html',
						type:'POST',
						dataType:'json',
						async:true,
						success:function(msg){
							
							if(msg.code == "1"){
								//正常业务逻辑
								
								for(var i = 0; i < msg.data.length - 1; i++){
									var sst = '<div class="product" id="'+ msg.data[5].occType +'">'+
														 '<h2><span class="salej">'+ msg.data[5].name +'</span><a href="##" >更多</a></h2>'+
														'<div class="kong1"></div>'+
														 '<span class="line"></span>'+
														  '<ul>'+		
														  '</ul>'+
														'<div class="kong1"></div>'+
													'</div>'+
													'<span class="line"></span>'
									var sts = '<div class="product" id="'+ msg.data[i].occType +'">'+
														 '<h2><span class="salej">'+ msg.data[i].name +'</span><a href="##" >更多</a></h2>'+
														'<div class="kong1"></div>'+
														 '<span class="line"></span>'+
														  '<ul>'+		
														  '</ul>'+
														'<div class="kong1"></div>'+
													'</div>'+
													'<span class="line"></span>'
											$(".box").append(sts)
											
											iscroll.refresh()		

								}
								$(".box").prepend(sst)
								var index = $(".product h2 a")
								for (var i = 0; i < index.length; i++) {						 			
									index[i].addEventListener('click', function(){
											if($(this).hasClass("aa")){
												$(this).parent().siblings("ul").css({"height":"13rem"})
												$(this).removeClass("aa")
												$(this).html("更多")
											}else{
												$(this).parent().siblings("ul").css({"height":"100%"})
												$(this).addClass("aa")
												$(this).html("收起")
											}									
										iscroll.refresh()													
									}, false);
								}						
						User(1)
						User(2)
						User(4)
						User(16)
						User(8)
						User(0)
				function User(occtype){
					$.ajax({
						url:'/user/getUserList.html',
						type:'GET',
						data:{
							occType: occtype,
							curPage:0
							},
						dataType:'json',
						async:true,
						success:function(msg){
							
							if(msg.code == "1"){
								//正常业务逻辑
								
									for(var a = 0; a < msg.data.length; a++){
										
											var str = "";
												str += 												
													'<li>'+
														'<div class="bigmsg" title="'+ msg.data[a].userId +'">'+																	
															'<div class="bigpic">'+
																'<img src="'+ msg.data[a].headImage +'" width="100%">'+
															'</div>'+
															'<div class="bigtxt">'+
																'<h3><span class="name">'+ msg.data[a].nickname +'</span><span class="zhiwei">'+ msg.data[a].occupationName +'</span></h3>'+
																'<p>'+
																	'<span>'+ msg.data[a].companyName +'</span>'+
																'</p>'+
															'</div>'+              						
														'</div>'+
														'<a href="##">交谈</a>'+
													'</li>'
																											
											$("#"+occtype).find("ul").append(str)
											iscroll.refresh()
											$(".product ul li a").on("mousedown",function(){
													var userId = $(this).siblings(".bigmsg").attr("title")
													location.href = "/user/toUserCenter.html?userId=" + userId +"&from=singlemessage&isappinstalled=0"
											})
											$(".bigpic").on("mousedown",function(){
												var userId = $(this).parents(".bigmsg").attr("title")							
												location.href = "/user/toUserCenter.html?userId=" + userId +"&from=singlemessage&isappinstalled=0"
											})	
										}		
							} else{
								console.log(msg.info);
							}
						}
					
						});
					
				}
							} else{
								console.log(msg.info);
							}
						}
					
					});
				
				
				
					
				
				
					
			
			
		</script>
				
</html>
