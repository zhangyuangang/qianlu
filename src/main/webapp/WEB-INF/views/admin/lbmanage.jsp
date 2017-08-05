<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>轮播管理</title>
		<link rel="stylesheet" href="../../../css/admin/bootstrap.css" />
		<link rel="stylesheet" href="../../../css/admin/lbmanage.css" />
		<script src="../../../js/admin/jquery.min.js"></script>
	</head>
	<script type="text/javascript">
		document.domain = "upbirds.com";
		var imgBasePath = "http://resource.upbirds.com"
	</script>
	<style>
		.tan{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
		.tan1{width:27%;height:66px;background:#ccc;border-radius:8px;position:fixed;top:26%;left:30%;text-align:center;line-height:66px;z-index: 11;display: none;}
	</style>
	<script>
		//新增轮播
		function addlb(){
			var Cover = $("#lkl").val()
			var Detils = $("#lsl").val()
			console.log(Cover)
			console.log(Detils)
			$.ajax({
				url:'/admin/banner/addBanner.html',
				type:'POST',
				data: {
					cover: Cover, //封面图路径
					detils: Detils //详情图路径
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
		}
		//删除轮播
		function dellb(){
			var Id = $("#lunb").val()
			$.ajax({
				url:'/admin/banner/deleteById.html',
				type:'POST',
				data: {
					id: Id
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
		}
		//轮播列表
		lblist()
		function lblist(){
			$.ajax({
				url:'/admin/banner/getBannerList.html',
				type:'GET',
				success: function(msg){
					if(msg.code == "1"){
						
						for(var i = 0; i < msg.data.length; i++){
							
									var str = ""
									str += 
										'<div class="complete">'+
												'<p>'+
													'<span class="bj">轮播图ID<span>'+ msg.data[i].id +'</span></span>'+
												'</p>'+									
												'<dl style="margin-left: 12.5%;">'+
													'<dt>'+
														
													'</dt>'+
													'<dd>'+
														'<div class="lb"><img src="'+ msg.data[i].cover +'" width="280"/></div>'+
														'<span>banner轮播图</span>'+
													'</dd>'+
												'</dl>'+
												'<dl>'+
													'<dt>'+												
													'</dt>'+
													'<dd>'+
														'<div class="lb"><img src="'+ msg.data[i].detils +'" width="280"/></div>'+
														'<span>详细内容图片</span>'+
													'</dd>'+
												'</dl>'+
											'</div>'
										$("body").append(str)
						}
							

					}else{
							console.log(msg.info)
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
			<!---->
			 <input type="hidden"   id="lkl" value=""/>
			  <input type="hidden" id="lsl" value=""/>
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
				<span class="bj">编辑区域</span>
				<span class="keep" onclick="addlb()">保存</span>
			</p>
			<!--导入-->
			<span class="leading">导入图片</span>
			<dl class="im" >
				<dt>
					<div class="pImgItemMain" id="viewPhoto"></div>
					<i class="add pImgItem uploadTarget" onclick="$('#base64_up_file').click()"></i>					
					<i class="del">删除</i>
				</dt>
				<dd>					
					<span>banner轮播图</span>
				</dd>
			</dl>
			<dl class="il">
				<dt>
					<div class="pImgItemMain" id="viewPhoto"></div>
					<i class="add pImgItem uploadTarget" onclick="$('#base64_up_file').click()"></i>					
					<i class="del">删除</i>
				</dt>
				<dd>
					
					<span>详细内容图片</span>
				</dd>
			</dl>
		</div>
		<!--完成区域-->
		<div class="complete">
			<p>
				<span class="bj">删除轮播</span>
				<span class="keep" onclick="dellb()">删除</span>
			</p>
			<dl>
				<dd>轮播id</dd>
				<dt>
					<input type="text" name="id" value="" id="lunb">
				</dt>

			</dl>
		</div>
	</body>
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
				var _dom = '<div class="lb pImgItem" id="localImg"><div class="vPic"><img id="preview" src="'+ _src +'" width="280" /></div><input type="hidden" value="'+imgData.data+'"></div>';              
				$('.im dd').prepend(_dom) 
				$("#lkl").val($(".pImgItem").eq(1).find("img").attr("src"))
				$("#lsl").val($(".pImgItem").eq(2).find("img").attr("src"))          
            }
        });
	
		$(".del").on("mousedown",function(){
			console.log("ad")
			$(this).parents().siblings("dd").find('div').remove()
		})
</script>

</html>
