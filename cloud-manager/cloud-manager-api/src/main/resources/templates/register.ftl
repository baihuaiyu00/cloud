<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>校园资源共享平台</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="/css/canvas.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
    <#--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
        <![endif]-->
</head>

<body>
<canvas id="canvas"></canvas>
<div class="container">
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/v1/">用户</a></li>
        <li role="presentation"><a href="/v1/manager">管理员</a></li>
        <li role="presentation" class="active"><a href="/v1/user/register">注册</a></li>
    </ul>

    <form id="register_form" action="/v1/register" class="form-signin" method="post">
        <h2 class="form-signin-heading">注册</h2>
        <label for="Username" class="sr-only">用户名</label>
            <p>用户名</p>
            <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
            <p>密码</p>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="请输入密码" required>
        <label for="inputPassword" class="sr-only">密码确认</label>
            <p>再次输入密码</p>
            <input type="password" name="twicePassword" id="twicePassword" class="form-control" placeholder="请再次输入密码" required>
        <label for="Email" class="sr-only">邮箱</label>
            <p>邮箱</p>
            <input type="text" name="email" id="Email" class="form-control" placeholder="请输入邮箱" required>
        <label for="Phone" class="sr-only">手机</label>
            <p>手机号码</p>
            <input type="text" name="phone" id="Phone" class="form-control" placeholder="请输入手机号码" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
    </form>

</div> <!-- /container -->
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/canvas.js"></script>
<script>
    $().ready(function() {
        $("#register_form").validate({
            onfocusout: function (element) {$(element).valid()},
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                twicePassword: {
                    required: true
                    // equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                phone: {
                    required: true,
                    number: true
                }

            },
            messages: {
                username: {
                    required: "用户名未输入！"
                },
                password: {
                    required: "密码未输入！"
                },
                twicePassword: {
                    required: "密码确认未输入！",
                    // equalTo: "两次输入密码不一致"
                },
                email: {
                    required: "邮箱未输入！",
                    email: "请输入正确的邮箱格式"
                },
                phone: {
                    required: "电话不能为空！",
                    number: "电话必须为数字"
                }
            }
        });
    })
</script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

