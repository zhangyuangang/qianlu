<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>热潮</title>
		<link rel="stylesheet" href="../../css/Reset/reset.css" />
		<link rel="stylesheet" href="../../css/Upsurge/Upsurge.css" />
		<script type="text/javascript" src="../../js/jquery.min.js" ></script>
		<script type="text/javascript" src="../../js/Upsurge/Upsurge.js" ></script>
	</head>
	<body>
		
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
            	<div class="header">
            		<div class="kong"></div>
					<h2>EVERY DAY IS DIFFERENT</h2>
					<h3>箴言</h3>					
					<div class="kong"></div>
					
            	</div>
            	
				<!--tap-->
				<div class="kong1"></div>
				<div class="upbox">
				
				</div>
				
		
			<!--END-->
			<div class="kong"></div>
		  </div>
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
	<script src="../../js/iscroll-min.js"></script>
	<script type="text/javascript">
	
		 document.addEventListener("touchmove",function(e){
						e.preventDefault();
					})	    		    
		    var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
                preventDefault: false
                
			})		
		    $.ajax({
                url:'/hotdis/getList.html',
                type:'GET',
                data:{
                	isPaging:1,
					pageSize:5,
					curPage:0
					},
                dataType:'json',
				async:true,
                success:function(msg){
					
                	if(msg.code == "1"){
						//正常业务逻辑
						var str = ""
						for(var i=0; i<msg.data.length; i++){
							
							str +=
								'<div class="tap" name="'+ msg.data[i].id+'" >'+
									'<div class="tap_">'+
										'<div class="data">'+
											'<b>'+ msg.data[i].createTime +'</b>'+						
										'</div>'+
										'<div class="text_">'+
											'<h3>'+ msg.data[i].title +'</h3>'+
											'<p>'+ msg.data[i].content +'</p>'+
										'</div>'+
										'<div class="number">'+
											'<b>'+ msg.data[i].num +'</b>'+
										'</div>'+
									'</div>'+
									'<div class="kong2"></div>'+
								'</div>'+
								'<div class="kong1"></div>'
								
						}
          				$(".upbox").append(str)
						iscroll.refresh()
						$(".tap").on("mousedown",function(){
								console.log("adas")
								var Id = $(this).attr("name")
								location.href = "/hotdis/getById.html?id="+ Id + "&from=singlemessage&isappinstalled=0"
						})
						
					} else{
						console.log(msg.info);
					}
                }
			
            });
		  
		  
	</script>
</html>
