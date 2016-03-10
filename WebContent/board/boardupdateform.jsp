<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>수정창</title>
</head>
<body>
<form class="contact_form" action="update.board" method="post">
<input type=hidden name="seq"  value="${vo.seq}"  />
<input type=hidden name="boardtype" value="${vo.boardtype }"/>
제목  :<input name="boardtitle" type=text value="${vo.boardtitle }"><br>
작성자:${sessionID }<br>
<textarea name="boardcontents" rows=10 cols=50>${vo.boardcontents }</textarea><br>
<button class="submit" type="submit" >작성완료</button>
</form>
</body>
</html>