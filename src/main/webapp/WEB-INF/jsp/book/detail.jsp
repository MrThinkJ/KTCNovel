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
    <link rel="stylesheet" href="/css/book/detail.css"/>
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
                    <a href="/truyen/${book.convertAll()}"><div class="navi-tab active">Giới thiệu</div></a>
                    <a href="/truyen/${book.convertAll()}/danh-gia"><div class="navi-tab">Đánh giá</div></a>
                    <a href="/truyen/${book.convertAll()}/danh-sach-chuong"><div class="navi-tab">Danh sách chương</div></a>
                    <a href="/truyen/${book.convertAll()}/binh-luan"><div class="navi-tab">Bình luận</div></a>
                </div>
            </div>
        </div>
        <div class="tab-content" id="myTabContent">
            <div
                    class="tab-pane fade show active"
                    id="intro"
                    role="tabpanel"
                    aria-labelledby="intro-tab"
            >
                <div class="row mt-1">
                    <div class="col-8">
                        <div class="mb-4">
                            <div class="content mt-2 p-2">
                                <p>
                                    <c:forEach items="${bookDescription}" var="line">
                                        ${line} <br>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                        <div class="mt-5"></div>
                    </div>
                    <div class="col-4">
                        <div class="mt-2 mb-4 rounded-2 p-4 poster">
                            <div
                                    class="mai-avatar mai-avatar--90 mx-auto mb-3"
                                    style="cursor: pointer"
                            >
                                <img class="img-fluid" src="${book.account.user.avatar}" />
                                <span class="level bg-danger rounded-3 text-white"
                                >Đồng</span
                                >
                            </div>
                            <div class="h4 py-3 text-center">${book.account.user.name}</div>
                            <div class="row no-gutters text-center">
                                <div class="col-4">
                                    <i class="mai-icon icon-book text-primary h4 m-0"></i>
                                    <div class="my-2">Số truyện</div>
                                    <div class="h4 mb-0">112</div>
                                </div>
                            </div>
                            <hr />
                        </div>
                    </div>
                </div>
            </div>
            <!-- END NAV TABS -->
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<script src="/js/page/header.js"></script>
</body>
</html>