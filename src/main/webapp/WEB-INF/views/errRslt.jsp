<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>请求失败</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.structure.min.css" />
    <link rel="stylesheet" href="../../css/jquery.mobile.custom.theme.min.css" />
    <link rel="stylesheet" href="../../css/theme.css" />
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/jquery.mobile.custom.min.js"></script>
</head>

<body>
    <div data-role="page" id="" data-title="Inbox" data-url="" class="messagePage">
        <!-- <div data-role="header" data-position="fixed" data-theme="b">
            <h1>请求失败</h1>
        </div> -->
        <!-- /header -->
        <div role="main" class="ui-content ">
            <div class="messageCls">${err }</div>
        </div>
        <!-- /content -->
        <div data-role="footer" data-position="fixed">
            <a href="javascript:void(0);" class="ui-btn btnBlock cancelBtn" data-rel="back">X</a>
        </div>
        <!-- /footer -->
    </div>
</body>


</html>
