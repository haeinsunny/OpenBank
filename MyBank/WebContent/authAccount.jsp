<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자인증</title>
</head>
<body>
토큰: ${access_token }<br>
리토큰: ${refresh_token }<br>
사용자일련번호:${user_seq_no }<br>
인증되었습니다.
</body>
</html>