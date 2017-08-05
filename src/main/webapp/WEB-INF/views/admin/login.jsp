<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
		<style type="text/css">
			*{margin: 0;padding: 0;}
			body{color:#595959;}
			.login{width: 469px;height: 279px;border:  5px solid #FE8413;border-radius: 36px;background: #fff;margin: 10% auto;position: relative;}
			.login img{margin-top: 12%;float: left;}
			.login_{float:left;margin-top: 20%;width: 60%;margin-left: 5%;}
			.login_ p{color: #595959;font-family: "微软雅黑";font-size: 16px;}
			.login_ input{border-radius: 36px; border: 2px solid #979797;outline: none;text-indent: 10px;}
			.login_ p:nth-of-type(1) input{margin-left: 4%;}
			.login_ p:nth-of-type(2) input{margin-left: 10%;}
			.login_confirm{position: absolute;bottom: 10%;left: 35%;}
			.login_cancel{position: absolute;bottom: 10%;left: 65%;}
			.login_confirm input:hover{border: 2px solid #DD3333;}
			.login_cancel input:hover{border: 2px solid #DD3333;}
			.login_confirm input,.login_cancel input{background: none;outline: none;border: none;width: 96px;height: 33px;border: 2px solid #979797;border-radius: 36px;text-align: center;line-height: 29px;cursor: pointer;}
			.tan{width:27%;height:66px;background:#ccc;border-radius:8px;position:absolute;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
			.tan1{width:27%;height:66px;background:#ccc;border-radius:8px;position:absolute;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
		</style>
	</head>
	<body style="background: #f3f5f7;overflow: hidden;">
		<!--弹框-->
			<div class="tan">
				登录成功
			</div>
			<div class="tan1">
				登录失败
			</div>
		<div class="login">
			<img src="../../../img/ql.png" width="30%">
			<div class="login_">
			<p>用户名：<input type="text"  id="name" value=""/></p>
			<p>密码：<input type="password" id="password" value=""/></p>
			</div>
			<div class="login_confirm">
				<input type="submit" name="" id="" value="登录" onclick="login()" />
			</div>
		</div>
	</body>
	<script>


	function login(){	
	var name = $("#name").val()
	var Password = $("#password").val()
		$.ajax({
			url:'/admin/login.html',
			type:'POST',
			data:{
				username:name,
				password:Password
			},
			success:function(msg){
				if(msg.code == "1"){
					$(".tan").fadeIn(200)
					setTimeout(function(){
							$(".tan").fadeOut(400)
					},1000)
					location.href = "index.html"	
				}else{
					$(".tan1").fadeIn(200)
					setTimeout(function(){
							$(".tan1").fadeOut(400)
					},1000)
				}
				
			}
			
		})
	}
	</script>
</html>