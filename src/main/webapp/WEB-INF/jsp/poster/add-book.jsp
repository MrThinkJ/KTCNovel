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
    <link rel="stylesheet" href="/css/poster/add-book.css"/>
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
        <a href="/nguoi-dang" class="nav-link"
        ><i class="fa-solid fa-book-open-reader"></i>Truyện đang làm</a
        >
    </li>
    <li class="page-active">
        <a href="/nguoi-dang/novel/add" class="nav-link"
        ><i class="fa-sharp fa-solid fa-file-circle-plus"></i>Thêm
            truyện mới</a
        >
    </li>
    <li>
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
    <div class="directory">Đăng truyện mới</div>
    <div class="add-book-form">
        <form action="" id="add-form">
            <input type="hidden" name="book.id">
            <div class="field">
                <label for="book-name">Tên truyện: </label>
                <div class="input">
                    <input type="text" class="book-name" name="name" id="book-name" placeholder="Tên của truyện"/>
                </div>
            </div>
            <div class="field">
                <label for="book-author">Tác giả: </label>
                <div class="input">
                    <input type="text" class="book-author" name="author" placeholder="Tác giả của truyện"/>
                </div>
            </div>
            <div class="field">
                <label for="">Chọn các thể loại của truyện</label>
                <div class="type">
                    <label><input type="checkbox" name="typeList" value="1" >Tiên Hiệp</label>
                    <label><input type="checkbox" name="typeList" value="2">Huyền Huyễn</label>
                    <label><input type="checkbox" name="typeList" value="3">Đô Thị</label>
                    <label><input type="checkbox" name="typeList" value="4">Kiếm Hiệp</label>
                    <label><input type="checkbox" name="typeList" value="5">Linh Dị</label>
                    <label><input type="checkbox" name="typeList" value="6">Huyền Nghi</label>
                    <label><input type="checkbox" name="typeList" value="7">Mạt Thế</label>
                </div>
            </div>
            <div class="field">
                <label for="book-description">Mô tả truyện: </label>
                <div class="input">
              <textarea
                      name="description"
                      id=""
                      cols="100"
                      rows="10"
                      aria-controls=""
                      class="book-description"
              ></textarea>
                </div>
            </div>
            <button class="add-btn">Thêm truyện</button>
        </form>
    </div>
</main>
<script src="/js/poster/add-book.js"></script>
</body>
</html>