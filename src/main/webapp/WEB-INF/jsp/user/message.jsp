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
    <link href="<c:url value="/css/user/message.css" />" rel="stylesheet">
    <link href="<c:url value="/css/page/header.css" />" rel="stylesheet">
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
        <img
                src="/images/default/background.jpg"
                class="background_image"
                alt="anhbia1"
        />
    </div>
    <div class="container wrapper-message">
        <div class="header">
            <div class="avatar">
                <img src="${toAccount.user.avatar}" alt="" />
            </div>
            ${toAccount.user.name}
        </div>
        <div class="body">
            <div class="message-container">
                <c:forEach items="${messageList}" var="message">
                    <c:if test="${message.fromAccount.id == toAccount.id}">
                        <div class="receive">
                            <div class="avatar">
                                <img src="${message.fromAccount.user.avatar}" alt="" />
                            </div>
                            <div class="message-content">
                                <div class="content">
                                    ${message.content}
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${message.fromAccount.id != toAccount.id}">
                        <div class="send">
                            <div class="avatar">
                                <img src="${message.toAccount.user.avatar}" alt="" />
                            </div>
                            <div class="message-content">
                                <div class="content">
                                    ${message.content}
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <div class="input-field">
            <form action="" id="message-form">
                <input type="text" placeholder="Aa" name="content"/>
                <button class="send-btn"><i class="fa-solid fa-paper-plane"></i></button>
            </form>
        </div>
    </div>
</div>
<div class="notification">
</div>
<script src="/js/user/message.js"></script>
</body>
<script src="/js/page/header.js"></script>
</html>