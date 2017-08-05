<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="${static_resource_h5}css/common.css">
<title>微信安全支付</title>
<script type="text/javascript" src="../../js/jquery.min.js" ></script>
<script type="text/javascript" src="../../js/ajax-utils.js" ></script>
<script>
var type = '${type}';
	function jsApiCall() {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			'appId' : '${rslt.appid}',
			'timeStamp' : '${rslt.timeStamp}',
			'nonceStr' : '${rslt.nonce_str}',
			'package' : 'prepay_id=${rslt.prepay_id}',
			'signType' : 'MD5',
			'paySign' : '${rslt.sign}',
		}, function(res) {
			WeixinJSBridge.log(res.err_msg);
			if(res.err_msg == "get_brand_wcpay_request:ok"){
				ajaxEngine.postAjax("/order/synCallBack.html", {orderId:${orderId}}, function(){
					if(type == "XIADAN"){
						window.location.href = "/user/toUserCenter.html?userId=${tuserId}&payStatus=1&msg=支付成功";
					} else {
						window.location.href = "/chat.html?selfId=${BIRDS_USER.id}&tId=${tuserId}&id=${msgId}&xudanId=${orderId}";							
					}
				});
			} else {
				//alert("支付取消或失败");
				if(type == "XIADAN"){
					window.location.href = "/user/toUserCenter.html?userId=${tuserId}&msg=支付取消&payStatus=-1";
				} else {
					alert("续单失败，原因:支付失败或者取消");
					window.location.href = "/chat.html?selfId=${BIRDS_USER.id}&tId=${tuserId}&id=${msgId}&xudanId=-1";							
				}
			}
		});
	};
	function callpay() {
		if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', jsApiCall,
						false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', jsApiCall);
				document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
			}
		} else {
			jsApiCall();
		}
	};
</script>

<body>
	
</body>
<script>
callpay();
</script>
</html>