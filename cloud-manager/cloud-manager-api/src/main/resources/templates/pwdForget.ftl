<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>校园资源共享平台</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/validate.css" rel="stylesheet">
    <link href="/css/canvas.css" rel="stylesheet">

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
<canvas id="canvas"></canvas>
<div class="container">
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/v1/">用户</a></li>
        <li role="presentation"><a href="/v1/manager">管理员</a></li>
        <li role="presentation"><a href="/v1/user/register">注册</a></li>
        <li role="presentation" class="active"><a href="/v1/user/password">忘记密码</a></li>
    </ul>

    <form action="/v1/user/password" class="form-signin" method="post">
        <h2 class="form-signin-heading">校园资源共享平台</h2>
        <label for="forgetName" class="sr-only">用户名</label>
        <input type="text" name="forgetName" id="forgetName" class="form-control" placeholder="请输入用户名" required autofocus>
        <button class="btn btn-lg btn-primary btn-block" id="login_btn" type="submit">密码修改</button>
    </form>

</div> <!-- /container -->
<script src="/js/validate.js"></script>
<script src="/js/canvas.js"></script>
<script src="/js/jquery-3.3.1.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

