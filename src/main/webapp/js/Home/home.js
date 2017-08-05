$(function(){
$(".hottou").on("mousedown", function() {
		$(".erwm").fadeIn(400)
		$(".zhezhao").fadeIn(400)
	})
	$(".zhezhao").on("mousedown", function() {
		$(".erwm").fadeOut(400)
		$(".zhezhao").fadeOut(400)
	})
	scrollLoad()
	
var swiper = new Swiper(".swiper-container", {
	//自动播放
	autoplay: 1500,
	//循环播放
	loop: true,
	//分页器的声明
	pagination: '.swiper-pagination',
	paginationClickable: true
});
document.addEventListener("touchmove", function(e) {
	e.preventDefault();
})
var Page = parseInt(Math.random() * 25)
//点击换一换
$(".hotx a").on("mousedown", function() {
	Page = parseInt(Math.random() * 25)
$.ajax({
	url: '/user/getUserList.html',
	type: 'GET',
	data: {
		isPaging: 1,
		pageSize: 3,
		curPage: Page
	},
	dataType: 'json',
	async: true,
	success: function(msg) {
		if(msg.code == "1") {
			//正常业务逻辑
			var str = ""
			for(var i = 0; i < msg.data.length; i++) {
				str +=
					'<li>' +
					'<div class="bigmsg attr-title" title="' + msg.data[i].userId + '">' +
					'<div class="bigpic">' +
					'<img src="' + msg.data[i].headImage + '" width="100%" height="100%">' +
					'</div>' +
					'<div class="bigtxt">' +
					'<h3><span class="name">' + msg.data[i].nickname + '</span><span class="zhiwei">' + msg.data[i].occupationName + '</span></h3>' +
					'<p>' +
					'<span>' + msg.data[i].companyName + '</span>' +
					'</p>' +
					'</div>' +
					'</div>' +
					'<a href="##">交谈</a>' +
					'</li>'
			}
			$(".hotx ul").html(str)
			iscroll.refresh()
			$(".hotx ul li a").on("mousedown", function() {
				var userId = $(this).siblings(".bigmsg").attr("title")
				location.href = "/user/toUserCenter.html?userId=" + userId + "&from=singlemessage&isappinstalled=0"
			})
			$(".bigpic").on("mousedown", function() {
				var userId = $(this).parents(".bigmsg").attr("title")
				location.href = "/user/toUserCenter.html?userId=" + userId + "&from=singlemessage&isappinstalled=0"
			})
		} else {
			console.log(msg.info);
		}
	}
});
})
//用户列表
$.ajax({
	url: '/user/getUserList.html',
	type: 'GET',
	data: {
		isPaging: 1,
		pageSize: 3,
		curPage: Page
	},
	dataType: 'json',
	cache: true,
	ansy: true,
	success: function(msg) {
		console.log(msg)
		if(msg.code == "1") {
			//正常业务逻辑
			var str = ""
			for(var i = 0; i < msg.data.length; i++) {
				str +=
					'<li>' +
					'<div class="bigmsg attr-title" title="' + msg.data[i].userId + '">' +
					'<div class="bigpic">' +
					'<img src="' + msg.data[i].headImage + '" width="100%" height="100%">' +
					'</div>' +
					'<div class="bigtxt">' +
					'<h3><span class="name">' + msg.data[i].nickname + '</span><span class="zhiwei">' + msg.data[i].occupationName + '</span></h3>' +
					'<p>' +
					'<span>' + msg.data[i].companyName + '</span>' +
					'</p>' +
					'</div>' +
					'</div>' +
					'<a href="##">交谈</a>' +
					'</li>'

			}
			$(".hotx ul").html(str)
			iscroll.refresh()
			$(".hotx ul li a").on("mousedown", function() {
				var userId = $(this).siblings(".bigmsg").attr("title")
				location.href = "/user/toUserCenter.html?userId=" + userId + "&from=singlemessage&isappinstalled=0"
			})
			$(".bigpic").on("mousedown", function() {
				var userId = $(this).parents(".bigmsg").attr("title")
				location.href = "/user/toUserCenter.html?userId=" + userId + "&from=singlemessage&isappinstalled=0"
			})
		} else {
			console.log(msg.info);
		}
	},
	error: function() {
		console.log("失败")
	}

});
//轮播图数据
$.ajax({
	url: '/admin/banner/getBannerList.html',
	type: 'GET',
	success: function(msg) {
		var str, str1, str2 = ""
		for(var i = 0; i < msg.data.length; i++) {
			str = ' <img src="' + msg.data[0].cover.replace("-smaller", "") + '" width="100%" >'
			str1 = ' <img src="' + msg.data[1].cover.replace("-smaller", "") + '" width="100%" >'
			str2 = ' <img src="' + msg.data[2].cover.replace("-smaller", "") + '" width="100%" >'
		}
		$(".swiper-slide").eq(0).append(str2)
		$(".swiper-slide").eq(1).append(str)
		$(".swiper-slide").eq(2).append(str1)
		$(".swiper-slide").eq(3).append(str2)
		$(".swiper-slide").eq(4).append(str)
		iscroll.refresh()
	}
})
//轮播图链接
$(".c").on("mousedown", function() {
	location.href = "https://mp.weixin.qq.com/s?sn=a04ccbc18600f1912ef303d92003d043&__biz=MzU1NjA1NzYxMA%3D%3D&mid=2247483661&idx=1&chksm=fbcb9468ccbc1d7ef53091310e25ddd3cc5be781a2061269b6b270688d5b46c1012eefcdc6f8#rd"
})
$(".b").on("mousedown", function() {
	location.href = "https://mp.weixin.qq.com/s?sn=2d332a8605252c958a9e55a6c6ecfd95&__biz=MzU1NjA1NzYxMA%3D%3D&mid=2247483661&idx=2&chksm=fbcb9468ccbc1d7ed7a22cf20060320977356db89a2596d8cc66fbcc9104a930d2892bb96076#rd"
})
$(".a").on("mousedown", function() {
	location.href = "http://m.upbirds.com/user/toUserCenter.html?userId=91&from=singlemessage&isappinstalled=0"
})
//精选长文
var page = 1
changwen(0)
//随笔
suibi(0)
//发送评论
$(".buttn").on("mousedown", function() {
	//console.log("asd")
	var val = $(this).siblings("#text").val()
	var Id = $(this).parents(".sbsearch").siblings(".bigmsg").attr("name")
	var e = $(this).parents(".segu").index()
	if(val == "") {
		alert("请输入内容")
	} else {
		$.ajax({
			url: '/dynamic/comment.html',
			type: 'POST',
			data: {
				content: val,
				dynamicId: Id
			},
			success: function(msg) {
				$(".text").val("")
				$(this).parents(".sbsearch").siblings(".plcontent").find("ul").empty()
				pingl(Id, 0, e)
				iscroll.refresh()
			}
		})
	}
})

	//统计点击量
	$(".segu_a").on("mousedown", function() {
		var IDS = $(this).siblings(".bigmsg").attr("name")
		$.ajax({
			url: 'dynamic/getById.html',
			type: 'POST',
			data: {
				dynamicId: IDS
			},
			success: function() {
			}
		})
	})
	document.addEventListener("touchend", function() {
		//无限加载
		if(iscroll.y < iscroll.maxScrollY - 50) {
			page = page + 1
			changwen(page)
			suibi(page)			
			var val = $(".stars")
			//console.log(msg.data.occType)
			for(var a = 0; a < val.length; a++) {
				//console.log($(".stars").eq(0).find(".bigmsg").attr("con"))
				if($(".stars").eq(a).find(".bigmsg").attr("con") == 'true') {
					$(".zan").eq(a).find("i").eq(0).css({ "background": "url('../../../img/zanx@2x.png') no-repeat", "background-size": "100% 100%" })
				}
				if($(".stars").eq(1).find(".bigmsg").attr("con") == 'false') {
					$(".zan").eq(a).find("i").eq(0).css({ "background": "url('../../../img/zanw@2x.png') no-repeat", "background-size": "100% 100%" })
				}
			}
		}
	})
})


