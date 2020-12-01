<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<th width=100 class="text-center danger" style="font-size:20px">제목</th>
					<td width=100 class="text-left" colspan=3 style="color:black;">${vo.title }</td>
				</tr>
				<tr>
					
					
					<th width=20% class="text-center danger">작성일</th>
					<td width=30% class="text-left"><fmt:formatDate
							value="${vo.regdate}" pattern="yyyy-MM-dd" /></td>
				</tr>

				<tr>
					<th width=20% class="text-center danger">작성자</th>
					<td width=30% class="text-left">${vo.id }</td>
					
					<th width=20% class="text-center danger">연락처</th>
					<td width=30% class="text-left">${vo.tel }</td>
				</tr>
				<tr>
					<th width=20% class="text-center danger">발견날짜</th>
					<td width=30% class="text-left"><fmt:formatDate
							value="${vo.pdate}" pattern="yyyy-MM-dd" /></td>

					<th width=20% class="text-center danger">발견지역</th>
					<td width=30% class="text-left">${vo.loc }</td>
					
				</tr>
				
				
				<tr>
					<td colspan=4 class="text-center">
						<img src="${vo.poster }" width=400px height=400px> <p><p>${vo.content }</p></p>
					</td>
					
					
				</tr>
				
				<tr>
				<td></td>
				</tr>
			</table>
			
				</div>
				<div class="text-right" style="padding-bottom:50px;">
				<a href="list.do" class="btn btn-sm btn-primary py-2 px-4">&nbsp;목록&nbsp;</a>
				</div>
	</div>

</body>
</html>