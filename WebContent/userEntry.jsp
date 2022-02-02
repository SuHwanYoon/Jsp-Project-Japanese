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
	<h1 align="center">成績管理アプリver 1.0</h1>
</header>
<div align=center>
<menu>
	<%@ include file="menu_header.jsp" %>
</menu>

<section>
<script type="text/javascript">
function check(f){
	if(f.IDCHECKED.value == ''){alert("重複検査を行ってください。"); return false;}
	if(f.ENTRYID.value == ''){alert("IDを入力してください。"); return false;}
	if(f.ENTRYPWD.value == ''){alert("パスワードを入力してください。"); return false;}
	if(f.ENTRYPWD.value != f.PWDD.value){
		alert("暗号が一致しません"); return false;
	}
	var c = confirm("加入しますか?。");
	if(c == false)return false;
}
function idCheck(){
	if(document.frm.ENTRYID.value == ''){alert("IDを入力してください。"); return;}
	var url = "idCheck.do?USER_ID="+document.frm.ENTRYID.value;
	window.open(url,"check","width=450,height=200");
}
</script>
	<form action="entry.do" method="post" name="frm" onSubmit="return check(this)">
	<input type="hidden" name="IDCHECKED">
		<table>
			<tr><th>ID</th><td><input type="text" name="ENTRYID"  id="userid" maxlength="8">
			<input type="button" value="重複チェック" onClick="idCheck()">
			</td></tr>
			<tr><th>PASSWORD</th><td><input type="password" name="ENTRYPWD">
			
			</td></tr>
			<tr><th>PASSWORD確認</th><td>
			<input type="password" name="PWDD">
			</td></tr>
			<tr><td colspan="2" align="center">
				<input type="submit" value="加入">
				<input type="reset" value="キャンセル">
			</td></tr>
		</table>
	</form>
</section>
</div>
<footer>

	<h3 align="center">CopyRight 2021  ユンスファン  All right reserved. KOSEA</h3>
</footer>
</body>
</html>