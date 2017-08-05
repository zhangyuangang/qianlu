<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>	
	    <link rel="stylesheet" href="../../../css/Reset/reset.css" />
	    <link rel="stylesheet" href="../../../css/pers/pbigbbj.css" />
	    <script type="text/javascript" src="../../../js/jquery.min.js" ></script>
		<script type="text/javascript">
		document.domain = "upbirds.com";
		var imgBasePath = "http://resource.upbirds.com"
		
	</script>
	    <title>${BIRDS_USER.userInfo.nickname }</title>
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
									<img alt="" id="headImage" src="${BIRDS_USER.userInfo.headImage }" width="100%" onclick="$('#base64_up_file').click()">
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
								<input type="text" name="" id="companyName" value="${BIRDS_USER.userInfo.companyName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>职位</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="occupationName" value="${BIRDS_USER.userInfo.occupationName }" />
							</div>
						</li>
						<div class="kong1"></div>
						<li>
							<div class="bj_left">
								<span>行业</span>
							</div>
							<div class="bj_right">
							
								<select type="text" id="occType" >
								  <option value="请选择">请选择</option>								 
								  <option value="IT互联网">IT互联网</option>						 							  
								  <option value="金融销售">金融销售</option> 	
								  <option value="传统行业">传统行业</option> 								  								   
								  <option value="其他">其他</option> 
								
							</select>
							</div>
						</li>
						<div class="schoolm">
							<div class="kong1"></div>
							<h3>学校信息</h3>
							<div class="kong1"></div>
						</div>
						<li>
							<div class="bj_left">
								<span>本科</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="schoolName" value="${BIRDS_USER.userInfo.schoolName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>所学专业</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="majorName" value="${BIRDS_USER.userInfo.majorName }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>研究生</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="schoolName2" value="${BIRDS_USER.userInfo.schoolName2 }" />
							</div>
						</li>
						<li>
							<div class="bj_left">
								<span>所学专业</span>
							</div>
							<div class="bj_right">
								<input type="text" name="" id="majorName2" value="${BIRDS_USER.userInfo.majorName2 }" />
							</div>
						</li>
						<div class="personm">
							<div class="kong1"></div>
							<h3>大白介绍</h3>
							<div class="kong1"></div>
						</div>
						<div class="personm_ perin">
							<p>（你擅长的回答／或独特的经历）</p>
							<textarea  placeholder="" rows="4" id="intro" >${BIRDS_USER.userInfo.intro}</textarea>
						</div>
						<div class="personm">
							<div class="kong1"></div>
							<h3>擅长领域</h3>
							<div class="kong1"></div>
						</div>
						<div class="personm_ perlinyu" >
							<p>（你擅长的领域／或独特的经历）</p>
							<textarea  placeholder="" rows="4" id="lingy" >${BIRDS_USER.userInfo.hob}</textarea>
						</div>
						<div class="personz">
							<div class="kong1"></div>
							<h3>个人资源</h3>
							<div class="kong1"></div>
						</div>
						<div class="personz_">
							<p>（公司正在招聘的职位／或自己能提供的一些机会）</p>
							<textarea  placeholder="" rows="4" id="resTxt" >${BIRDS_USER.userInfo.resTxt}</textarea>
						</div>
					</ul>
					<div class="kong"></div>
					<div class="button">
						保存修改
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
   <div style="display:none">
            <form id="base64_up_form" action="/uploadImg.html" enctype="multipart/form-data" method="post" target="base64_up">
                <input type="hidden" name="authkey" value="FC1E530BE622FEFD5745EE121A9776CB"/>
                <input type="hidden" name="type" value="2"/>
                <input type="file" name="file" id="base64_up_file" />
                <iframe name="base64_up" id="base64_up" style="display:none;"></iframe>
                <input type="submit"/>
            </form>
       		 </div>
	</body>
	<script src="../../../js/iscroll-min.js"></script>
		<script type="text/javascript">
		var trade = '${BIRDS_USER.userInfo.tradeName}'
			if(trade == "IT互联网"){
				$("#occType option").eq(1).attr("selected","selected").siblings().removeAttr("selected","selected")
			}else if(trade == "金融销售"){
				$("#occType option").eq(2).attr("selected","selected").siblings().removeAttr("selected","selected")
			}else if(trade == "传统行业"){
				$("#occType option").eq(3).attr("selected","selected").siblings().removeAttr("selected","selected")
			}else if(trade == "其他"){
				$("#occType option").eq(4).attr("selected","selected").siblings().removeAttr("selected","selected")
			}
			
		
			
			
			var iscroll = new IScroll("#wrapper",{
				mouseWheel:true,
				preventDefault: false
			})
			 document.addEventListener("touchmove",function(e){
    			e.preventDefault();
    			})
				
				if($("#intro").val() != ""){
					$(".perin p").hide()
				}
				if($("#lingy").val() != ""){
					$(".perlinyu p").hide()
				}
				if($("#resTxt").val() != ""){
					$(".personz_ p").hide()
				}
				$(".button").on("mousedown",function(){
					 var HeadImage = $("#headImage").attr("src")
					 var NickName = $("#nickname").val()
					 var OccupationName = $("#occupationName").val()
					 var CompanyName = $("#companyName").val()
					 var Place = $("#occType option:checked").attr("value")
					 var SchoolName = $("#schoolName").val()
					 var MajorName = $("#majorName").val()
					 var SchoolName2 = $("#schoolName2").val()
					 var  MajorName2 = $("#majorName2").val()
					 var  Intro = $("#intro").val()
					 var  ResTxt= $("#resTxt").val()
					 var Hob = $("#lingy").val().split("\n").join("<br />");					 
					 $.ajax({
						 url:'/user/updateUserInfo.html',
						 type:"POST",
						 cache:true,
						 ansy:true,
						 data:{
							 userId:${BIRDS_USER.userInfo.userId },
							 headImage: HeadImage,
							 nickname: NickName,
							 occupationName: OccupationName,
							 companyName: CompanyName,
							 tradeName: Place,
							 schoolName: SchoolName,
							 schoolName2: SchoolName2,
							 majorName: MajorName,
							 majorName2: MajorName2,
							 intro: Intro,
							 resTxt: ResTxt,
							 hob:Hob
						 },
						 success: function(msg){
							location.href = "/user/toUserCenter.html?userId=" + ${BIRDS_USER.userInfo.userId }
						 },
						 error: function(){
							 console.log("失败")
						 }
					 })
				})
			
			
		$("#base64_up_file").change(function(){           
            $('#base64_up_form').submit();
        });
		
        $("#base64_up").on("load", function(){          
            var r =  this.contentWindow.document.getElementById('jsonData').innerHTML;
            var imgData = JSON.parse(r);
            if(imgData.code !=1){
                alert_msg(imgData.info)
            }else{
                var _src = imgBasePath + imgData.data;
				//var src = _src.replace("-smaller","")
				//var _dom = '<div class="lb pImgItem" id="localImg"><div class="vPic"><img id="preview" src="'+ _src +'" width="280" /></div><input type="hidden" value="'+imgData.data+'"></div>';              
				$('#headImage').attr("src",_src)      
            }
        });
		</script>
</html>

