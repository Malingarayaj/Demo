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
<style type="text/css">
.jumbotron {
	background-color: gray;
	height: 650px;
	margin-top: 0px;
	margin-bottom: 0px;
	padding-bottom: 210px;
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

	font-size: 30px;
}

h1 {
	align: center;
}

button[type=reset]:hover {
	background-color: red;
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
				<div>
					<h2 align="center" color="blue">Enter secure Phrase</h2>
				</div>
				
				<div align="center" font-wieght="bold">
				<p>${invalidPhrase}</p>
				<p>${invalidUser}</p>
				<p>${emptyFields}</p>
				</div>
				<form class="form-horizontal" action="secureLogIn.do" method="post">

					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1" for="securePhrase">Secure
							Phrase :</label>
						<div class="col-sm-10 col-md-4">

							<input type="text" name="securePhrase" class="form-control"
								id="securePhrase" rows="1" cols="40" minlength="10"
								maxlength="40"/>

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-2 col-md-offset-4">
							<button type="submit" class="btn btn-default" value="Submit">Log-In</button>
						</div>
						<div class="col-sm-offset-1 col-sm-2">
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


</body>
</html>