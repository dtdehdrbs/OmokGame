<%@ page  contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(document).ready(
	function(){
		$("#update").on("click",
			function(){
				location.href="OmokServlet?menu=update&id=${vo.id}";
			}		
		);
		$("#delete").on("click",
			function(){
				var chk = confirm("정말로 탈퇴하시겠습니까?");
				if(chk == 1)
					location.href="OmokServlet?menu=delete&id=${vo.id}";
			}		
		);	
		
	}		
);
</script>
<title></title>
</head>
<body>

<form class="contact_form" action="OmokServlet" method="post">
	<ul>
        <li>
             <h2>회원정보</h2>
        </li>
        <li>
            <label for="id">아이디</label>
            ${vo.id}
        </li>
        <li>
            <label for="name">이름</label>
           ${vo.name}
        </li>
        <li>
            <label for="grade">회원등급</label>
            ${vo.grade}
        </li>
        <li>
            <label for="email">이메일</label>
            ${vo.email}
        </li>
        <li>
            <label for="phone">전화번호</label>
           ${vo.phone}
        </li>
        <li>
            <label for="birth">생년월일</label>
            ${vo.birth}
        </li>
        <li>
            <label for="indate">가입일</label>
            ${vo.indate}
        </li>
        <li>
        	<button type="button" id="update" class="btn" > 정보수정 </button>
        	<button type="button" id="delete" class="btn" > 탈퇴 </button>
        </li>
    </ul>
</form>
</body>
</html>