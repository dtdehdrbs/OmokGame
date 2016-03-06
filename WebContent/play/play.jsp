<%@ page  contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<style>
div.omok{
	margin: -3px -2px;
	display: inline-block;
	width: 24px;
	height: 24px;
	overflow:hidden;
    position:relative;
}
div:hover.omok img {

    position:absolute;
    left: 0px;
    top: -24px;
}
.putblack img {
    position:absolute;
    left: 0px;
    top: -24px;
}
.putwhite img {
    position:absolute;
    left: 0px;
    top: -48px;
}
</style>
<title>오목판</title>
<script type="text/javascript">
$(document).ready(
	function(){
		setInterval("update()",1000);
		$(".omok").one("click",
			function(){
				$(this).addClass("putblack");
					var url = "PlayServlet";
					var pos = $(this).attr("id");
					posX = pos.split(",")[0];
					posY = pos.split(",")[1];
					var sendData = { menu : "save", order:"checkBlack","posX" : posX, "posY":posY, "status" : "1" , userid :"${userid}"};
					$.post(
						url,
						sendData,
						function(data){
							$("#alarm").text(data);
						}
				);
				}
		);
			$("#btn").on("click",
				function(){
					location.href="PlayServlet?menu=delete&userid=${userid}";
				}		
			);
	}
);
function update(){
	var url = "PlayServlet";
	var pos=$(this).attr("id");
	var sendData = { menu : "sync", userid : "${userid}"};
	$.post(
		url,
		sendData,
		function(data){
			var list = data.split(":");
			for(var i=0;i<list.length-1;i++){
				$("div").each(
					function(){						
						if($(this).attr("id") == list[i]){
							$(this).addClass("putwhite");
						}
					}
				);
			}
		}
	);
};

</script>
</head>
<body>
<h2>${sessionID} vs ${userid}</h2>

<c:forEach var="i" begin="1" end="20" step="1">
	<c:forEach var="j" begin="1" end="20" step="1">
		<c:if test="${i==1 and j==1}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaltopleft.gif"  alt="" /></div>
		</c:if>
		<c:if test="${i==1and j>1 and j<20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaltop.gif" alt="" /></div>
		</c:if>
		<c:if test="${i==1and j==20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaltopright.gif" alt="" /></div><br>
		</c:if>
		<c:if test="${i>1 and i<20 and j==1}">
		<div class="omok" id="${i},${j}"><img src="play/images/totalleft.gif" alt="" /></div>
		</c:if>
		<c:if test="${i>1and i<20 and j>1 and j<20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totalcross.gif" alt="" /></div>
		</c:if>
		<c:if test="${i>1 and i<20 and j==20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totalright.gif" alt="" /></div><br>
		</c:if>
		<c:if test="${i==20and j==1}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaldownleft.gif" alt="" /></div>
		</c:if>
		<c:if test="${i==20and j>1 and j<20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaldown.gif" alt="" /></div>
		</c:if>
		<c:if test="${i==20and j==20}">
		<div class="omok" id="${i},${j}"><img src="play/images/totaldownright.gif" alt="" /></div><br>
		</c:if>
	</c:forEach>
</c:forEach>
<br>
<form class="contact_form">
	<button id="btn" class="btn" type="button">방 나가기</button>
</form>
<div id="alarm"></div>
</body>
</html>