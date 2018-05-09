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
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

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
                    <li><a href="/v1/myFile">资源上传</a></li>
                <#--<li><a href="/v1/file/${Session["username"]}/list">我的资源</a></li>-->
                    <li class="dropdown">
                        <a href="/v1/file/${Session["username"]}/list" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的资源 <span class="caret"></span></a>
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
        <p>资源列表.</p>
    <#--<p>-->
    <#--<a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>-->
    <#--</p>-->
        <div class="center-block">
            <table class="table table-hover">
                <tr>
                    <th>文件名</th>
                    <#--<td>文件路径</td>-->
                    <th>大小</th>
                    <th>文件类型</th>
                    <th>文件上传时间</th>
                    <th colspan="3">操作</th>
                </tr>
            <#list fileList as item>
                <tr>
                    <td>${item.fileName!}</td>
                    <#--<td>${item.filePatd!}</td>-->
                    <td>${item.fileSize!}</td>
                    <td>${item.fileType!}</td>
                    <td>${item.updateTime!}</td>
                    <#--<td><a class="btn btn-info" href="/v1/share/${Session["username"]}/${item.fileName!}">分享</a></td>-->
                    <td><button onclick="file_share('${item.fileName!}')" class="btn btn-info">分享</button></td>
                    <td><a class="btn btn-info" href="/v1/file?fileName=${item.fileName!}">下载</a></td>
                    <td><button class="btn btn-danger" onclick="file_del('${item.fileName!}')">删除</button></td>
                </tr>
            </#list>
            </table>
        </div>
    </div>

</div> <!-- /container -->

<#--file Share-->
<div class="modal fade" tabindex="-1" role="dialog" id="file_share_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">分享成功</h4>
            </div>
            <div class="modal-body h4">
                <form id="share_form">
                    <p>文件名称:</p><p id="file_name"></p><br>
                    <p>文件路径：</p><p id="file_site"></p><br>
                    <p>提取码：</p><p id="file_code"></p><br>
                </form>
            </div>
            <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" id="file_del_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" onclick="refresh()" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">文件删除成功</h4>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="refresh()" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-3.3.1.min.js"></script>
<script>window.jQuery || document.write('<script src="/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="/js/upload.js"></script>
<script>
    function file_share(fileName) {
        $.ajax({
            url:'/v1/file/'+fileName+'/share',
            dataType:'json',
            type:'POST',
            success: function (data) {
                $("#file_name").val(data.fileName);
                $("#file_site").val(data.shareWebsite);
                $("#file_code").val(data.code);
                $("#file_share_modal").modal('show');
            },
            error: function (data) {
                alert("error"+data)
            }
        })
    }

    function file_del(fileName) {
        $.ajax({
            url:'/v1/file/'+fileName+'/del',
            dataType:'json',
            type:'GET',
            success: function (data) {
                $("#file_del_modal").modal('show');
            },
            error: function (data) {
                alert("error"+data)
            }
        })
    }

    function refresh() {
        window.location.reload();
    }
</script>
</body>
</html>