//评论列表
function pingl(mg, mm, e) {
	$.ajax({
		url: '/dynamic/loadingMoreComment.html',
		type: 'GET',
		data: {
			isPaging: 1,
			pageSize: 5,
			curPage: mm,
			dynamicId: mg
		},
		success: function(msg) {
			var str = ""
			for(var i = 0; i < msg.data.length; i++) {
				str += '<li><span class="nam">' + msg.data[i].nickname + ' :</span>' + msg.data[i].content + '</li>'
			}
			$(".stars").eq(e).find(".plcontent ul").html(str)
			iscroll.refresh()
		}
	})
}	


function addb() {	
	//点击
	$(".ping").off("mousedown").on("mousedown", function() {
		var ej = $(this).parents(".segu").index()
		console.log(ej)
		if($(".stars").eq(ej).find(".foot .ping").hasClass("tt")) {
			$(".stars").eq(ej).find(".sbsearch").css("display", "none")
				$(".stars").eq(ej).find(".plcontent").hide()
				$(".stars").eq(ej).find(".foot .ping").removeClass("tt")
				iscroll.refresh()
		} else {
			//debugger
				$(".stars").eq(ej).find(".sbsearch").css("display", "flex")
				$(".stars").eq(ej).find(".plcontent").show()
				var Id = $(".stars").eq(ej).find(".bigmsg").attr("name")
				$(".stars").eq(ej).find(".plcontent ul").empty()
				pingl(Id, 0, ej)
				$(this).addClass("tt")
				iscroll.refresh()
		}
	})
	var indexs = $(".more")
	for(var i = 0; i < indexs.length; i++) {
		indexs[i].addEventListener('click', function() {
			var Ids = $(this).parents(".plcontent").siblings(".bigmsg").attr("name")
			var e = $(this).parents(".segu").index()
			$.ajax({
				url: '/dynamic/loadingMoreComment.html',
				type: 'GET',
				data: {
					curPage: 0,
					dynamicId: Ids
				},
				success: function(msg) {
					var str = ""
					for(var i = 0; i < msg.data.length; i++) {
						str += '<li><span class="nam">' + msg.data[i].nickname + ' :</span>' + msg.data[i].content + '</li>'
						$(".stars").eq(e).find(".plcontent ul").empty()
						$(".stars").eq(e).find(".plcontent ul").html(str)
					}
					iscroll.refresh()
				}
			})
			iscroll.refresh()
		}, false);
	}
}


