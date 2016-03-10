<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div id="three-column" class="container">
		<div id="tbox1">
			<div class="box-style box-style01">
				<div class="content">
					<div class="image"><img src="omok/images/img01.jpg" width="324" height="200" alt="" /></div>
					<h2>1등</h2>
						<c:forEach var="data" items="${rank}">
							<c:if test="${data.r == 1}">
							<p><h2>${data.userid }</h2></p>
							<p><h2>${data.score } 점</h2></p>
							<p><h2>${data.win }승 ${data.los }패 </h2></p>
							</c:if>
						</c:forEach>
				</div>
			</div>
		</div>
		<div id="tbox2">
			<div class="box-style box-style02">
				<div class="content">
					<div class="image"><img src="omok/images/img02.jpg" width="324" height="200" alt="" /></div>
					<h2>2등</h2>
					<c:forEach var="data" items="${rank}">
						<c:if test="${data.r == 2}">
						<p><h2>${data.userid }</h2></p>
						<p><h2>${data.score } 점</h2></p>
						<p><h2>${data.win }승 ${data.los }패 </h2></p>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="tbox3">
			<div class="box-style box-style03">
				<div class="content">
					<div class="image"><img src="omok/images/img03.jpg" width="324" height="200" alt="" /></div>
					<h2>3등</h2>
					<c:forEach var="data" items="${rank}">
						<c:if test="${data.r == 3}">
						<p><h2>${data.userid }</h2></p>
						<p><h2>${data.score } 점</h2></p>
						<p><h2>${data.win }승 ${data.los }패 </h2></p>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div id="page" class="container">
		<div id="content">
			<div class="post">
				<h2 class="title"><a href="BoardServlet?menu=free">자유게시판</a></h2>
				<div class="list-style2">
						<c:forEach var="vo" items="${free}" begin="0" end="4" step="1" >
						<p>제목 : <a href="BoardServlet?menu=detail&seq=${vo.seq}&boardtype=${vo.boardtype}">${vo.boardtitle}</a>
						작성자 : ${vo.boardwriter}<br></p>
						</c:forEach>
				</div>
			</div>
			<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end #content -->
		<div id="sidebar">
			<div>
				<h2><a href="BoardServlet?menu=notice">공지사항</a></h2>
				<ul class="list-style2">
						<c:forEach var="vo" items="${notice}" begin="0" end="4" step="1" >
						<li>제목 : <a href="BoardServlet?menu=detail&seq=${vo.seq}&boardtype=${vo.boardtype}">${vo.boardtitle}</a>
						작성자 : ${vo.boardwriter}<br></li>
						</c:forEach>
				</ul>
			</div>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #page --> 
</body>
</html>