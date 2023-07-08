<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
<h1>${pass}</h1>

<div class="header"></div>

	<div class="container">
		${pass} <br>
		<a href="/admin/product-approve"><button>Approve Product</button></a>
		<br>
		<a href="/logout"><button>Logout</button></a>
	</div>

	<div class="footer"></div>
</body>
</html>