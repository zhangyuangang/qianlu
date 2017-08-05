<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>注册</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.structure.min.css" />
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.theme.min.css" />
    <link rel="stylesheet" href="../../css/theme.css" />
    <script type="text/javascript" src="../../js/jquery.min.js" ></script>
    <script>
        $(document).bind("mobileinit", function() {
            // global ajax stop
            $.mobile.ajaxEnabled=false;
        });
    </script>
    <script type="text/javascript" src="../js/jquery.mobile.custom.min.js" ></script>
</head>

<body>
    <div data-role="page" id="" data-title="注册" data-url="" class="messagePage">
        <!-- <div data-role="header" data-position="fixed" data-theme="b">
            <h1>注册</h1>
            <a href="#" data-rel="back" class="topBack">&lt;</a>
        </div> -->
        <!--选择-->
        <div class="select">
        	<div class="logo"></div> 
            <p>与有才干的青年一对一交流</p>     	
        	<div class="smallb">
        		<span>小白 立即体验</span>
        	</div>
        	<div class="bigb">
        		<span>大白 填写邀请码！</span>
        	</div>
        </div>
        <!-- /header -->
        <div role="main" class="ui-content">
            <div class="registCls">
            	<span class="back">返回</span>
                <h2>注册新账号</h2>
                <form action="/user/register.html" method="post" id="cf" >
					<input type="hidden" name="headImgUrl" value="${user.headImgUrl }"/>
					<input type="hidden" name="unionid" value="${user.unionid }"/>
					<input type="hidden" name="sex" value="${user.sex }"/>
                    <table cellpadding="0" cellspacing="0" width="100%" border="0">
                        <tr>
                            <td colspan="2">
                                <input type="text" name="nickname" id="" value="${user.nickname}" placeholder="自动抓取微信名">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="code" id="codeInput" value="" placeholder="输入邀请码">
                                <input type="hidden" name="userType" id="userType" value="1">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <a href="javascript:void(0)" class="ui-btn ui-corner-all greenCls" onclick="onSubmitForm()">注册</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <!-- /content -->
    </div>
    <script type="text/javascript">
    var checkPhone = function(){
        var phoneNumReg = /^(((13[0-9]{1})|(14[7]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(!phoneNumReg.test($('#regPhone').val())) {
              $( "#popupAlert" ).popup( "open", { x: $('#regPhone').offset().left, y: ($('#regPhone').offset().top+25) } );
              return false;
        }
        return true
    }
    var onSubmitForm = function(){
    	if(!$("#codeInput").val() || $("#codeInput").val() == ""){
    		$("#userType").val(2);
    	}
    	$('#cf').submit()
        return false;
    }
    $(".bigb").on("mousedown",function(){
    	$(".select").animate({"left":"-400px"},600)
    })
    $(".smallb").on("mousedown",function(){
    	onSubmitForm();
    })
    $(".back").on("mousedown",function(){
    	$(".select").animate({"left":"0"},600)
    })
	
	
    </script>
</body>

</html>
