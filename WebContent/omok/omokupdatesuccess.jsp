<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<c:if test="${sessionGrade=='관리자'}">
	<script>
		location.href="infodetail.omok&id=${id}"
	</script>
</c:if>
<c:if test="${sessionGrade != '관리자' }">
	<script>
		location.href="mypage.omok";
	</script>
</c:if>
</head>
<body>
</body>
</html>