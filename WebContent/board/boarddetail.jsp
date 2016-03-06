<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시물</title>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("list").onclick =
		function(){
		location.href="BoardServlet?menu=${vo.boardtype}";
	}
	document.getElementById("reply").onclick =
		function(){
		location.href = "BoardServlet?menu=reply&seq=${vo.boardseq}&boardtype=${vo.boardtype}";
	}
	document.getElementById("update").onclick =
		function(){
		location.href = "BoardServlet?menu=update&seq=${vo.boardseq}&boardtype=${vo.boardtype}";
	}
	document.getElementById("delete").onclick =
		function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href = "BoardServlet?menu=delete&seq=${vo.boardseq}&boardtype=${vo.boardtype}";
		}
	}
}
</script>
</head>
<body>

<form class="contact_form">
	<ul>
		<li><h2></h2></li>
		
		<c:if test="${reply == 1 }">
        <li>
            <label for="id">제목</label>
            ${repvo.title}
        </li>
         <li>
            <label for="id">작성일</label>
            ${repvo.time}
        </li>
        <li>
            <label for="id">조회수</label>
            ${repvo.viewcount}
        </li>
        <li>
            <label for="name">작성자</label>
            ${repvo.writer}
        </li>
        <li>
            <label for="email">내용</label>
            ${repvo.contents}
        </li>
		</c:if>
		<c:if test="${reply != 1 }">
        <li>
            <label for="id">제목</label>
            ${vo.boardtitle}
        </li>
         <li>
            <label for="id">작성일</label>
            ${vo.boardtime}
        </li>
        <li>
            <label for="id">조회수</label>
            ${vo.boardviewcount}
        </li>
        <li>
            <label for="name">작성자</label>
            ${vo.boardwriter}
        </li>
        <li>
            <label for="email">내용</label>
            ${vo.boardcontents}
        </li>
		</c:if>
		<c:if test="${reply == 1 }">
	        <li>
				<button type="button" id="list" class="btn">목록</button>
	        </li>
        </c:if>
        <c:if test="${reply != 1 }">
	        <li>
				<button type="button" id="list" class="btn">목록</button>
	        	<c:if test="${vo.boardwriter == sessionID }">
				<button type="button" id="update" class="btn">수정</button>
				<button type="button" id="delete" class="btn">삭제</button>
	        	</c:if>
	        	<c:if test="${grade=='관리자'}">
	        	<button type="button" id="reply" class="btn">답글</button>
	        	</c:if>
	        </li>
        </c:if>
    </ul>    
	</form>
</body>
</html>



