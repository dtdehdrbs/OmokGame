<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<form class="contact_form" action="updatesuccess.omok" method="post">
	<ul>
        <li>
             <h2>회원정보수정</h2>
        </li>
        <li>
            <label for="id">아이디</label>
            ${vo.id}
            <input type="hidden" name="id" value="${vo.id}">
        </li>
        <li>
            <label for="pw">비밀번호</label>
            <input type="password" name="pw" />
        </li>
        <li>
            <label for="name">이름</label>
            <input type="text" name="name" value="${vo.name}" />
        </li>
        <li>
            <label for="email">이메일</label>
            <input type="text" name="email"  value="${vo.email}" />
        </li>
        <li>
            <label for="phone">전화번호</label>
            <input type="text" name="phone"  value="${vo.phone}" />
        </li>
        <li>
            <label for="birth">생년월일</label>
            <input type="text" name="birth" value="${vo.birth}" />
        </li>
        <li>
        	<button class="submit" type="submit">정보 수정</button>
        </li>
    </ul>
</form>

</body>
</html>