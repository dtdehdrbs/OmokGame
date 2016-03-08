<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form class="contact_form">
<table>
	<ul>
        <li>
             <h2>랭킹</h2>
        </li>
        
		<tr><td><label>순위</label></td><td><label>아이디</label></td><td><label>점수</label></td><td><label>승</label></td><td><label>패</label></td></tr>
		<c:forEach var="data" items="${list}">
			<tr><td><label>${data.r}</label></td><td><label>${data.userid}</label></td><td><label>${data.score}</label></td><td><label>${data.win}</label></td><td><label>${data.los}</label></td></tr>
		</c:forEach>
    </ul>
</table>
</form>
</body>
</html>