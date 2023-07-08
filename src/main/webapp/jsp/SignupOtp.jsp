<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
<h1>${fail}</h1>
<h1>Hello ${merchant.getName()} ENter Otp ${extra}</h1>
<form action="/merchant/verify-otp/${merchant.getEmail()}" method="post">
<input type="text" name="otp" placeholder="Enter OTP">
<button>Verify</button><button type="reset">Cancel</button>


</form><br>
<a href="/merchant/resend-otp/${merchant.getEmail()}">click here to resend otp</a>
</html>