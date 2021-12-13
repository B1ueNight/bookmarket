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
    <link rel="stylesheet" href="/assets/css/company_list.css">
    <script src="/assets/js/company.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-building"></i> 출판사 관리</h1>
        <button id="add_category"><i class="fas fa-plus-circle"></i>출판사 추가</button>
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
                    <th>출판사명</th>
                    <th>연락처</th>
                    <th>이메일</th>
                    <th>주소</th>
                    <th>조작</th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${data.total == 0}">
                        <tr>
                            <td id="nodata" colspan="6">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${data.list}" var="co">
                        <tr>
                            <td>${co.ci_seq}</td>
                            <td>${co.ci_name}</td>
                            <td>${co.ci_phone}</td>
                            <td>${co.ci_email}</td>
                            <td>${co.ci_address}</td>
                            <td>${co.ci_reg_dt}</td>
                            <td>
                                <button class="modify_btn" data-seq="${co.ci_seq}"><i class="fas fa-pencil-alt"></i></button>
                                <button class="delete_btn" data-seq="${co.ci_seq}"><i class="fas fa-minus-circle"></i></button>
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
                    <a href="/company?offset=${(i-1)*10}"${i}></a>
                </c:forEach>
            </div>
            <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="company_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-book-open"></i>
                </div>
                <h2>출판사 추가</h2>
                <p>출판사 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="ci_name" placeholder="출판사명">
                <input type="text" id="ci_phone" placeholder="연락처">
                <input type="text" id="ci_email" placeholder="이메일">
                <input type="text" id="ci_address" placeholder="주소">
            </div>
            <div class="btn_area">
                <button id="add_cate">등록하기</button>
                <button id="cancel_cate">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>