<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/category_list.css">
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
                    <th>저자</th>
                    <th>출판사</th>
                    <th>카테고리</th>
                    <th>도서상태</th>
                    <th>출판일</th>
                    <th>등록일</th>
                    <th>수정일</th>
                    <th>재고</th>
                    <th>적립 포인트</th>
                    <th>조작</th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${data.total == 0}">
                        <tr>
                            <td id="nodata" colspan="12">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${data.list}" var="b">
                        <tr>
                            <td>${b.bi_seq}</td>
                            <td>${b.bi_name}</td>
                            <td>${b.bi_writer}</td>
                            <td>${b.bi_company}</td>
                            <td>${b.bi_cate}</td>
                            <td class="book_status">
                                <c:if test="${b.bi_status == 1}">
                                    <span style="background-color: rgb(17, 226, 27)">입고</span>
                                </c:if>
                                <c:if test="${b.bi_status == 2}">
                                    <span style="background-color: rgb(251, 186, 64)">입고예정</span>
                                </c:if>
                                <c:if test="${b.bi_status == 3}">
                                    <span style="background-color: rgb(211, 115, 115)">절판</span>
                                </c:if>
                                <c:if test="${b.bi_status == 4}">
                                    <span style="background-color: rgb(255, 23, 23)">리콜</span>
                                </c:if>
                            </td>
                            <td>${b.bi_pub_dt}</td>
                            <td><fmt:formatDate value="${b.bi_reg_dt}" pattern="yyyy년-MM월-dd일 (EE) HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${b.bi_mod_dt}" pattern="yyyy년-MM월-dd일 (EE) HH:mm:ss"/></td>
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
        <div class="popup" id="category_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-book-open"></i>
                </div>
                <h2>도서 추가</h2>
                <p>도서 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="bi_cate_name" placeholder="카테고리" disabled>
                <button id="search_cate">카테고리 선택</button>
                <input type="text" id="bi_name" placeholder="도서명">
                <input type="text" id="bi_code" placeholder="도서코드">
                <input type="text" id="bi_writer" placeholder="저자,번역가">
                <input type="text" id="bi_company" placeholder="출판사">
                <input type="text" id="bi_pub_dt" placeholder="출판일(.을 제외한 숫자 8자리)">
                <input type="text" id="bi_stock" placeholder="재고">
                <input type="text" id="bi_point" placeholder="적립 포인트">
                <select id="bi_status">
                    <option value="1">입고</option>
                    <option value="2">입고 예정</option>
                    <option value="3">절판</option>
                    <option value="4">리콜</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_cate">등록하기</button>
                <button id="modify_cate">수정하기</button>
                <button id="cancel_cate">취소하기</button>
            </div>
            <div class="category_search">
                <div class="cate_search_box">
                    <input type="text" id="cate_keyword" placeholder="예) 문학, 외국문학">
                    <button id="cate_search_btn"><i class="fas fa-search"></i></button>
                </div>
                <div class="search_result">
                    <ul>
                        
                    </ul>
                </div>
                <div class="cate_search_buttons">
                    <button id="cate_search_close">닫기</button>
                </div>
        </div>
    </div>
</body>
</html>