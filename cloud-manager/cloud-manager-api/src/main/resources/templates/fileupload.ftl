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
    <link href="/css/fileinput-rtl.css" rel="stylesheet">
    <link href="/css/fileinput.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <![endif]-->
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
                <a class="navbar-brand" href="#">校园云平台</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/v1/home">用户主页</a></li>
                    <li class="active"><a href="/v1/myFile">资源上传</a></li>
                <#--<li><a href="/v1/file/${Session["username"]}/list">我的资源</a></li>-->
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
    <#--<div class="jumbotron">-->
        <#--<p>资源上传.</p>-->
    <#--&lt;#&ndash;<p>&ndash;&gt;-->
    <#--&lt;#&ndash;<a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>&ndash;&gt;-->
    <#--&lt;#&ndash;</p>&ndash;&gt;-->
        <#--<div id="progressBar" style='height: 20px;boder:2px solid green'>-->
            <#--<div id='bar' style='height: 100%;background:#33dd33;width: 0%'>-->
            <#--</div>-->
        <#--</div>-->
        <#--<form>-->
            <#--<input type="file" id="files" name="files" multiple="multiple"/>-->
            <#--<output id="selectedFiles"></output>-->
            <#--<input id="uploadButton" type="button" class="btn btn-info" value="上传"/>-->
        <#--</form>-->
        <#--&lt;#&ndash;<div id='debug' style='height: 100px;border: 2px solid green;overflow: auto;'>&ndash;&gt;-->
        <#--<div id='debug' class="jumbotron" style='height: 100px;overflow: auto;'>-->
        <#--</div>-->
    <#--</div>-->
    <div class="jumbotron">
        <div class="file-input file-input-ajax-new">
            <div class="file-preview ">
                <div class=" file-drop-zone"><div class="file-drop-zone-title">Drag &amp; drop files here …</div>
                    <div class="file-preview-thumbnails">
                    </div>
                    <div class="clearfix"></div>    <div class="file-preview-status text-center text-success"></div>
                    <div class="kv-fileinput-error file-error-message" style="display: none;"></div>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="input-group file-caption-main">
            <div class="file-caption form-control kv-fileinput-caption" tabindex="500">
                <span class="file-caption-icon"></span>
                <input class="file-caption-name" onkeydown="return false;" onpaste="return false;" placeholder="Select files...">
            </div>
            <div class="input-group-btn input-group-append">
                <form>
                    <button id="uploadButton" type="submit" tabindex="500" title="Upload selected files" class="btn btn-default btn-secondary fileinput-upload fileinput-upload-button"><i class="glyphicon glyphicon-upload"></i>  <span class="hidden-xs">Upload</span></button>
                    <div tabindex="500" class="btn btn-primary btn-file"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;  <span class="hidden-xs">Browse …</span><input type="file" class="file" id="files" name="files" multiple="multiple" data-min-file-count="3" value="Browse ..."/></div>
                </form>
            </div>
        </div>
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
<script src="/js/upload.js"></script>
<script src="../static/js/fileinput.js"></script>
</body>
</html>
