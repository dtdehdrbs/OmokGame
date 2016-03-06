<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<head>
</head>
<body>
<form action="OmokServlet" method="post" id="slick-login">
<h2>로그인</h2>
<input type="text" name="id" class="placeholder" placeholder="아이디">
<input type="password" name="pw" class="placeholder" placeholder="비밀번호">
<input type="submit" value="로그인">
<input type="hidden" name="menu" value="loginsuccess" />
</form>
</body>
</html>