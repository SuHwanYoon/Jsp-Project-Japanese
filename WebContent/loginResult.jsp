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
	<c:choose>
		<c:when test="${param.R == 'NOID' }">
			<script type="text/javascript">
				alert("IDが存在しません。");
				location.href="home.jsp";
			</script>
		</c:when>
		<c:when test="${param.R == 'NOTPWD' }">
				<script type="text/javascript">
				alert("パスワードが違います。");
				location.href="home.jsp";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("ログインされました。");
				location.href="home.jsp";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>