<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>${tName }</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="../../css/jquery.mobile.custom.structure.min.css" />
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.theme.min.css" />
    <link rel="stylesheet" href="../../css/theme.css" />
	<link rel="stylesheet" href="../../css/Chat/Chat.css" />
    <script type="text/javascript">
        define = null;
        require = null;
    </script>
    <!-- <script src="../../js/webim/jweixin-1.0.0.js" type="text/javascript"></script> -->
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery.mobile.custom.min.js"></script>
    <script type='text/javascript' src='../../js/webim/webim.config.js'></script>
    <script type='text/javascript' src='../../js/webim/sdk/strophe-1.2.8.js'></script>
    <script type='text/javascript' src='../../js/webim/sdk/websdk-1.4.10.js'></script>
    <script src='../../js/webim/webrtc/dist/adapter.js'></script>
    <script src='../../js/webim/webrtc/dist/webrtc-1.4.10.js'></script>
    <script src="../../js/ajax-utils.js"></script>
    <script type="text/javascript">
	    var GetQueryString = function(name){
	        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if(r!=null)return  unescape(r[2]); return null;
	   	}
		var XUNDAN_ID = GetQueryString("xudanId"); //字符串类型
		//当XUNDAN_ID不为空得时候，即表示为续单之后
    	var fromWho = '${selfId }',fromWhoPwd = '${password }';
        var _toWho = '${tId }';
        var tHeadImg = '${tHeadImage}';
        var _history = [];
        var WECHAT_HISTORY = '${selfId}-${tId }';
        if(localStorage[WECHAT_HISTORY]){
            try{
                _history = JSON.parse(localStorage[WECHAT_HISTORY]);
            } catch(err){
                _history = [];
            }
        };
        
    </script>
	<style>
	#Bigimg{position:fixed;z-index:11111;top:0;}
	.zhezhao{width:100%;height:100%;background:#000;display:none;position:fixed;z-index:1111;top:0;}
	</style>
</head>
<body>
	<div data-role="page" id="" data-title="Inbox" data-url="" class="msnBg">
      <!-- <div data-role="header" data-position="fixed" data-theme="b">
          <h1>Tom</h1>
          <a href="#" data-rel="back" class="topBack">&lt;</a>
      </div> -->
      <!-- /header -->
     
	   <div data-role="header" data-position="fixed" data-theme="b" class="header">
        	<div class="left" onclick="cx()">
        		<i></i>
        		<span>25min</span>
        		<b class="line"></b>
        	</div>
        	<div class="rights" onclick="$('#msnPicture').click()">
        		<i></i>
        		<span>发送图片</span>
        	</div>
        </div>

      <div role="main" class="ui-content msnCls">                           
                <p class="tix"></p>
                 <div class="kong2"></div>
          <ul id="msnPlace" class="touch msnPlace" data-role="listview" data-icon="false" >
				
		  
		  </ul>
      </div>
      <!-- /content -->
      <div data-role="footer" data-position="fixed" class="msnFooter">
          <div class="msnTextarea">
                <form action="#" id="sendMsgForm">
               	  <span href="#" class="jianp"></span>
                  <a href="#" class="msnLeft micIcon" id="msnMic">mic</a>
                  <textarea  id="sendMsg"  name="word" ></textarea>
                  <span class="micSubmitBtn" id="micSubmitBtn" onclick="sendPrivateText()">发送</span>
                  <a href="#" class="msnLeft keyboardIcon" id="msnKeyboard" style="display:none">Keyboard</a>
                  <div id="sendVoice" style="display:none">按住开始录音</div>
            </form>
                <div class="hideThis"><input type="file" id="msnMicFile"><input type="file" accept="image/*" id="msnPicture" /><audio  id="msnVideo" autoplay></audio ></div>
          </div>
          <!-- /navbar -->
      </div>
      <!-- /footer -->
      <div class="voicelayermchild" id="voiceing"><div class="voicelayermcont"><div>开始录音...</div></div></div>
  </div>
  <img src="" id="Bigimg" width="100%"/>
  <div class="zhezhao"></div>
</body>
<script>
window.onload = function(){
	  //alert("adad")
	$("#sendMsg").css("height","2.4rem")
	ajaxEngine.postAjax("/user/checkSendMsg.html", {tuserId:parseInt(_toWho)}, function(rslt){
		if(rslt.code == 1 && _toWho != 52){
			$(".tix").html("问答开始喽，小白可以先描述下自己的情况和具体问题哈~")
			//倒计时
				var timeIndexs = new Date().getMinutes();
				 
				timeIndexs = (30-(timeIndexs+5)%30)*60;
				//console.log(timeIndexs)
				  var setTimes = function(){
					var Hour = parseInt(timeIndexs / 3600);    // 计算时 
					var Minutes = parseInt((timeIndexs % 3600) / 60);    // 计算分 
					var Seconds = parseInt(timeIndexs % 60);    // 计算秒  
					Hour = Hour < 10 ? "0" + Hour : Hour;
					Minutes = Minutes < 10 ? "0" + Minutes : Minutes;
					Seconds = Seconds < 10 ? "0" + Seconds : Seconds;
					//console.log(Hour + ":" + Minutes + ":" + Seconds);					
					 timeIndexs--;
					 if(Minutes == 5 && Seconds > 58 && _toWho != 52 ){
						 var str = ""
						 str += '<li class="timeMsg" style="1rem">问答时间还剩最后5min，请抓紧时间哦~</li>'
						 $("#msnPlace").append(str)
					 }
				 }
				
				  setTimes();        
				var Times = setInterval(setTimes, 1000);
		}
	})
	$(".msnCon img").on("mousedown",function(){
		var Imgs = $(this).attr("src")
		$("#Bigimg").attr("src",Imgs)
		$("#Bigimg").fadeIn(600)
		$(".zhezhao").fadeIn(600)
		if($(this).height() < 300){
			$("#Bigimg").css("top","25%")
		}else{
			$("#Bigimg").css("top",0)
		}
	})
	$("#Bigimg").on("mousedown",function(){
				$(this).fadeOut(600)
				$(".zhezhao").fadeOut(600)
	})	
}
//textField.returnKeyType = UIReturnKeySend;
var timeIndex = 0;
//计时
var setTime = function(){
	 var hour = parseInt(timeIndex / 3600);    // 计算时 
	 var minutes = parseInt((timeIndex % 3600) / 60);    // 计算分 
	 var seconds = parseInt(timeIndex % 60);    // 计算秒  
	 hour = hour < 10 ? "0" + hour : hour;
	 minutes = minutes < 10 ? "0" + minutes : minutes;
	 seconds = seconds < 10 ? "0" + seconds : seconds;
	 $(".showTime").val(hour + ":" + minutes + ":" + seconds);
	 timeIndex++;
 }


var upMic = function(localId, callback){
	var serverId = '';
	wx.uploadVoice({
	    localId: localId,
	    isShowProgressTips: 1,
	        success: function (res) {
	        	callback(res.serverId);
	    }
	});
	return serverId;
};
var downLoadMic = function(servId, callback){
	wx.downloadVoice({
	    serverId: servId, // 需要下载的音频的服务器端ID，由uploadVoice接口获得
	    isShowProgressTips: 0, // 默认为1，显示进度提示
	    success: function (res) {
	    	callback.call(this, res.localId);
	    }
	});
};
/**
 * 暂停
 */
var pauseMic = function(localId){
	wx.pauseVoice({
	    localId: localId 
	});
};
var micFlag = null
var playMic = function(isRe,id,times,obj){
    clearTimeout(micFlag);
    $('.playing').removeClass('playing');
    $(obj).addClass('playing');
	if(isRe){
		downLoadMic(id, splayMic);
	} else {
		splayMic(id);
	};
    micFlag = setTimeout(function(){
        $(obj).removeClass('playing');
    },times*1000)
};

var splayMic = function(localId){
	wx.playVoice({
	    localId: localId
	});
};
wx.onVoicePlayEnd({
    success: function (res) {
    //播放完毕 播放下一条    
    }
});
var stopMic = function(localId){
	wx.stopVoice({
	    localId: localId
	});
}
var sendVoice = function(servId,isRe,_times){
	if(isRe){
		$('#msnPlace').append(sendTimeFunc()+'<li class="hisMsg"><div class="portrait_left"><img src="'+ tHeadImg +'" width="100%""></div><div class="chtext_left"><div class="msnCon voice" onclick="playMic(1,\'' + servId + '\',\''+_times+'\',this)"><i></i> '+_times+'\'\'</div></div></li>');
	}else{
		$('#msnPlace').append(sendTimeFunc()+'<li class="myMsg"><div class="portrait_right"><img src="${selfHeadImage}" width="100%""></div><div class="msnCon voice" onclick="playMic(0,\'' + servId + '\',\''+_times+'\',this)"><i></i> '+_times+'\'\'</div></li>');
	}
};
var sendTimeFunc = function(){
    var _time = formatterDate(new Date);
    if(curTimePoint()){
        return '<li class="timeMsg">'+_time+'</li>'  
    }else{
        return ''
    }	
}
var curTimePoint = function(_time){
    if($('#msnPlace li.timeMsg').length > 0){
      var lastTime =todate($('li.timeMsg:last').text()),nowTime = new Date();
      if(nowTime - lastTime > 900000){
        //时间间隔大于15分钟
        return true
      }else{
        return false
      }
    }else{
        return true;
    }
}
//显示当前日期
var formatterDate = function (date) {
  var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate(),
  month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1),
  hor = date.getHours()> 9 ?date.getHours():"0"+date.getHours(),
  min = date.getMinutes()> 9 ?date.getMinutes():"0"+date.getMinutes();
  return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min;
};
    var conn = {};
    conn = new WebIM.connection({
        isMultiLoginSessions: WebIM.config.isMultiLoginSessions,
        https: typeof WebIM.config.https === 'boolean' ? WebIM.config.https : location.protocol === 'https:',
        url: WebIM.config.xmppURL,
        isAutoLogin: true,
        heartBeatWait: WebIM.config.heartBeatWait,
        autoReconnectNumMax: WebIM.config.autoReconnectNumMax,
        autoReconnectInterval: WebIM.config.autoReconnectInterval
    });
    // listern，添加回调函数
    conn.listen({
        onOpened: function (message) {          //连接成功回调，连接成功后才可以发送消息
           
        },
        onTextMessage: function (message) {
        	var isVoice = false,isImg = false,isVoiceData = null,msgType = 'txt';
            var msgD = {};
        	try{
        		isVoiceData = JSON.parse(message.data);
        		if(isJson(isVoiceData)){
        			if(isVoiceData.type == 2){
        				isImg = true;
        				msgType = 'img2';
        			} else {
	        			isVoice = true;
	        			msgType = 'voice';
        			}
        		}
        	}catch(e){}
            // 在此接收和处理消息，根据message.type区分消息来源，私聊或群组或聊天室
            if(isVoice){
            	if(message.from == _toWho){
	            	sendVoice(isVoiceData.serverId,1,isVoiceData.times);
            	}
                var _vData = {
                    "localId":isVoiceData.serverId,
                    "times":isVoiceData.times
                   };
                msgD['msg'] = {'message':_vData,'type':'voice'};
            }else if(isImg){
            	sx(isVoiceData.serverId, message);
            }else{
            	if(message.from == _toWho){
	            	var _txtDom = sendTimeFunc()+'<li class="hisMsg"><div class="portrait_left"><img src="'+ tHeadImg +'" width="100%""></div><div class="chtext_left"><span>'+message.data+'</span></div></li>';
					$('#msnPlace').append(_txtDom);
            	}
                msgD['msg'] = {'message':message.data,'type':'txt'};
            }
            msgD['who'] = 'other';
            msgD['isRead'] = 'isRead';
            msgD['time'] = formatterDate(new Date);
            if(!isImg){
	            //_history.push(msgD);
	            //localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
	            addHistory(message.from, msgD);
	            setTimeout(scrollToBottom,300);
            }
        },  //收到文本消息
        onEmojiMessage: function (message) {
            // 当为WebIM添加了Emoji属性后，若发送的消息含WebIM.Emoji里特定的字符串，connection就会自动将
            // 这些字符串和其它文字按顺序组合成一个数组，每一个数组元素的结构为{type: 'emoji(或者txt)', data:''}
            // 当type='emoji'时，data表示表情图像的路径，当type='txt'时，data表示文本消息
        	if(message.from != _toWho){
        		return;
        	}
            var data = message.data;
            for (var i = 0, l = data.length; i < l; i++) {
                console.log(data[i]);
            }
        },   //收到表情消息
        onPictureMessage: function (message) {
            var options = {url: message.url};
            options.onFileDownloadComplete = function () {
                // 图片下载成功
                var _dId = 'chatImg_' + message.id;
                if(message.from == _toWho){
	                var _domPic = sendTimeFunc()+'<li class="hisMsg"><div class="portrait_left"><img src="'+ tHeadImg +'" width="100%""></div><div class="chtext_left"><div class="msnCon"><img src="'+message.url+'" id="'+_dId+'"></div></div></li>'
    	            $('#msnPlace').append(_domPic);
            	}								
                imgLocalStorage(_dId,message.url,function(_url){
                    var msgD = {};
                    msgD['who'] = 'other';
                    msgD['isRead'] = 'isRead';
                    msgD['msg'] = {'message':_url,'type':'img'};
                    msgD['time'] = formatterDate(new Date);
                    //_history.push(msgD);
                    //localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
                    addHistory(message.from, msgD);
                });
                setTimeout(scrollToBottom,300)
            };
            options.onFileDownloadError = function () {
                // 图片下载失败
            };
            WebIM.utils.download.call(conn, options);       // 意义待查

        }, //收到图片消息
        
        onVideoMessage: function (message) {
        	if(message.from != _toWho){
        		return;
        	}
            var node = document.getElementById('privateVideo');
            var option = {
                url: message.url,
                headers: {
                    'Accept': 'audio/mp4'
                },
                onFileDownloadComplete: function (response) {
                    var objectURL = WebIM.utils.parseDownloadResponse.call(conn, response);
                    node.src = objectURL;
                },
                onFileDownloadError: function () {
                    console.log('File down load error.')
                }
            };
            WebIM.utils.download.call(conn, option);
        },   //收到视频消息
        onOnline: function () {
            console.log('onLine');
        },                  //本机网络连接成功
        onOffline: function () {
            console.log('offline');
        },                 //本机网络掉线
        onError: function (message) {
            console.log('Error');
        }
    });
    // 初始化WebRTC Call
    var rtcCall = new WebIM.WebRTC.Call({
        connection: conn,
        mediaStreamConstaints: {
            audio: true,
            video: true
        },
        listener: {
            onAcceptCall: function (from, options) {
                console.log('onAcceptCall::', 'from: ', from, 'options: ', options);
            },
            onGotRemoteStream: function (stream) {
                console.log('onGotRemoteStream::', 'stream: ', stream);
                var video = document.getElementById('video');
                video.src = window.URL.createObjectURL(stream);
            },
            onGotLocalStream: function (stream) {
                console.log('onGotLocalStream::', 'stream:', stream);
                var video = document.getElementById('localVideo');
                video.src = window.URL.createObjectURL(stream);
            },
            onRinging: function (caller) {
                console.log('onRinging::', 'caller:', caller);
            },
            onTermCall: function (reason) {
                console.log('onTermCall::');
                console.log('reason:', reason);
            },
            onIceConnectionStateChange: function (iceState) {
                console.log('onIceConnectionStateChange::', 'iceState:', iceState);
            },
            onError: function (e) {
                console.log(e);
            }
        }
    });
    
