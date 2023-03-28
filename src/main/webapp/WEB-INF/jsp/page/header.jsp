<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<div id="header">
    <nav>
        <div class="menu container px-3">
            <a href="/"><img src="/images/page/logo.png" height="50px" alt="" /></a>
            <div class="left-nav">
                <div class="category">
                    <div class="title">
                        Thể loại
                        <i class="fa-solid fa-caret-down icon-category"></i>
                    </div>
                    <ul class="sub-menu sub-menu-category">
                        <li><a href="#">Tất Cả</a></li>
                        <li><a href="">Huyền Huyễn</a></li>
                        <li><a href="">Võng Du</a></li>
                        <li><a href="">Đồng Nhân</a></li>
                        <li><a href="">Cạnh Kỹ</a></li>
                        <li><a href="">Kiếm Hiệp</a></li>
                        <li><a href="">Tiên Hiệp</a></li>
                        <li><a href="">Khoan Huyễn</a></li>
                        <li><a href="">Đô Thị</a></li>
                        <li><a href="">Dã Sử</a></li>
                        <li><a href="">Huyền Nghi</a></li>
                        <li><a href="">Kì Ảo</a></li>
                    </ul>
                </div>
                <div class="rank">
                    <div class="title">
                        <a href="#">
                            Bảng xếp hạng
                            <i class="fa-solid fa-caret-down icon-category"></i>
                        </a>
                    </div>
                    <ul class="sub-menu sub-menu-rank">
                        <li><a href="#">Thịnh Hành</a></li>
                        <li><a href="#">Đọc Nhiều</a></li>
                        <li><a href="#">Tặng Thưởng</a></li>
                        <li><a href="#">Đề Cử</a></li>
                        <li><a href="#">Yêu Thích</a></li>
                        <li><a href="#">Thảo Luận</a></li>
                    </ul>
                </div>
            </div>
            <form action="" id="search-box">
                <input type="text" id="search-text" />
                <button id="search-btn">
                    <i class="fa-solid fa-magnifying-glass icon-search"></i>
                </button>
            </form>
            <div class="post-book">
                <a href="/nguoi-dang">
                    <i class="fa-solid fa-plus"></i>
                    Đăng truyện
                </a>
            </div>
            <div class="action">
                <a href="/register" class="register">Đăng ký</a>
                <a href="/login" class="login">Đăng nhập</a>
            </div>
        </div>
    </nav>
</div>