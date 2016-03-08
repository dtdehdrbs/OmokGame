<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Team Project 오목</title>
<link href="http://fonts.googleapis.com/css?family=Oxygen:400,700,300" rel="stylesheet" type="text/css" />
<link href="omok/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" media="screen" href="omok/css/mypage.css" >
<link rel="stylesheet" type="text/css" href="omok/css/login.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://www.modernizr.com/downloads/modernizr-latest.js"></script>
<script type="text/javascript" src="omok/js/placeholder.js"></script>
<script type="text/javascript" src="omok/js/jquery-1.11.1.js"></script>
<script src="omok/js/jquery-1.11.1.js"></script>
</head>
<body> 
<jsp:include page="header.jsp"></jsp:include>
<div id="wrapper">
	<div align="center">
	<jsp:include page="${page}"></jsp:include>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>