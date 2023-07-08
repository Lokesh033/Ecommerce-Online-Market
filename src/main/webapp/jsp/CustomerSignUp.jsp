<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="header">
</div>
${fail}
<div class="container">
<form action="/customer/signup" method="post" enctype="multipart/form-data">
Name: <input type="text" name="name"><br>
Email: <input type="text" name="email"><br>
Mobile: <input type="tel" name="mobile" pattern="{0-9}[10]"><br>
Create Password: <input type="password" name="password"><br>
Date of Birth: <input type="date" name="date"><br>
Gender:
<input type="radio" name="gender" value="male">Male
<input type="radio" name="gender" value="female">Female<br>
Address: <textarea rows="5" cols="30" name="address"></textarea> <br>
<button>Signup</button><button type="reset">Cancel</button>
</form>
<br><br>
<a href="/customer/login"><button>Back</button></a>
</div>

<div class="footer">
</div>
</body>
</html>