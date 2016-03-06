<%@ page  contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
$(document).ready(
	function(){
		$("#create").on("click",
			function(){
				location.href="PlayServlet?menu=create";
			}		
		);		
	}
);
</script>
</head>
<body>


<form class="contact_form" >
<ul>
	<li>
		<h2>방 목록</h2>
	</li>
		<tr><td><label>방번호</label></td><td><label>방제목</label></td><td><label>방장</label></td><td><label>인원수</label></td><td><label id="time">만든시간</label></td></tr>
	<c:forEach var="data" items="${list}">
		<tr>
		<td><label>${data.prseq}</label></td>
		<td><label><a href="PlayServlet?menu=play&userid=${data.id}">${data.prtitle}</a></label></td>
		<td><label>${data.id}</label></td>
		<td><label>${data.prpopular}</label></td>
		<td><label>${data.indate}</label></td></tr>
	</c:forEach>
	<label></label><label></label><label></label><button id="create" class="btn" type="button">방 만들기</button>
</ul>
</form>
</body>
</html>