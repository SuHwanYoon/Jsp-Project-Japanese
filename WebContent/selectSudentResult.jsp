<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body {background-color:yellow;}
header {background-color:#F781F3;}
section {background-color:#40FF00;}
footer {background-color:#F781F3;}
menu {border:2px solid blue;}
menu {background-color:#2EFEF7}
</style>
</head>
<body>
<header>
	<h1 align="center">成績管理アプリ ver 1.0</h1>
</header>
<div align=center>
<menu>
	<%@ include file="menu_header.jsp" %>
</menu>
<section>
	<table border="1">
		<tr><th>学番</th><th>名前</th><th>専攻</th>
		<th>科目</th><th>単位</th><th>作成者</th><th>作成日</th></tr>
		<c:forEach items="${SCORE }" var="score">
		<tr>
		<td>${score.id }</td>
		<td><a href="detailSelect.do?ID=${score.id }">${score.name }</a></td>
		<td>${score.major }</td>
		<td>${score.subject }</td>
		<td>${score.grade }</td>
		<td>${score.userid }</td>
		<td>${score.std_date }</td>
		</tr>
		</c:forEach>
	</table>
	<c:forEach begin="1" end="${PAGE }" var="page">
		<a href="selectGrade.do?PAGE_NUM=${page }">${page }</a>
	</c:forEach>
</section>
</div>
<footer>

	<h3 align="center">CopyRight 2021 ユンスファン All right reserved. KOSEA</h3>
</footer>
</body>
</html>