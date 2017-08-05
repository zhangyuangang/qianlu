<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>${hotDis.title }</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="../../css/jquery.mobile.custom.structure.min.css" />
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.theme.min.css" />
	<link rel="stylesheet" href="../../../css/Upsurge/Ghat.css" />
    <link rel="stylesheet" href="../../css/theme.css" />
	<link rel="stylesheet" href="../../css/Chat/Chat.css" />
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery.mobile.custom.min.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	 <script src="../../../js/ajax-utils.js"></script>
	 <script type="text/javascript">
		define = null;
		require = null;
	</script>
	<style>
	.data b{font-size:1rem;}
	.header{min-height:3rem;height:3rem;}
	.text_ h3{font-size:1rem;}
	.tan{width:40%;height:66px;background:#fff;border-radius:8px;position:fixed;top:43%;left:30%;text-align:center;line-height:66px;display:none;}
	.tan1{width:40%;height:66px;background:#fff;border-radius:8px;position:fixed;top:43%;left:30%;text-align:center;line-height:66px;display:none;}
	</style>
</head>
<body>
	<div data-role="page" id="" data-title="Inbox" data-url="" class="msnBg">
     
	   <div data-role="header" data-position="fixed" data-theme="b" class="header">
        	<div class="header">
            			<div class="tap">
					<div class="tap_">
							<div class="data">
							<b>${hotDis.createTime}</b>						
						</div>
						<div class="text_">
							<h3>${hotDis.title }</h3>						
						</div>
						
					</div>
					
					</div>
            	</div>
        </div>

      <div role="main" class="ui-content msnCls">                           
                <p class="tix"></p>
                 <div class="kong2"></div>
				
          <ul id="msnPlace" class="touch msnPlace" data-role="listview" data-icon="false" >
				
		  
		  </ul>
		  
      </div>
      <!-- /content -->
      <div data-role="footer" data-position="fixed" class="msnFooter">
         <div class="chat">
					<textarea class="rearchs" id="seaa" contenteditable="true" ></textarea>
           			
           			<span class="release">发布</span>
           		</div>
            <footer>
		 		<div class="last as" >	 			
		 			<span class="nav">上篇</span>
		 		</div>
		 		<div class="next as">
		 			<span class="nav">下篇</span>
		 		</div>
 			</footer>
      </div>  
	 
  </div>
   <!-----tankuang----->
	<div class="tan">
		内容不能为空
	</div>
	<div class="tan1">
		没有更多
	</div>
</body>

	<script type="text/javascript">
	function scrollToBottom(){
        setTimeout(function(){$('body').scrollTop( $('body')[0].scrollHeight)},50);
    }
	window.onload = function(){	
			$(".rearchs").css("height","2rem")
			var HotId = ${hotDis.id}
			msglist()
			var Page = 1
			function msglist(Page){
			 $.ajax({
                url:'/hotdis/getMsgList.html',
                type:'GET',
                data:{
					hotDiscussId:HotId,
					order: 0/1
					},
                dataType:'json',
				async:true,
                success:function(msg){
					
                	if(msg.code == "1"){
						var str =  ""
						for(var i = 0; i< msg.data.length; i++){
							
							
							str +=
							
							'<li>'+
								'<div class="portrait_left">'+
									'<img src="'+ msg.data[i].headImage +'" width="100%" height="100%">'+
								'</div>'+
								'<div class="chtext_left">'+
									msg.data[i].content
								+'</div>'+
									'<div class="kong"></div>'+
							'</li>'
							

						}
						$("#msnPlace").prepend(str)
						scrollToBottom();
							
						
					} else{
						console.log(msg.info);
					}
                }
			
            });
			}
			
			
	
		
		
			$(".release").on("mousedown",function(){
				 $("#seaa").focus()
				var Message = $(".rearchs").val()
				$(".rearchs").css("height","2rem")
				if(Message.length<=256 && Message.length!=0){
						//发送	
						$.ajax({
							url:'/hotdis/addMsg.html',
							type:'POST',
							data:{
								hotDiscussId: HotId,
								content: Message
								},
							async:true,
							success:function(msg){	
										
								if(msg.code == "1"){
									$("#msnPlace").empty()									
									msglist()									
									$(".rearchs").val("")
									scrollToBottom();
																
								} else{
									console.log(msg.info);
								}
							},
							error: function(){
								console.log("失败，请重试")
							}
						
							});		
					
				}else{
					$(".tan").fadeIn(200)
						setTimeout(function(){
								$(".tan").fadeOut(400)
						},1000)
				}		
			});
			
			$(".last").on("mousedown",function(){
					HotId = HotId -1
						$.ajax({
							url:'/hotdis/getNextPrev.html',
							type:'GET',
							data:{
								op: -1,
								id: HotId 
								},
							async:true,
							success:function(msg){	
													
								if(msg.code == "1"){
									 $.ajax({
											url:'/hotdis/getList.html',
											type:'GET',
											data:{
												curPage:0
												},
											dataType:'json',
											async:true,
											success:function(msg){
												
												if(msg.code == "1"){
													//正常业务逻辑
													var str = ""
													for(var i=0; i<msg.data.length; i++){
														if(HotId == msg.data[i].id){
															location.href = "/hotdis/getById.html?id="+HotId + "&from=singlemessage&isappinstalled=0"
														}else{
															HotId = HotId -1;
															location.href = "/hotdis/getById.html?id="+HotId + "&from=singlemessage&isappinstalled=0"
														}
															
													}
													
													
												} else{
													console.log(msg.info);
												}
											}
										
										});
									
									
									
								} else{
									$(".tan1").fadeIn(200)
									setTimeout(function(){
											$(".tan1").fadeOut(400)
									},1000)
								}
							},
							error: function(){
								console.log("失败，请重试")
							}
						
						});
			})
			$(".next").on("mousedown",function(){
					HotId = HotId +1
						$.ajax({
							url:'/hotdis/getList.html',
							type:'GET',
							data:{
								curPage:0
								},
							dataType:'json',
							async:true,
							success:function(msg){
								
								if(msg.code == "1"){
									//正常业务逻辑
									for(var i=0; i<msg.data.length; i++){
										if(HotId == msg.data[i].id){
												$.ajax({
														url:'/hotdis/getNextPrev.html',
														type:'GET',
														data:{
															op: 1,
															id: HotId 
															},
														async:true,
														success:function(msg){	
																				
															if(msg.code == "1"){
																// location.href = "/hotdis/getById.html?id="+HotId + "&from=singlemessage&isappinstalled=0"
															} else{
																$(".tan1").fadeIn(200)
																	setTimeout(function(){
																			$(".tan1").fadeOut(400)
																	},1000)
															}
														},
														error: function(){
															console.log("失败，请重试")
														}
													
													});

										}else{
											return
										}
											
									}
									
									
								} else{
									console.log(msg.info);
								}
							}
						
						});
					
					
					
					
						
			})
			}	
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
				title: '${hotDis.title }', // 分享标题
				desc: '${hotDis.content }', // 分享描述
				link: 'http://m.upbirds.com/hotdis/getById.html?id=' + ${hotDis.id } +'&from=singlemessage&isappinstalled=0', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: 'http://m.upbirds.com/img/ql.png', // 分享图标
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
		  ajaxEngine.postAjax("/getWeChatSign.html", {timestamp:now, noncestr:nonceStr, url:"http://m.upbirds.com/hotdis/getById.html?id=" + ${hotDis.id } +"&from=singlemessage&isappinstalled=0"}, wechatback);	
		});
		
		
		</script>
</html>
