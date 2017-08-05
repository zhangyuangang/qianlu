<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>交易数据管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/paydata.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<script>
		//交易数据
		paydata()
		function paydata(){
			$.ajax({
				url:'/admin/order/getPayAmountList.html',
				type:'GET',
				dataType:'json',
				data:{
					curPage:0
				},
				success: function(msg){
					if(msg.code == 1){
						for(var i=0;i<msg.data.length;i++){
							
							var str = ""
							str += 
								'<div class="carousel">'+
									'<p>'+
										'<span class="bj">微信号:<i>'+ msg.data[i].nickname +'</i></span>'+
										'<span class="keep">1V1消费:<i>'+ msg.data[i].payAmount +'</i></span>'+
									'</p>'+
									'<span class="ding">相关订单号:</span>'+
									'<ul>'+
									'<li><i>' + msg.data[i].orderCode + '</i></li>'+
									'</ul>'+
									'<div class="button">'+
										'<span>展开内容</span>'+
									'</div>'+
								'</div>'
								 $("#paydata").append(str)
								
											
						}

						$(".button").on("mousedown",function(){

						if($(this).hasClass("bb")){
							$(this).siblings("ul").fadeOut(100)
						    $(this).find("span").html("展开内容")
						     $(this).removeClass("bb")
						}else{					
						    $(this).siblings("ul").fadeIn(600)
						    $(this).find("span").html("收起内容")			   
						    $(this).addClass("bb")
						}

					})
					}else{
						console.log(msg.info)
					}
				},
				error: function(){

				}
			})
		}
		//收入数据
		addpaydata()
		function addpaydata(){
			$.ajax({
				url:'/admin/order/getProfitAmountList.html',
				type:'GET',
				dataType:'json',
				data:{
					pageSize:1,
					curPage:0
				},
				success: function(msg){
					if(msg.code == 1){
						for(var i=0;i<msg.data.length;i++){
							
							var str = ""
							str += 
								'<div class="carousel">'+
									'<p>'+
										'<span class="bj">微信号:<i>'+ msg.data[i].nickname +'</i></span>'+
										'<span class="keep">1V1消费:<i>'+ msg.data[i].payAmount +'</i></span>'+
									'</p>'+
									'<span class="ding">相关订单号:</span>'+
									'<ul>'+
									'<li><i>' + msg.data[i].orderCode + '</i></li>'+
									'</ul>'+
									'<div class="button buut">'+
										'<span>展开内容</span>'+
									'</div>'+
								'</div>'
								 $("#addpaydata").append(str)
								
											
						}					
						$(".buut").on("mousedown",function(){

						if($(this).hasClass("bb")){
							$(this).siblings("ul").fadeOut(100)
						    $(this).find("span").html("展开内容")
						     $(this).removeClass("bb")
						}else{					
						    $(this).siblings("ul").fadeIn(600)
						    $(this).find("span").html("收起内容")			   
						    $(this).addClass("bb")
						}

					})
					}else{
						console.log(msg.info)
					}
				},
				error: function(){

				}
			})
		}
	</script>
	<body style="background: #f3f5f7;">
		<!--编辑区域-->
		<div class="outside left" id="paydata">
			<b>交易数据:</b>
			
		
		</div>
		
		<div class="outside left" id="addpaydata">
			<b>收入数据:</b>
			
		</div>
		
	</body>
</html>