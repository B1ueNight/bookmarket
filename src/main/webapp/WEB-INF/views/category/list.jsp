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
    <link rel="stylesheet" href="/assets/css/category_list.css">
    <script src="/assets/js/category.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-bookmark"></i> 카테고리 관리</h1>
        <button id="add_category"><i class="fas fa-plus-circle"></i>카테고리 추가</button>
        <div class="content_area">
                <div class="menu_area">
                    <div class="search_box">
                        <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                        <button id="search_btn"><i class="fas fa-search"></i></button>
                    </div>
                    <button id="reset_btn">초기화</button>
                </div>
        <div class="table_area">
            <table>
                <thead>
                    <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>코드</th>
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
                    <c:forEach items="${data.list}" var="c">
                        <tr>
                            <td>${c.cate_seq}</td>
                            <td>${c.cate_name}</td>
                            <td>${c.cate_code}</td>
                            <td>${c.cate_reg_dt}</td>
                            <td>${c.cate_mod_dt}</td>
                            <td>
                                <button class="modify_btn" data-seq="${c.cate_seq}"><i class="fas fa-pencil-alt"></i></button>
                                <button class="delete_btn" data-seq="${c.cate_seq}"><i class="fas fa-minus-circle"></i></button>
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
                    <a href="/category?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
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
                <h2>카테고리 추가</h2>
                <p>카테고리 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="cate_name" placeholder="카테고리 이름">
                <input type="text" id="cate_code" placeholder="코드번호">
                </select>
            </div>
            <div class="btn_area">
                <button id="add_cate">등록하기</button>
                <button id="modify_cate">수정하기</button>
                <button id="cancel_cate">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>