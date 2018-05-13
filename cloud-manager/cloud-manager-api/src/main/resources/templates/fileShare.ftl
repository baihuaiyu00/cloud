<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>校园云平台</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
<#--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
    <form action="/v1/file/${userName!}/${fileName!}/share" class="form-signin" method="get">
        <h2 class="form-signin-heading">SHARE DOWNLOAD</h2>
        <span>分享用户名称:${userName!}</span>
        <span>分享文件名称:${fileName!}</span>
        <label for="inputCode" class="sr-only">Password</label>
        <input type="text" name="code" id="inputCode" class="form-control" placeholder="Code" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Download</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

