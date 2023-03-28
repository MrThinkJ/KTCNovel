<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>hello</title>
    <link href="/css/user/profile-style.css" rel="stylesheet">
    <link href="/css/page/header.css" rel="stylesheet">
    <link href="<c:url value="/webjars/bootstrap/5.1.3/css/bootstrap.min.css"/> " rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/3.6.0/jquery.js"/> "/>
    </script>
    <script type="text/javascript" src="<c:url value="/webjars/popper.js/2.9.3/umd/popper.min.js"/> "/>
    </script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat&amp;display=swap" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/swiper@9.1.0/swiper-bundle.min.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../page/header.jsp"/>
<div id="wp-content">
    <div class="background">
        <img src="/images/default/background.jpg" class="background_image" alt=""/>
    </div>
    <div class="container user">
        <div class="row">
            <div class="col-4">
                <div class="user_head">
                    <div class="wrapper_user">
                        <div class="avatar">
                            <img src="${user.avatar}" alt="" />
                        </div>
                        <div class="username">${user.username}</div>
                    </div>
                    <div class="wrapper-message">
                        <div class="message">
                            <i class="fa-regular fa-message"></i>
                            <div>Nhắn tin</div>
                        </div>
                    </div>
                </div>
                <div class="user_detail">
                    <div class="user_detail_block">
                        <label for="">Giới tính</label>
                        <div class="gender text">${user.gender}</div>
                    </div>
                    <div class="user_detail_block">
                        <label for="">Nơi ở</label>
                        <div class="address text">${user.address}</div>
                    </div>
                    <div class="user_description">
                        <label for="">Mô tả bản thân</label>
                        <div class="description">
                            <div class="text">
                                ${user.description}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="bookshelf book_block">
                    <label for="">Đã cất giữ </label>
                    <div class="number_in_bookshelf number">${user.numberInBookShelf}</div>
                    <label class="text" for="">công pháp</label>
                </div>
                <div class="book_like book_block">
                    <label for="">Hứng thú với </label>
                    <div class="number_book_like number">${user.numberBookRead}</div>
                    <label class="text" for=''>công pháp</label>
                </div>
                <div class="book_read">
                    <label for="">Truyện đang đọc: </label>
                    <div class="slider_container">
                        <div class="swiper mySwiper">
                            <div class="swiper-wrapper">
                                <c:forEach items="${bookList}" var="book">
                                    <div class="swiper-slide">
                                        <div class="book">
                                            <a href="#"
                                            ><img
                                                    src="${book.bookImage}"
                                                    alt=""
                                                    class="avatar_mention"
                                            /></a>
                                            <div class="book_detail">
                                                <div class="book_name">${book.bookName}</div>
                                                <div class="book_author"><i class="fa-solid fa-user"></i>${book.bookAuthor}</div>
                                                <div class="book_description">
                                                    ${book.bookDescription}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="swiper-pagination"></div>
                            <div class="swiper-button-prev button"></div>
                            <div class="swiper-button-next button"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script src="/js/user/profile.js"></script>
<script src="/js/page/header.js"></script>
</html>