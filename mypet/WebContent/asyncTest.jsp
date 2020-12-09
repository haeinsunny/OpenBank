<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기방식 연습</title>

</head>
<body>
<h1 id="demo"></h1>
<script type="text/javascript">
	async function myDisplay() {
		let myPromise = new Promise(function(myResolve, myReject) {  //(성공할때의 함수, 에러날때의 함수)
			setTimeout(function() {
				myResolve("I love You !!");
			}, 3000);
		});
		document.getElementById("demo").innerHTML = await myPromise;  //await: 비동기식이지만 myPromise가 실행되고나면 inner.HTML이 실행해라.
	}
	
	myDisplay().then(function(){console.log("===display");});  //then: myDisplay()함수가 실행되고나면 then의함수 실행해라
	console.log("===end");  //비동기식이라서 순서지키지않고 바로 뜸
</script>
</body>
</html>