// open，登录
    var options = {
        apiUrl: WebIM.config.apiURL,
        user: fromWho,
        pwd: fromWhoPwd,
        appKey: WebIM.config.appkey
    };
    conn.open(options);
    // 私聊发送文本消息，发送表情同发送文本消息，只是会在对方客户端将表情文本进行解析成图片
	 var sendPrivateText = function () {
		
    	ajaxEngine.postAjax("/user/checkSendMsg.html", {tuserId:parseInt(_toWho)}, function(rslt){
    		if(rslt.code == 1){
		        var message = $('#sendMsg').val();
		        if($.trim(message) == ""){
		            layer.open({
		                content: '发送内容不能为空',
		                time: 2
		            });
		            return;
		        }
		        var id = conn.getUniqueId();
		        var msg = new WebIM.message('txt', id);
		        msg.set({
		            msg: message,                       // 消息内容
		            to: _toWho,                          // 接收消息对象
		            roomType: false,
		            success: function (id, serverMsgId) {
		            	addMsgCount(message);
		                var _dom = sendTimeFunc()+'<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%""></div><div class="chtext_right"><span>'+message+'<span></div></li>';
		                $('#msnPlace').append(_dom);
		                $('#sendMsg').val('');
		                var msgD = {};
		                msgD['who'] = 'my';
		                msgD['isRead'] = 'isRead';
		                msgD['msg'] = {'message':message,'type':'txt'};
		                msgD['time'] = formatterDate(new Date);
		                _history.push(msgD);
		                localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
		                $('#sendMsg').focus();
						$("#sendMsg").css("height","2.4rem")
		                scrollToBottom();
		            }
		        });
		        msg.body.chatType = 'singleChat';
		        conn.send(msg.body);
    		}else{
    			alert(rslt.info);
    		}
    	});
    };
	//
    //发送名片
   
    //发送语音
    var sendVoiceFunc = function (msgs) {
    	ajaxEngine.postAjax("/user/checkSendMsg.html", {tuserId:parseInt(_toWho)}, function(rslt){
    		if(rslt.code == 1){
		    	var id = conn.getUniqueId();
				var msg = new WebIM.message('txt', id);
				var message = msgs;
				msg.set({
					msg: message,                       // 消息内容
					to: _toWho,                          // 接收消息对象
					roomType: false,
			   success: function (id, serverMsgId) {
            	addMsgCount("[语音]:" + msgs);
            	var _data = JSON.parse(msgs);
				var msgD = {},_vData = {
                "localId":_data.localId,
                "times":_data.times
               };
				$('#msnPlace').append(sendTimeFunc()+'<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%""></div><div class="chtext_right"><div class="msnCon voice" onclick="playMic(0,\'' + _data.localId + '\',\''+_data.times+'\',this)"><i></i> '+_data.times+'\'\'</div></div></li>');
				
                msgD['who'] = 'my';
                msgD['isRead'] = 'isRead';
                msgD['msg'] = {'message':_vData,'type':'voice'};
                msgD['time'] = formatterDate(new Date);
                _history.push(msgD);
                localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
                setTimeout(scrollToBottom,300)
				}
				});
				msg.body.chatType = 'singleChat';
				conn.send(msg.body);
    		} else {
    			alert(rslt.info);
    		}
    	});
    };
    // 私聊发送图片消息
    var sendPrivateImg = function () {
    	ajaxEngine.postAjax("/user/checkSendMsg.html", {tuserId:parseInt(_toWho)}, function(rslt){
    		if(rslt.code == 1){
		        var id = conn.getUniqueId();
		        var msg = new WebIM.message('img', id);
		        var input = document.getElementById('msnPicture');           // 选择图片的input
		        var file = WebIM.utils.getFileUrl(input);                   // 将图片转化为二进制文件
		        var allowType = {
		            'jpg': true,
		            'jpeg': true,
		            'gif': true,
		            'png': true,
		            'bmp': true
		        };
		        if (file.filetype.toLowerCase() in allowType) {
		            var option = {
		                apiUrl: WebIM.config.apiURL,
		                file: file,
		                to: _toWho,
		                roomType: false,
		                chatType: 'singleChat',
		                onFileUploadError: function () {
		                    console.log('onFileUploadError');
		                },
		                onFileUploadComplete: function () {
		                    console.log('onFileUploadComplete');
		                },
		                success: function () {
		                	addMsgCount("[图片]:"+file.url);
		                	var _dId = 'chatImg_' + id;
							
		                    var _dom = sendTimeFunc()+'<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%"></div><div class="chtext_right"><div class="msnCon"><img src="'+file.url+'" id="'+_dId+'"></div></div></li>';
		                    $('#msnPlace').append(_dom);							
							$(".msnCon img").on("mousedown",function(){
								var Imgs = $(this).attr("src")
									$("#Bigimg").attr("src",Imgs)
								$("#Bigimg").fadeIn(600)
								$(".zhezhao").fadeIn(600)
								console.log($(this).height())
								if($(this).height() < 300){
										$("#Bigimg").css("top","25%")
									}else{
										$("#Bigimg").css("top",0)
									}
							})
		                    imgLocalStorage(_dId,file.url,function(_url){
		                        var msgD = {};
		                        msgD['who'] = 'my';
		                        msgD['isRead'] = 'isRead';
		                        msgD['msg'] = {'message':_url,'type':'img'};
		                        msgD['time'] = formatterDate(new Date);
		                        _history.push(msgD);
		                        localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
		                    });
		                    $('#msnPicture').clear();
		                    setTimeout(scrollToBottom,300)
							
		                }
		                // flashUpload: WebIM.flashUpload               // 意义待查
		            };
		            msg.set(option);
		            conn.send(msg.body);
		        }
    		} else {
    			alert(rslt.info);
    		}
    	});
    };
    
    var recorder,audio = document.querySelector('audio');
    var _chatRecord = {};
    //时间转换 毫秒
    function todate(str_time){
        var dateArr = str_time.substring(0,10).split('-');
        var timeArr = str_time.substring(11,16).split(':');
        return new Date(parseInt(dateArr[0]),parseInt(dateArr[1]) - 1,parseInt(dateArr[2]),parseInt(timeArr[0]),parseInt(timeArr[1]));
    }
    //聊天记录回显
    var releaseChatRecord = function(_history) {
        $('#msnPlace').empty();
        var _chatRecord = _history;
        $.each(_chatRecord,function(i,v){
            var dom = '',_vType = 0,_data = v.msg;
            if($('#msnPlace li.timeMsg').length > 0){
                var lastTime = todate($('li.timeMsg:last').text()),nowTime = todate(v.time);
                if(nowTime - lastTime > 900000){
                    dom += '<li class="timeMsg">'+v.time+'</li>'
                };
            }else{
                dom += '<li class="timeMsg">'+v.time+'</li>'
            }
            if(v.who == 'other'){
               dom += '<li class="hisMsg"><div class="portrait_left"><img src="'+ tHeadImg +'" width="100%""></div><div class="chtext_left">';
               _vType = 1
            }else{
               dom += '<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%""></div><div class="chtext_right">';
               _vType = 0
            };
            if(_data.type == 'voice'){
                dom += '<div class="msnCon voice" onclick="playMic('+_vType+',\'' + _data.message.localId + '\',\''+_data.message.times+'\',this)"><i></i> '+_data.message.times+'\'\'</div></li>'
            }
            if(_data.type == 'txt'){
                dom += '<span>'+_data.message+'</span></div></li>'
            }
            if(_data.type == 'img'){
                dom += '<div class="msnCon"><img src="'+_data.message+'"></div></li>'
            };
            if(_data.type == 'img2'){
                dom += '<div class="msnCon"><img src="'+_data.message.localId+'" serverId="' + _data.message.serverId + '" onerror="reLoadImg();"></div></li>'
            };
			
            $('#msnPlace').append(dom);
            v['isRead'] = 'isRead';
        });
        localStorage[WECHAT_HISTORY] = JSON.stringify(_chatRecord);
    };
    
    $(document).ready(function(){
        var now = new Date().getTime();
        var nonceStr = "ZhuBaoShi";
        var signature = "";
        var wechatback =function(rslt){
            signature = rslt.data;
			console.log(signature)
            wx.config({
            debug: false, 
            appId: 'wx8858c54271aadf64', 
            timestamp: now, 
            nonceStr: nonceStr, 
            signature: signature,
            jsApiList: ['startRecord'
                        ,'stopRecord'
                        ,'onVoiceRecordEnd'
                        ,'playVoice'
                        ,'pauseVoice'
                        ,'stopVoice'
                        ,'onVoicePlayEnd'
                        ,'uploadVoice'
                        ,'downloadVoice'
                        ,'chooseImage'
                        ,'previewImage'
                        ,'uploadImage'
                        ,'downloadImage'
                        ,'getLocalImgData'
						] 
            });
            var times = null
            $('#sendVoice').bind('touchstart',function(e){
                e.preventDefault();
                wx.startRecord();
                timeIndex = 0;
                setTime();
                times = setInterval(setTime, 1000);
                $('#voiceing').show();
            }).bind('touchend',function(){
                clearInterval(times);
                $('#voiceing').hide();
                wx.stopRecord({
                    success: function (res) {
                        var localId = res.localId;
                    //   sendVoice(localId,0);
                        upMic(localId, function(_serverId){
                            var voiceData = {};
                            voiceData.serverId = _serverId;
                            voiceData.localId = localId;
                            voiceData.times = timeIndex;
                            voiceData.type = 1;
                            voiceData = JSON.stringify(voiceData);
                            sendVoiceFunc(voiceData);
                        });
                    }
                });
            })
        }
        var __url = window.location.href;
        ajaxEngine.postAjax("/getWeChatSign.html", {timestamp:now, noncestr:nonceStr, url:__url}, wechatback);
    });
    var getPixelRatio = function(context) {
    	  var backingStore = context.backingStorePixelRatio ||
    	    context.webkitBackingStorePixelRatio ||
    	    context.mozBackingStorePixelRatio ||
    	    context.msBackingStorePixelRatio ||
    	    context.oBackingStorePixelRatio ||
    	    context.backingStorePixelRatio || 1;
    	   return (window.devicePixelRatio || 1) / backingStore;
   	};
    //图片本地缓存
    var imgLocalStorage = function(id,src,_callback){
        //<!--image1-->
       // var gsFiles = JSON.parse(localStorage.getItem("gsFiles")) || {};
        var a = document.getElementById(id);
        a.addEventListener("load", function () {
            if($('#chatImg').length == 0){
                var imgCanvas = document.createElement("canvas");
                imgCanvas.setAttribute("id", "chatImg");
            }else{
                var imgCanvas = document.getElementById("chatImg");
            };
            imgContext = imgCanvas.getContext("2d");
            // 确保canvas尺寸和图片一致
            imgCanvas.width = 4*a.width;
            imgCanvas.height = 4*a.height;
           // var ratio = getPixelRatio(imgContext);
            // 在canvas中绘制图片
            imgContext.drawImage(a, 0, 0, a.width*4, a.height*4);
            // 将图片保存为Data URI
            if(_callback){
                _callback(imgCanvas.toDataURL(src))
            }
        }, false);
    }
    function scrollToBottom(){
        setTimeout(function(){$('body').scrollTop( $('body')[0].scrollHeight)},500);
    }
    $(function(){
        $('#sendMsg').bind('focus',function(){
            scrollToBottom();
        })
        //文本
        if(navigator.userAgent.match(/mobile/i)) { 
            $('#sendMsgForm').on('submit', function(e){
              sendPrivateText();
              return false;
            });
        }else{
            $('#sendMsg').unbind('keydown').bind('keydown',function(e){
                var ev = document.all ? window.event : e; if(ev.keyCode==13) {sendPrivateText()} 
            }); 
        }
        //图片
        $('#msnPicture').unbind('change').bind('change',function(){
            sendPrivateImg()
        })
        if(localStorage[WECHAT_HISTORY]){
            releaseChatRecord(JSON.parse(localStorage[WECHAT_HISTORY]));
            setTimeout(scrollToBottom,300)
        }
       $('#msnMic').on('mousedown',function(){
            $('#sendVoice').show();
            $(".ui-input-text").hide()
            $(".msnLeft").hide()
            $(".jianp").show()
            $(".micSubmitBtn").hide()
        });
        $(".jianp").on("mousedown",function(){
			$("#msnMic").show()
        	 $(".micSubmitBtn").show()
        	  $(".ui-input-text").show()
        	  $('#sendVoice').hide();
        	     $(".jianp").hide()
        })
    });
    function addMsgCount(msg){
    	ajaxEngine.postAjax("/user/sendMsg.html",{msg:msg,id:${id}}, function(rslt){
    		console.log(rslt);
    	});
      
    };
    function addMsgCount(msg){
    	ajaxEngine.postAjax("/user/sendMsg.html",{msg:msg,id:${id}}, function(rslt){
    		console.log(rslt);
    	});
      
    };
    
    var cx = function(){
    	wx.chooseImage({
    	    count: 1, // 默认9
    	    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
    	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
    	    success: function (res) {
    	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
    	        setTimeout(ux(localIds),100);
    	    }
    	});
    };
    
    var ux = function(localIds){
    	var localId = localIds[0];
    	wx.uploadImage({
    	    localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
    	    isShowProgressTips: 1, // 默认为1，显示进度提示
    	    success: function (res) {
    	        var serverId = res.serverId; // 返回图片的服务器端ID
    	        var _dId = "chatImg2_" + serverId;
    	        var picData = {};
    	        picData.serverId = serverId;
    	        picData.localId = localId;
    	        picData.type = 2;
    	        picData = JSON.stringify(picData);
    	        sendPicFunc(picData);
    	       // $('#msnPlace').append(sendTimeFunc()+'<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%"></div><div class="chtext_right"><div class="msnCon"><img src="'+localIds+'" id="'+_dId+'"></div></div></li>');
    	    }
    	});
    };
    
    var sx = function(serverId, message){
    	var message = message;
    	var serverId = serverId;
    	wx.downloadImage({
    	    serverId: serverId, // 需要下载的图片的服务器端ID，由uploadImage接口获得
    	    isShowProgressTips: 1, // 默认为1，显示进度提示
    	    success: function (res) {
    	        var localId = res.localId; // 返回图片下载后的本地ID
    	        var _dId = 'chatImg2_' + message.id;
    	        if(message.from == _toWho){
	    	        $('#msnPlace').append(sendTimeFunc()+'<li class="hisMsg"><div class="portrait_left"><img src="'+ tHeadImg +'" width="100%""></div><div class="chtext_left"><div class="msnCon"><img src="'+localId+'" id="'+_dId+'" serverId="' + serverId + '" "></div></div></li>');
    	    	}
    	       
    	        var msgD = {},_vData = {
    	            "localId":localId,
    	            "serverId":serverId
    	        };
    	        msgD['who'] = 'other';
                msgD['isRead'] = 'isRead';
                msgD['time'] = formatterDate(new Date);
                msgD['msg'] = {'message':_vData,'type':'img2'};
                //_history.push(msgD);
	            //localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
	            addHistory(message.from, msgD);
	            setTimeout(scrollToBottom,300);
    	    }
    	});
    };
    
    var sendPicFunc = function (msgs) {
    	ajaxEngine.postAjax("/user/checkSendMsg.html", {tuserId:parseInt(_toWho)}, function(rslt){
    		if(rslt.code == 1){
		    	var id = conn.getUniqueId();
				var msg = new WebIM.message('txt', id);
				var message = msgs;
				msg.set({
					msg: message,                       // 消息内容
					to: _toWho,                          // 接收消息对象
					roomType: false,
			   success: function (id, serverMsgId) {
            	addMsgCount("[图片]:" + msgs);
            	var _data = JSON.parse(msgs);
				var msgD = {},_vData = {
                "localId":_data.localId,
                "serverId":_data.serverId
               };
				var _dId = 'chatImg2_' + message.id;
				$('#msnPlace').append($('#msnPlace').append(sendTimeFunc()+'<li class="myMsg al"><div class="portrait_right"><img src="${selfHeadImage}" width="100%"></div><div class="chtext_right"><div class="msnCon"><img src="'+_data.localId+'" id="'+_dId+'"></div></div></li>'));
                msgD['who'] = 'my';
                msgD['isRead'] = 'isRead';
                msgD['msg'] = {'message':_vData,'type':'img2'};
                msgD['time'] = formatterDate(new Date);
                _history.push(msgD);
                localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
                setTimeout(scrollToBottom,300);
				}
				});
				msg.body.chatType = 'singleChat';
				conn.send(msg.body);
    		} else {
    			alert(rslt.info);
    		}
    	});
    };
    
    var addHistory = function(from, msgD){
    	if(from == _toWho){
    		_history.push(msgD);
    		localStorage[WECHAT_HISTORY] = JSON.stringify(_history);
    	} else {
    		var key = fromWho + '-' + from;
    		var __history = localStorage[key] ? JSON.parse(localStorage[key]) : [];
    		__history.push(msgD);
    		localStorage[key] = JSON.stringify(__history);
    	}
    };
    
    var reLoadImg = function(){
    	var serId = $(this).attr("serverId");
    	var me = $(this);
    	if(serId && serId != null){
    		wx.downloadImage({
        	    serverId: serId, // 需要下载的图片的服务器端ID，由uploadImage接口获得
        	    isShowProgressTips: 0, // 默认为1，显示进度提示
        	    success: function (res) {
        	        var localId = res.localId; // 返回图片下载后的本地ID
        	      me.attr("src", localId);
        	    }
        	});
    	}
    };
    </script>
</html>