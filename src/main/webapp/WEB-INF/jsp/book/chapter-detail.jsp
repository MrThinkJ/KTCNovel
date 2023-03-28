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
    <link rel="stylesheet" href="/css/book/chapter-detail.css"/>
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
<main>
    <div class="chapter-container chapter-content">
        <div class="chapter-action">
            <a class="previous-chapter" href="">
                <i class="fa-solid fa-arrow-left"></i>Chương trước
            </a>
            <a class="next-chapter" href="">
                Chương sau<i class="fa-solid fa-arrow-right"></i>
            </a>
        </div>
        <div class="chapter-title">

        </div>
        <div class="chapter-information">
            <ul class="list-unstyled d-flex align-items-center flex-wrap">
                <li class="d-flex mr-4 mb-1">
                    <a href="" class="text-inherit d-flex align-items-center book-name"
                    ><i class="fa-solid fa-book"></i>
                    </a>
                </li>
                <li class="d-flex align-items-center mr-4 mb-1 book-author">
                    <i class="fa-solid fa-user-pen"></i
                    ><a href=""></a>
                </li>
                <li class="d-flex align-items-center mr-4 mb-1 post-date">
                    <i class="fa-regular fa-clock"></i>
                </li>
            </ul>
        </div>
        <div class="read-content"></div>
    </div>
</main>
<div class="chapter-action-foot">
    <a class="previous-chapter" href="">
        <i class="fa-solid fa-arrow-left"></i>Chương trước
    </a>
    <a class="next-chapter" href="">
        Chương sau<i class="fa-solid fa-arrow-right"></i>
    </a>
</div>
<div class="notification">
</div>
<script src="/js/page/header.js"></script>
<script src="/js/book/chapter-detail.js"></script>
</body>
</html>