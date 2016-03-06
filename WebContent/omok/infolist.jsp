<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form class="contact_form">
<ul class="list-style2">
	<li><h2>회원정보</h2></li>
   	<table>
   	<tr><td><label><b>아이디</b></label></td><td><label><b>회원등급</b></label></td><td><label><b>이름</b></label></td><td><label><b>e-mail</b></label></td><td><label><b>가입일자</b></label></td></tr>
   	
	<c:forEach var="info" items="${oinfolist}">
		<tr><td><label>${info.id}</label></td>
			<td>${info.grade}</td>
			<td><label><a href="OmokServlet?menu=infodetail&id=${info.id}">${info.name}</a></label></td><td><label>${info.email}</label></td><td><label>${info.indate}</label></td></tr>	
	</c:forEach>
   	</table>
</ul>
</form>
</body>
</html>