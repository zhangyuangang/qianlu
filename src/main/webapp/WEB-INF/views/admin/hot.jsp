<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>热论管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/hot.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<style>
			.tan{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
			.tan1{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
	</style>
	<script>
		//箴言编辑
		function zhengyan(){
			var Val = $("#val").val()
			$.ajax({
					type:"POST",
					url:"/admin/keyvalue/addProverbs.html",
					async:true,
					data:{
						value :Val
					},
					success: function(msg){
						if(msg.code == "1"){
							$(".tan").fadeIn(200)
							setTimeout(function(){
									$(".tan").fadeOut(400)
							},1000)
						}else{
							$(".tan1").fadeIn(200)
							setTimeout(function(){
									$(".tan1").fadeOut(400)
							},1000)
						}
					}
				});
		}
		//新增热论
		function hot(){
			var Val = $("#content").val()
			var Title = $("#title").val()
			$.ajax({
					type:"POST",
					url:"/admin/hotdis/addHotdis.html",
					async:true,
					data:{
						title: Title,
						content: Val
					},
					success: function(msg){
						if(msg.code == "1"){
							$(".tan").fadeIn(200)
							setTimeout(function(){
									$(".tan").fadeOut(400)
							},1000)
						}else{
							$(".tan1").fadeIn(200)
							setTimeout(function(){
									$(".tan1").fadeOut(400)
							},1000)
						}
					}
				});
		}
		//热论列表
		hotlist()
		function hotlist(){
			$.ajax({
					type:"GET",
					url:"/admin/hotdis/loadingMore.html",
					async:true,
					data:{
						curPage:0
					},
					dataType:'json',
					success: function(msg){
						if(msg.code == "1"){
							for(var i = 0; i < msg.data.length; i++){
								var str = ""
								str += 
								 '<div class="complete">'+
										'<p>'+
											'<span class="bj">热论主要内容</span>'+
										'</p>'+
										'<div class="title">'+
											'<span class="leading">主标题</span>'+
											'<input type="text" name="title" id="title" value="'+ msg.data[i].title +'" />'+
										'</div>'+
										'<div class="title_">'+
											'<span class="leading">内容</span>'+
												'<input type="text" name="content" id="content" value="'+ msg.data[i].content +'" />'+
										'</div>'+
										'<div class="title_">'+
											'<span class="leading">热论Id</span>'+
												'<input type="text" name="content" id="content" value="'+ msg.data[i].id +'" />'+
										'</div>'+
									'</div>'
									$("body").append(str)
							}
							
						}else{
							console.log(msg.info)
						}
					}
				});
		}
		//删除热论
		function delhot(){
			var Id = $("#id").val()
			$.ajax({
					type:"POST",
					url:"/admin/hotdis/deleteById.html",
					async:true,
					data:{
						id:Id
					},
					success: function(msg){
						if(msg.code == "1"){
							$(".tan").fadeIn(200)
							setTimeout(function(){
									$(".tan").fadeOut(400)
							},1000)
						}else{
							$(".tan1").fadeIn(200)
							setTimeout(function(){
									$(".tan1").fadeOut(400)
							},1000)
						}
					}
				});
		}
	</script>
	<body style="background: #f3f5f7;">
		<!--弹框-->
			<div class="tan">
				修改成功
			</div>
			<div class="tan1">
				修改失败
			</div>
		<!--编辑区域-->
		<div class="carousel">
			<p>
				<span class="bj">箴言编辑</span>
				<span class="keep" onclick="zhengyan()">新增</span>
			</p>
			<!--search-->
          <div class="form-group">
            <div class="input-group">
            	<span class="name">导入文字</span>
              <input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="val">
            </div>
          </div>
		</div>
		<!--热论区域-->
		<div class="complete">
			<p>
				<span class="bj">热论主要内容</span>
				<span class="keep" onclick="hot()">新增</span>
			</p>
			<!---->			
			<div class="title">
				<span class="leading">主标题</span>
				<input type="text" name="title" id="title" value="" />
			</div>
			<div class="title_">
				<span class="leading">内容</span>
				<input type="text" name="content" id="content" value="" />
			</div>
		</div>
		<!--热论完成-->
		<div class="complete">
			<p>
				<span class="bj">热论主要内容</span>
				<span class="keep" onclick="delhot()">删除</span>
			</p>
			<!---->
			<div class="data">
				<span class="leading">热论id</span>
				<input type="text" id="id" value="" />
			</div>			
		</div>
	</body>
</html>
