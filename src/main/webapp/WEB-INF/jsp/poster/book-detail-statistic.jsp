<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*" %>
<%
    List<List<Object>> revenue = (List<List<Object>>) request.getAttribute("revenue");
    Gson gsonObj = new Gson();
    Map<Object,Object> map = null;
    List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
    for (List<Object> day : revenue){
        Date date = (Date)day.get(1);
        long timestamp = date.getTime();
        map = new HashMap<Object,Object>(); map.put("x", timestamp); map.put("y", day.get(0)); list.add(map);
    }
    String dataPoints = gsonObj.toJson(list);
    System.out.println(dataPoints.toString());
%>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Kỳ Thư Các</title>
<link rel="icon" href="/images/page/logo.png" type="image/x-icon" />
    <link rel="stylesheet" href="/css/poster/book-detail-statistic.css"/>
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
    <script type="text/javascript">
        window.onload = function() {
            const chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                exportEnabled: true,
                title: {
                    text: "Doanh Thu"
                },
                axisX: {
                    title: "Date",
                    valueFormatString: "DD/MM/YYYY",
                    type: "date"
                },
                axisY:{
                    includeZero: true
                },
                data: [{
                    xValueType: "dateTime",
                    type: "column",
                    indexLabelFontColor: "#5A5757",
                    indexLabelPlacement: "outside",
                    dataPoints: <%out.print(dataPoints);%>
                }]
            });
            chart.render();
        }
    </script>
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
    <div class="directory">
        Thống kê doanh thu<i class="fa-solid fa-circle"></i>${bookName}
    </div>
    <div class="chart-container">
        <a href="/nguoi-dang/novel/cash-out/${bookLink}">
            <div class="cash-out-btn">
                Giao dịch
            </div>
        </a>
        <div id="chartContainer" style="height: 370px; width: 100%;"></div>
    </div>
    <div class="purchase-list">
        <div class="title">Danh sách mua chương gần nhất<i class="fa-solid fa-circle"></i></div>
        <div class="list">
            <div class="container">
                <div class="row head">
                    <div class="col-4">Tên chương</div>
                    <div class="col-2">Thời gian mua</div>
                    <div class="col-2">Giá chương</div>
                    <div class="col-2">Người mua</div>
                </div>
                <c:forEach items="${chapterList}" var="chapter">
                    <div class="row purchase-information">
                        <div class="chapter-name col-4">${chapter.chapterName}</div>
                        <div class="puchase-date col-2">${chapter.purchaseDate}</div>
                        <div class="chapter-price col-2">
                            ${chapter.chapterPrice} <img class="currency" src="/images/page/currency.png" alt="" style="width: 20px;"/>
                        </div>
                        <div class="username-purchase col-2">
                            <a href="">${chapter.username}</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="note">
        * Lưu ý: Danh sách trên là 10 chương gần nhất được mua, xem đầy đủ tại -> <a href="/nguoi-dang/novel/statistic/${bookLink}/excel">Thống kê</a>
    </div>
</main>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="/js/poster/book-detail-statistic.js"/>
</body>
</html>