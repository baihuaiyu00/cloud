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
                <a class="navbar-brand" href="#">校园资源共享平台</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/v1/home">用户主页</a></li>
                    <li><a href="/v1/myFile">资源上传</a></li>
                    <li class="active"><a href="/v1/${Session['username']}/shares">我的分享</a></li>
                    <li class="dropdown">
                        <a href="/v1/file/${Session["username"]}/list" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的资源 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/v1/file/${Session["username"]}/list">全部</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=video">视频</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=audio">音乐</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=application">文档</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=image">图片</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=text">文本</a></li>
                            <li><a href="/v1/file/${Session["username"]}/list?fileType=others">其他</a></li>
                        </ul>
                    </li>
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
                    <th>分享用户</th>
                    <th>提取码</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr>
            <#list fileShares as item>
                <tr>
                    <td>${item.fileName!}</td>
                    <#--<td>${item.filePatd!}</td>-->
                    <td>${item.username!}</td>
                    <td>${item.code!}</td>
                    <td>${item.shareWebsite!}</td>
                    <#--<td><a class="btn btn-info" href="/v1/share/${Session["username"]}/${item.fileName!}">分享</a></td>-->
                    <td><button class="btn btn-danger" onclick="file_del('${item.fileName!}')">删除</button></td>
                </tr>
            </#list>
            </table>
        </div>
    </div>

</div> <!-- /container -->

<#--file del-->

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
<script>
    function file_share(fileName) {
        $.ajax({
            url:'/v1/file/'+fileName+'/share',
            // dataType:'json',
            type:'POST',
            success: function (data) {
                var json = JSON.parse(data);
                $("#file_name").text(json.fileName);
                $("#file_site").text("http://127.0.0.1/file/"+json.username+"/"+json.fileName);
                $("#file_code").text(json.code);
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
