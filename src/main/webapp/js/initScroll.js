$.fn.scrollPage = function(options) {
    var o = $.extend({
        wrapper: null,
        scroller:null,
        listContainer: null,
        listDom: function(data){
            return '<li></li>'
        },
        noUpBtn:false,
        url: null,
        ajaxType: 'get',
        ajaxDataType: 'json',
        data: null,
        callback:null,
        onMoveDownFunction:null,
        onMoveUpFunction:null,
        onEndDownFunction:null,
        onEndUpFunction:null,
        dataCallback:null,
        qarmData : ''
    }, options || {});
    var self = this;
    self.close = function() {
        $.mobile.loading('hide');
    };
    self.inputId = "scrollData_" + o.scroller;
    self.pullDownId = "pullDown_" + o.scroller;
    self.pullUpId = "pullUp_" + o.scroller;
    if ($('#' + self.inputId).length == 0) {
        self.dataInput = $('<input type=hidden id="' + self.inputId + '">').appendTo('#' + o.wrapper);
    };
    if ($('#' + self.pullDownId).length == 0 && !o.noUpBtn) {
        self.pullDown = $('<div id="'+self.pullDownId+'"><span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span></div>').prependTo('#' + o.scroller);
    };
    if ($('#' + self.pullUpId).length == 0) {
        self.pullUp = $('<div id="'+self.pullUpId+'"><span class="pullUpIcon"></span><span class="pullUpLabel">上拉刷新...</span></div>').appendTo('#' + o.scroller);
    };
    if ($('#scroll_pop').length == 0) {
        self.popup = $('<div data-role="popup" id="scroll_pop">'+
        '<div data-role="header" data-theme="d"><h2>提示</h2></div>'+
        '<div data-role="content" class="ui-bar-b" data-theme="d"><p id="scroll_message"></p></div>'+
        '<div dataa-role="foot" data-theme="a">'+
        '<a data-role="button" data-transition="flow" data-theme="d" onclick="$.mobile.loading(\'hide\')" data-rel="back">确定</a>'+
        '</div></div>').appendTo('body');
    }
    $('#' + self.inputId).removeData().data(o.data);
    self.ifs = true;
    self.page = 1;

    self.init = function() {
        $.ajax({
            type: o.ajaxType,
            url: o.url + "?curPage=1"+o.qarmData,
            data: $('#' + self.inputId).data(),
            dataType: o.ajaxDataType,
            success: self.callback
        });
    };

    self.callback = function(data) {
            var obj = eval(data);
            var tree = "";
            if (obj.data.length != 0) {
                $.each(obj.data, function(i, item) {
                    $("#" + o.listContainer).append(o.listDom(item));
                })
                $("#" + o.listContainer).listview("refresh");
                self.close();
                self.myScroll.refresh(); 
                self.ifs = true;
            } else {
                //$("#scroll_message").text("网络不稳定或者数据异常,请稍后再试"); //判断没有数据的时候弹出框
                //$("#scroll_pop").popup("open");
                self.CLoses();
                $('#'+self.pullDownId).hide();
                $('#'+self.pullUpId).hide();
                self.ifs = false;
                $('<li class="noResult">未找到查询结果</li>').appendTo("#" + o.listContainer);
            };
            if(obj.data.length < $('#' + self.inputId).data().pageSize){
                $('#'+self.pullUpId).hide();
            }
            if(o.dataCallback){
                o.dataCallback(data)
            }
        }
        //页面加载完 转圈消失
    self.CLoses = function() {
        $.mobile.loading("hide");
    };
    self.generatedCount = 1;
    /**
     * 下拉刷新 （自定义实现此方法）
     * myScroll.refresh();    // 数据加载完成后，调用界面更新方法
     */
    self.pullDownAction = function() {
            if (self.ifs == false) { //当因为网络或者是其他原因没有加载出来，下拉再一次刷新数据
                $.ajax({
                    type: o.ajaxType,
                    url: o.url + "?curPage=1"+o.qarmData,
                    data: $('#' + self.inputId).data(),
                    dataType: o.ajaxDataType,
                    success: function(data) {
                        var obj = eval(data);
                        $.each(obj.data, function(i, item) {
                            $("#" + o.listContainer).append(o.listDom(item));
                        });
                        $("#" + o.listContainer).listview("refresh");
                        self.myScroll.refresh();
                        $('#'+self.pullDownId).hide();
                    }
                });
            } else if (self.ifs == true) { 
                $('#'+self.pullDownId).find('.pullDownLabel').text("当前为最新信息");
                //$("#" + o.listContainer).empty();
                //self.generatedCount = 1;
                //self.init();
                setTimeout(function() {
                    $('#'+self.pullDownId).hide();
                }, 1000);
            }
        }
        /**
         * 滚动翻页 （自定义实现此方法）
         * myScroll.refresh();    // 数据加载完成后，调用界面更新方法
         */
    self.pullUpAction = function() { //下拉翻页加载数据
            var el, li, i;
            el = document.getElementById(o.listContainer);
            var tree = "";
            $.ajax({
                type: o.ajaxType,
                url: o.url + "?curPage=" + (++self.generatedCount)+o.qarmData,
                data: $('#' + self.inputId).data(),
                dataType: o.ajaxDataType,
                success: function(data) {
                    var obj = eval(data);
                    if (obj.data.length != 0) {
                        $.each(obj.data, function(i, item) {
                            $("#" + o.listContainer).append(o.listDom(item));
                        });
                        $("#" + o.listContainer).listview('refresh');
                        self.myScroll.refresh();
                    } else {
                        $('#'+self.pullUpId).find('.pullUpLabel').text('当前栏目数据已加载完');
                        setTimeout(function() {
                            $('#'+self.pullUpId).hide();
                        }, 1000);
                    }
                }
            });
        }
    self.loaded = function() {
            if(!o.noUpBtn){
                self.pullDownEl = document.getElementById(self.pullDownId);
                self.pullDownOffset = self.pullDownEl.offsetHeight;
            }
            self.pullUpEl = document.getElementById(self.pullUpId);
            self.pullUpOffset = self.pullUpEl.offsetHeight;
            self.myScroll = new iScroll(o.wrapper, {
                scrollbarClass: 'myScrollbar',
                useTransition: false,
                topOffset: self.pullDownOffset,
                onRefresh: function() {
                    if(!o.noUpBtn){
                        if (self.pullDownEl.className.match('loading')) {
                            self.pullDownEl.className = '';
                            $('#'+self.pullDownId).find('.pullDownLabel').text('下拉刷新...');
                        }
                    } 
                    if (self.pullUpEl.className.match('loading')) {
                        self.pullUpEl.className = '';
                        $('#'+self.pullUpId).find('.pullUpLabel').text('上拉加载更多...');
                    }
                },
                onScrollMove: function() {
                    if(!o.noUpBtn){
                        if (this.y > 5 && !self.pullDownEl.className.match('flip')) {
                            self.pullDownEl.className = 'flip';
                            $('#'+self.pullDownId).find('.pullDownLabel').text('松手开始更新...');
                            $('#'+self.pullDownId).show();
                            $('#'+self.pullUpId).hide();
                            this.minScrollY = 0;
                            if(o.onMoveDownFunction){
                                o.onMoveDownFunction()
                            }
                        } else if (this.y < 5 && self.pullDownEl.className.match('flip')) {
                            if(!o.noUpBtn){
                                self.pullDownEl.className = '';
                                $('#'+self.pullDownId).find('.pullDownLabel').text('下拉刷新...');
                                $('#'+self.pullDownId).show();
                                $('#'+self.pullUpId).hide();
                                this.minScrollY = -self.pullDownOffset;
                                if(o.onMoveDownFunction){
                                    o.onMoveDownFunction()
                                }
                            }
                        }
                    }
                    if (this.y < (this.maxScrollY - 5) && !self.pullUpEl.className.match('flip')) {
                        self.pullUpEl.className = 'flip';
                        $('#'+self.pullUpId).find('.pullUpLabel').text('松手开始更新...');
                        $('#'+self.pullUpId).show();
                        $('#'+self.pullDownId).hide();
                        this.maxScrollY = this.maxScrollY;
                        if(o.onMoveUpFunction){
                            o.onMoveUpFunction()
                        }
                    } else if (this.y > (this.maxScrollY + 5) && self.pullUpEl.className.match('flip')) {
                        self.pullUpEl.className = '';
                        $('#'+self.pullUpId).find('.pullUpLabel').text('上拉加载更多...');
                        $('#'+self.pullUpId).show();
                        $('#'+self.pullDownId).hide();
                        this.maxScrollY = self.pullUpOffset;
                        if(o.onMoveUpFunction){
                            o.onMoveUpFunction()
                        }
                    }
                },
                onScrollEnd: function() {
                    if(!o.noUpBtn){
                        if (self.pullDownEl.className.match('flip')) {
                       
                          self.pullDownEl.className = 'loading';
                            $('#'+self.pullDownId).find('.pullDownLabel').text('加载中...');
                            $('#'+self.pullDownId).show();
                            self.pullDownAction();
                            if(o.onEndDownFunction){
                                o.onEndDownFunction()
                            }  
                        }
                    } 
                    if (self.pullUpEl.className.match('flip')) {
                        self.pullUpEl.className = 'loading';
                        $('#'+self.pullUpId).find('.pullUpLabel').text('加载中...');
                        self.pullUpAction();
                        if(o.onEndUpFunction){
                            o.onEndUpFunction()
                        }
                    }
                }
            });
            setTimeout(function() {
                document.getElementById(o.wrapper).style.left = '0';
            }, 800);
        }
        //初始化绑定iScroll控件 
   /* document.addEventListener('touchmove', function(e) {
        e.preventDefault();
    }, false);*/
    //document.addEventListener('DOMContentLoaded', self.loaded, false);
    self.loaded();
    //
    self.init();
    if(o.callback){
        o.callback()
    }
   // self.myScroll.refresh(); 
}
