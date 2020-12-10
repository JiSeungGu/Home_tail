<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${count==0 }">
		<h3 class="text-center">검색결과가 없습니다</h3>
	</c:if>
	
	<c:if test="${count>0 }">
		<c:forEach var="vo" items="${list }">
			<table class="table table-striped">
			<tr >
			  <td width=30%>병원이름</td>
			  <td width=70%>${vo.title }</td>
			</tr>
			<tr>
			  <td width=5%>주소</td>
			  <td width=95%>${vo.addr }</td>
			</tr>
			</table>
		</c:forEach>
	</c:if>
</body>
</html>
