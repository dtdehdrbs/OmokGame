<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>	
	<div id="logo" class="container">
		<h1><a href="#">오목</a></h1>
		<p>made by 이훈경 제동균</p>
	</div>
	<div id="menu-wrapper">
		<div id="menu" class="container">
			<ul>
				<c:if test="${sessionGrade=='관리자'}">
					<li><a href="infosearch.omok">회원관리</a></li>
				</c:if>
				<c:if test="${empty sessionID }">				
					<li class="current_page_item1"><a href="begin2.omok">홈페이지</a></li>
					<li><a href="login.omok">로그인</a></li>
					<li><a href="join.omok">회원가입</a></li>
				</c:if>
				<c:if test="${!empty sessionID }">
					<li class="current_page_item1"><a href="begin.omok">홈페이지</a></li>
					<li><a href="mypage.omok">마이페이지</a></li>				
					<li><a href="notice.board">공지사항</a></li>
					<li><a href="free.board">자유게시판</a></li>
					<li><a href="qna.board">Q&A</a></li>
					<li><a href="PlayServlet?menu=list">게임시작</a></li>
					<li><a href="rank.omok">랭킹</a></li>
					<li><a href="logout.omok">로그아웃</a></li>				
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>