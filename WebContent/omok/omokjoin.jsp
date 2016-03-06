<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<form class="contact_form" action="OmokServlet" method="post">
	<ul>
        <li>
             <h2>회원가입</h2>
             <span class="required_notification">* 필수 입력 항목</span>
        </li>
        <li>
            <label for="name" class="required_notification">아이디</label>
            <input type="text" name="id" placeholder="아이디" required />
            <span class="form_hint">아이디를 입력하세요</span>
        </li>
        <li>
            <label for="pw" class="required_notification">비밀번호</label>
            <input type="password" name="pw" placeholder="비밀번호" required />
            <span class="form_hint">비밀번호를 입력하세요</span>
        </li>
        <li>
            <label for="name" class="required_notification">이름</label>
            <input type="text" name="name" placeholder="이름" required />
            <span class="form_hint">이름을 입력하세요</span>
        </li>
        <li>
            <label for="email">이메일</label>
            <input type="text" name="email" placeholder="example@example.com"  />
            <span class="form_hint">이메일을 입력하세요</span>
        </li>
        <li>
            <label for="phone">전화번호</label>
            <input type="text" name="phone" placeholder="010-1234-5678"  />
            <span class="form_hint">전화번호를 입력하세요</span>
        </li>
        <li>
            <label for="birth">생년월일</label>
            <input type="date" name="birth" min="1900-01-01" max="2100-12-31" value="1991-01-01"/>
            <span class="form_hint">생년월일을 입력하세요</span>
        </li>
        <li>
        	<button class="submit" type="submit">회원가입</button>
        </li>
    </ul>
	<input type="hidden" name="menu" value="joinsuccess" />
</form>
</body>
</html>