<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>精选</title>
		<link rel="stylesheet" href="../../css/Reset/reset.css" />
		<link rel="stylesheet" href="../../css/Article/Selected.css" />
		<script type="text/javascript" src="../../js/ripple.js" ></script>
		<script type="text/javascript" src="../../js/jquery.min.js" ></script>
		<style>
		#Bigimg{position:fixed;z-index:111;top:27%;}
		.zhezhao{width:100%;height:100%;background:#000;display:none;position:absolute;z-index:11;top:0;}
		</style>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
            	<div class="kong2"></div>
            	<!--title-->
            	<div class="title">
            		<span class="title_c">随笔</span>
            		<span class="title_c vk">长文</span>
            	</div>
            	<!--长文精选-->    
            	<div class="article cw">

            	</div>
            	<!--精选广播-->
            	<div class="article_ gb">
            	
            	</div>
				<div class="kong2"></div>
				
           </div>
          </div>
		  <div class="zhezhao"></div>
		  <img src="" width="100%"  id="Bigimg">
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
	 			<span class="nav">消息</span>
 			</a>
 		</div>
 		<div class="person as">
 			<a href="/user/toUserCenter.html?from=singlemessage&isappinstalled=0">
 				<span class="img"></span>
 				<span class="nav">我的</span>
 			</a>
 		</div>
 	</footer>
	</body>
	<script src="../../js/iscroll-min.js"></script>
	<script type="text/javascript">
			$("#Bigimg").on("mousedown",function(){
				$(this).fadeOut(600)
				$(".zhezhao").fadeOut(600)
			})	
		 document.addEventListener("touchmove",function(e){
						e.preventDefault();
					})
		    var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false
                
			})
		    //ready
			document.ready = function (callback) {
					///兼容FF,Google
					if (document.addEventListener) {
						document.addEventListener('DOMContentLoaded', function () {
							document.removeEventListener('DOMContentLoaded', arguments.callee, false);
							callback();
						}, false)
					}
					 //兼容IE
					else if (document.attachEvent) {
						document.attachEvent('onreadystatechange', function () {
							  if (document.readyState == "complete") {
										document.detachEvent("onreadystatechange", arguments.callee);
										callback();
							   }
						})
					}
					else if (document.lastChild == document.body) {
						callback();
					}
				}

			
			document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
		    
		    

		    $(function(){
		    
			
			
		})
			//随笔
			var page = 1
			
			document.ready(function () {        
				suibi(0)
		    function suibi(page){
				$.ajax({
                url:'/dynamic/getList.html',
                type:'GET',
                data:{
                	isPaging:1,
					pageSize:4,
					curPage:page
					},
                dataType:'json',
				async:false,
                success:function(msg){
					
                	if(msg.code == "1"){
						//正常业务逻辑
						//console.log(msg.data)
					for(var i=0; i<msg.data.length; i++){					
						//console.log(msg.data[i].imgUrls.split("<split>"))
							var str = "";
							str += 
								'<div class="segu">'+
								'<div class="bigmsg attr-title" title="'+ msg.data[i].userId +'">'+												
									'<div class="bigpic">'+
										'<img src="'+ msg.data[i].headImage +'" width="100%" height="100%">'+
									'</div>'+
									'<div class="bigtxt">'+
										'<h3><span class="name">'+ msg.data[i].nickname +'</span><span class="zhiwei">'+ msg.data[i].occupationName +'</span></h3>'+
										'<p>'+										
											'<span>'+ msg.data[i].companyName +'</span>'+
										'</p>'+
									'</div>'+ 
									'<a href="##">交流</a>'+									
								'</div>'+
              					'<div class="segu_text">'+
								'<p>'+ msg.data[i].content +'</p>'+
								'</div>'+
								'<span class="segu_a">全文</span>'+
								'<div class="segu_pic" id="glist">'+
									'<ul>'+										
									'</ul>'+
								'</div>'+							
								'<div class="kong"></div>'+	
								'</div>'+
								'<div class="kong"></div>'	
								$(".article_").append(str)	
									var navScoll = new IScroll(".segu_pic",{
													scrollX:true,
													preventDefault: false,
													click:true
												})
									$(".bigmsg a").on("mousedown",function(){
													var userId = $(this).parent().attr("title")
													location.href = "/user/toUserCenter.html?userId=" + userId
									})			
									$(".title_c").on("mousedown",function(){
												var index = $(this).index()
												$(".title_c").eq(index).removeClass("vk").siblings().addClass("vk")
												
												if(index == 0){
													$(".article").hide()
													$(".article_").show()												
													navScoll.refresh()
													iscroll.refresh()
												}else if(index == 1){
													$(".article").show()
													$(".article_").hide()
													iscroll.refresh()
												}
															
															
											})
												
								iscroll.refresh()
							    
						for(var a = 0;a < msg.data[i].imgUrls.split("<split>").length; a++){
										var aa = msg.data[i].imgUrls.split("<split>")[a]
										
										if(aa.substr(0,4)){
											var sts = "";
											sts += 									
											'<li class="segu_t"><img src="'+ aa +'" width="100%" height="72px"/></li>'
												$(".segu_pic").eq(i).find("ul").append(sts)
											iscroll.refresh()
											
											
										}else{
											if(aa == ""){
												
											}else{
												
												var sts = "";
											sts += 
											'<li class="segu_t"><img src="${IMG_URL}'+  +'" width="100%" height="72px" /></li>'
												$(".segu_pic").eq(i).find("ul").append(sts)
											iscroll.refresh()
											}

										}
																	
									}				
															
								}
		
							$(".segu_t img").on("mousedown",function(){
								var Imgs = $(this).attr("src")
								var Imgb = Imgs.replace("-smaller","")
								$("#Bigimg").attr("src",Imgb)
								$("#Bigimg").fadeIn(600)
								$(".zhezhao").show()
							})
							var index = $(".segu_a")
								for (var i = 0; i < index.length; i++) {						 			
									index[i].addEventListener('click', function(){
											if($(this).hasClass("aa")){
												$(this).siblings(".segu_text").css({"height":"3.8rem"})
												$(this).removeClass("aa")
												$(this).html("全文")
											}else{
												$(this).siblings(".segu_text").css("height","auto")
												$(this).addClass("aa")
												$(this).html("收起")
											}									
										iscroll.refresh()													
									}, false);
								}				
								
					} else{
						console.log(msg.info);
					}
                }
			
            });
			
			}
			//精选长文
			changwen(0)
			function changwen(page){
				
				$.ajax({
                url:'/article/getList.html',
                type:'GET',
                data:{
                	isPaging:1,
					pageSize:4,
					curPage:page
					},
                dataType:'json',
				async:true,
                success:function(msg){
					
                	if(msg.code == "1"){
						//正常业务逻辑
						var str = ""
						for(var i=0; i<msg.data.length; i++){
							var bb = msg.data[i].headImg.replace("-smaller","")
							str +=
							'<div class="text_one" id="te" name="'+ msg.data[i].id +'">'+           	             	
								'<div class="headerpic">'+
								'<img src="'+ bb +'" width="100%">'+
								'</div>'+          	
								'<div class="kong"></div>'+
								'<h2>'+ msg.data[i].title +'</h2>'+
								'<div class="kong1"></div>'+
								'<p>'+ msg.data[i].intro +'</p>'+
								'<div class="kong"></div>'+
								'<div class="kong1"></div>'+
							'</div>'+         		
							'<div class="kong2"></div>'
						
						}
          				$(".article").append(str)
						iscroll.refresh()
						
						$(".text_one").on("mousedown",function(){
							var Id = $(this).attr("name")
						
							location.href = "/article/getById.html?id=" + Id +"&from=singlemessage&isappinstalled=0"
						})
					} else{
						console.log(msg.info);
					}
                }
			
            });
				
				
			}			
			document.addEventListener("touchend",function(){
				
					 //无限加载
					 if(iscroll.y<iscroll.maxScrollY-50){
						page = page + 1				
						changwen(page)
						suibi(page)
					 }
						
			
				
				})
			
			})
	</script>
</html>
