<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>	
	    <link rel="stylesheet" href="../../../css/Reset/reset.css" />
	    <link rel="stylesheet" href="../../../css/pers/psmallb.css" />
	    <script type="text/javascript" src="../../../js/jquery.min.js" ></script>
		<script type="text/javascript"src="../../../js/iscroll-min.js"></script>
	    <title>${user.userInfo.nickname}</title>
		<style>
		#school2{display:none;}
		</style>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
		<!--header-->
		<header>
			<div class="modify"></div>
			<div class="kong1"></div>		
			<div class="person_pic">				
				<img src="${user.userInfo.headImage }" width="100%">
			</div>
			
			<div class="person_text">
				<h2><span class="name">${user.userInfo.nickname }</span></h2>
				<p>公司<span class="address">${user.userInfo.companyName }</span></p>
				<p>居之地<span class="address">${user.userInfo.place }</span></p>
			</div>					
		</header>
		<div class="kong1"></div>	
		<div class="line"></div>
		<!--jibenziliao-->
		<div class="school">
			<span class="school_">
			<i>本科	</i>		
			<i>${user.userInfo.schoolName}</i>					
			</span>			
			<span class="school_education"><i>专业</i><i>${user.userInfo.majorName }</i></span>			
		</div>
		<div class="school" id="school2">
			<span class="school_">
			<i>硕士</i>
			<i>${user.userInfo.schoolName2}</i>						
			</span>						
			<span class="school_major"><i>专业</i><i>${user.userInfo.majorName2 }</i></span>
		</div>
		<!--gerenjianjie-->
		<div class="kong1"></div>
		<div class="persosn_introduction">
			<span>个人描述:</span>
			<p>${user.userInfo.intro}</p>
		</div>
		<div class="persosn_introduction">
			<span>兴趣爱好:</span>
			<p>${user.userInfo.hob}</p>
		</div>
		<div class="kong"></div>
		<div class="line"></div>
		<!--fangkejilu-->
		<div class="kong"></div>	
		<div class="visitor">
			<span>访客记录</span>
			<div class="visitor_pic">
				
			</div>
		</div>
		
		<div class="kong"></div>	
		<div class="line"></div>
		<div class="kong"></div>
		<div class="kong"></div>
		<div class="kong"></div>
		<div class="footer">
			<p>如果您想开启个人文章模块 请联系小助手进行投稿</br>您后续购买的他人付费文章在购买后会自动出现付费版块</br>感谢您对平台的支持和认可</p>
		</div>
		
		 </div>
   </div>
   <!--footer-->
		
		<footer>
 		<div class="home as" data-ripple>
 		<a href="/home.html?from=singlemessage&isappinstalled=0">
 			<span class="img"></span>
 			<span class="nav">前路</span>
 			</a>
 		</div>
 		<div class="chat as" data-ripple>
 			<a href="/user/toChatList.html?from=singlemessage&isappinstalled=0">
	 			<span class="img"></span>
	 			<span class="nav">交谈</span>
 			</a>
 		</div>
 		<div class="person as" data-ripple>
 			<a href="/user/toUserCenter.html?from=singlemessage&isappinstalled=0">
 				<span class="img"></span>
 				<span class="nav">主页</span>
 			</a>
 		</div>
 	</footer>
	</body>
	
		<script type="text/javascript">
			if($("#school2 .school_ i").eq(1).html() != ''){
				$("#school2").css("display","flex")
			}
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false

			})
			 document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    			})
			 $(".modify").on("mousedown",function(){
			 	location.href = "/user/toUserInfo.html?userId="+  ${user.userInfo.userId}
			 })
			 $.ajax({
				 url:'/user/getUserList.html',
				 type:'GET',
				 data:{
					 isPaging:1,
					pageSize:5,
					curPage:0,
					cuserId: ${user.userInfo.userId },//当前访问个人中心的userId，用来获取【访客记录】同时userType传-1
					userType:-1
				 },
				 success: function(msg){
					 var str = ""
					 for(var i = 0; i < msg.data.length; i++){
						  str += 
						'<div class="pic_fang" style="margin-left: -.5rem;" name="'+ msg.data[i].userId +'">'+
							'<img src="'+ msg.data[i].headImage +'" width="100%"  />'+
						'</div>'
						
					 }
					$(".visitor_pic").append(str)
					$(".pic_fang").on("mousedown",function(){
						var userId = $(this).attr("name")							
						location.href = "/user/toUserCenter.html?userId=" + userId
					})
				 }
			 })
		</script>
</html>











	