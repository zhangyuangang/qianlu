<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
	<meta type="description" content="这里有你想要的答案"> 
    <link rel="stylesheet" href="../../css/Pluglib/swiper.min.css" />
    <link rel="stylesheet" href="../../css/Reset/reset.css" />
    <link rel="stylesheet" href="../../css/Home/home.css" />
    <script type="text/javascript" src="../../js/jquery.min.js" ></script>
    <script type="text/javascript" src="../../js/Home/home.js" ></script>
    <script src="../../js/iscroll-min.js"></script>
	<script type="text/javascript" src="../../js/swiper-3.3.1.min.js" ></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script src="../../js/ajax-utils.js"></script>
	<script type="text/javascript">
        define = null;
        require = null;
    </script>
	<style>
	</style>
    <title>前路</title>
</head>
<body>
	<div class="wrapper" id="wrapper">
		<div class="scroll" id="scrollbar">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide a"></div>
					<div class="swiper-slide b"></div>
					<div class="swiper-slide c"></div>
				</div>
				<div class="swiper-pagination"></div>
			</div>
			<!--toutiao-->
			<div class="hottou">
				<div class="hotleft">
				</div>
				<div class="hotline"></div>
				<div class="hotright">
					<div class="headimg">
						<img src="../../img/lu.png" width="100%" />
					</div>
					<div class="hotxt">
						<p><b>@小助手(微信：qianluxzs)</b></p>
						<p>24h活人陪聊、领30¥代金券</p>
					</div>
				</div>
			</div>
			<div class="zhezhao"></div>
			<img src="../../img/erw.jpg" width="200px" height="279px" style="display:none;position:absolute;z-index:11;left:25%;top:10%;" class="erwm" />
			<!--remen-->
			<div class="kong"></div>
			<div class="middle">
				<div class="hotx">
					<h2><span class="salej">大白</span><a href="/user/toUserList.html">更多</a></h2>
					<div class="kong"></div>
					<span class="line"></span>
					<ul>
					</ul>
				</div>
			</div>
			<!--knowleg-->
			<div class="kong1"></div>
			<div class="title">
				<span class="title_c">随笔</span>
				<span class="title_c vk">长文</span>
			</div>
			<div class="kong1"></div>
			<!--长文精选-->
			<div class="article cw">
			</div>
			<!--精选广播-->
			<div class="article_ gb">
			</div>
			<div class="kong1"></div>
			<div class="footerline">
				<p>更多，请下拉拉拉！</p>
			</div>
		</div>
	</div>
	<!--kefu-->
	<div class="kefu" name="52">
		<img src="../../img/lu.png" width="100%" height="100%">
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
<script>
$(document).ready(function() {
	
	$(".kefu").on("mousedown", function() {
		var Title = $(this).attr("name")
		location.href = "/chat.html?selfId=${BIRDS_USER.id}&tId=" + Title + "&id=" + 69
	})	
	var now = new Date().getTime();
	var nonceStr = "ZhuBaoShi";
	var signature = "";
	var wechatback = function(rslt) {
		signature = rslt.data;
		//console.log(signature)
		wx.config({
			debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId: 'wx8858c54271aadf64', // 必填，公众号的唯一标识
			timestamp: now, // 必填，生成签名的时间戳
			nonceStr: nonceStr, // 必填，生成签名的随机串
			signature: signature, // 必填，签名，见附录1
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
			desc: '这里有你想1对1交谈的人', // 分享描述
			link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl: 'http://m.upbirds.com/img/ql.png', // 分享图标
			type: 'link', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function() {
				// 用户确认分享后执行的回调函数
				$.ajax({
					url: 'user/shareWX.html',
					type: 'POST',
					data: {
						type: 'index'
					}
				})
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
				console.log("失败")
			}
		});
	}
	ajaxEngine.postAjax("/getWeChatSign.html", { timestamp: now, noncestr: nonceStr, url: window.location.href }, wechatback);
});
</script>
</html>