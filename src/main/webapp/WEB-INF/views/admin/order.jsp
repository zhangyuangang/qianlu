<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/order.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<script>
		//订单搜索
		order()
		function order(){
			var OrderCode = $("#order").val()
			$.ajax({				
					url:"/admin/order/loadingMore.html",
					type:"GET",
					async:true,
					data:{
						curPage:0,
						orderCode: OrderCode
					},
					success: function(msg){
						if(msg.code == "1"){
							for(var i = 0; i < msg.data.length; i++){
								var Time = msg.data[i].timeType
								var Times = msg.data[i].payTime
								
								var time = ""
								if(Time == 1){
									time = '10:00-10:25'
								}else if(Time == 2){
									time = '10:30-10:55'
								}else if(Time == 3){
									time = '19:00 - 19:25'
								}else if(Time == 4){
									time = '19:30 - 19:55'
								}else if(Time == 5){
									time = '20:00 - 20:25'
								}else if(Time == 6){
									time = '20:30 - 20:55'
								}else if(Time == 7){
									time = '21:00 - 21:25'
								}else if(Time == 8){
									time = '21:30 - 21:55'
								}else if(Time == 9){
									time = '22:00 - 22:25'
								}else if(Time == 10){
									time = '22:30 - 22:55'
								}else if(Time == 11){
									time = '23:00 - 23:25'
								}else if(Time == 12){
									time = '23:30 - 23:55'
								}
								var str = ""
								str +=
									'<div class="carousel">'+
										'<p>'+
											'<span class="bj">订单号:<i>'+ msg.data[i].orderCode +'</i></span>'+
											'<span class="keep">售后</span>'+
										'</p>'+
										'<div class="row">'+
										'<div class="col-md-6">'+
											'<span class="dbname">小白<i>'+ msg.data[i].nickname +'</i>向大白<i>'+ msg.data[i].tnickname +'</i></span>'+			  	
										'</div>'+
										'<div class="col-md-6">'+
											'<span class="dbname">约定时间:<i>'+ Times.substr(0,10) +'</i><i>'+ time +'</i></span>'+
										'</div>'+
										'<div class="col-md-12">'+
											'<span class="dbname" style="margin-left: 5%;">问题内容:<i>'+ msg.data[i].intro +'</i></span>'+
										'</div>'+								
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
		//sousuo
		function msgg(){
			var UserId = $("#order").val()
			$.ajax({
				url:'/admin/order/searchByStart.html',
				type:'GET',
				dataType:'json',
				data:{
					orderCode:UserId//用户输入
				},
				success: function(msg){
					if(msg.code == 1){
						for(var i=0;i<msg.data.length;i++){
							
							var str = ""
							str += '<li>'+ msg.data[i].orderCode +'</li>'	
							$("#searchresult").html(str)					
						}
						
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
			<!--search-->
          <div class="form-group">
            <div class="input-group">
            	<span class="name">订单号搜索</span>
              <input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="order" onkeyup="msgg()">
				<span class="revise" onclick = "order()"></span>
            </div>
            </div>
		<!--编辑区域-->
		
		<!--热论区域-->
		
	</body>
	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript">
		$(function(){
			$(".button").on("mousedown",function(){
				if($(this).hasClass("bb")){
					$(this).parent().siblings(".container").fadeOut(100)
				    $(this).find("span").html("详细内容")
				    $(this).removeClass("bb")
				}else{					
				    $(this).parent().siblings(".container").fadeIn(600)
				    $(this).find("span").html("收起内容")			   
				    $(this).addClass("bb")
				}
			
			})
		})
	</script>
</html>
