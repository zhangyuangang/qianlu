<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>前路后台管理</title>
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="../../../css/admin/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="../../../css/admin/animate.css" type="text/css" />
  <link rel="stylesheet" href="../../../css/admin/font.css" type="text/css" />
  <link rel="stylesheet" href="../../../css/admin/app.css" type="text/css" />
  
</head>
	
<body>
 
  <div class="app app-header-fixed" id="app">
    <!-- navbar -->
    <div class="app-header navbar">
      <!-- navbar header -->
      <div class="navbar-header bg-dark">
        <button class="pull-right visible-xs dk" data-toggle="class:show" data-target=".navbar-collapse">
          <i class="glyphicon glyphicon-cog"></i>
        </button>
        <button class="pull-right visible-xs" data-toggle="class:off-screen" data-target=".app-aside" ui-scroll="app">
          <i class="glyphicon glyphicon-align-justify"></i>
        </button>
        <!-- brand -->
        <a href="#/" class="navbar-brand text-lt">
          <img src="../../../img/qianlu.png" alt="." width="50%" height="50%" >
        </a>
        <!-- / brand -->
      </div>
      <!-- / navbar header -->

      <!-- navbar collapse -->
      <div class="collapse pos-rlt navbar-collapse box-shadow bg-white-only">   

        <!-- nabar right -->
        <ul class="nav navbar-nav navbar-right">
        	
        	<li class="dropdown">
	            <a href="#" data-toggle="dropdown" class="dropdown-toggle clear" data-toggle="dropdown">
	            	<span class="time">2016年6月21日</span>
	           		<span class="quits">周三</span>
	           		
	            </a>            
          	</li>
         <li class="dropdown">
          	  <span class="line_s"></span>
          </li> 
         <li class="dropdown">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle clear" data-toggle="dropdown">
            	<i class="icon_tou"></i>
           		<span class="quits">CLAIR</span>
           		
            </a>            
          </li>
          <li class="dropdown">
          	  <span class="line_s"></span>
          </li>       
          <li class="dropdown">
            <a  data-toggle="dropdown" class="dropdown-toggle clear" >
            	<i class="icon_off"></i>
           		<span class="quits">退出系统</span>
            </a>
          </li>
        </ul>
        <!-- / navbar right -->
      </div>
      <!-- / navbar collapse -->
    </div>
    <!-- / navbar -->

    <!-- menu -->
    <div class="app-aside hidden-xs bg-dark">
      <div class="aside-wrap">
        <div class="navi-wrap">
          <!-- nav -->
          <nav ui-nav class="navi">
            <ul class="nav">       
              <li>
                <a href="lbmanage.html" class="auto"  target="fram">      
                  <span class="font-bold" >轮播管理</span>
                </a>               
              </li>
              <!---->
              <li ui-sref-active="active">
                <a>                  
                  <span class="font-bold" >文章管理</span>
                </a>
                <!--子集-->
                <ul class="nav nav-sub dk">

                  <li ui-sref-active="active">
                    <a href="dbessay.html"  target="fram">
                      <span>大白随笔</span>
                    </a>
                  </li>
                  <li ui-sref-active="active">
                    <a  href="arclongt.html" target="fram">
                      <span>精选长文</span>
                    </a>
                  </li>
                </ul>
              </li>
              <!---->
              <li>
                <a>                 
                  <span class="font-bold" translate="aside.nav.EMAIL">热论管理</span>
                </a>
                <ul class="nav nav-sub dk">

                  <li ui-sref-active="active">
                    <a href="plshen.html"  target="fram">
                      <span>评论审核</span>
                    </a>
                  </li>
                  <li ui-sref-active="active">
                    <a href="hot.html"  target="fram">
                      <span>热论编辑</span>
                    </a>
                  </li>
                </ul>
              </li>
              <!---->
              <li>
                <a href="dbmanage.html" class="auto" target="fram">
                  <span class="font-bold">大白管理</span>
                </a>              
              </li>          
              <li>
                <a href="order.html" class="auto" target="fram">      
                  <span class="font-bold">订单管理</span>
                </a>             
              </li>
              <!---->
             <li>
                <a  class="auto">      
                  <span class="font-bold">交易数据</span>
                </a>
                <!--子集-->
                <ul class="nav nav-sub dk">

                  <li ui-sref-active="active">
                    <a>
                      <span>平台付费人数<span class="payhum"></span></span>
                    </a>
                  </li>
                  <li ui-sref-active="active">
                    <a>
                      <span>平台盈利人数<span class="lihum"></span></span>
                    </a>
                  </li>
                   <li ui-sref-active="active">
                    <a>
                      <span>平台交易总额<span class="allm"></span></span>
                    </a>
                  </li>
                  <li ui-sref-active="active">
                    <a href="paydata.html" target="fram" >
                      <span>详细</span>
                    </a>
                  </li>
                </ul>               
              </li>
              <!---->
            </ul>
          </nav>
          <!-- nav -->
        </div>
      </div>
    </div>
    <!-- / menu -->

    <!-- content -->
    <div class="app-content">
      <div ui-butterbar></div>
      <a href class="off-screen-toggle hide" data-toggle="class:off-screen" data-target=".app-aside" ></a>
      <div class="app-content-body fade-in-up">
        <!-- COPY the content from "tpl/" -->
         <iframe name="fram" frameborder="0"></iframe>
        <!-- PASTE above -->
      </div>
    </div>
    <!-- /content -->
  </div>
  <!-- jQuery -->
  <script src="../../../js/admin/jquery.min.js"></script>
  <script src="../../../js/admin/bootstrap.js"></script>
  <script type="text/javascript">
    +function ($) {
      $(function(){
        // class
        $(document).on('click', '[data-toggle^="class"]', function(e){
          e && e.preventDefault();
          console.log('abc');
          var $this = $(e.target), $class , $target, $tmp, $classes, $targets;
          !$this.data('toggle') && ($this = $this.closest('[data-toggle^="class"]'));
          $class = $this.data()['toggle'];
          $target = $this.data('target') || $this.attr('href');
          $class && ($tmp = $class.split(':')[1]) && ($classes = $tmp.split(','));
          $target && ($targets = $target.split(','));
          $classes && $classes.length && $.each($targets, function( index, value ) {
            if ( $classes[index].indexOf( '*' ) !== -1 ) {
              var patt = new RegExp( '\\s' + 
                  $classes[index].
                    replace( /\*/g, '[A-Za-z0-9-_]+' ).
                    split( ' ' ).
                    join( '\\s|\\s' ) + 
                  '\\s', 'g' );
              $($this).each( function ( i, it ) {
                var cn = ' ' + it.className + ' ';
                while ( patt.test( cn ) ) {
                  cn = cn.replace( patt, ' ' );
                }
                it.className = $.trim( cn );
              });
            }
            ($targets[index] !='#') && $($targets[index]).toggleClass($classes[index]) || $this.toggleClass($classes[index]);
          });
          $this.toggleClass('active');
        });

        // collapse nav
        $(document).on('click', 'nav a', function (e) {
          var $this = $(e.target), $active;
          $this.is('a') || ($this = $this.closest('a'));
          
          $active = $this.parent().siblings( ".active" );
          $active && $active.toggleClass('active').find('> ul:visible').slideUp(200);
          
          ($this.parent().hasClass('active') && $this.next().slideUp(200)) || $this.next().slideDown(200);
          $this.parent().toggleClass('active');
          
          $this.next().is('ul') && e.preventDefault();

          setTimeout(function(){ $(document).trigger('updateNav'); }, 300);      
        });
      });
    }(jQuery);
    


    //
    $.ajax({
      url:'/admin/order/getOrderData.html',
      type:'GET',
      dataType:'json',
      success: function(msg){
        $(".payhum").html(msg.totalPayPerson)
        $(".lihum").html(msg.totalProfitPerson)
        $(".allm").html(msg.totalAmount)
      }
    })
  </script>
  
</body>
</html>