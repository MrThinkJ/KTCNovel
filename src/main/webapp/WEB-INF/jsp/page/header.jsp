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
                        <li><a href="/truyen?type=1">Tiên Hiệp</a></li>
                        <li><a href="/truyen?type=2">Huyền Huyễn</a></li>
                        <li><a href="/truyen?type=3">Đô Thị</a></li>
                        <li><a href="/truyen?type=4">Kiếm Hiệp</a></li>
                        <li><a href="/truyen?type=5">Linh Dị</a></li>
                        <li><a href="/truyen?type=6">Huyền Nghi</a></li>
                        <li><a href="/truyen?type=7">Mạt Thế</a></li>
                    </ul>
                </div>
                <div class="rank">
                    <div class="title">
                        <a href="/bang-xep-hang/doc-nhieu">
                            Bảng xếp hạng
                            <i class="fa-solid fa-caret-down icon-category"></i>
                        </a>
                    </div>
                    <ul class="sub-menu sub-menu-rank">
                        <li><a href="/bang-xep-hang/doc-nhieu">Đọc Nhiều</a></li>
                        <li><a href="/bang-xep-hang/yeu-thich">Yêu Thích</a></li>
                    </ul>
                </div>
            </div>
            <form action="/truyen" id="search-box" method="get">
                <input type="text" id="search-text" name="keyword"/>
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
                <a href="/login" class="register">Đăng ký</a>
                <a href="/login" class="login">Đăng nhập</a>
            </div>
        </div>
    </nav>
</div>
