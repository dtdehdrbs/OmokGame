<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(document).ready(
	function(){
		$("#write").on("click",
			function(){
				location.href="write.board?boardtype=${boardtype2}";
			}		
		);
	}		
);
</script>
<title></title>
</head>
<body>
<form class="contact_form" action="OmokServlet" method="post">
<ul class="list-style2">
	<li><h2>${boardtype}</h2></li>
   	<table>
   	<tr><td><label><b>번호</b></label></td><td><label><b>제목</b></label></td><td><label><b>작성자</b></label></td><td><label><b>조회수</b></label></td><td><label><b>작성일</b></label></td></tr>
   	
	<c:forEach var="vo" items="${list}">
		<tr><td><label>${vo.seq}</label></td>
			<td><a href="boarddetail.board?seq=${vo.seq}&boardtype=${vo.boardtype}">${vo.boardtitle}</a></td>
			<td><label>${vo.boardwriter }</label></td><td><label>${vo.boardviewcount }</label></td><td><label>${vo.boardtime }</label></td></tr>
			<c:forEach var="reply" items="${replyList}">
				<c:if test="${reply.replyseq == vo.seq }">
					<tr><td><label>답변 : </label></td>
					<td><a href="BoardServlet?menu=detail&reply=1&seq=${reply.replyseq}&boardtype=${reply.replytype}">${reply.replytitle }</a></td>
					<td><label>${reply.replywriter }</label></td><td><label>${reply.replyviewcount }</label></td><td><label>${reply.replytime }</label></td></tr>
				</c:if>	
			</c:forEach>	
	</c:forEach>
   	</table>
   	<c:forEach var="i" begin="1" end="${totalpage}">
   		<a href="?menu=${boardtype2}&page=${i}" >${i}</a>
   	</c:forEach>
	<li>
      	<c:if test="${(grade=='관리자' and boardtype2=='notice') or boardtype2=='free' or boardtype2=='qna'}"><label></label><label></label><label></label><button class="btn" id="write" type="button">글쓰기</button></c:if>
    </li>
</ul>
</form>
</body>
</html>