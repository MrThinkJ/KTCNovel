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
    <link rel="stylesheet" href="/css/book/rate.css"/>
    <link href="<c:url value="/css/page/header.css" />" rel="stylesheet">
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
<jsp:include page="../page/header.jsp"/>
<div id="wp-content">
    <!-- SLIDER -->
    <div id="carousel" class="carousel slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <a href="#"
                ><img src="/images/page/bia1.jpg" class="d-block w-100" alt="anhbia1"
                /></a>
            </div>
            <div class="carousel-item">
                <a href="#"
                ><img src="/images/page/bia1.jpg" class="d-block w-100" alt="anhbia2"
                /></a>
            </div>
            <div class="carousel-item">
                <a href="#"
                ><img src="/images/page/bia1.jpg" class="d-block w-100" alt="anhbia3"
                /></a>
            </div>
        </div>
        <button
                class="carousel-control-prev"
                type="button"
                data-bs-target="#carousel"
                data-bs-slide="prev"
        >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button
                class="carousel-control-next"
                type="button"
                data-bs-target="#carousel"
                data-bs-slide="next"
        >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <!-- END SLIDER -->
    <div class="container detail">
        <jsp:include page="../book/header.jsp"/>

        <!-- NAV TABS -->
        <div class="row nav-row">
            <div class="col-11">
                <div class="navigation">
                    <a href="/truyen/${book.convertAll()}"><div class="navi-tab">Giới thiệu</div></a>
                    <a href="/truyen/${book.convertAll()}/danh-gia"><div class="navi-tab active">Đánh giá</div></a>
                    <a href="/truyen/${book.convertAll()}/danh-sach-chuong"><div class="navi-tab">Danh sách chương</div></a>
                    <a href="/truyen/${book.convertAll()}/binh-luan"><div class="navi-tab">Bình luận</div></a>
                </div>
            </div>
        </div>
        <div class="row section-row">
            <div class="main">
                <div class="col-8">
                    <div class="wrapper-rate-form">
                        <form action="" id="rate-form">
                            <div class="rate-star">
                                <label>Xếp hạng theo số sao: </label>
                                <div class="star-number">
                                    <i class="fa-solid fa-star text-warning normal-num"></i>
                                    <i class="fa-regular fa-star text-warning star-num"></i>
                                    <i class="fa-regular fa-star text-warning star-num"></i>
                                    <i class="fa-regular fa-star text-warning star-num"></i>
                                    <i class="fa-regular fa-star text-warning star-num"></i>
                                </div>
                                <input type="hidden" id="star-value" value="1" name="star"/>
                            </div>
                            <div class="rate-content">
                                <textarea
                                        name="content"
                                        id="content"
                                        id="description"
                                        placeholder="Cho đánh giá"
                                ></textarea>
                            </div>
                            <button class="submit">Gửi</button>
                        </form>
                    </div>
                    <div class="wrapper-content">
                        <c:forEach items="${rateList}" var="rate">
                            <div class="comment">
                                <div class="head-comment">
                                    <div class="user-avatar">
                                        <img src="${rate.account.user.avatar}" alt="" width="45">
                                    </div>
                                    <div class="information">
                                        <div class="username">
                                            ${rate.account.user.name}
                                        </div>
                                        <div class="wrapper-rate">
                                            <div class="rate">
                                                ${rate.star} sao
                                            </div>
                                            <div class="rate-date">
                                                <i class="fa-regular fa-clock"></i>${rate.rateDate}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="content">
                                    <span>${rate.content}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<script src="/js/book/rate.js"></script>
<script src="/js/page/header.js"></script>
</body>
</html>