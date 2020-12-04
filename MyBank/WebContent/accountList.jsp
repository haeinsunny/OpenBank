<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<jsp:include page="menu.jsp" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>accountList.jsp</title>
</head>
<body>
<br/>
<c:forEach items="${account_list }" var="account">
<div class="container">
	은행명: <span class="bname">${account.bank_name } |</span>
	계좌번호: <span>${account.account_num_masked } |</span>
	상품명: <span>${account.account_alias }</span><br>
</div>
</c:forEach> 

</body>
</html>