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
<script type="text/javascript">
function check(f){
	var c = confirm("入力した内容で合っていますか？");
	if(c == false) return false;
}

</script>
	<form action="detailModify.do" method="post" name="frm" onSubmit="return check(this)">
		
	<table border="1">
		<tr><th>学番</th>
		<td><input type="text" name="ID" value="${DETAIL.id }" readonly="readonly"></td></tr>
		<tr><th>名前</th>
		<td><input type="text" name="NAME" value="${DETAIL.name }"></td></tr>
		<tr><th>専攻</th>
		<td><input type="text" name="MAJOR" value="${DETAIL.major }"></td></tr>
		<tr><th>性別</th>
		<td>
		<c:if test="${DETAIL.gender == 'Male' }">
			男性<input type="radio" value="Male" name="GENDER" checked="checked">
			女性<input type="radio" value="Female" name="GENDER">
		</c:if>
		<c:if test="${DETAIL.gender == 'Female' }">
			男性<input type="radio" value="Male" name="GENDER" >
			女性<input type="radio" value="Female" name="GENDER" checked="checked">
		</c:if>
		</td></tr>
		<tr><th>科目</th>
		<td><input type="text" name="SUBJECT" value="${DETAIL.subject }"></td></tr>
		<tr><th>単位</th>
		<td>
		<select name="GRADE">
			<c:if test="${DETAIL.grade == 'A' }">
				<option selected="selected">A</option>
				<option>B</option>
				<option>C</option>
				<option>D</option>
			</c:if>
			<c:if test="${DETAIL.grade == 'B' }">
				<option>A</option>
				<option  selected="selected">B</option>
				<option>C</option>
				<option>D</option>
			</c:if>
			<c:if test="${DETAIL.grade == 'C' }">
				<option>A</option>
				<option>B</option>
				<option  selected="selected">C</option>
				<option>D</option>
			</c:if>
			<c:if test="${DETAIL.grade == 'D' }">
				<option>A</option>
				<option>B</option>
				<option>C</option>
				<option  selected="selected">D</option>
			</c:if>
		</select>
		</td></tr>
		<tr><th>作成者</th><td><input type="text" name="USERID" value="${DETAIL.userid }" readonly="readonly"></td></tr>
		<c:choose>
			<c:when test="${DATA == DETAIL.userid}">
				<tr><td colspan="2" align="center">
			<input type="submit" value="DELETE" name="BTN">
			<input type="submit" value="CHANGE" name="BTN">
		</td></tr>
			</c:when>
			
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
		
	</table>
	</form>
</section>
</div>
<footer>

	<h3 align="center">CopyRight 2021 ユンスファン All right reserved. KOSEA</h3>
</footer>
</body>
</html>