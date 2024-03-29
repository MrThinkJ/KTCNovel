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
    <link rel="stylesheet" href="/css/poster/cash-out.css"/>
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
        Rút tiền<i class="fa-solid fa-circle"></i>${bookName}
    </div>
    <div class="wrapper-notification">
        <div class="notification">
            <span class="title">Lưu ý khi thực hiện rút tiền:</span>
            <span
            >- Yêu cầu Giao dịch sẽ được xử lí vào ngày 15 và 30 hàng
            tháng</span
            >
            <br />
            <span
            >- Nhập chính xác số tài khoản và tên ngân hàng để tránh xảy ra lỗi
            ngoài ý muốn</span
            >
            <br />
            <span class="noti-limit"
            >- Số tiền thấp nhất có thể rút là 1.000.000 vnd tương đương
            100.000<img src="/image_upload/page/currency.png" class="currency" alt="" /> (
            Nguyên Thạch )</span
            >
            <span
            >- Có thể kiểm tra trạng thái của yêu cầu ở mục
            <a href="">giao dịch</a></span
            >
        </div>
    </div>
    <div class="form">
        <form action="" id="cash-out-form">
            <div class="field">
                <div class="title">
                    <label for="amount">Số tiền muốn rút ( Lưu ý 1 nguyên thạch<img src="/images/page/currency.png" class="currency"> = 10 vnd)</label>
                </div>
                <input type="text" name="amount" id="amount">
            </div>
            <div class="field">
                <div class="title">
                    <label for="bank-id">Số tài khoản người nhận</label>
                </div>
                <input type="text" name="bankId" id="bank-id">
            </div>
            <div class="field">
                <div class="title">
                    <label for="bank">Ngân hàng</label>
                </div>
                <select name="bank" id="bank">
                    <option value="0">--Chọn ngân hàng--</option>
                    <option value="MB">MB</option>
                    <option value="Techcombank">Techcombank</option>
                    <option value="Vietcombank">Vietcombank</option>
                </select>
            </div>
            <button class="cash-out-btn">Rút tiền</button>
        </form>
    </div>
</main>
<div class="notice">
</div>
<script src="/js/poster/cash-out.js"></script>
</body>
</html>