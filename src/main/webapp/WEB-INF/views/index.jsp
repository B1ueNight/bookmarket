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
    <link rel="stylesheet" href="/assets/css/index.css">
    <script>
        $(function() {
            $(".main_menu a:nth-child(1)").addClass("active")
        })
    </script>
</head>
<body>
    <main>
        <h1>도서 정보 관리 대시보드(Book Market DashBoard)</h1>
        <div class="content_area">
            <div class="book_area">
                <h2>도서 정보</h2>
                <p>총 등록 도서 수<span> ${cnt.book[0]}</span></p>
                <p>오늘 판매된 도서 수<span> ${cnt.book[1]}</span></p>
                <p>신간 도서 수<span> ${cnt.book[2]}</span></p>
                <p>카테고리 수<span> ${cnt.book[3]}</span></p>
                <p><i class="far fa-clock"></i> 업데이트 : <span><fmt:formatDate value="${update.book}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
            <div class="company_area">
                <h2>출판사 정보</span></h2>
                <p>등록된 출판사<span> ${cnt.company[0]}</span></p>
                <p><i class="far fa-clock"></i> 업데이트 : <span><fmt:formatDate value="${update.company}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
            <div class="writer_area">
                <h2>작가 정보</h2>
                <p>등록된 작가<span> ${cnt.writer[0]}</span></p>
                <p><i class="far fa-clock"></i> 업데이트 : <span><fmt:formatDate value="${update.writer}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
            </div>
        </div>
    </main>
</body>
</html>