function suibi(page) {
	$.ajax({
		url: '/dynamic/getList.html',
		type: 'GET',
		data: {
			isPaging: 1,
			pageSize: 4,
			curPage: page
		},
		dataType: 'json',
		async: false,
		success: function(msg) {
			if(msg.code == "1") {
				//正常业务逻辑
				//console.log(msg.data)
				for(var i = 0; i < msg.data.length; i++) {
					//console.log(msg.data[i].imgUrls.split("<split>"))
					var str = "";
					str +=
						'<div class="segu stars" id="segu">' +
						'<div class="bigmsg attr-title" title="' + msg.data[i].userId + '" name="' + msg.data[i].id + '" con="' + msg.data[i].isStar + '" style="width:100%">' +
						'<div class="bigpic">' +
						'<img src="' + msg.data[i].headImage + '" width="100%" height="100%">' +
						'</div>' +
						'<div class="bigtxt" style="width:60%">' +
						'<h3><span class="name">' + msg.data[i].nickname + '</span><span class="zhiwei">' + msg.data[i].occupationName + '</span></h3>' +
						'<p>' +
						'<span>' + msg.data[i].companyName + '</span>' +
						'</p>' +
						'</div>' +
						'<a href="##">交流</a>' +
						'</div>' +
						'<div class="segu_text">' +
						'<p>' + msg.data[i].content + '</p>' +
						'</div>' +
						'<span class="segu_a">全文</span>' +
						'<div class="segu_pic" id="glist">' +
						'<ul>' +
						'</ul>' +
						'</div>' +
						'<div class="line2"></div>' +
						'<div class="foot">' +
						'<div class="af zan"><i></i><i>' + msg.data[i].starTimes + '</i></div>' +
						'<div class="af ping"><i></i><i>' + msg.data[i].commentTimes + '</i></div>' +
						'</div>' +
						'<div class="kong"></div>' +
						'<div class="sbsearch"><input type="text" id="text" class="text" placeholder="发表评论…"/><div class="buttn">发送</div></div>' +
						'<div class="plcontent"><ul></ul><span class="more">加载更多</span></div>' +
						'</div>'
					$(".article_").append(str)
					var navScoll = new IScroll(".segu_pic", {
						scrollX: true,
						preventDefault: false,
						click: true
					})
					$(".bigmsg a").on("mousedown", function() {
						var userId = $(this).parent().attr("title")
						location.href = "/user/toUserCenter.html?userId=" + userId
					})
					$(".title_c").on("mousedown", function() {
						var index = $(this).index()
						$(".title_c").eq(index).removeClass("vk").siblings().addClass("vk")
						if(index == 0) {
							$(".article").hide()
							$(".article_").show()
							navScoll.refresh()
							iscroll.refresh()
						} else if(index == 1) {
							$(".article").show()
							$(".article_").hide()
							iscroll.refresh()
						}
					})
					iscroll.refresh()
					for(var a = 0; a < msg.data[i].imgUrls.split("<split>").length; a++) {
						var aa = msg.data[i].imgUrls.split("<split>")[a]
						if(aa.substr(0, 4)) {
							var sts = "";
							sts +=
								'<li class="segu_t"><img src="' + aa + '" width="100%" height="72px"/></li>'
							$(".segu_pic").eq(i).find("ul").append(sts)
							iscroll.refresh()
						} else {
							if(aa == "") {
							} else {
								var sts = "";
								sts +=
									'<li class="segu_t"><img src="${IMG_URL}' + +'" width="100%" height="72px" /></li>'
								$(".segu_pic").eq(i).find("ul").append(sts)
								iscroll.refresh()
							}
						}
					}
				}
				//展开评论、加载更多
				addb()
				$(".segu_a").off("mousedown").on("mousedown", function() {
						//console.log($(this).index())
						if($(this).hasClass("aa")) {
							$(this).siblings(".segu_text").css({ "height": "3.8rem" })
							$(this).removeClass("aa")
							$(this).html("全文")
						} else {
							$(this).siblings(".segu_text").css("height", "auto")
							$(this).addClass("aa")
							$(this).html("收起")
						}
						iscroll.refresh()
					});
				
				var val = $(".stars")
				//console.log(msg.data.occType)
				for(var a = 0; a < val.length; a++) {
					//console.log($(".stars").eq(0).find(".bigmsg").attr("con"))
					if($(".stars").eq(a).find(".bigmsg").attr("con") == 'true') {
						$(".zan").eq(a).find("i").eq(0).css({ "background": "url('../../../img/zanx@2x.png') no-repeat", "background-size": "100% 100%" })
					}
					if($(".stars").eq(1).find(".bigmsg").attr("con") == 'false') {
						$(".zan").eq(a).find("i").eq(0).css({ "background": "url('../../../img/zanw@2x.png') no-repeat", "background-size": "100% 100%" })

					}
				}
				$(".segu_t img").on("mousedown", function() {
					var Imgs = $(this).attr("src")
					var Imgb = Imgs.replace("-smaller", "")
					$("#Bigimg").attr("src", Imgb)
					$("#Bigimg").fadeIn(600)
					$(".zhezhao").show()
				})
				//点赞
				$(".zan").on("mousedown", function() {
					var index = $(this).parents(".segu").index()
					//console.log(index)
					var Id = $(this).parents(".foot").siblings(".bigmsg").attr("name")
					var star = $(this).parents(".foot").siblings(".bigmsg").attr("con")
					var isstar = $(this).find("i").eq(1).html()
					//console.log(star)
					$.ajax({
						url: '/dynamic/hitStarById.html',
						type: 'POST',
						data: {
							dynamicId: Id
						},
						success: function(msg) {
							if(star == 'true') {
								isstar--
								$(".segu").eq(index).find(".foot i").eq(0).css({ "background": "url('../../../img/zanw@2x.png') no-repeat", "background-size": "100% 100%" })
								$(".segu").eq(index).find(".foot i").eq(1).html(isstar)
								$(".segu").eq(index).find(".bigmsg").attr("con", false)
							}
							if(star == 'false') {
								//console.log($(this))
								isstar++
								$(".segu").eq(index).find(".foot i").eq(0).css({ "background": "url('../../../img/zanx@2x.png') no-repeat", "background-size": "100% 100%" })
								$(".segu").eq(index).find(".foot i").eq(1).html(isstar)
								$(".segu").eq(index).find(".bigmsg").attr("con", true)
							}
						}
					})
				})
				
			} else {
				console.log(msg.info);
			}
		}
	});
}


