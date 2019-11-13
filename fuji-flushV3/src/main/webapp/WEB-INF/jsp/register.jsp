<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Creer un compte joueur</title>
</head>
<body background="images/ll.jpg">

<form name="firstForm" action="login" style="width:30%;margin:auto;background-color: #fffaf0;padding-bottom:10px;" method="post">
	<h1>Creer un compte joueur</h1>
	<form action="register" method="post">
		<table class="table table-striped table-responsive">
			<tr>
				<td>email</td>
				<td><input type="text" name="email" class="form-control"></td>
			</tr>

			<tr>
				<td>pseudo</td>
				<td><input type="text" name="pseudo" class="form-control"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" class="form-control"></td>
			</tr>
			<tr>
				<td>Confirme Password</td>
				<td><input type="password" name="confirmPass"
					class="form-control"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit"
					value="valider" class="btn btn-primary btn-lg" style="text-align:center;">Deja enregistrer? <a href="<%=request.getContextPath()%>/login.jsp">Login</a></td>
			</tr>
		</table>
	</form>



</body>
</html>