<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form class="contact_form" action="OmokServlet">
<input type="hidden" name="menu" value="infolist"/>
<ul class="list-style2">
	<li><h2>회원정보</h2></li>
	<li>
	<select name="searchtype">
		<option value="id">아이디</option>
		<option value="name">이름</option>
		<option value="grade">등급</option>
		<option value="email">e-mail</option>
		<option value="indate">가입날짜</option>
	</select>
   	<input type="text" name="searchcontent" placeholder="검색어를 입력하세요" required />
   	<button type="submit" class="submit">검색</button></li>
</ul>
</form>
</body>
</html>