<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<ul>
		<!-- 최상위 페이지 -> request.getContextPath()  -->
		<li><a href="<%= request.getContextPath()%>/member/index.jsp">멤버 관리 페이지</a> 
	</ul>
</body>
</html>