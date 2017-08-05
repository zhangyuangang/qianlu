<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>精选文章</title>
		<link rel="stylesheet" href="../../../css/Reset/reset.css" />
		<link rel="stylesheet" href="../../../css/Article/Article_/Article.css" />
		<script type="text/javascript" src="../../../js/jquery.min.js" ></script>
		<script type="text/javascript" src="../../../js/Article/Article.js" ></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
		 <script src="../../../js/ajax-utils.js"></script>
		 <script type="text/javascript">
			define = null;
			require = null;
		</script>
		<style>
		.plist{width:90%;margin:0 auto;}
		.plist ul li{min-height:4rem;overflow: hidden;margin:.5rem 0;}
		.phead{width: 3rem;
			    height: 3rem;
			    border-radius: 100%;
			    overflow: hidden;
			    margin-left: 1rem;
			    margin-top: .8rem;
			    float: left;}
		.ptxt{width:65%;min-height:5rem;float: left;}
		.pname{width:100%;height:2rem;margin: 1rem 0 0.5rem 1rem;}
		.pname span{display: block;font-family: "微软雅黑";}
		.pname span:nth-of-type(2){font-size:.8rem;color:#b3b3b3;margin-top:.3rem;}
		.txt{width:100%;min-height:3rem;margin: 1rem 0 0 1rem;font-size:.9rem;color:#000;font-family: "微软雅黑";text-align: justify;line-height: 1.5rem;}
		
		.plist ul li a{
				display: inline-block;
			    height: 1.8625rem;
			    float: right;
			    margin-top: 1rem;
			    margin-right: 2%;
			    font-size: .95rem;
			    text-align: center;
			    color: #027CFF;}
		.line{    
			display: block;
		    width: 90%;
		    height: 1px;
		    background: #e5e5e5;
		    margin: 0 5%;
		}
		.txt p span:nth-of-type(1){color:#027CFF;margin:0 .3rem;}	  
		#text{width:70%;float:left;line-height: 2rem;text-indent: 8%;height:2rem;margin-top:.7rem;border:1px solid #f3f5f7;} 		
		.butn{width: 20%;float:left;background: #FD9A3F;text-align: center;height:2rem;margin-top: .6rem;line-height: 2rem;color:#fff;margin-left:1rem;border-radius:8px;}
		#text1{width:70%;float:left;line-height: 2rem;text-indent: 8%;height:2rem;margin-top:.7rem;border:1px solid #f3f5f7;margin-left:.5rem;font-family: "微软雅黑";font-size:.8rem;} 		
		.butnn{width: 20%;float:left;background: #FD9A3F;text-align: center;height:2rem;margin-top: .6rem;line-height: 2rem;color:#fff;margin-left:1rem;border-radius:8px;}
		.Gianr{width:100%;height:3.5rem;margin:0 auto;}	
		.htan{width:80%;height:3.2rem;border-radius:8px;background:#fff;position:fixed;top:64%;display:none;left:10%;z-index:11;}
		.zhezha{width:100%;height:1000px;background:#333;opacity:0.8;position:absolute;top:0;display:none;}
	</style>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
            	<!--11-->
            	
            	<div class="kong"></div>
            	<!--wenzhang-->
            	<div class="contet">
            		<h2>${article.title }</h2>
            		<!-- ${article.title}为文章内容，为富文本编辑框得html -->
            		<div class="kong"></div>
            		${article.content }
            	</div>
				<p style="width:90%;margin:10px auto;">参与评论</p>
				<div class="line"></div>
				<div class="plist">
				<div class="Gianr">
					<input type="text" name="text" id="text" placeholder="发表评论…" />
					<div class="butn">
						发表
					</div>
				</div>
						<ul>
							
							
						</ul>
					</div>
					<div class="kong"></div>
					<div class="kong"></div>
					<div class="kong"></div>
					<div class="kong"></div>
					<div class="kong"></div>
           </div>
          </div>
          <!--tankuang-->
          <div class="tan">
          	<div class="bigtxt">
					<h3><span class="name">${article.nickname }</span><span class="dian"></span><span class="zhiwei">${article.occupationName }</span></h3>
					<p>
						<span>${article.companyName }</span>
					</p>
				</div>    
				<div class="questions">
					向作者提问
				</div>
          </div>
          <!--footer-->
		<footer>
			<div class="back af">
				<img src="../../../img/back.png" width="29%" >
			</div>
			<div class="af zan">
			<i></i>
			<i>${article.starTimes }</i>
			</div>
			<div class="af ping">
			
			<i></i>
			<i>${article.commentTimes}</i>
			</div>
			<div class="perosonol">
				<div class="perimg">
					<img src="${article.headImage }" width="100%" > 
				</div>				
				<h2 class="nam">${article.nickname }</h2>
				
				<!--zhezhaoceng-->
				<div class="zhezhao zh" id="zh">
					
				</div>
		
			</div>
			<div class="af fenx">
			</div>	
		</footer>
		<!-----回复---->
		<div class="htan">
			
		</div>
		<div class="zhezha"></div>
	</body>
	
	<script src="../../../js/iscroll-min.js"></script>
	<script type="text/javascript">
		
		window.onload = function(){
			$(".ping").on("mousedown",function(){
					var height  = $(".scroll").height()-$(".wrapper").height()
				iscroll.scrollTo(0,-height,600)		
			})
			$(".zhezha").on("mousedown",function(){
				$(".htan").fadeOut(600)
				$(".zhezha").fadeOut(600)
			})
		}

		
			if(localStorage.getItem("key") == "true"){
					$(".zan i:nth-of-type(1)").css({"background":"url('../../../img/zanx@2x.png') no-repeat","background-size":"100% 100%"})
				}else{
					$(".zan i:nth-of-type(1)").css({"background":"url('../../../img/zanw@2x.png') no-repeat","background-size":"100% 100%"})
				}
				$(".zan").on("mousedown",function(){
					var startime = $(this).find("i").eq(1).html()
							$.ajax({
								url:'/article/hitStarById.html',
								type:'POST',
								data:{
									articleId:${article.id }
								},
								success:function(msg){
									if(msg.code == 1){
										if(localStorage.getItem("key") == "true"){
											startime--
											$(".zan i:nth-of-type(1)").css({"background":"url('../../../img/zanw@2x.png') no-repeat","background-size":"100% 100%"})
											$(".zan i:nth-of-type(2)").html(startime)
											
											localStorage.setItem("key","false");
										}else{
											startime++
											$(".zan i:nth-of-type(1)").css({"background":"url('../../../img/zanx@2x.png') no-repeat","background-size":"100% 100%"})
											$(".zan i:nth-of-type(2)").html(startime)
											
											localStorage.setItem("key","true");
										}
										
									}
								}
							})
						})
	
		document.addEventListener("touchmove",function(e){
						e.preventDefault();
				})
		     var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
                preventDefault: false,
                scrollbars: 'custom'
                
			})
		    var myScroll;
			function loaded () {
			    myScroll = new IScroll('#wrapper', { scrollX: true, scrollbars: 'custom' });
			}  
			   $(".back").on("mouseover",function(){
					location.href = "../home.html"
				})
				$(".questions").on("mousedown",function(){
					location.href = "../user/toUserCenter.html?userId=" + ${article.userId }
				})				

		adping()
	function adping(){

		$.ajax({
			url:'/article/loadingMoreComment.html',
				type:'GET',
				data:{
					isPaging:1,
					pageSize:5,
					curPage:1,
					articleId:${article.id }
				},
				success:function(msg){
					if(msg.code == 1){
						var str = ""
						var sts = ""
						for(var i = 0; i < msg.data.length; i++){
							str += '<li>'+
										'<div class="phead">'+
											'<img src="'+ msg.data[i].headImage +'" width="100%">'+
										'</div>'+
										'<div class="ptxt">'+
											'<div class="pname">'+
												'<span>'+ msg.data[i].nickname +'</span>'+
												'<span>'+ msg.data[i].updateTime.substr(6,1) +'月'+ msg.data[i].updateTime.substr(8,2) +'号</span>'+
											'</div>'+
											'<div class="txt">'+
												'<p>'+ msg.data[i].content+'</p>'+							
											'</div>'+
										'</div>'+
										'<a href="##" name="'+ msg.data[i].id +'">回复</a>'+	
									'</li>'+
									'<span class="line"></span>'
								if(msg.data[i].tnickname != ""){	
								sts += '<li>'+
										'<div class="phead">'+
									'<img src="'+ msg.data[i].headImage +'" width="100%">'+
										'</div>'+
										'<div class="ptxt">'+
											'<div class="pname">'+
												'<span>'+ msg.data[i].nickname +'</span>'+
								 				'<span>'+ msg.data[i].updateTime.substr(6,1) +'月'+ msg.data[i].updateTime.substr(8,2) +'号</span>'+
											'</div>'+
											'<div class="txt">'+
												'<p>回复<span>@'+ msg.data[i].tnickname +':</span><span>'+ msg.data[i].content +'</span></p>'+							
											'</div>'+
										'</div>'+										
									'</li>'+
									'<span class="line"></span>'
								} 
							
						}
						$(".plist ul").append(str)						
						$(".plist ul").append(sts)
						iscroll.refresh()
						$(".plist ul li a").on("mousedown",function(){						
							var sts = ""
							sts +=
									'<input type="text" name="text" id="text1" placeholder="回复:@'+$(this).siblings(".ptxt").find(".pname span").eq(0).html()+'" />'+
									'<div class="butnn" name="'+ $(this).attr("name") +'">'+
										'回复'+
									'</div>'
							$(".htan").html(sts)
							$(".zhezha").fadeIn(600)
							$(".htan").fadeIn(600)
						})
						//回复评论
						$(".butnn").on("mousedown",function(){
							var Id = $(this).attr("name")
							var content = $("#text1").val()
							$.ajax({
								url:'/article/comment.html',
								type:'POST',
								data:{
									tuserId:Id,
									articleId:${article.id },
									content:content
								},
								success:function(msg){
									$(".htan").fadeOut(600)
									$(".zhezha").fadeOut(600)
								}
							})
						})
					}
				}
		})
		}
		//评论长文
		$(".butn").on("mousedown",function(){
			$("")
			var content = $("#text").val()
			$.ajax({
				url:'/article/comment.html',
				type:'POST',
				data:{
					articleId:${article.id },
					content:content
				},
				success:function(msg){
					$(".plist ul").empty()
					adping()
					$("#text").val("")
					iscroll.refresh()
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
            'onMenuShareAppMessage',
			'onMenuShareQQ',
			'onMenuShareWeibo',
			'onMenuShareQZone'
			] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});		
			//分享朋友
			wx.onMenuShareAppMessage({
				title: '${article.title }', // 分享标题
				desc: '${article.intro }', // 分享描述
				link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: '${article.headImg }', // 分享图标
				type: 'link', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () { 
					// 用户确认分享后执行的回调函数
					$.ajax({
						url:'../user/shareWX.html',
						type:'POST',
						data:{
							toId: ${article.id},
							type:'article'
						}
					})
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
					console.log("失败")
				}
			});
			//分享朋友圈
			wx.onMenuShareTimeline({
				title: '${article.title }', // 分享标题
				link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: '${article.headImg }', // 分享图标
				success: function () { 
					// 用户确认分享后执行的回调函数
					$.ajax({
						url:'../user/shareWX.html',
						type:'POST',
						data:{
							toId: ${article.id},
							type:'article'
						}
					})
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
					console.log("失败")
				}
			});
			//分享qq
			wx.onMenuShareQQ({
				title: '${article.title }', // 分享标题
				desc: '${article.intro }', // 分享描述
				link: window.location.href, // 分享链接
				imgUrl: '${article.headImg }', // 分享图标
				success: function () { 
				   // 用户确认分享后执行的回调函数
				  $.ajax({
						url:'../user/shareWX.html',
						type:'POST',
						data:{
							toId: ${article.id},
							type:'article'
						}
					})
				},
				cancel: function () { 
				   // 用户取消分享后执行的回调函数
				   console.log("失败")
				}
			});
			//分享空间
			wx.onMenuShareQZone({
				title: '${article.title }', // 分享标题
				desc: '${article.intro }', // 分享描述
				link: window.location.href, // 分享链接
				imgUrl: '${article.headImg }', // 分享图标
				success: function () { 
				   // 用户确认分享后执行的回调函数
				   $.ajax({
						url:'../user/shareWX.html',
						type:'POST',
						data:{
							toId: ${article.id},
							type:'article'
						}
					})
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
