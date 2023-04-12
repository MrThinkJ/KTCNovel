<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kỳ Thư Các</title>
<link rel="icon" href="/images/page/logo.png" type="image/x-icon" />
    <link href="<c:url value="/css/user/recharge.css" />" rel="stylesheet">
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
        <img src="/images/default/background.jpg" class="background_image" alt=""/>
    </div>
    <div class="container wrapper-user">
        <div class="row user-head">
            <div class="col-3 left-col">
                <div class="user">
                    <div class="avatar">
                        <img src="${user.avatar}" alt="" class="avatar_image">
                    </div>
                    <div class="user-information">
                        <div class="name">
                            <span>${user.name}</span>
                        </div>
                        <div class="information">
                            <div class="gender">
                                ${gender}
                            </div>
                            <i class="fa-solid fa-circle"></i>
                            <div class="address">
                                ${user.address}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="option">
                    <a href="/trang-ca-nhan/${user.id}"><div class="row"><i class="fa-solid fa-user"></i>Trang cá nhân</div></a>
                    <a href="/tai-khoan/tu-truyen"><div class="row"><i class="fa-solid fa-book"></i>Tủ truyện</div></a>
                    <a href="/tai-khoan/da-doc"><div class="row"><i class="fa-solid fa-book"></i>Đã đọc</div></a>
                    <a href="/tai-khoan/nguyen-thach"><div class="row"><i class="fa-solid fa-coins"></i>Nguyên thạch</div></a>
                    <a href="/tai-khoan/cai-dat"><div class="row"><i class="fa-solid fa-gear"></i>Chỉnh sửa</div></a>
                    <a href="/tai-khoan/nap-tien"><div class="row active"><i class="fa-solid fa-sack-dollar"></i>Nạp tiền</div></a>
                </div>
            </div>
            <div class="col-9 right-col">
                <div class="head">
                    <div class="profile">
                        Tài khoản
                    </div>
                </div>
                <div class="body">
                    <div class="currency-statistic">
                        Tài khoản hiện có: ${currency} <img src="/images/page/currency.png" alt="" width="15">
                    </div>
                    <div class="wrapper-recharge">
                        <div class="wrapper-notification">
                            <div class="notification">
                                <span class="title">Lưu ý khi thực hiện nạp tiền:</span>
                                <span
                                >- Không hoàn lại tiền khi mua nguyên thạch dưới mọi hình thức</span
                                >
                                <br />
                                <span
                                >- Nội dung chuyển tiền phải chính xác, mọi sai lầm về nội dung chuyển tiền sẽ không được giải quyết</span
                                >
                                <br />
                                <span class="noti-limit"
                                >- 1 nguyên thạch<img src="./img/currency.png" class="currency" alt="" width="15"/> = 10 vnđ</span
                                >
                                <span
                                >- Yêu cầu nạp tiền sẽ được xử lí nhiều nhất trong 24h</span
                                >
                            </div>
                        </div>
                    </div>
                    <div class="recharge-method">
                        <div class="row">
                            <div class="momo method col-4">
                                <div class="method-head">
                                    MOMO
                                    <div class="bank-id">
                                        0775102785
                                    </div>
                                </div>
                                <div class="method-body">
                                    <div class="title">
                                        Nội dung chuyển tiền
                                    </div>
                                    <div class="content">
                                        NAP ID 1
                                    </div>
                                </div>
                            </div>
                            <div class="mb method col-4">
                                <div class="method-head">
                                    MBBank
                                    <div class="bank-id">
                                        281220039988
                                    </div>
                                </div>
                                <div class="method-body">
                                    <div class="title">
                                        Nội dung chuyển tiền
                                    </div>
                                    <div class="content">
                                        NAP ID 1
                                    </div>
                                </div>
                            </div>
                            <div class="vcb method col-4">
                                <div class="method-head">
                                    Vietcombank
                                    <div class="bank-id">
                                        92736458912
                                    </div>
                                </div>
                                <div class="method-body">
                                    <div class="title">
                                        Nội dung chuyển tiền
                                    </div>
                                    <div class="content">
                                        NAP ID 1
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/user/bookshelf.js"></script>
<script src="/js/page/header.js"></script>
</html>