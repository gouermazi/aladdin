<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript" src="/res/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/res/3rdparty/tether-1.3.3/dist/js/tether.min.js"></script>
<script type="text/javascript" src="/res/3rdparty/bootstrap-4.0.0-alpha.6-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/res/3rdparty/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/res/css/login.css" />
<title>登录页</title>
</head>
<body>
 <div class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">登录</h2>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="邮箱" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div>
    <script type="text/javascript">
    /*!
     * IE10 viewport hack for Surface/desktop Windows 8 bug
     */

    (function () {
      'use strict'

      if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
        var msViewportStyle = document.createElement('style')
        msViewportStyle.appendChild(
          document.createTextNode(
            '@-ms-viewport{width:auto!important}'
          )
        )
        document.head.appendChild(msViewportStyle)
      }

    }())
    </script>
</body>
</html>