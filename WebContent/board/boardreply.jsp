<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<h1>답글쓰기</h1>
<h2>
<form class="contact_form" action="BoardServlet">
<input type="hidden" name="menu" value="replysuccess"/>
<input type="hidden" name="replytype" value="${boardtype}"/>
<input type="hidden" name="replyseq" value="${boardseq}"/>
제목  :<input name="replytitle" type=text><br>
작성자:${sessionID}<br>
<textarea name="replycontents" rows=10 cols=50 placeholder="여기에 내용을 입력하세요"></textarea><br>
<button class="submit" type="submit">답글쓰기</button>
</form>
</h2>
</body>
</html>