<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>轮播管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/dbessay.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<script type="text/javascript">
		document.domain = "upbirds.com";
		var imgBasePath = "http://resource.upbirds.com"
	</script>
	<style>
		#searchresult{
				border:1px solid #fff;
				width:200px;
				left:18%;
				margin-left:-121px;
				top:30px;
				position:absolute;
				z-index: 111;
			}
			li{list-style: none}
			#searchresult li:hover{
				
				color:#000;
				cursor: pointer;
			}
			#doc,#doc1,#doc2,#doc3{
				opacity: 0;
				position: absolute;
				top: -1px;
				cursor: pointer;
			}
			.tan{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
			.tan1{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
				#listdb1 input{width:100%;}
	</style>
	<script>
		//新增随笔
		function addsuibi(){
			
				var UserId = $("#resh").val()
				var Content = $("#content").val()
				
				var Image = $("#image").val()
					$.ajax({
					type:"POST",
					url:"/admin/dynamic/addDynamic.html",
					async:true,
					data:{
						code:UserId,
						content:Content,
						imgUrls:Image
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
		//删除随笔
		function deleate(){
			var Id = $("#del").val()
			$.ajax({
				url:'/admin/dynamic/deleteById.html',
				type:'POST',
				data: {
					id:Id
				},
				success:function(msg){
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
		}
		//修改随笔
		function xiusuibi(){
			var UserId = $("#Ucode").val()
			var Id = $("#del").val()
			$.ajax({
				type:"GET",
				url:"/admin/dynamic/editById.html",
				async:true,
				data:{
					id:Id,
					code:UserId
				},
				success: function(rslt){
					if(rslt.code == "1"){
						//正常业务逻辑
						$(".tan").fadeIn(200)
							setTimeout(function(){
									$(".tan").fadeOut(400)
							},1000)
					} else{
						$(".tan1").fadeIn(200)
							setTimeout(function(){
									$(".tan1").fadeOut(400)
							},1000)
					}
				}
			});
		}
		//加载随笔
		suibi()
		function suibi(){
			$.ajax({
				url:'/admin/dynamic/loadingMore.html',
				type:'GET',
				data:{
					curPage:1
				},
				dataType:'json',
				success:function(msg){
					if(msg.code == 1){
						for(var i = 0; i < msg.data.length; i++){
								var str = ""
						str += 
							'<div class="complete">'+
									'<p>'+
										'<span class="bj">大白随笔</span>'+
									'</p>'+
								'<div class="form-group">'+
									'<div class="input-group">'+
										'<span class="name">随笔ID</span>'+
										'<input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="del" value="'+ msg.data[i].id +'">'+
										'<span class="name">大白邀请码</span>'+
										'<input type="text"  class="form-control input-sm bg-light no-border rounded padder" value="'+ msg.data[i].code +'" id="Ucode">'+
									'</div>'+
									'<div class="drtext">'+
										'<span class="leading">导入文字</span>'+
										'<dl>'+				
											'<dd>'+
												'<input type="" name="" id="" value="'+ msg.data[i].content +'" />'+				
											'</dd>'+
										'</dl>'+
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
													'<input type="text" />'+													
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
													'<span class="dbname sjxinz" name="'+ msg.data[i].id +'" title="'+ msg.data[i].weightId +'">新增</span>'+
												'</div>'+									
												'</div>'
												
								$("body").append(str)
									
						}
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
																toTable:'dynamic',
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

					}else{
						$(".tan1").fadeIn(200)
							setTimeout(function(){
									$(".tan1").fadeOut(400)
							},1000)
					}

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
			<input type="hidden" id="image" value="">
		<div style="display:none">
            <form id="base64_up_form" action="/upload.html" enctype="multipart/form-data" method="post" target="base64_up">
                <input type="hidden" name="authkey" value="FC1E530BE622FEFD5745EE121A9776CB"/>
                <input type="hidden" name="type" value="2"/>
                <input type="file" name="file" id="base64_up_file" />
                <iframe name="base64_up" id="base64_up" style="display:none;"></iframe>
                <input type="submit"/>
            </form>
        </div>
		<!--编辑区域-->
		<div class="carousel">
			<p>
				<span class="bj">大白随笔</span>
				<span class="keep" onclick="addsuibi()">保存</span>
			</p>
			<!--search-->
          <div class="form-group">
            <div class="input-group">
            	<span class="name">大白ID</span>
              <input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="resh">
			   <ul id="searchresult">
				
			 </ul>
				<span class="revise">修改</span>
            </div>
            <!---->
            <div class="drtext" style="height:20rem;">
            	<span class="leading">导入文字</span>
	            <dl>				
					<dd>
						<textarea rows="6" cols="50" id="content"></textarea> 				
					</dd>
				</dl>
            </div>
            <div class="drpic">
            	<span class="leading">导入图片</span>
			<dl  class="im">
				<dt>
					<div class="pImgItemMain" id="viewPhoto"></div>
					<i class="add pImgItem uploadTarget" onclick="$('#base64_up_file').click()"></i>
					
					<i class="del">删除</i>
				</dt>
				<dd>
					<!--<div class="lb" id="localImg">
						<img id="preview" src=""/>
					</div>-->
				</dd>
			</dl>						
            </div>
			
          </div>
		</div>
		<!--修改区域-->
		<div class="complete">
					<p>
						<span class="bj">删除修改区域</span>
						<span class="keep" onclick="xiusuibi()">修改</span>
						<span class="keep delt" onclick="deleate()">删除</span>
					</p>
				<div class="form-group">
				<div class="input-group">
						<span class="name">随笔ID</span>
						<input type="text"  class="form-control input-sm bg-light no-border rounded padder" id="del" value="">					
				</div>
				<div class="input-group">
						<span class="name">大白邀请码</span>
						<input type="text"  class="form-control input-sm bg-light no-border rounded padder" value="" id="Ucode">
				</div>
		</div>

		<!--弹框-->
	<div class="tan">
		修改成功
	</div>
	<div class="tan1">
		修改失败
	</div>
	</body>
	
	<script type="text/javascript">
		
		$("#resh").bind("keyup",function(){
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
		})
		

		
	</script>
	<script type="text/javascript">
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
				var _dom = '<div class="lb pImgItem mlgb" id="localImg"><div class="vPic"><img id="preview" src="'+ _src +'" width="280" /></div><input type="hidden" value="'+imgData.data+'"></div>';
                $('.im dd').append(_dom)  
				var Img = $(".mlgb")
					if(Img.length == "1"){
						$("#image").val(Img.eq(0).find("img").attr("src"))			
					}else if(Img.length == "2"){
						$("#image").val(Img.eq(0).find("img").attr("src")+ '<split>'+Img.eq(1).find("img").attr("src"))
					}else if(Img.length == "3"){
						$("#image").val(Img.eq(0).find("img").attr("src")+ '<split>'+Img.eq(1).find("img").attr("src")+ '<split>'+Img.eq(2).find("img").attr("src"))	
					}else if(Img.length == "4"){
						$("#image").val(Img.eq(0).find("img").attr("src")+ '<split>'+Img.eq(1).find("img").attr("src")+ '<split>'+Img.eq(2).find("img").attr("src")+ '<split>'+Img.eq(3).find("img").attr("src"))
					}
				
				
				
					
            }
        });


		
		$(".del").on("mousedown",function(){
			console.log("ad")
			$(this).parents().siblings("dd").find('div').remove()
		})
		
</script>
<script>
		$("#searchresult").on("mouseleave",function(){
			$("#searchresult").empty()
		})
	</script>
</html>
