<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Kỳ Thư Các</title>
<link rel="icon" href="/images/page/logo.png" type="image/x-icon" />

    <link rel="stylesheet" href="/css/admin/recharge-account.css"/>
    <link rel="stylesheet" href="/css/poster/header-manager.css"/>
    <link rel="shortcut icon" href="./icon/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.js"/> "/>
    </script>
    <script type="text/javascript" src="<c:url value="/webjars/popper.js/2.9.3/umd/popper.min.js"/> "/>
    </script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link
            rel="stylesheet"
            href='https://fonts.googleapis.com/css?family=Montserrat&amp;display=swap"rel="stylesheet'
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
    ></script>
</head>
<body>
<jsp:include page="../page/header-manager.jsp"/>
<ul class="control">
    <li>
        <a href="/admin" class="nav-link"
        ><i class="fa-solid fa-book-open-reader"></i>Quản lí giao dịch</a
        >
    </li>
    <li class="page-active">
        <a href="/admin/recharge-account" class="nav-link"
        ><i class="fa-sharp fa-solid fa-file-circle-plus"></i>Thanh toán nạp tiền</a
        >
    </li>
</ul>
</div>
<div class="head-bar">
    <ul>
        <li class="home">
            <a href="/" class="nav-link"><i class="fa-solid fa-house"></i></a>
        </li>
        <li class="user">
            <a href="/tai-khoan/cai-dat" class="nav-link"><i class="fa-solid fa-user"></i></a>
        </li>
    </ul>
</div>
</div>
</nav>
<main>
    <div class="directory">Thanh toán nạp tiền</div>
    <div class="recharge">
        <div class="container">
            <div class="row head">
                <div class="col-1">Id</div>
                <div class="col-2">Tên user</div>
                <div class="col-2">Số dư</div>
                <div class="col-2">Vai trò</div>
                <div class="col-2" style="text-align: left">Thanh toán</div>
            </div>
            <c:forEach items="${userList}" var="user">
                <div class="row recharge-detail">
                    <div class="col-1 book-name">${user.id}</div>
                    <div class="col-2">${user.name}</div>
                    <div class="col-2">
                        ${user.account.currency}<img src="/images/page/currency.png" width="15px" />
                    </div>
                    <div class="col-2">${user.account.roleList.get(0).name}</div>
                    <div class="col-3">
                        <form action="/admin/recharge-account/${user.id}" id="recharge-form" method="post">
                            <input type="text" name="amount" id="amount" />
                            <button>Thanh toán</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script src="/js/poster/add-book.js"></script>
</body>
</html>