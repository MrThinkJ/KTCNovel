<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>hello</title>
    <link href="<c:url value="/css/page/login.css" />" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/> "
          rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.js"/> "/>
    </script>
    <script type="text/javascript" src="<c:url value="/webjars/popper.js/2.9.3/umd/popper.min.js"/> "/>
    </script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.1/css/all.min.css"/>
    <link rel="stylesheet" href='https://fonts.googleapis.com/css?family=Montserrat&amp;display=swap"rel="stylesheet'/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<header>
    <div class="logo">
        <a href="#"><img src="/images/page/logo.png" height="50px" alt="" /></a>
    </div>
</header>
<div class="main container">
    <div class="left-section col-7">
        <img src="/images/page/left-section-login.png" alt="" />
    </div>
    <div class="login-form main-form glass wrapper-login">
        <div class="method">
            <div class="login-btn">Đăng nhập
                <div class="border"></div>
            </div>
            <div class="register-btn">Đăng ký</div>
        </div>
        <div class="form login">
            <form action="" id="login-form">
                <div class="field">
                    <div class="title">
                        <label for="username">Tên đăng nhập</label>
                    </div>
                    <input type="text" id="username" placeholder="Nhập tên đăng nhập" name="username"/>
                </div>
                <div class="error">
                </div>
                <div class="field password-field">
                    <div class="title">
                        <label for="password">Mật khẩu</label>
                    </div>
                    <input type="password" id="password" placeholder="Nhập mật khẩu" name="password"/>
                </div>

                <div class="button">
                    <button>Đăng nhập</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="notification">
</div>
<script src="<c:url value="/js/page/login.js"/>"></script>
</body>
</html>