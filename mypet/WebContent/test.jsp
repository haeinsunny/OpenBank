<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<title>그룹이벤트함수 연습</title>
</head>
<body>
	<div>
		grand
		<div>
			parent
			<div>child</div>
		</div>
	</div>
<a href="http://www.naver.com">naver</a>
	<ul>
		<li>test1</li>
		<li>test2</li>
	</ul>
	<script>
		$("ul").on("click", "li", function() {
			$(this).css("backgroundColor", "gray");
		});
		$("#grand").on("click", function() {
			alert("grand");
		});
		$("#parent").on("click", function() {
			alert("parent");
		});
		$("#child").on("click", function(e) {
			e = e || window.event;
			e.stopPropagation(); //전파중지
			alert("child");
		});
		
		$("a").on("click", function(e){
			if(!confirm("이동할까요?")){ //전파중지 함수 걸지않으면 아니오(취소)를 눌러도 submit된다
				e.preventDefault(); //a, submit 기본기능 중지 (함수에 매개변수 e받아와야 사용가능하다)
			}
		})
	</script>
</body>
</html>