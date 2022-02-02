<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>ID重複チェック</h2>
	<form action="idCheck.do" name="frm">
	아이디:<input type="text" name="USER_ID" value="${ENTRYID }">
	<input type="submit" value="重複チェック">
	</form>
<c:if test="${ ! empty ACU }">
${ACU }は既に使用中です。
</c:if>
<c:if test="${ empty ACU }">
${ENTRYID }は使用できます。
<input type="button" value="使用" onClick="idok()">
</c:if>
</div>
<script type="text/javascript">
function idok(){
	opener.document.frm.IDCHECKED.value = "OK";
	opener.document.getElementById("userid").readOnly = true;
	opener.document.frm.ENTRYID.value = document.frm.USER_ID.value;
	self.close();
	
}
</script>
</body>
</html>