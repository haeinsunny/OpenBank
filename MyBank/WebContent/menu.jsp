<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>menu.jsp</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
  #jumbo{
  background-color: #FCE5E2;
  }
  nav{
  background-color: coral;
  }
  </style>
</head>

<body>
  <div class="jumbotron text-center" id="jumbo" style="margin-bottom:0">
    <h2>해 인</h2>
  </div>

  <nav class="navbar navbar-expand-sm navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
      <!-- Brand -->
      <a class="navbar-brand" href="#">About</a>

      <!-- Links -->

      <ul class="navbar-nav mr-auto">

        <li class="nav-item">
          <a class="nav-link" href="#">입출금</a>
        </li>


        <li class="nav-item">
          <a class="nav-link" href="AuthAccount">계좌등록</a>
        </li>


        <li class="nav-item">
          <a class="nav-link" href="GetAccountList">모든계좌조회</a>
        </li>
      </ul>
    </div>

    <div class="mx-auto order-0">
      <a class="navbar-brand mx-auto" href="/MyBank/jsp/menu.jsp">HOME</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
      <ul class="navbar-nav ml-auto">

        <li class="nav-item">
          <a class="nav-link" href="#">로그아웃</a>
        </li>


        <li class="nav-item">
          <a class="nav-link" href="#">로그인</a>
        </li>


        <li class="nav-item">
          <a class="nav-link" href="#">회원가입</a>
        </li>


      </ul>
    </div>
  </nav>
  <br>
</body>

</html>