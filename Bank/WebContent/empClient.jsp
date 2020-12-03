<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empClient</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" 
integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
$(function(){
	var url = "EmpServ"; //내 서블릿을 통해서 값 받기(우회)
	$.ajax(url)
		.done(function(response){ //succes보다 많이 씀. 구동되면 실행해라
			console.log(response);  //CORS에러 조치(제공자 서버단 조치, 브라우저 설정 바꾸기, 우회:내가 서블릿호출해서 값 주고받아서 넘겨주기)
			$("#result").append(response.name + ":" + response.addr);
	})
});
</script>
</head>
<body>
<div id="result"></div>
</body>
</html>