<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign-UP</title>
<meta charset="ISO-8859-1">
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Playball"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Playball|Tangerine"
	rel="stylesheet">

<script>
	$(document).ready(function() {
		$("#hide").click(function() {
			$("#hide").hide();
			$("#show").show();
		});
		$("#show").click(function() {
			$("#hide").show();
			$("#show").hide();
		});
	});
</script>

<style type="text/css">
.jumbotron {
	background-color: gray;
	height: 650px;
	margin-top: 0px;
	margin-bottom: 0px;

	/* padding-bottom: 100px; */
}

button[type=submit] {
	background-color: #564218;
	border-radius: 8px;
	border: none;
	color: white;
	padding: 8px 10px;
}

button[type=submit]:hover {
	background-color: #45a049;
}

button[type=reset] {
	background-color: #562348;
	border-radius: 8px;
	border: none;
	color: white;
	padding: 8px 10px;
}

div>label {
	padding: 0px;
	margin-top: 0px;
	font-size: 20px;
}

h1 {
	font-weight: bold;
	text-align: center;
}

button[type=reset]:hover {
	background-color: blue;
}

div>p {
	font-weight: bold;
	text-align: center;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="Header.jsp"></jsp:include>
	</div>

	<div class="jumbotron">
		<div style="margin-top: 100px">
			<div class="container">
				<h2 style="text-align: center; font-weight: bold;">Log In your
					Account</h2>
				<div>
					<p>${disable}</p>
					<p>${userNotFound}</p>
					<p font-color="red">${message}</p>
				</div>

				<form class="form-horizontal" action="logIn" method="post">

					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1"
							for="userName">User Name :</label>
						<div class="col-sm-10 col-md-4" col>
							<input type="text" class="form-control" id="userName"
								placeholder="Your userName.." name="userName">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1"
							for="password">Password :</label>
						<div class="col-sm-10 col-md-4">
							<input type="password" class="form-control" id="password"
								placeholder="Your password.." name="password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-4" style="padding-left: 20px;">
							<input type="checkbox" onclick="myFunction()">Show
							Password
						</div>
					</div>

					<div class="form-group">
						<div class=" col-xs-2 col-md-offset-4">
							<button type="submit" class="btn btn-default" value="Submit">Log-In</button>
						</div>
						<div class="col-xs-offset-7 col-xs-2 col-md-offset-1">
							<button type="reset" class="btn btn-default">Clear</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	<script>
		function myFunction() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>

</body>
</html>