<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<form class="contact_form" action="PlayServlet">
<ul>
        <li>
             <h2>방 만들기</h2>
        </li>
        <li>
            <label for="title">방제목</label>
            <input type="text" name="prtitle" placeholder="방제목" required />
            <span class="form_hint">방 제목을 입력하세요</span>
        </li>        
        <li>
        	<button class="submit" type="submit">방 만들기</button>
        </li>
    </ul>
	<input type="hidden" name="menu" value="createsuccess" />
	<input type="hidden" name="userid" value="${sessionID}" />
</form>
</body>
</html>