function changwen(page) {

	$.ajax({
		url: '/article/getList.html',
		type: 'GET',
		data: {
			isPaging: 1,
			pageSize: 4,
			curPage: page
		},
		dataType: 'json',
		async: true,
		success: function(msg) {

			if(msg.code == "1") {
				//正常业务逻辑
				var str = ""
				for(var i = 0; i < msg.data.length; i++) {
					var bb = msg.data[i].headImg.replace("-smaller", "")
					str +=
						'<div class="text_one" id="te" name="' + msg.data[i].id + '" title="' + msg.data[i].isStar + '">' +
						'<div class="headerpic">' +
						'<img src="' + bb + '" width="100%">' +
						'</div>' +
						'<div class="kong"></div>' +
						'<h2>' + msg.data[i].title + '</h2>' +
						'<div class="kong1"></div>' +
						'<p>' + msg.data[i].intro + '</p>' +
						'<div class="kong"></div>' +
						'<div class="kong1"></div>' +
						'</div>' +
						'<div class="kong2"></div>'

				}
				$(".article").append(str)
				iscroll.refresh()
				$(".text_one").on("mousedown", function() {
					var Id = $(this).attr("name")
					var tf = $(this).attr("title")
					localStorage.setItem("key", tf);
					location.href = "/article/getById.html?id=" + Id + "&from=singlemessage&isappinstalled=0"
				})
			} else {
				console.log(msg.info);
			}
		}

	});
}


function scrollLoad(){
		 iscroll = new IScroll("#wrapper",{
			mouseWheel: true,
			preventDefault: false
		})
	}