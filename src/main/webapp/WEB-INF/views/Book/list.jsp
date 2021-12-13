<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/book_list.css">
    <script src="/assets/js/book.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-book"></i> 도서 관리</h1>
        <button id="add_category"><i class="fas fa-plus-circle"></i>도서 추가</button>
        <div class="content_area">
                <div class="menu_area">
                    <div class="search_box">
                        <input type="text" id="keyword" placeholder="검색어 입력">
                        <button id="search_btn"><i class="fas fa-search"></i></button>
                    </div>
                    <button id="reset_btn">초기화</button>
                </div>
        <div class="table_area">
            <table>
                <thead>
                    <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>소개</th>
                    <th>저자</th>
                    <th>출판사</th>
                    <th>카테고리</th>
                    <th>도서상태</th>
                    <th>출판일</th>
                    <th>등록일</th>
                    <th>재고</th>
                    <th>적립 포인트</th>
                    <th>조작</th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${data.total == 0}">
                        <tr>
                            <td id="nodata" colspan="6">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${data.list}" var="b">
                        <tr>
                            <td>${b.bi_seq}</td>
                            <td>${b.bi_name}</td>
                            <td>${b.bi_sub}</td>
                            <td>${b.bi_writer}</td>
                            <td>${b.bi_cop}</td>
                            <td>${b.bi_cate}</td>
                            <td>${b.bi_status}</td>
                            <td>${b.bi_pub_dt}</td>
                            <td>${b.bi_reg_dt}</td>
                            <td>${b.bi_stock}</td>
                            <td>${b.bi_point}</td>
                            <td>
                                <button class="modify_btn" data-seq="${b.bi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                <button class="delete_btn" data-seq="${b.bi_seq}"><i class="fas fa-minus-circle"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pager_area">
            <button id="prev"><i class="fas fa-chevron-left"></i></button>
            <div class="pagers">
                <c:forEach begin="1" end="${data.pageCnt}" var="i">
                    <a href="/book?offset=${(i-1)*10}"${i}></a>
                </c:forEach>
            </div>
            <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="book_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-book-open"></i>
                </div>
                <h2>도서 추가</h2>
                <p>도서 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="bi_name" placeholder="도서명">
                <input type="text" id="bi_sub" placeholder="소제목, 소개">
                <input type="text" id="bi_writer" placeholder="저자,번역가">
                <input type="text" id="bi_cop" placeholder="출판사">
                <input type="text" id="bi_cate" placeholder="카테고리">
                <select id="bi_status">
                    <option value="1">입고</option>
                    <option value="2">입고 예정</option>
                    <option value="3">절판</option>
                    <option value="4">리콜</option>
                </select>
                <input type="text" id="bi_stock" placeholder="재고">
                <input type="text" id="bi_point" placeholder="적립 포인트">
            </div>
            <div class="btn_area">
                <button id="add_cate">등록하기</button>
                <button id="cancel_cate">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>