<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>评论审核管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/plshen.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<script>
		//查询热论评论
		function hotp(){
			$.ajax({
					type:"GET",
					url:"/admin/hotdis/getCommentDetilsList.html",
					async:true,
					data:{
						isPaging:1,
						curPage:0,
						hotDiscussId:
					},
					success: function(msg){
						if(msg.code == "1"){
							
						}else{
							console.log(msg.info)
						}
					}
				});
		}
	</script>
	<body style="background: #f3f5f7;overflow: hidden;">
		<!--编辑区域-->
		<div class="carousel">
			<p>
				<span class="bj">2017-6-12</span>
				<span class="keep">展开</span>
			</p>
			<!---->
			<div class="pingl">
				<div class="row">
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
				</div>
			</div>
		</div>
		<!--完成区域-->
		<div class="complete">
			<p>
				<span class="bj">2017-6-13</span>
				<span class="keep">展开</span>
			</p>
			<div class="pingl">
				<div class="row">
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
					<div class="col-md-12">
						<span class="text">这个话题蛮好的～</span>
						<span class="del">删除</span>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="../../../js/jquery.min.js" ></script>
	<script type="text/javascript">
		$(function(){
			$(".keep").on("mousedown",function(){
				if($(this).parent().siblings(".pingl").hasClass("vv")){
					$(this).parent().siblings(".pingl").css("overflow","hidden")
					$(this).parent().siblings(".pingl").removeClass("vv")
					$(this).html("展开")
				}else{					
					$(this).parent().siblings(".pingl").css("overflow","visible")
					$(this).parent().siblings(".pingl").addClass("vv")
					$(this).html("收起")
				}
			})
			
		})
	</script>
</html>