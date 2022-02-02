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
	<c:when test="${param.R > 0 }">
		<script type="text/javascript">
			alert("登録が完了しました。");
			location.href="selectGrade.do";
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("登録が完了していません。");
			location.href="selectGrade.do";
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>