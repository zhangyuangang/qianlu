<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link rel="stylesheet" href="../css/Reset/reset.css" />
		<script type="text/javascript" src="../js/jquery.min.js" ></script>
		<style type="text/css">
			html,body{height:100%;overflow: hidden;}
			body{background: #f3f5f7;display: -webkit-box;-webkit-box-orient: vertical;height: 100%;}
			.wrapper{-webkit-box-flex: 1;position: relative;}
			.scroll{overflow: hidden;}
			footer{height: 4rem;position: relative;background: #fff;border-top:1px solid #E5E5E5;display: -webkit-box;  display: -moz-box;display: -ms-flexbox;display: -webkit-flex;display: flex;}
			footer p{line-height: 4rem;text-align: center;width: 70%;}
			footer button{width: 30%;background: #FC8F2A;color: #fff;font-size: 1rem;}
			.header{width: 90%;height: 14rem;background: url(../img/kuang.png) no-repeat;margin: 0 auto;background-size: 100% 100%;margin-top: 2rem;overflow: hidden;}
			.header p{color: #4b4b4b;font-size: 1.2rem;margin:1rem 0 0 1rem;}
			.pay{color: #4B4B4B;font-size: 1.2rem;text-align: center;margin-top: 1rem;}
			.pay b{margin-right: 1rem;}
			.select{width:100%;min-height: 20rem;margin: 0 auto;position: relative;overflow:hidden;}
			.select ul{min-height:20rem;}
			.select ul li{width: 10rem;height: 5rem;border:4px solid #DDD;border-radius: 12px;float: left;margin-left: 5%;margin-top: 1rem;}
			.select ul li.sc{border: 4px solid #FC8F2A;}
			.select ul li b{text-align: center;display: block;margin-top: .5rem;}
			.select span{display: block;width: 11rem;height: 5rem;position: absolute;text-align: center;font-size: .8rem;color: #4B4B4B;line-height: 18px;right: 1.2rem;bottom: -0.8rem;}
			.tan{width:40%;height:66px;background:#fff;border-radius:8px;position:absolute;top:43%;left:30%;text-align:center;line-height:66px;display:none;}
			@media only screen and (min-width: 320px) and (max-width: 320px) {
			html,body{font-size:14px;}
			}
		</style>
	</head>
	<body>
	<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
	<form action="/order/choose.html" method="post" id="cd">
				<input type="hidden" name="orderId"  value="${order.id}"/>
					<input type="hidden" name="timeType" id="timeType" value=""/>
					<input type="hidden" name="status" id="status" value=""/>
            	<div class="header">
            		<p><b>${ouser.userInfo.nickname }:</b></p>
            		<p>${order.intro }</p>
            	</div>
            	<!--pay-->
            	<p class="pay"><b>支付时间:${payTime }</b></p>
            	<!--选择时间-->
            	<div class="select">
            		<ul>
            			<li name="1" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>10:00-10:25</b>
            			</li>
						<li name="2" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>10:30-10:55</b>
            			</li>
            			<li name="3" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>19:00-19:25</b>
            			</li>
            			<li name="4" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>19:30-19:55</b>
            			</li>
            			<li name="5" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>20:00-20:25</b>
            			</li>
						<li name="6" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>20:30-20:55</b>
            			</li>
						<li name="7" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>21:00-21:25</b>
            			</li>
						<li name="8" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>21:30-21:55</b>
            			</li>
						<li name="9" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>22:00-22:25</b>
            			</li>
						<li name="10" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>22:30-23:55</b>
            			</li>
						<li name="11" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>23:00-23:25</b>
            			</li>
						<li name="12" data="1" class="gy">
            				<b>交谈时间</b>
            				<b>23:30-23:55</b>
            			</li>
            			<li data="0" name="" class="gy">
            				<b>拒绝交谈</b>
            				<b>改天再约</b>
            			</li>	
            		</ul>
            		<span>
            			点击时间进行选择<br/>
						确认后不能修改-请准时赴约<br/>
						（后续会开放更多时间）
            		</span>
            	</div>
            </div>
          </div>
    <footer>
 		<p>交谈时间当天 <span></span></p>
 		<button onclick="submit()">确定提交</button>
 	</footer>
	</form>
	<!-----tankuang----->
	<div class="tan">
		提交成功
	</div>
 	
	
	</body>
	<script src="../js/iscroll-min.js"></script>
	<script type="text/javascript">
		var select = ${selected}
		var Li = $(".select ul li")
		
			for(var i = 0; i < select.length; i++){
				for(var a = 0; a < Li.length; a++){
				if($(".select ul li").eq(a).attr("name") == select[i]){				
					$(".select ul li").eq(select[i]-1).find("b").eq(1).html("结束或已选")
					$(".select ul li").eq(select[i]-1).removeClass("gy")
				}
			}
		}
		var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false
                
				})
			var Name
			var Data 
		$(".select ul .gy").on("mousedown",function(){
			$(this).addClass("sc").siblings().removeClass("sc")
			 Name = $(this).attr("name")
			 Data = $(this).attr("data")
			 var time = $(this).find("b").eq(1).html()
			 $("footer p span").html(time)
			$("#status").val(Data)
			$("#timeType").val(Name)
		})

		var submit = function(){
			$("#cd").submit()
			return false
		}
		document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    })
	</script>
</html>

