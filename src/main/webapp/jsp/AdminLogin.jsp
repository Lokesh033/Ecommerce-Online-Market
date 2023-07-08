<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="css/Home.css">
</head>
<body>



<div class="header"></div>


	<div class="container">
		<h1>${fail}</h1>
		<div class="form">
			<form action="/admin/login" method="post">
				Email: <input type="text" name="email"><br> Password: <input
					type="password" name="password"><br>
				<button>Login</button>
				<button type="reset">Cancel</button>
			</form>
			<br>
			<br> <a href="/"><button>Back</button></a>

		</div>
	</div>

	<div class="footer"></div>


</body>
</html>