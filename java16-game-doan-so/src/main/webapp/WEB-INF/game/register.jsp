<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cybersoft.javabackend.gamedoanso.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<% String notmatchedpass = request.getAttribute("notmatchedpass").toString(); %>
<% String existeduser = request.getAttribute("existeduser").toString(); %>
<% String blankplayername = request.getAttribute("blankplayername").toString(); %>
<% String blankusername = request.getAttribute("blankusername").toString(); %>
<% String blankpassword = request.getAttribute("blankpassword").toString(); %>
	<div class="container">
		<h1 class="text-primary text-center">Game Đoán Số</h1>
		<form class="w-50"
			action="<%=request.getContextPath() + UrlConst.PLAYER_REGISTER%>"
			method="post" class="form-group">
			<label for="playerName">Your name</label> 
			<input type="text" class="form-control" name="playerName" id="playerName"
				placeholder="Enter your name" value='${lastplayername!=null ? lastplayername : ""}'
				<%if (blankplayername.equals("1")) {%> style="border-color: red" <%}%>> 
			<%if(blankplayername.equals("1")) { %> <span style="font-size: 12px; color:red">Tên không được để trống</span> <br> <% }%>				
			<label for="username">Username</label> 
			<input type="text" class="form-control" name="username" id="username"
				placeholder="Enter username" value='${lastusername != null ? lastusername : "" }' 
				<%if (existeduser.equals("1") || blankusername.equals("1")) {%> style="border-color: red" <%}%>> 
			<%if (existeduser.equals("1")) {%> <span style="font-size: 12px; color:red">Tên đăng nhập đã tồn tại</span> <br> 
			<%} else if (blankusername.equals("1")) {%> <span style="font-size: 12px; color:red">Tên đăng nhập không được để trống</span> <br> <%} %>	
			<label for="password">Password</label> 
			<input type="password" class="form-control" name="password" id="password" 
				placeholder="New account will be created if username isn't exists"
				<%if (blankpassword.equals("1")) {%> style="border-color: red" <%}%>>
				<%if (blankpassword.equals("1")) {%> <span style="font-size: 12px; color:red">Mật khẩu không được để trống</span> <br> <%}%>
			<label for="rPassword">Repeat Password:</label> 
			<input type="password" class="form-control" name="rPassword" id="rPassword"
				<%if (notmatchedpass.equals("1") || blankpassword.equals("1")) {%> style="border-color: red" <%}%>>
			<%if (notmatchedpass.equals("1")) {%> <span style="font-size: 12px; color:red">Mật khẩu không trùng khớp</span> <br> <%}%>
			<input class="btn btn-primary mt-2" type="submit" value="Đăng ký" />
		</form>
	</div>
</body>
</html>