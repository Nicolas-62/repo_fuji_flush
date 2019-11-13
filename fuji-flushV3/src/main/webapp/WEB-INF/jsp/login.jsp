<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Compte joueur</title>
</head>
<body background="images/ll.jpg">
	<h2>${ message }</h2>

	<form action="login" method="POST">
		<table class="table table-responsive table-striped">
			<tr>
				<td>Pseudo</td>
				<td><input type="text"  name="pseudo" class="form-control"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" class="form-control"></td>
			</tr>
			<tr>

				<td colspan="2"><input type="submit" name="submit"
					value="Valider" class="btn btn-primary btn-lg">
					<p style="text-align:right;width:10%;margin:auto;"><a href="register.jsp">Inscription</a></p></td>
			</tr>
		</table>
	</form>


</body>
</html>