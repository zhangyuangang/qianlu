<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>	
	    <link rel="stylesheet" href="../../../css/Reset/reset.css" />
	    <link rel="stylesheet" href="../../../css/pers/pbigb.css" />
	    <script type="text/javascript" src="../../../js/jquery.min.js" ></script>
	    <script type="text/javascript" src="../../../js/pers/pbigb.js" ></script>
	   	<script src="../../../js/iscroll-min.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		 <script src="../../../js/ajax-utils.js"></script>
		 <script type="text/javascript">
			define = null;
			require = null;
		</script>
		<style>
		#school2{display:none;}
		#Bigimg{position:fixed;z-index:111;top:27%;}
		.zhezhao{width:100%;height:100%;background:#000;display:none;position:absolute;z-index:11;top:0;}
		</style>
	    <title>${BIRDS_USER.userInfo.nickname }</title>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
		<!--header-->
		<header>
			<div class="modify"></div>
			<div class="kong1"></div>
			<div class="person_pic">				
				<img src="${BIRDS_USER.userInfo.headImage }" width="100%" height="100%">
			</div>
			<i class="allst"></i>
			<div class="person_text">
				<h2><span class="name">${BIRDS_USER.userInfo.nickname }</span><span class="zhiwei">${BIRDS_USER.userInfo.occupationName }</span></h2>
				<p>公司<span class="address">${BIRDS_USER.userInfo.companyName }</span></p>
				<p>行业<span class="address">${BIRDS_USER.userInfo.tradeName }</span></p>
			</div>					
		</header>
		<div class="kong1"></div>	
		<div class="line"></div>
		<!--shouru-->
		<div class="kong1"></div>
		<div class="income">			
			<span class="allin">总收入: <i>${userExtPo.moneyStr }</i></span>
			<span class="answers">共进行: <i>${userExtPo.orderNum }</i>次回答<b></b></span>

		</div>
		<div class="kong1"></div>
		<!--jibenziliao-->
		<div class="school">
			<span class="school_">
			<i>本科	</i>		
			<i>${BIRDS_USER.userInfo.schoolName}</i>					
			</span>			
			<span class="school_education"><i>专业</i><i>${BIRDS_USER.userInfo.majorName }</i></span>			
		</div>
		<div class="school" id="school2">
			<span class="school_">
			<i>硕士</i>
			<i>${BIRDS_USER.userInfo.schoolName2}</i>						
			</span>						
			<span class="school_major"><i>专业</i><i>${BIRDS_USER.userInfo.majorName2 }</i></span>
		</div>
		<!--gerenjianjie-->
		<div class="kong1"></div>
		<div class="persosn_introduction">
			<span>大白介绍:</span>
			<p>${BIRDS_USER.userInfo.intro}</p>
			<span>擅长领域:</span>
			<p>${BIRDS_USER.userInfo.hob}</p>
			<span>个人资源:</span>
			<p>${BIRDS_USER.userInfo.resTxt}</p>
		</div>
		<div class="kong"></div>	
		<div class="line"></div>
		<!--fangkejilu-->
		<div class="kong"></div>	
		<div class="visitor">
			<span>访客记录</span>
			<div class="visitor_pic">
				
			</div>
		</div>
		
		<div class="kong"></div>	
		<div class="line"></div>
		<!---->
		<div class="title">
            		<span class="title_c vk">随笔</span>
            		<span class="sline"></span>
            		<span class="title_c vk">长文</span>
         </div>
		<div class="ziliao">
			<div class="article">
				
			</div>
				<div class="article_">
					
				</div>
				
			</div>

		 </div>
   </div>
   
   <!--tan-->
   		<div class="tix">
   			<p>尊敬的用户您好<i></i></p>
   			<div class="line"></div>
   			<span>1、请在消息里面找到前路小助手</span>
   			<span>2、向小助手回复“（大白花名）+提现+（金额）”，比如，Chuck+提现+100。</span>
   			<span>3、小助手收到后会回复您提现认证码和小助手的微信号。</span>
			<span>4、添加前路小助手的微信，备注信息填写提现认证码。</span>
			<span>5、前路小助手通过微信转账的方式随时帮您提现。</span>
			<span>6、您的主页总收入，是扣除微信等服务费后的可提现收入。</span>			
   		</div>
   	<!----footer---->
	<footer>
 		<div class="home as" data-ripple>
 		<a href="/home.html?from=singlemessage&isappinstalled=0">
 			<span class="img"></span>
 			<span class="nav">前路</span>
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
<img src="" width="100%"  id="Bigimg">	
  <div class="zhezhao"></div>
	</body>
		<script type="text/javascript">
		window.onload = function(){
			$("#Bigimg").on("mousedown",function(){
				$(this).fadeOut(600)
				$(".zhezhao").fadeOut(600)
			})	
			$(".title_c").on("mousedown",function(){
				var index = $(this).index()
				$(this).removeClass("vk").siblings(".title_c").addClass("vk")
				
				if(index == 0){
					$(".article").hide()
					$(".article_").show()	
				
					iscroll.refresh()
				}else if(index == 2){
					$(".article").show()
					$(".article_").hide()

					iscroll.refresh()
				}
							
							
			})
			
		}	
		if($("#school2 .school_ i").eq(1).html() != ''){
				$("#school2").css("display","flex")
			}
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false
			})
			 document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    			})
			 
			 $(".modify").on("mousedown",function(){
			 	location.href = "/user/toUserInfo.html"
			 })
			 $(".tix p i").on("mousedown",function(){
				 $(".tix").fadeOut(600)
			 })
			 
			  $(".segu_a").on("mousedown",function(){
				
				$(this).siblings(".segu_text").css("height","auto")
				$(".segu_a").hide()
				iscroll.refresh()
			})

			$.ajax({
                url:'/dynamic/getList.html',
                type:'GET',
                data:{
                	isPaging:1,
					pageSize:3,
					curPage:0,
					userId: ${user.userInfo.userId }
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
								'<div class="segu stars" id="segu">'+
								'<div class="bigmsg attr-title" title="'+ msg.data[i].userId +'" name="'+ msg.data[i].id +'" con="'+ msg.data[i].isStar +'" style="width:100%">'+												
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
								'<div class="line2"></div>'+
								'<div class="foot">'+
								'<div class="af zan"><i></i><i>'+ msg.data[i].starTimes +'</i></div>'+
								'<div class="af ping"><i></i><i>'+ msg.data[i].commentTimes +'</i></div>'+
								'</div>'+
								'<div class="kong"></div>'+	
								'<div class="sbsearch"><input type="text" id="text" class="text" placeholder="发表评论…"/><div class="buttn">发送</div></div>'+
								'<div class="plcontent"><ul></ul><span class="more">加载更多</span></div>'+
								'</div>'
								$(".article_").append(str)	
								
								$(".title_c").on("mousedown",function(){
									
											var index = $(this).index()
											$(this).removeClass("vk").siblings(".title_c").addClass("vk")
												
												if(index == 0){
													$(".article").hide()
													$(".article_").show()													
													iscroll.refresh()
												}else if(index == 2){
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
							if($(".article").html() != ""){
									$(".article").show()
									$("#changw").removeClass("vk")
									iscroll.refresh()							
								}
								if($(".article_").html() != ""){
									$("#suib").removeClass("vk")
									$(".article_").show()
									iscroll.refresh()
								}
								if($(".article").html() != "" && $(".article_").html() != "" ){
									$("#suib").removeClass("vk")
									$("#changw").addClass("vk")
									$(".article_").show()
									$(".article").hide()
									iscroll.refresh()
								}
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
								var val = $(".stars")
								//console.log(msg.data.occType)
								for(var a = 0; a < val.length; a++){
									//console.log($(".stars").eq(0).find(".bigmsg").attr("con"))
									if($(".stars").eq(a).find(".bigmsg").attr("con") == 'true'){
										$(".zan").eq(a).find("i").eq(0).css({"background":"url('../../../img/zanx@2x.png') no-repeat","background-size":"100% 100%"})
									}
									if($(".stars").eq(1).find(".bigmsg").attr("con") == 'false'){
									$(".zan").eq(a).find("i").eq(0).css({"background":"url('../../../img/zanw@2x.png') no-repeat","background-size":"100% 100%"})
									
									}
								}
								//点赞
						$(".zan").on("mousedown",function(){
							var index = $(this).parents(".segu").index()
							
							console.log(index)
							var Id = $(this).parents(".foot").siblings(".bigmsg").attr("name")

							var star = $(this).parents(".foot").siblings(".bigmsg").attr("con")

					

							var isstar = $(this).find("i").eq(1).html()

							

							console.log(star)

							$.ajax({

								url:'/dynamic/hitStarById.html',

								type:'POST',

								data:{

									dynamicId:Id

								},

								success:function(msg){

									

										if(star == 'true'){

											isstar--

											$(".segu").eq(index).find(".foot i").eq(0).css({"background":"url('../../../img/zanw@2x.png') no-repeat","background-size":"100% 100%"})

											$(".segu").eq(index).find(".foot i").eq(1).html(isstar)

											$(".segu").eq(index).find(".bigmsg").attr("con",false)

											

										}

										if(star == 'false'){
											console.log($(this))
											isstar++

											$(".segu").eq(index).find(".foot i").eq(0).css({"background":"url('../../../img/zanx@2x.png') no-repeat","background-size":"100% 100%"})

											$(".segu").eq(index).find(".foot i").eq(1).html(isstar)

											$(".segu").eq(index).find(".bigmsg").attr("con",true)



										}

									

								}

							})

							

						})

					
					} else{
						console.log(msg.info);
					}
                }
			
            });
			addb()
			function addb(){
				//点击
					
						$(".ping").on("mousedown",function(){
							if($(this).hasClass("tt")){
								$(this).parents(".foot").siblings(".sbsearch").css("display","none")
								$(this).parents(".foot").siblings(".plcontent").hide()
								$(this).removeClass("tt")
								iscroll.refresh()
							}else{					
								//debugger
								$(this).parents(".foot").siblings(".sbsearch").css("display","flex")
								$(this).parents(".foot").siblings(".plcontent").show()
								var Id = $(this).parents(".foot").siblings(".bigmsg").attr("name")
								var e = $(this).parents(".segu").index()
								console.log()
								$(this).parents(".foot").siblings(".plcontent").find("ul").empty()
								pingl(Id,0,e)
								iscroll.refresh()
								$(this).addClass("tt")					
							}
						})
					
						
						
						var indexs = $(".more")
								
								for (var i = 0; i < indexs.length; i++) {						 			
									indexs[i].addEventListener('click', function(){
										var Ids = $(this).parents(".plcontent").siblings(".bigmsg").attr("name")
										var e = $(this).parents(".segu").index()
											$.ajax({
												url:'/dynamic/loadingMoreComment.html',
												type:'GET',
												data:{
													dynamicId:Ids
												},
												success:function(msg){
													var  str = ""
													for(var i = 0; i < msg.data.length; i++){
														str+= '<li><span class="nam">'+ msg.data[i].nickname +' :</span>'+ msg.data[i].content +'</li>'
														$(".stars").eq(e).find(".plcontent ul").empty()
														$(".stars").eq(e).find(".plcontent ul").html(str)
													}
													
												
													iscroll.refresh()
												}
											})
										iscroll.refresh()													
									}, false);
								}
			}
			//评论列表
			function pingl(mg,mm,e){
				$.ajax({
					url:'/dynamic/loadingMoreComment.html',
					type:'GET',
					data:{
						isPaging:1,
						pageSize:5,
						curPage:mm,
						dynamicId:mg
					},
					success:function(msg){
						var  str = ""
						for(var i = 0; i < msg.data.length; i++){
							str+= '<li><span class="nam">'+ msg.data[i].nickname +' :</span>'+ msg.data[i].content +'</li>'
						}
						
						$(".stars").eq(e).find(".plcontent ul").html(str)
						iscroll.refresh()
					}
				})
			}
			
			//发送评论
			$(".buttn").on("mousedown",function(){
				//console.log("asd")
				var val = $(this).siblings("#text").val()
				var Id = $(this).parents(".sbsearch").siblings(".bigmsg").attr("name")
				var e = $(this).parents(".segu").index()
				if(val == ""){
					alert("请输入内容")					
				}else{
					$.ajax({
						url:'/dynamic/comment.html',
						type:'POST',
						data:{
							content:val,
							dynamicId:Id
						},
						success:function(msg){
							$(".text").val("")
							$(this).parents(".sbsearch").siblings(".plcontent").find("ul").empty()
								pingl(Id,0,e)
								iscroll.refresh()
						}
					})
				}
				
				
				
			})
			//精选长文
				$.ajax({
                url:'/article/getList.html',
                type:'GET',
                data:{
                	isPaging:1,
					pageSize:1,
					curPage:0,
					userId: ${user.userInfo.userId }
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
							'<h2>'+ msg.data[i].title+'</h2>'+
							'<p>'+ msg.data[i].intro +'</p>'+
							'<div class="kong"></div>'+
							'<div class="kong1"></div>'+
						 '</div>'+         		
						 '<div class="kong2"></div>'
						
						}
          				$(".article").append(str)
						iscroll.refresh()
						var Id = $(".text_one").attr("name")
						$(".text_one").on("mousedown",function(){
							location.href = "/article/getById.html?id=" + Id
						})
						if($(".article").html() != ""){
							$(".article").show()
							$("#changw").removeClass("vk")
							iscroll.refresh()							
						}
						if($(".article_").html() != ""){
							$("#suib").removeClass("vk")
							$(".article_").show()
							iscroll.refresh()
						}
						if($(".article").html() != "" && $(".article_").html() != "" ){
							$("#suib").removeClass("vk")
							$("#changw").addClass("vk")
							$(".article_").show()
							$(".article").hide()
							iscroll.refresh()
						}
					} else{
						console.log(msg.info);
					}
                }
			
            });
			$(".bttn").on("mousedown",function(){
				$.ajax({
					url:'/user/withdraw.html',
					type:'POST',
					success: function(msg){
						console.log("成功")
					},
					error: function(msg){
						console.log("失败")
					}
				})
			})
			
			$.ajax({
				 url:'/user/getUserList.html',
				 type:'GET',
				 data:{
					 isPaging:1,
					pageSize:5,
					curPage:0,
					cuserId: ${BIRDS_USER.userInfo.userId },//当前访问个人中心的userId，用来获取【访客记录】同时userType传-1
					userType:-1
				 },
				 success: function(msg){
					 var str = ""
					 for(var i = 0; i < msg.data.length; i++){
						  str += 
						'<div class="pic_fang" style="margin-left: -.5rem;" name="'+ msg.data[i].userId +'">'+
							'<img src="'+ msg.data[i].headImage +'" width="100%"  />'+
						'</div>'
						
					 }
					$(".visitor_pic").append(str)
					$(".pic_fang").on("mousedown",function(){
						var userId = $(this).attr("name")							
						location.href = "/user/toUserCenter.html?userId=" + userId
					})
				 }
			 })
			
		</script>
		<script>
	$(document).ready(function(){
		 var now = new Date().getTime();
		 var nonceStr = "ZhuBaoShi";
         var signature = "";
        var wechatback =function(rslt){
			  signature = rslt.data;
			//console.log(signature)
		wx.config({
			debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId: 'wx8858c54271aadf64', // 必填，公众号的唯一标识
			timestamp: now, // 必填，生成签名的时间戳
			nonceStr: nonceStr, // 必填，生成签名的随机串
			signature:signature ,// 必填，签名，见附录1
			jsApiList: [
				'checkJsApi',
            'openLocation',
            'getLocation',
            'onMenuShareTimeline',
            'onMenuShareAppMessage'
			] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});				
			wx.onMenuShareAppMessage({
				title: '前路，让3年后的自己为现在的你指路', // 分享标题
				desc: '大白   ${BIRDS_USER.userInfo.nickname }                               ${BIRDS_USER.userInfo.companyName }  ${BIRDS_USER.userInfo.occupationName }', // 分享描述
				link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: '${BIRDS_USER.userInfo.headImage }', // 分享图标
				type: 'link', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () { 
					// 用户确认分享后执行的回调函数
					console.log("成功")
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
					console.log("失败")
				}
			});
		}
		  ajaxEngine.postAjax("/getWeChatSign.html", {timestamp:now, noncestr:nonceStr, url:window.location.href}, wechatback);	
		});
		
		
		</script>
</html>

