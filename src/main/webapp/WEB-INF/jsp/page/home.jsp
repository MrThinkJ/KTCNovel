<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Kỳ Thư Các</title>
<link rel="icon" href="/images/page/logo.png" type="image/x-icon" />
    <link href="<c:url value="/css/page/home.css"/>" rel="stylesheet">
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
<c:if test="${isAdmin}">
    <div class="admin"><a href="/admin" style="color: #fff">Quản Lí</a> </div>
</c:if>
<body>
<div id="wrapper">
    <jsp:include page="../page/header.jsp"/>
    <!-- END HEADER -->
    <div id="wp-content">
        <!-- SLIDER -->
        <div id="carousel" class="carousel slide">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <a href="#"
                    ><img src="<c:url value="images/page/bia1.jpg"/>" class="d-block w-100" alt="anhbia1"
                    /></a>
                </div>
                <div class="carousel-item">
                    <a href="#"
                    ><img src="<c:url value="images/page/bia1.jpg"/>" class="d-block w-100" alt="anhbia2"
                    /></a>
                </div>
                <div class="carousel-item">
                    <a href="#"
                    ><img src="<c:url value="images/page/bia1.jpg"/>" class="d-block w-100" alt="anhbia3"
                    /></a>
                </div>
            </div>
            <button
                    class="carousel-control-prev"
                    type="button"
                    data-bs-target="#carousel"
                    data-bs-slide="prev"
            >
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button
                    class="carousel-control-next"
                    type="button"
                    data-bs-target="#carousel"
                    data-bs-slide="next"
            >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!-- END SLIDER -->

        <!-- MENTION READING -->
        <div class="container" id="mention_reading">
            <div class="row p-4 mt-4 glass">
                <!-- CONTENT MENTION -->
                <div class="col-8" id="mention">
                    <h5 class="d-inline">Biên tập viên đề cử</h5>
                    <div class="row mention_row">
                        <c:forEach var="book" items="${bookRecent}" begin="1" end="8">
                            <div class="col-6 d-flex mention_col book-col">
                                <a href="/truyen/${book.get(0).convertAll()}"
                                ><img
                                        src="${book.get(0).image}"
                                        alt=""
                                        class="avatar_mention"
                                        width="80"
                                        height="100"
                                /></a>
                                <div class="wp_content_mention ms-3">
                                    <a href="/truyen/${book.get(0).convertAll()}" class="title_mention"
                                    >${book.get(0).name}</a
                                    >
                                    <p class="des_mention" style="max-width: 250px">
                                        ${book.get(0).description}
                                    </p>
                                    <div class="author_mention">
                                        <a href="#" class="name_author_mention"
                                        ><i class="fa-solid fa-user"></i>${book.get(0).author}</a
                                        >
                                        <br/>
                                        <a href="#" class="type_mention">${book.get(0).typeList.get(0).name}</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!--END CONTENT MENTION -->
                </div>
                <!-- CONTENT READING -->
                <div class="col-4" id="reading">
                    <h5 class="d-inline">Đang đọc</h5>
                    <c:forEach items="${bookReadList}" var="book">
                        <div class="rate_row row mt-4">
                            <div class="content_had_read d-flex">
                                <a href="/truyen/${book.convertAll()}"
                                ><img
                                        src="<c:url value="${book.bookImage}"/>"
                                        alt=""
                                        class="avatar_mention"
                                        width="35"
                                        height="50"
                                /></a>
                                <div class="wp_content_mention ms-3">
                                    <a href="#" class="title_mention"
                                    >${book.bookName}</a
                                    >
                                    <div class="had_read">
                                        <div class="had_read_yet">
                                            Đã đọc:
                                            <span class="number_read me-2">${book.chapterMark} / ${book.totalChapter}</span>
                                            <a href="#" class="delete_read"
                                            ><i class="fa-solid fa-trash-can text-dark"></i
                                            ></a>
                                        </div>
                                        <div class="continue">
                                            <a
                                                    href="/truyen/${book.convertAll()}/chuong-${book.chapterMark}"
                                                    class="continue_read text-decoration-none float-end text-dark"
                                            >Đọc tiếp</a
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <hr/>
                    <!-- GUIDE -->
                    <h5 class="d-inline">Hướng dẫn</h5>
                    <ul class="guide" style="padding-left: 1.2rem">
                        <li class="mb-2">
                            <a href="#" class="text-dark text-decoration-none">
                                Làm sao đổi màu nền, cỡ chữ, font chữ
                            </a>
                        </li>
                        <li class="mb-2">
                            <a href="#" class="text-dark text-decoration-none">
                                Làm sao đổi màu nền, cỡ chữ, font chữ
                            </a>
                        </li>
                        <li class="mb-2">
                            <a href="#" class="text-dark text-decoration-none">
                                Làm sao đổi màu nền, cỡ chữ, font chữ
                            </a>
                        </li>
                        <li class="mb-2">
                            <a href="#" class="text-dark text-decoration-none">
                                Làm sao đổi màu nền, cỡ chữ, font chữ
                            </a>
                        </li>
                    </ul>
                    <!-- END GUIDE -->
                </div>
                <!-- END CONTENT READING -->
            </div>
            <!-- END CONTENT READING -->
        </div>
        <!-- END MENTION READING -->

        <!-- NEW UPDATE -->
        <div id="new_update" class="mt-5">
            <div class="container">
                <div class="new_update_head">
                    <h5 class="d-inline">Mới cập nhật</h5>
                    <a href="/truyen?keyword=&sortBy=newUpdated">
                        <h6 class="float-end text-dark view_all">Xem tất cả</h6>
                    </a>
                </div>
                <div class="table_new_update">
                    <table class="table table-borderless table-hover">
                        <tbody class="text-center">
                        <c:forEach items="${bookRecent}" var="bookChapter">
                            <tr>
                                <th scope="row" class="type">${bookChapter.get(0).typeList.get(0).name}</th>
                                <td class="name">
                                    <a href="/truyen/${bookChapter.get(0).convertAll()}"
                                    >${bookChapter.get(0).name}</a
                                    >
                                </td>
                                <td class="chapter">
                                    <a href="/truyen/${bookChapter.get(0).convertAll()}/chuong-${bookChapter.get(1).indexInBook}"
                                    >Chương ${bookChapter.get(1).indexInBook}: ${bookChapter.get(1).chapterName}</a
                                    >
                                </td>
                                <td class="author">
                                    <span>${bookChapter.get(0).author}</span>
                                </td>
                                <td class="poster"><span>${bookChapter.get(0).account.user.name}</span></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- END NEW UPDATE -->

        <!-- RANKING -->
        <div id="ranking" class="ranking">
            <div class="container">
                <div
                        class="row d-flex justify-content-center"
                        style="justify-content: space-between !important"
                >
                    <!-- MOST READ -->
                    <div class="col-4 p-3 glass" id="most_read" style="width: 32%">
                        <div class="ranking_header">
                            <h5 class="d-inline">Đọc nhiều</h5>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top1.png"/> "
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestViewBooks[0].convertAll()}">${highestViewBooks[0].bookName}</a>
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[0].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[0].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[0].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestViewBooks[0].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestViewBooks[0].bookImage}"/> "/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top2.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestViewBooks[1].convertAll()}">${highestViewBooks[1].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[1].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[1].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[1].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestViewBooks[1].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestViewBooks[1].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top3.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestViewBooks[2].convertAll()}">${highestViewBooks[2].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[2].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[2].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestViewBooks[2].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestViewBooks[2].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestViewBooks[2].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- END MOST READ -->

                    <!-- MOST POPULAR -->
                    <div class="col-4 p-3 glass" id="most_common" style="width: 32%">
                        <div class="ranking_header">
                            <h5 class="d-inline">Thích nhiều</h5>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top1.png"/> "
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestLikeBooks[0].convertAll()}">${highestLikeBooks[0].bookName}</a>
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[0].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[0].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[0].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestLikeBooks[0].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestLikeBooks[0].bookImage}"/> "/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top2.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestLikeBooks[1].convertAll()}">${highestLikeBooks[1].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[1].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[1].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[1].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestLikeBooks[1].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestLikeBooks[1].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top3.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestLikeBooks[2].convertAll()}">${highestLikeBooks[2].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[2].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[2].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestLikeBooks[2].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestLikeBooks[2].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestLikeBooks[2].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- END POPULAR -->
                    <div class="col-4 p-3 glass" id="suggest" style="width: 32%">
                        <div class="ranking_header">
                            <h5 class="d-inline">Cất giữ nhiều</h5>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top1.png"/> "
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestBookshelfBooks[0].convertAll()}">${highestBookshelfBooks[0].bookName}</a>
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[0].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[0].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[0].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestBookshelfBooks[0].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestBookshelfBooks[0].bookImage}"/> "/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top2.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestBookshelfBooks[1].convertAll()}">${highestBookshelfBooks[1].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[1].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[1].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[1].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestBookshelfBooks[1].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestBookshelfBooks[1].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                        <div class="wp_all_rank d-flex mt-3 mb-4">
                            <div class="rank_top">
                                <img
                                        src="<c:url value="images/page/top3.png"/>"
                                        alt=""
                                        width="50px"
                                        height="50px"
                                />
                            </div>
                            <div class="wp_rank_content">
                                <div class="rank_title truncate">
                                    <a href="/truyen/${highestBookshelfBooks[2].convertAll()}">${highestBookshelfBooks[2].bookName}</a
                                    >
                                </div>
                                <div class="rank_view">
                                    <i class="fa-solid fa-eye"></i
                                    ><div class="rank_view_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[2].bookView}</div
                                >
                                </div>
                                <div class="rank_author">
                                    <i class="fa-solid fa-user-pen"></i
                                    ><div class="rank_author_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[2].bookAuthor}</div
                                >
                                </div>
                                <div class="rank_type">
                                    <i class="fa-solid fa-tag"></i
                                    ><div class="rank_type_content m-2 text-decoration-none text-dark"
                                >${highestBookshelfBooks[2].typeName}</div
                                >
                                </div>
                            </div>
                            <a class="book-container" href="/truyen/${highestBookshelfBooks[0].convertAll()}" target="_blank">
                                <div class="book">
                                    <img alt="..." src="<c:url value="${highestBookshelfBooks[2].bookImage}"/>"/>
                                </div>
                            </a>
                        </div>
                    </div>
                    <!-- SUGGEST -->

                    <!-- END SUGGEST -->
                </div>
            </div>
        </div>
        <!-- END RANKING -->
        <!-- SWIPER -->
        <div class="container text-center newest">
            <h5 style="font-size: 40px">MỚI ĐĂNG</h5>
        </div>
        <div class="swiper" style="height: 507px">
            <div class="app">
                <div class="cardList">
                    <button class="cardList__btn btn btn--left">
                        <div class="icon">
                            <svg>
                                <use xlink:href="#arrow-left"></use>
                            </svg>
                        </div>
                    </button>

                    <div class="cards__wrapper">
                        <div class="card current--card">
                            <div class="card__image">
                                <a href="#">
                                    <img src="<c:url value="${bookRecentFoot.get(0).bookImage}"/> " alt=""/>
                                </a>
                            </div>
                        </div>

                        <div class="card next--card">
                            <div class="card__image">
                                <a href="#">
                                    <img src="<c:url value="${bookRecentFoot.get(1).bookImage}"/>" alt=""/>
                                </a>
                            </div>
                        </div>

                        <div class="card previous--card">
                            <div class="card__image">
                                <a href="#">
                                    <img src="<c:url value="${bookRecentFoot.get(2).bookImage}"/>" alt=""/>
                                </a>
                            </div>
                        </div>
                    </div>

                    <button class="cardList__btn btn btn--right">
                        <div class="icon">
                            <svg>
                                <use xlink:href="#arrow-right"></use>
                            </svg>
                        </div>
                    </button>
                </div>

                <div class="infoList">
                    <div class="info__wrapper">
                        <div class="info current--info">
                            <h1 class="text name">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(0).bookName}</a
                                >
                            </h1>
                            <h4 class="text location">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(0).bookAuthor}</a
                                >
                            </h4>
                            <p class="text description">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(0).bookDescription}</a
                                >
                            </p>
                        </div>

                        <div class="info next--info">
                            <h1 class="text name">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(1).bookName}</a
                                >
                            </h1>
                            <h4 class="text location">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(1).bookAuthor}</a
                                >
                            </h4>
                            <p class="text description">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(1).bookDescription}</a
                                >
                            </p>
                        </div>

                        <div class="info previous--info">
                            <h1 class="text name">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(2).bookName}</a
                                >
                            </h1>
                            <h4 class="text location">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(2).bookAuthor}</a
                                >
                            </h4>
                            <p class="text description">
                                <a href="#" class="text-decoration-none text-white"
                                >${bookRecentFoot.get(2).bookDescription}</a
                                >
                            </p>
                        </div>
                    </div>
                </div>

                <div class="app__bg">
                    <div class="app__bg__image current--image">
                        <img src="<c:url value="${bookRecentFoot.get(0).bookImage}"/>" alt=""/>
                    </div>
                    <div class="app__bg__image next--image">
                        <img src="<c:url value="${bookRecentFoot.get(1).bookImage}"/>" alt=""/>
                    </div>
                    <div class="app__bg__image previous--image">
                        <img src="<c:url value="${bookRecentFoot.get(2).bookImage}"/>" alt=""/>
                    </div>
                </div>
            </div>

            <div class="loading__wrapper">
                <div class="loader--text">Loading...</div>
                <div class="loader">
                    <span></span>
                </div>
            </div>

            <svg class="icons" style="display: none">
                <symbol
                        id="arrow-left"
                        xmlns="http://www.w3.org/2000/svg"
                        class="fs-20"
                        viewBox="0 0 512 512"
                >
                    <polyline
                            points="328 112 184 256 328 400"
                            style="
                  fill: none;
                  stroke: #fff;
                  stroke-linecap: round;
                  stroke-linejoin: round;
                  stroke-width: 48px;
                "
                    />
                </symbol>
                <symbol
                        id="arrow-right"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 512 512"
                >
                    <polyline
                            points="184 112 328 256 184 400"
                            style="
                  fill: none;
                  stroke: #fff;
                  stroke-linecap: round;
                  stroke-linejoin: round;
                  stroke-width: 48px;
                "
                    />
                </symbol>
            </svg>

            <!-- partial -->
            <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.3.3/gsap.min.js"></script>
        </div>
        <!-- END SWIPER -->
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
<script src="<c:url value="/js/page/home.js"/>"></script>
<script src="<c:url value="/js/page/header.js"/>"></script>
</body>
</html>