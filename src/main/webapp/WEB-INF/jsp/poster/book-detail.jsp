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
    <title>Document</title>
    <link rel="stylesheet" href="/css/poster/book-detail.css"/>
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
            <a href="" class="nav-link"><i class="fa-solid fa-house"></i></a>
        </li>
        <li class="user">
            <a href="" class="nav-link"><i class="fa-solid fa-user"></i></a>
        </li>
    </ul>
</div>
</div>
</nav>
<main>
    <div class="directory">${book.name}</div>
    <div class="book">
        <div class="title">Thông tin truyện</div>
        <div class="book-detail">
            <div class="book-image">
                <img src="${book.image}" alt="" width="150px"/>
            </div>
            <div class="book-information">
                <div class="book-name">${book.name}</div>
                <div class="book-author">
                    <i class="fa-solid fa-user-pen"></i>${book.author}
                </div>
                <div class="book-description">
                    ${book.description}
                </div>
                <div class="book-type">${book.typeList.get(0).name}</div>
            </div>
        </div>
        <div class="book-action">
            <a href="/nguoi-dang/novel/update/${book.convert()}">
                <div class="update-book">Chỉnh sửa</div>
            </a>
            <a href="/nguoi-dang/novel/${book.convert()}/chapter/add">
                <div class="add-chapter">Thêm chương mới</div>
            </a>
        </div>
    </div>
    <div class="chapter">
        <div class="title">
            Danh sách chương<i class="fa-solid fa-circle"></i>
        </div>
        <div class="chapter-detail">
            <div class="container">
                <div class="row head">
                    <div class="col-2">Chương số</div>
                    <div class="col-5">Tên chương</div>
                    <div class="col-2">Thời gian đăng chương</div>
                </div>
                <c:forEach items="${chapterList}" var="chapter">
                    <div class="row chapter-information">
                        <div class="col-2">
                            <a href="/nguoi-dang/novel/${book.convert()}/chapter/chuong-${chapter.indexInBook}/update">Chương ${chapter.indexInBook}</a>
                        </div>
                        <div class="col-5">
                            <a href="/nguoi-dang/novel/${book.convert()}/chapter/chuong-${chapter.indexInBook}/update">${chapter.chapterName}</a>
                        </div>
                        <div class="col-2 time">${chapter.postDate}</div>
                        <c:if test="${chapter.vipStatus == 1}">
                            <div class="col-2 wrapper-vip">
                                <div class="vip">
                                    VIP
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="pagination">
        <a href="">
            <div class="page active">1</div>
        </a>
        <a href="">
            <div class="page">2</div>
        </a>
        <a href="">
            <div class="page">3</div>
        </a>
        <a href="">
            <div class="page">4</div>
        </a>
    </div>
</main>
</body>
</html>