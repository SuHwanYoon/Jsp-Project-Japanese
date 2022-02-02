<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
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
	if(f.MINFOPWD.value == ''){alert("新しいパスワードを入力してください。"); return false;}
	var c = confirm("入力した内容は本当に合っていますか？");
	if(c == false)return false;
}
</script>
<form action="myInfoModify.do" method="post" onSubmit="return check(this)">
	<table>
		<tr><th>ID:</th>
		<td><input type="text" name="MINFOID" value="${INFOID }" readonly="readonly"></td></tr>
		<tr><th>新しいパスワード:</th>
		<td><input type="password" name="MINFOPWD"></td></tr>
		<tr><td colspan="2" align="center">
		<input type="submit" value="DROP" name="BTN">
		<input type="submit" value="PWDCHANGE" name="BTN">
		</td></tr>
	</table>
</form>
</section>
</div>
<footer>

	<h3 align="center">CopyRight 2021 ユンスファン All right reserved. KOSEA</h3>
</footer>
</body>
</html>