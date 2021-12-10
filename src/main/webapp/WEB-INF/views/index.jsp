<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <h1>북마켓 대시보드(Book Market DashBoard)</h1>
        <div class="content_area">
            <div class="book_area">
                <h2>도서 정보</h2>
                <p>총 등록 도서 수</p>
                <p>오늘 판매된 도서 수</p>
                <p>신간 도서 수</p>
                <p>절판 도서 수</p>
            </div>
            <div class="user_area">
                <h2>유저 정보</h2>
                <p>총 유저 수</p>
                <p>신규 유저 수</p>
                <p>탈퇴 유저 수</p>
            </div>
            <div class="company_area">
                <h2>출판사 정보</h2>
                <p>등록된 출판사</p>
            </div>
            <div class="writer_area">
                <h2>작가 정보</h2>
                <p>등록된 작가</p>
            </div>
        </div>
</body>
</html>