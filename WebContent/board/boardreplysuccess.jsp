<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<c:if test="${result==1 }">
<h1>글 작성을 완료하였습니다.</h1>
<h2>제목 : ${vo.title }</h2>
<h2>작성자 : ${sessionID }</h2>
<h2>내용 : ${vo.contents }</h2>
<a href="${vo.type}.board">메인메뉴로 이동</a>하시겠습니까?
</c:if>
<c:if test="${result==0 }">
<h1>글 작성을 실패하였습니다.</h1>
<a href="${vo.type}.board">메인메뉴로 이동</a>하시겠습니까?
</c:if>
</body>
</html>