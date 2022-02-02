<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="entryGrade.jsp">★ 成績登録</a>&nbsp;
<a href="selectGrade.do">★ 成績照会</a>&nbsp;
<a href="home.jsp">★ ホームへ</a>&nbsp;
<c:choose>
	<c:when test="${sessionScope.LOGIN != null }">
		いらっしゃいませ ${sessionScope.LOGIN }様
		<a href="logout.do">★ログアウト</a>
	</c:when>
	<c:otherwise>
		<a href="login.jsp">★ログイン</a>
	</c:otherwise>
</c:choose>
<a href="myInfo.do">★自分の情報</a>