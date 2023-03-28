<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row">
    <div class="col-12 d-flex mt-4 ms-3">
        <div class="avatar float-left me-4">
            <a href="#"
            ><img
                    class="img_shadown"
                    src="${book.image}"
                    alt=""
                    width="180"
                    height="240"
                    class="avatar_mention"
            /></a>
        </div>
        <div class="detail_des float-right">
            <div class="title_book">
                <a href="#" style="color: black">${book.name}</a>
            </div>

            <div class="tag_book mt-2 mb-2">
                <a
                        href="#"
                        class="d-inline-block border border-secondary px-3 py-1 text-secondary"
                >${book.author}</a
                >
                <a
                        href="#"
                        class="d-inline-block border border-danger px-3 py-1 text-danger "
                >${status}</a
                >
                <a
                        href="#"
                        class="d-inline-block border border-primary px-3 py-1 text-primary"
                >${book.typeList.get(0).name}</a
                >
            </div>

            <ul class="list-unstyled d-flex mb-2 mt-3">
                <li class="me-4 evaluate">
                    <div class="mb-1 num">${totalChapter}</div>
                    <div class="text">Chương</div>
                </li>
                <li class="me-4 evaluate">
                    <div class="mb-1 num">${bookLike}</div>
                    <div class="text">Lượt thích</div>
                </li>
                <li class="me-4 evaluate">
                    <div class="mb-1 num">${book.view}</div>
                    <div class="text">Lượt đọc</div>
                </li>
                <li class="me-4 evaluate">
                    <div id="bookmarkedValue" class="mb-1 num">${bookShelf}</div>
                    <div class="text">Cất giữ</div>
                </li>
            </ul>

            <div class="d-flex align-items-center mb-3">
                <div class="mai-rating">
                    <i class="fa-solid fa-star text-warning"></i>
                    <i class="fa-solid fa-star text-warning"></i>
                    <i class="fa-solid fa-star text-warning"></i>
                    <i class="fa-solid fa-star text-warning"></i>
                    <i class="fa-regular fa-star text-warning"></i>
                    <span class="d-inline-block ml-2"
                    ><span class="fw-bold">${averageRate}</span>/5</span
                    >
                </div>
            </div>
            <div class="linkToRead d-flex">
                <div class="d-flex">
                    <c:if test="${chapterMark == null}">
                        <a href="/truyen/${book.convertAll()}/chuong-1" class="text-white reading">
                            <i class="fa-solid fa-glasses"></i>
                            Đọc Truyện
                        </a>
                    </c:if>
                    <c:if test="${chapterMark != null}">
                        <a href="/truyen/${book.convertAll()}/chuong-${chapterMark}" class="text-white reading">
                            <i class="fa-solid fa-glasses"></i>
                            Đọc Tiếp
                        </a>
                    </c:if>
                </div>
                <div class="d-flex">
                    <a href="/tai-khoan/tu-truyen/add/${book.convertAll()}" class="mark_book">
                        <i class="fa-solid fa-bookmark"></i>
                        Thêm vào tủ truyện
                    </a>
                </div>
                <div class="d-flex">
                    <a href="/truyen/${book.convertAll()}/like" class="mark_book">
                        <i class="fa-solid fa-bookmark"></i>
                        Thích
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>