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
    <link rel="stylesheet" href="/assets/css/writer_list.css">
    <script src="/assets/js/writer.js"></script>
</head>
<body>
    <main>
        
        <h1><i class="fas fa-pen-fancy"></i> 작가 관리</h1>
        <button id="add_category"><i class="fas fa-plus-circle"></i>작가 추가</button>
        <div class="content_area">
                <div class="menu_area">
                    <div class="search_box">
                        <select id="search_type">
                            <option value="company">출판사</option>
                            <option value="name"
                                <c:if test="${data.type=='name'}">selected</c:if>
                            >이름</option>
                        </select>
                        <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                        <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
        <div class="table_area">
            <table>
                <thead>
                    <tr>
                    <th>번호</th>
                    <th>작가명</th>
                    <th>생년월일</th>
                    <th>이메일</th>
                    <th>소속 출판사</th>
                    <th>등록일</th>
                    <th>수정일</th>
                    <th>조작</th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${data.total == 0}">
                        <tr>
                            <td id="nodata" colspan="6">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${data.list}" var="w">
                        <tr>
                            <td>${w.wi_seq}</td>
                            <td>${w.wi_name}</td>
                            <td>${w.wi_birth}</td>
                            <td>${w.wi_email}</td>
                            <td>${w.wi_company}</td>
                            <td><fmt:formatDate value="${w.wi_reg_dt}" pattern="yyyy년-MM월-dd일 (EE) HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${w.wi_mod_dt}" pattern="yyyy년-MM월-dd일 (EE) HH:mm:ss"/></td>
                            <td>
                                <button class="modify_btn" data-seq="${w.wi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                <button class="delete_btn" data-seq="${w.wi_seq}"><i class="fas fa-minus-circle"></i></button>
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
                    <a href="/writer?offset=${(i-1)*10}&type=${type}&keyword=${keyword}">${i}</a>
                </c:forEach>
            </div>
            <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="writer_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-book-open"></i>
                </div>
                <h2>작가 추가</h2>
                <p>작가 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="wi_name" placeholder="작가명">
                <input type="text" id="wi_birth" placeholder="생년월일(8자리)">
                <input type="text" id="wi_email" placeholder="이메일">
                <br>
                <input type="text" id="wi_company" placeholder="소속 출판사">
                <button id="search_company">출판사 검색</button>
                <br>
            </div>
            <div class="btn_area">
                <button id="add_cate">등록하기</button>
                <button id="modify_cate">수정하기</button>
                <button id="cancel_cate">취소하기</button>
            </div>
        </div>
        <div class="company_search">
            <div class="company_search_box">
                <input type="text" id="company_keyword" placeholder="출판사 이름">
                <button id="company_search_btn"><i class="fas fa-search"></i></button>
            </div>
            <div class="search_result">
                <ul>
                    
                </ul>
            </div>
            <div class="company_search_buttons">
                <button id="company_search_close">닫기</button>
            </div>
    </div>
</body>
</html>