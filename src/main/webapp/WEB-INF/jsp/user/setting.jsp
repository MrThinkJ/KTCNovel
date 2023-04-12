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
    <link href="<c:url value="/css/user/setting.css" />" rel="stylesheet">
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
    <script src="https://cdn.jsdelivr.net/npm/cropperjs@1.5.12/dist/cropper.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/cropperjs@1.5.12/dist/cropper.min.css" />
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
                    <a href="/tai-khoan/cai-dat"><div class="row active"><i class="fa-solid fa-gear"></i>Chỉnh sửa</div></a>
                    <a href="/tai-khoan/nap-tien"><div class="row"><i class="fa-solid fa-sack-dollar"></i>Nạp tiền</div></a>
                </div>
            </div>
            <div class="col-9 right-col">
                <div class="head">
                    <div class="profile">
                        Hồ sơ
                    </div>
                </div>
                <div class="body">
                    <div class="avatar">
                        <div class="wrapper-image">
                            <img src="${user.avatar}" alt="" class="avatar_image">
                            <input accept="image/png, image/gif, image/jpeg, image/bmp, image/x-icon" type="file" class="avatar-input">
                        </div>
                        <span>Ấn vào để đổi ảnh đại diện, không quá 2MB</span>
                        <div class="avatar-cropper-container">
                            <div class="avatar-cropper-image">
                                <img src="#" alt="" id="preview">
                            </div>
                        </div>
                    </div>
                    <div class="config-form">
                        <form action="" id="config-form">
                            <div class="field">
                                <label for="username">Tên hiển thị</label>
                                <input type="text" class="username" name="name" id="username" value="${user.name}">
                            </div>
                            <div class="field">
                                <label for="address">Địa chỉ</label>
                                <input type="text" class="address" name="address" id="address" value="${user.address}">
                            </div>
                            <div class="field" >
                                <label for="">Giới tính</label>
                                <select name="gender" id="gender">
                                    <option value="-1">-- Chọn --</option>
                                    <option value="male">Nam nhân</option>
                                    <option value="female">Nữ nhân</option>
                                    <option value="none">Bán thân bất toại</option>
                                </select>
                            </div>
                            <div class="field">
                                <label for="description">Mô tả về bản thân</label>
                                <textarea name="description" id="description" cols="100" rows="10" id="description">${user.description}</textarea>
                            </div>
                            <button class="submit">Cập nhật</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script src="/js/user/setting.js"></script>
<script src="/js/page/header.js"></script>
</html>