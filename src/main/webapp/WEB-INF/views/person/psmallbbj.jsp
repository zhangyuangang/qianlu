<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>	
	    <link rel="stylesheet" href="../../../css/Reset/reset.css" />
	    <link rel="stylesheet" href="../../../css/pers/psmallbbj.css" />
	    <script type="text/javascript" src="../../../js/jquery.min.js" ></script>
	    <script type="text/javascript" src="../../../js/iscroll-min.js"></script>
	    <title>${BIRDS_USER.userInfo.nickname }</title>
		<style>
		.tan{width:40%;height:66px;background:#fff;border-radius:8px;position:absolute;top:43%;left:30%;text-align:center;line-height:66px;display:none;}
		</style>
	</head>
	<body>
		<div class="wrapper" id="wrapper">
            <div class="scroll" id="scrollbar">
				<!--header-->
				<div class="header">
					<div class="kong1"></div>
					<h2>编辑资料</h2>
					<div class="kong1"></div>
				</div>
				<!--container-->
				<div class="container">
					<ul>
						<li>
							<div class="bj_left" id="img">
								<span>头像</span>
							</div>
							<div class="bj_right" id="img_">
								<span>
									<img alt="" id="headImage" src="${BIRDS_USER.userInfo.headImage }" width="100%">
								</span>
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>昵称</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="nickname" value="${BIRDS_USER.userInfo.nickname }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>公司</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" class="companyName" value="${BIRDS_USER.userInfo.companyName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>职位</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="workStatus" value="${BIRDS_USER.userInfo.workStatus }" />
							</div>
						</li>
						<div class="kong1"></div>
						<li>
							<div class="bj_left">
								<span>居住地</span>
							</div>
							<div class="bj_right">							
								<input type="text" name="" id="place" value="${BIRDS_USER.userInfo.place }" />
							</div>
						</li>
						<div class="schoolm">
							<div class="kong1"></div>
							<h3>学校信息</h3>
							<div class="kong1"></div>
						</div>
						<li>
							<div class="bj_left">
								<span>本科:</span>
								
							</div>
							<div class="bj_right">
								<input type="text" name="" id="schoolName" value="${BIRDS_USER.userInfo.schoolName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>所学专业:</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="majorName" value="${BIRDS_USER.userInfo.majorName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>研究生:</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="schoolName2" value="${BIRDS_USER.userInfo.schoolName2 }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>所学专业:</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="majorName2" value="${BIRDS_USER.userInfo.majorName2 }" />
							</div>
						</li>
						<div class="personm">
							<div class="kong1"></div>
							<h3>个人描述</h3>
							<div class="kong1"></div>
						</div>
						<div class="personm_  perin">
							<p>（你擅长的回答／或独特的经历）（260字之内）</p>
							<textarea  placeholder="" rows="4" id="intro" >${BIRDS_USER.userInfo.intro }</textarea>
						</div>
						<div class="personm">
							<div class="kong1"></div>
							<h3>兴趣爱好</h3>
							<div class="kong1"></div>
						</div>
						<div class="personm_ perhob">
							<p>（自己擅长和喜欢的事情）（260字之内）</p>
							<textarea  placeholder="" rows="4" id="hob" >${BIRDS_USER.userInfo.hob }</textarea>
						</div>							
					</ul>
					<div class="kong"></div>
					<div class="button">
						确认修改
					</div>
					<div class="kong"></div>
					<div class="footer">
						<p>个人资料的完善有助于别人对您的了解 请您尽量填写自己的</p>
						<p>真实信息 对于请教的朋友 请给予更耐心的对待</p>
						<p>每个人的相遇都是一种缘分 感谢您对平台的支持～</p>
					</div>
					<div class="kong2"></div>
				</div>
		 </div>
   </div>
   <!---------->
   <div class="tan">
	啊哦！ 长度过长
   </div>
	</body>
		<script type="text/javascript">
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false
			})
			 document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    			})
			 $(".personm_ textarea").on("mousedown",function(){
			 	$(".personm_ p").hide()
			 })
			 if($("#intro").val() != ""){
					$(".perin p").hide()
				}
			 if($("#hob").val() != ""){
					$(".perhob p").hide()
				}
			 
			 $(".button").on("mousedown",function(){
				 var companyName = $(".companyName").val()
				 var HeadImage = $("#headImage").attr("src")
				 var NickName = $("#nickname").val()				 
				 var WorkStatus = $("#workStatus").val()
				 var Place = $("#place").val()
				 var SchoolName = $("#schoolName").val()
				 var MajorName = $("#majorName").val()
				 var SchoolName2 = $("#schoolName2").val()
				 var  MajorName2 = $("#majorName2").val()
				 var  Intro = $("#intro").val()
				 var Hob = $("#hob").val().split("\n").join("<br />");
				 if(Intro.length > 260 ){
					 $(".tan").fadeIn(200)
							setTimeout(function(){
									$(".tan").fadeOut(400)
					},1000)
					 return
				 }
				 $.ajax({
						 url:'/user/updateUserInfo.html',
						 type:"POST",
						 cache:true,
						 ansy:true,
						 data:{
							 "userId":${BIRDS_USER.userInfo.userId },
							 "headImage":HeadImage,
							 "nickname":NickName,
							 "hob": Hob,
							 "workStatus":WorkStatus,
							 "place":Place,
							 "schoolName":SchoolName,
							 "schoolName2":SchoolName2,
							 "majorName":MajorName,
							 "majorName2":MajorName2,
							 "intro":Intro,
							 "companyName":companyName
						 },
						 success: function(msg){
							   console.log("cseccess")
							   if(msg.code == 1){
								  location.href = "/user/toUserCenter.html?uerId=" + ${BIRDS_USER.userInfo.userId }
							   }else{
								   console.log(msg.info)
							   }
								
							
							
							
						 },
						 error: function(){
							 console.log("失败")
							
						 }
					 })
				
				
					 
					 
			 })
		</script>
</html>

