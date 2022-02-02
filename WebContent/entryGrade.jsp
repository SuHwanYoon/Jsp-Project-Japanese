<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
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
	if(f.ID.value == ''){alert("学番を入力してください"); return false;}
	else{
		if(isNaN(f.ID.value)){alert("学番は数字で入力してください。"); return false;}
	}
	if(f.NAME.value == ''){alert("名前を入力してください。"); return false;}
	if(f.MAJOR.value == ''){alert("専攻を入力してください。"); return false;}
	if(! f.GENDER[0].checked && ! f.GENDER[1].checked)
		{alert("性別を選択してください。"); return false;}
	if(f.SUBJECT.value == ''){alert("科目を入力してください。"); return false;}
	var c = confirm("入力した内容で合っていますか？");
	if(c == false)return false;
}

</script>
	<form action="putScore.do" method="post" name="frm" onSubmit="return check(this)">
		<table>
			<tr><th>学番</th><td><input type="text" name="ID" maxlength="8"></td></tr>
			<tr><th>名前</th><td><input type="text" name="NAME" ></td></tr>
			<tr><th>専攻</th><td><input type="text" name="MAJOR" ></td></tr>
			<tr><th>性別</th><td>
			男性<input type="radio" name="GENDER" value="Male">
			女性<input type="radio" name="GENDER" value="Female">
			
			</td></tr>
			<tr><th>科目</th><td><input type="text" name="SUBJECT" ></td></tr>
			<tr><th>単位</th><td>
			<select name="GRADE">
			<option>A</option>
			<option>B</option>
			<option>C</option>
			<option>D</option>
			</select>
			</td></tr>
			<tr><td colspan="2" align="center">
				<input type="submit" value="登録">
				<input type="reset" value="キャンセル">
			</td></tr>
		</table>
	</form>
</section>
</div>
<footer>

	<h3 align="center">CopyRight 2021 ユン·スファン All right reserved. KOSEA</h3>
</footer>
</body>
</html>