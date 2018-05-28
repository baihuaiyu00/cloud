<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>用户空间</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="/fileinput/fileinput.min.css" rel="stylesheet">
    <link href="/fileinput/fileinput.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <![endif]-->
    <style>
        /* layout.css Style */
        .upload-drop-zone {
            height: 200px;
            border-width: 2px;
            margin-bottom: 20px;
        }

        /* skin.css Style*/
        .upload-drop-zone {
            color: #ccc;
            border-style: dashed;
            border-color: #ccc;
            line-height: 200px;
            text-align: center
        }
        .upload-drop-zone.drop {
            color: #222;
            border-color: #222;
        }
    </style>
</head>

<body>

<div class="container">

    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">校园资源共享平台</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/v1/home">用户主页</a></li>
                    <li class="active"><a href="/v1/myFile">资源上传</a></li>
                    <li><a href="/v1/${Session['username']}/shares">我的分享</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的资源 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/v1/file/${Session["username"]}/list">全部</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=text">文档</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=video">视频</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=audio">音乐</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=application">文档</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=image">图片</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=others">其他</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="./">Default <span class="sr-only">(current)</span></a></li>
                    <li><a href="../navbar-static-top/">Static top</a></li>
                    <li><a href="../navbar-fixed-top/">Fixed top</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <p>资源上传.</p>
        <#--<div class="form-group">-->
            <div>
                <input id="myFile" type="file" name="myFile" class="fileloading">
            </div>
            <input type="hidden" name="logo" id="logo">
        <#--</div>-->
    </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-3.3.1.min.js"></script>
<script>window.jQuery || document.write('<script src="/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
<#--<script src="/js/upload.js"></script>-->
<script src="/fileinput/fileinput.js"></script>
<script src="/fileinput/fileinput.min.js"></script>
<script>
    $("#myFile").fileinput({
        language : 'zh-CN',
        uploadUrl : "file",
        autoReplace : true,
        showClose: false,
        maxFileSize: 1000000000,
        maxFileCount : 6,
        browseClass : "btn btn-primary", //按钮样式
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
    }).on("fileuploaded", function(e, data) {
        var res = data.response;
        alert("上传成功");
        $("#logo").attr("value", res);
    })
</script>
</body>
</html>
