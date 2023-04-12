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
    <link rel="stylesheet" href="/css/poster/book-statistic.css"/>
    <link rel="stylesheet" href="/css/poster/header-manager.css"/>
    <link rel="shortcut icon" href="./icon/favicon.ico" type="image/x-icon">
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
        <a href="/nguoi-dang" class="nav-link"
        ><i class="fa-solid fa-book-open-reader"></i>Truyện đang làm</a
        >
    </li>
    <li>
        <a href="/nguoi-dang/novel/add" class="nav-link"
        ><i class="fa-sharp fa-solid fa-file-circle-plus"></i>Thêm
            truyện mới</a
        >
    </li>
    <li class="page-active">
        <a href="/nguoi-dang/novel/statistic" class="nav-link"
        ><i class="fa-solid fa-chart-simple"></i>Thống kê doanh thu</a
        >
    </li>
    <li>
        <a href="/nguoi-dang/transaction" class="nav-link"
        ><i class="fa-solid fa-money-check-dollar"></i>Giao dịch</a
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
    <div class="directory">Thống kê Doanh Thu</div>
    <div class="row head">
        <div class="head-book col-2">Truyện</div>
        <div class="head-post-date col-1" style="width: 10%;">Ngày đăng</div>
        <div class="head-like col-1">Lượt thích</div>
        <div class="head-view col-1">Lượt xem</div>
        <div class="head-revenue col-2">Tổng doanh thu</div>
        <div class="head-cash-out col-1">Có thể rút</div>
    </div>
    <div class="book-statistic">
        <c:forEach items="${bookStatisticList}" var="book">
            <div class="row book">
                <div class="book-detail col-2">
                    <div class="book-image">
                        <img src="${book.bookImage}" alt="" width="60px" />
                    </div>
                    <div class="book-information">
                        <div class="book-name">${book.bookName}</div>
                        <div class="book-author">
                            <i class="fa-solid fa-user-pen" style="margin-right: 5px;"></i>${book.bookAuthor}
                        </div>
                        <div class="book-type">
                            ${book.typeName}
                        </div>
                    </div>
                </div>
                <div class="book-post-date col-1" style="width: 10%;">${book.bookPostDate}</div>
                <div class="book-like col-1">${book.bookLike}</div>
                <div class="book-view col-1">${book.bookView}</div>
                <div class="book-revenue col-2">${book.totalRevenue} <img class="currency" src="/images/page/currency.png" alt=""></div>
                <div class="book-cash-out col-1">
                    <c:if test="${book.totalRevenue >= 1000}">
                        Có
                    </c:if>
                    <c:if test="${book.totalRevenue < 1000}">
                        Không
                    </c:if>
                </div>
                <div class="action-button col-1">
                    <a href="/nguoi-dang/novel/statistic/${book.convert()}" class="button">Xem chi tiết</a>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>