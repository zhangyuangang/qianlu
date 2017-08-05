<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>轮播管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/dbmanage.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<style>
		#searchresult{
				border:1px solid #fff;
				width:200px;
				left:50%;
				margin-left:-121px;
				top:29px;
				position:absolute;
				z-index: 111;
			}
			li{list-style: none}
			#searchresult li:hover{
				
				color:#000;
				cursor: pointer;
			}
			.tan{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
			.tan1{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
			.dbname{font-size:.8rem;float:left;}
			#nickname{float:left;margin-top:-0.2rem;}
			.del{left:73%;}
			#listdb1 input{width:100%;}
			.line{width:100%;height:2px;background:#ccc;}
	</style>
	<script>
		//获取大白信息
		getuserlist(0)
		var Page = 1
		
		function getuserlist(Page){
			$.ajax({
				url:'/admin/user/loadingMore.html',
				type:'GET',
				data:{
					isPaging:1,
					curPage:Page,
					userType:1
				},
				dataType:'json',
				success: function(msg){
					console.log(msg)
					
					for(var i = 0; i < msg.data.length; i++){	
						var str = ""
						str += 
						 	'<div class="row dblist" id="listdb">'+
								'<div class="col-md-3">'+
									'<span class="dbname">邀请码：</span>'+
									'<div id="nickname">' + msg.data[i].code +'</div>'+									
								'</div>'+
								'<div class="col-md-3">'+
									'<span class="dbname">昵称：</span>'+
									'<div id="nickname">' + msg.data[i].nickname +'</div>'+					
								'</div>'+
								'<div class="col-md-3">'+
									'<span class="dbname">公司：</span>'+
									'<div id="nickname">' + msg.data[i].companyName +'</div>'+									
								'</div>'+			
								'<div class="col-md-3">'+
									'<span class="dbname">活跃度：</span>'+
									'<div id="nickname">' + msg.data[i].liveness +'</div>'+	
									'<span class="del" name="'+ msg.data[i].userId +'">删除</span>'+									
								'</div>'+										
								'</div>'+
								
								'<div class="row dblist" id="listdb1">'+
								'<div class="col-md-1">'+
									'<span class="dbname">营销</span>'+
									'<input type="text"  id="gw_yx" value="'+ msg.data[i].gw_yx +'"/>'+									
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">产品</span>'+
									'<input type="text" id="gw_cp" value="'+ msg.data[i].gw_cp +'" />'+					
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">管理</span>'+
									'<input type="text" id="gw_gl" value="'+ msg.data[i].gw_gl +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">财务</span>'+
									'<input type="text" id="gw_js" value="'+ msg.data[i].gw_js +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">技术</span>'+
									'<input type="text" id="gw_cw" value="'+ msg.data[i].gw_cw +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">其他</span>'+
									'<input type="text" id="gw_qt" value="'+ msg.data[i].gw_qt +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">&nbsp;&nbsp;&nbsp;&nbsp;</span>'+												
									'<input type="text"   />'+													
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">IT互联网</span>'+
									'<input type="text" id="hy_it" value="'+ msg.data[i].hy_it +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">金融快消</span>'+
									'<input type="text" id="hy_jr" value="'+ msg.data[i].hy_jr +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">传统行业</span>'+
									'<input type="text" id="hy_ct" value="'+ msg.data[i].hy_ct +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname">其他</span>'+
									'<input type="text" id="hy_qt"value="'+ msg.data[i].hy_qt +'" />'+										
								'</div>'+
								'<div class="col-md-1">'+
									'<span class="dbname sjxinz" name="'+ msg.data[i].userId +'" title="'+ msg.data[i].weightId +'">新增</span>'+											
								'</div>'+									
								'</div>'+
								'<div class="line"></div>'
								$(".box").append(str)
									
					}
					var index = $(".del")
						for (var i = 0; i < index.length; i++) {						 			
							index[i].addEventListener('click', function(){
									var Id = $(this).attr("name")
									$.ajax({
										url:'/admin/user/deleteById.html',
										type:'POST',
										data:{
											userId:Id
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
										
									})
								iscroll.refresh()													
							}, false);
						}
						//数据新增
						var indexs = $(".sjxinz")
						for (var i = 0; i < indexs.length; i++) {						 			
							indexs[i].addEventListener('click', function(){
									var userId = $(this).attr("name")
									var Id = $(this).attr("title")
									var gw_yx = $(this).parent().siblings().find("#gw_yx").val()
									var gw_cp = $(this).parent().siblings().find("#gw_cp").val()
									var gw_gl = $(this).parent().siblings().find("#gw_gl").val()
									var gw_js = $(this).parent().siblings().find("#gw_js").val()
									var gw_cw = $(this).parent().siblings().find("#gw_cw").val()
									var gw_qt = $(this).parent().siblings().find("#gw_qt").val()
									var hy_it = $(this).parent().siblings().find("#hy_it").val()
									var hy_jr = $(this).parent().siblings().find("#hy_jr").val()
									var hy_ct = $(this).parent().siblings().find("#hy_ct").val()
									var hy_qt = $(this).parent().siblings().find("#hy_qt").val()
									$.ajax({
										url:'/admin/intelligent/addWeight.html',
										type:'POST',
										data:{
											id:Id,
											toId:userId,
											toTable:'user',
											gw_yx: gw_yx,
											gw_cp: gw_cp,
											gw_gl: gw_gl,
											gw_js: gw_js,
											gw_cw: gw_cw,
											gw_qt: gw_qt,
											hy_it: hy_it,
											hy_jr: hy_jr,
											hy_ct: hy_ct,
											hy_qt: hy_qt
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
										
									})													
							}, false);
						}
				}
			})
		}



		 function message(ava){
			
			
				var UserId = $("#resh").val()
				$.ajax({
					url:'/admin/user/findByCode.html',
					type:'GET',
					dataType:'json',
					data:{
						code: UserId //大白iD
					},
					success: function(msg){
						
							if(msg.code == 1){
								$("#nickname").val(msg.data.userInfo.nickname)
								$("#occupationName").val(msg.data.userInfo.occupationName)
								$("#companyName").val(msg.data.userInfo.companyName)
								$("#place").val(msg.data.userInfo.place)
								$("#schoolname").val(msg.data.userInfo.schoolName)
								$("#majorName").val(msg.data.userInfo.majorName)
								$("#schoolname2").val(msg.data.userInfo.schoolName2)
								$("#majorName2").val(msg.data.userInfo.majorName2)
								$("#intro").val(msg.data.userInfo.intro)
								$("#resTxt").val(msg.data.userInfo.resTxt)
								$("#dbname").val(msg.data.userInfo.userId)
								$("#hob").val(msg.data.userInfo.hob)
								var val = $("#occType option")
								console.log(msg.data.occType)
								for(var i = 0; i < val.length; i++){
									if(msg.data.occType == $("#occType option").eq(i).attr("value")){
										$("#occType option").eq(i).attr("selected","selected").siblings().removeAttr("selected","selected")
									}
								}
								var val1 = $("#occType1 option")
								console.log(msg.data.userInfo.tradeName)
								for(var i = 0; i < val1.length; i++){
									if(msg.data.userInfo.tradeName == $("#occType1 option").eq(i).attr("value")){
										$("#occType1 option").eq(i).attr("selected","selected").siblings().removeAttr("selected","selected")
									}
								}
								console.log(msg)
							}else{
								console.log(msg.info)
							}
					},
					error: function(msg){
						console.log("def")
					}
				})
		
		}
		function msgg(){
			var UserId = $("#resh").val()
			$.ajax({
				url:'/admin/user/searchByStart.html',
				type:'GET',
				dataType:'json',
				data:{
					start:UserId//用户输入
				},
				success: function(msg){
					if(msg.code == 1){
						var str = ""
						for(var i=0;i<msg.data.length;i++){

							str += '<li>'+ msg.data[i].code +'</li>'						
						}
						$("#searchresult").html(str)
					}else{
						console.log(msg.info)
					}
				},
				error: function(){

				}
			})
		}
		
		function adb(){
			var UserId = $("#resh").val()
			var occType = $("#occType option:checked").attr("value")
			var tradeName = $("#occType1 option:checked").attr("value")
			var NickName = $("#nickname").val()
			var OccupationName = $("#occupationName").val()
			var CompanyName = $("#companyName").val()
			var Place = $("#place").val()
			var SchoolName = $("#schoolName").val()
			var MajorName = $("#majorName").val()
			var SchoolName2 = $("#schoolname2").val()
			var  MajorName2 = $("#majorName2").val()
			var  Intro = $("#intro").val()
			var  ResTxt= $("#resTxt").val()
			var TradeName = $("#tradename").val()
			var Hob = $("#hob").val().split("\n").join("<br />");			
			$.ajax({
				url:'/admin/user/addUser.html',
				type:'POST',
				cache:true,
				ansy:true,
				data:{
					 code:UserId,
					 occType: occType,
					 
					 nickname:NickName,					
					 schoolName: SchoolName,   // 所在学校名称
					 majorName:MajorName,    // 专业名称
					 companyName:CompanyName,		// 公司名称
					 occupationName:OccupationName,		// 职业名称
					 tradeName:tradeName ,		// 所属于行业
					 intro:Intro,		// 自我介绍256
					 majorName2:MajorName2,		//128					
					 resTxt:ResTxt, 		//个人资源1024					
					 place:Place,		//128
					 schoolName2:SchoolName2,
					 hob:Hob
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
				},
				error: function(msg){
						console.log("失败")
				}
			})
		}


	function xiugai(){
			var occType = $("#occType option:checked").attr("value")
			var tradeName = $("#occType1 option:checked").attr("value")
			var UserId = $("#dbname").val()
			var NickName = $("#nickname").val()
			var OccupationName = $("#occupationName").val()
			var CompanyName = $("#companyName").val()
			var Place = $("#place").val()
			var SchoolName = $("#schoolname").val()
			var MajorName = $("#majorName").val()
			var SchoolName2 = $("#schoolname2").val()
			var  MajorName2 = $("#majorName2").val()
			var  Intro = $("#intro").val()
			var  ResTxt= $("#resTxt").val()
			var TradeName = $("#tradename").val()
			var Hob = $("#hob").val().split("\n").join("<br />");	
			$.ajax({
				url:'/admin/user/editById.html',
				type:'POST',
				data:{
					 userId:UserId,
					 occType:occType,
					 nickname:NickName,					
					 schoolName: SchoolName,   // 所在学校名称
					 majorName:MajorName,    // 专业名称
					 companyName:CompanyName,		// 公司名称
					 occupationName:OccupationName,		// 职业名称
					 tradeName:tradeName ,		// 所属于行业
					 intro:Intro,		// 自我介绍256
					 majorName2:MajorName2,		//128					
					 resTxt:ResTxt, 		//个人资源1024					
					 place:Place,		//128
					 schoolName2:SchoolName2,
					 hob:Hob
				},
				success: function(msg){
					console.log(msg)
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
				},
				error: function(msg){
						console.log("失败")
				}
			})
		
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
			<!--search-->
          <div class="form-group">
            <div class="input-group">
            	<span class="name">大白ID搜索</span>
              <input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="resh" onkeyup="msgg()">
			  <ul id="searchresult">
				
			 </ul>
				<span class="revise" onclick="message()">搜索</span>
            </div>
            </div>
			<!--删除-->
		<!--编辑区域-->
		<div class="carousel">
			<p>
				<span class="bj">大白编辑</span>
				<span class="keep" >保存</span>
			</p>
			<div class="row">
			  <div class="col-md-6">
			  	<span class="dbname">大白ID</span>
			  	<input type="text"  id="dbname"/>
			  	
			  </div>
			  <div class="col-md-6">
			  	<span class="dbname">昵称</span>
			  	<input type="text" id="nickname" />
	
			  </div>
			</div>
		</div>
		<!--热论区域-->
		<div class="complete">
			<p>
				<span class="bj">主要内容</span>
				<span class="keep" onclick="xiugai()">保存</span>
				<span class="keep" onclick="adb()">新增</span>
			</p>
			<div class="row">
				  <div class="col-md-4">
				  	<span class="dbname">微信</span>
			  		<input type="text" id="" />
				  </div>
				  <div class="col-md-4">
				  	<span class="dbname">职位</span>
			  		<input type="text" id="occupationName" />
				  </div>
				  <div class="col-md-4">
				  	<span class="dbname">居住地</span>
			  		<input type="text" id="place"/>
				  </div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span style="margin-left: 6%;">公司</span>
			  		<input type="text" id="companyName"/>
				</div>
				<div class="col-md-4">
					<span style="margin-left: 3%;">岗位</span>
			  		<select type="text" id="occType" >
						  <option value="1">营销</option>
						  <option value="2">产品</option>   
						  <option value="4">管理</option>   
						  <option value="8">技术</option>   
						  <option value="16">财务</option>   
						  <option value="0">其他</option>
			  		</select>	
				</div>
				<div class="col-md-4">
					<span style="margin-left: 3%;">行业</span>
			  		<select type="text" id="occType1" >
						  <option value="IT互联网">IT互联网</option>
						  <option value="金融快消">金融快消</option>   
						  <option value="传统行业">传统行业</option>   
						  <option value="其他">其他</option>   
			  		</select>	
				</div>
			</div>
			<div class="row">
				  <div class="col-md-3">
				  	<span style="margin-left: 12%;">学校</span>
			  		<input type="text" id="schoolname"/>
				  </div>
				   <div class="col-md-3">
				  	<span style="margin-left: 12;">专业</span>
			  		<input type="text" id="majorName"/>
				  </div>
				   <div class="col-md-3">
				  	<span style="margin-left: 12%;">研究生</span>
			  		<input type="text" id="schoolname2"/>
				  </div>
				   <div class="col-md-3">
				  	<span style="margin-left: 12%;">专业</span>
			  		<input type="text" id="majorName2"/>
				  </div>

			</div>
			<div class="row">
				<div class="col-md-12">
					<span style="margin-left: 3%;">个人描述</span>			  		
			  		<input type="text" name=""  value="" id="intro"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<span style="margin-left: 3%;">个人资源</span>
					<input type="text" name="" id="resTxt" value="" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<span style="margin-left: 3%;">擅长领域</span>
					<input type="text" name="" id="hob" value="" />
				</div>
			</div>
		</div>
		<!--大白列表-->
		<div class="complete db">
			<p>
				<span class="bj">大白列表</span>
				<span class="keep" id="last" >上一页</span>
				<span class="keep" id="next">下一页</span>
			</p>
			<div class="box"></div>
		</div>
	</body>
	<script>
		$("#searchresult").on("mouseleave",function(){
			$("#searchresult").empty()
		})
		$("#last").on("mousedown",function(){
			Page--
			$(".box").empty()
			getuserlist(Page)
		})
		$("#next").on("mousedown",function(){
			Page++
			$(".box").empty()
			getuserlist(Page)
		})
	</script>
</html>
