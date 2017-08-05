<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>	
	   	<link rel="stylesheet" href="../../../css/Reset/reset.css" />
	   	<link rel="stylesheet" href="../../../css/pers/pbigbtob.css" />
	    <script type="text/javascript" src="../../../js/jquery.min.js" ></script>
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
	    <title>${user.userInfo.nickname }</title>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
		<!--header-->
		<header>
			<div class="kong1"></div>
			<div class="person_pic">				
				<img src="${user.userInfo.headImage }" width="100%">
			</div>
			<i class="allst"></i>
			<div class="person_text">
				<h2><span class="name">${user.userInfo.nickname }</span><span class="zhiwei">${user.userInfo.occupationName }</span></h2>
				<p>居住地<span class="address">${user.userInfo.place }</span></p>
				<p>行业<span class="address">${user.userInfo.tradeName }</span></p>
			</div>					
		</header>
		<div class="kong1"></div>	
		<div class="line"></div>
		<!--jibenziliao-->
		<div class="school">
			<span class="school_">
			<i>本科	</i>		
			<i>${user.userInfo.schoolName}</i>					
			</span>			
			<span class="school_education"><i>专业</i><i>${user.userInfo.majorName }</i></span>			
		</div>
		<div class="school" id="school2">
			<span class="school_">
			<i>硕士</i>
			<i>${user.userInfo.schoolName2}</i>						
			</span>						
			<span class="school_major"><i>专业</i><i>${user.userInfo.majorName2 }</i></span>
		</div>
		<!--gerenjianjie-->
		<div class="kong1"></div>
		<div class="persosn_introduction">
			<span>个人描述:</span>
			<p>${user.userInfo.intro}</p>
			<span>擅长领域:</span>
			<p>${user.userInfo.hob}</p>
			<span>个人资源:</span>
			<p>${user.userInfo.resTxt}</p>
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
            		<span class="title_c vk" id="suib">随笔</span>
            		<span class="sline"></span>
            		<span class="title_c vk" id="changw">长文</span>
         </div>
		<div class="ziliao">
			<div class="article"></div>
				<div class="article_"></div>
				
			</div>
		
		
		
		 </div>
   </div>
   <img src="" width="100%"  id="Bigimg">
     <div class="zhezhao"></div>
	</body>	
		<script type="text/javascript">
		$("#Bigimg").on("mousedown",function(){
				$(this).fadeOut(600)
				$(".zhezhao").fadeOut(600)
			})	
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
			 	location.href = "Chat/Chat.html"
			 })
			 
			
		$(function(){
			
		
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
								'<div class="segu">'+
								'<div class="bigmsg attr-title">'+												
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
										//var bb = aa.replace("")
										if(aa.substr(0,4)){
											var sts = "";
											sts += 									
											'<li class="segu_t"><img src="'+ msg.data[i].imgUrls.split("<split>")[a] +'" width="100%" height="72px"/></li>'
												$(".segu_pic ul").append(sts)
											iscroll.refresh()
											
										}else{
											var sts = "";
											sts += 
											'<li class="segu_t"><img src="${IMG_URL}'+ msg.data[i].imgUrls.split("<split>")[a] +'" width="100%" height="72px"/></li>'
												$(".segu_pic ul").append(sts)
											iscroll.refresh()
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


					
					} else{
						console.log(msg.info);
					}
                }
			
            });
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
							'<h2>'+ msg.data[i].title +'</h2>'+
							'<div class="kong"></div>'+
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
			//访客记录
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
				desc: '大白   ${user.userInfo.nickname }                               ${user.userInfo.companyName }  ${user.userInfo.occupationName }', // 分享描述
				link: 'http://m.upbirds.com/user/toUserCenter.html?userId=' + ${user.userInfo.userId } + "&from=singlemessage&isappinstalled=0", // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: '${user.userInfo.headImage }', // 分享图标
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
		 ajaxEngine.postAjax("/getWeChatSign.html", {timestamp:now, noncestr:nonceStr, url:"http://m.upbirds.com/user/toUserCenter.html?userId=" + ${user.userInfo.userId} + "&from=singlemessage&isappinstalled=0"}, wechatback);
		});
		
		
		</script>
</html>
