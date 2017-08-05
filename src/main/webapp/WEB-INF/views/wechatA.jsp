<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>${selfName }</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="../../css/jquery.mobile.custom.structure.min.css" />
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.theme.min.css" />
    <link rel="stylesheet" href="../../css/theme.css" />
    <script>
	var uri = "http://m.upbirds.com/wechatRedirectCenter.html?" + "pre=" + encodeURIComponent('${uri}');
	window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8858c54271aadf64&redirect_uri=" + uri + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    </script>
</head>
<body>
</body>
</html>