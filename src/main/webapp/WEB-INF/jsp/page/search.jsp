<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String queryString = request.getQueryString();
    Boolean contain = queryString.contains("&");
    String newParam = "sortBy=newUpdated";
    String newURL;
    if (!queryString.contains("sortBy")) {
        newURL = "?" + queryString + "&" + newParam;
    } else{
        newURL = "?" + queryString.split("&")[0];
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kỳ Thư Các</title>
<link rel="icon" href="/images/page/logo.png" type="image/x-icon" />
    <link href="<c:url value="/css/page/search.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/page/header.css" />" rel="stylesheet">
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
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link href="<c:url value="/css/swiper.css"/>" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <jsp:include page="../page/header.jsp"/>
    <!-- END HEADER -->
    <div id="wp-content">
        <!-- SLIDER -->
        <div class="background">
            <img
                    src="/images/default/background.jpg"
                    class="background_image"
                    alt="anhbia1"
            />
        </div>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <div class="wrapper-type">
                        <div class="selected-type">
                            <div class="selected-title">Đã chọn</div>
                            <div class="selected">${type}</div>
                        </div>
                        <div class="type-title">Thể loại</div>
                        <div class="type-container">
                            <a href="/truyen?type=1">
                                <div class="type">Tiên hiệp</div>
                            </a>
                            <a href="/truyen?type=2">
                                <div class="type">Huyền huyễn</div>
                            </a>
                            <a href="/truyen?type=3">
                                <div class="type">Đô thị</div>
                            </a>
                            <a href="/truyen?type=4">
                                <div class="type">Kiếm hiệp</div>
                            </a>
                            <a href="/truyen?type=5">
                                <div class="type">Linh dị</div>
                            </a>
                            <a href="/truyen?type=6">
                                <div class="type">Huyền nghi</div>
                            </a>
                            <a href="/truyen?type=7">
                                <div class="type">Mạt thế</div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <div class="sort-method">
                        <div class="sort-title">Sắp xếp theo</div>
                        <c:if test="<%=contain%>">
                            <a href="<%=newURL%>">
                                <div class="sort-type active">
                                    Mới cập nhật
                                </div>
                            </a>
                        </c:if>
                        <c:if test="<%=!contain%>">
                            <a href="<%=newURL%>">
                                <div class="sort-type">
                                    Mới cập nhật
                                </div>
                            </a>
                        </c:if>
                    </div>
                    <div class="row">
                        <c:forEach items="${bookList}" var="book">
                            <div class="book col-6">
                                <div class="book-image">
                                    <a href="/truyen/${book.convertAll()}"><img src="${book.image}" alt=""/></a>
                                </div>
                                <div class="book-information">
                                    <div class="book-name"><a href="/truyen/${book.convertAll()}">${book.name}</a></div>
                                    <div class="book-description">
                                            ${book.description}
                                    </div>
                                    <div class="book-foot">
                                        <div class="book-author">
                                            <i class="fa-solid fa-user-pen"></i>${book.author}
                                        </div>
                                        <div class="book-type">${book.typeList.get(0).name}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END CONTENT -->
    <div id="footer" class="mt-5">
        <div class="container text-center">
            <a href="#"><img src="./images/page/logo.png" height="50px" alt=""/></a>
            <p class="content_footer">MrThinkJ</p>
            <div class="content_footer">
                <a href="#" class="text-dark text-decoration-none p-2"
                >Điều khoản dịch vụ</a
                >
                <a href="#" class="text-dark text-decoration-none p-2"
                >Chính sách bảo mật</a
                >
                <a href="#" class="text-dark text-decoration-none p-2"
                >Về bản quyền</a
                >
                <a href="#" class="text-dark text-decoration-none p-2"
                >Hướng dẫn sử dụng</a
                >
            </div>
        </div>
    </div>
    <!-- END FOOTER -->
</div>
<script src="<c:url value="/js/page/header.js"/>"></script>
</body>
</html>