<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "jakarta.tags.core" %>
<%@taglib prefix = "fmt" uri = "jakarta.tags.fmt" %>
<%@taglib prefix = "fn" uri = "jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/LoginTask/resetPassword" method="post">
	    <c:if test = "${alert != null}">
			<h3 class = "alert alert danger">${alert}</h3>
		</c:if>
		<div class="container">
		  <h1>Reset Password</h1>
		  <hr>
			  <label for="username"><b>Username</b></label>
			  <input type="text" placeholder="Enter Username" name="username" id="username" required>
			 
			  <label for="email"><b>Email</b></label>
		      <input type="text" placeholder="Enter Email" name="email" id="email" required>
		  
			  <label for="psw"><b>Password</b></label>
			  <input type="password" placeholder="Enter Password" name="password" id="psw" required>
			
			  <label for="psw-repeat"><b>Repeat Password</b></label>
			  <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
		  <hr>
		  <button type="submit" class="resetbtn">Reset Password</button>
	  	</div>
	
	  <div>
	    <p>Already have an account? <a href="${pageContext.request.contextPath }/login">Back</a>.</p>
	  </div>
	</form>
</body>
</html>