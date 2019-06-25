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
$(document).ready(function(){
  $("#hide").click(function(){
    $("#hide").hide();
    $("#show").show();
  });
  $("#show").click(function(){
    $("#hide").show();
    $("#show").hide();
  });
});
</script>
<style type="text/css">
.jumbotron {
	background-color: gray;
	height: 100%;
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
	font-size: 20px;
}

h1 {
	align: center;
}

div>h4 {
	font-weight: bold;
	text-align: center;
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
			<h2 style="text-align: center; font-weight: bold;">Register your
					Account</h2>
				<div>
					<h4>${success}</h4>
					<h4>${exist}</h4>
					<h4>${emptyField}</h4>

				</div>
				<div>
					<h4>${emptyMail}</h4>
					<h4>${wrongEmail}</h4>
				</div>
				<form class="form-horizontal" action="signUp.do" name="myForm"
					method="post">
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1" for="email">Email:</label>
						<div class="col-sm-10 col-md-4">
							<input type="email" class="form-control" id="email"
								placeholder="Enter email" name="email">
						</div>
					</div>

					<div>

						<h4>${EmptyNo}</h4>
						<h4>${invalidNo}</h4>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1" for="mobile">Mobile
							No :</label>
						<div class="col-sm-10 col-md-4">
							<input type="number" class="form-control" id="mobile"
								placeholder="Your mobile no.." name="mobileNo">
						</div>
					</div>
					<div>
						<h4>${emptyName}</h4>
						<h4>${nameLength}</h4>

					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1" for="user">User
							Name :</label>
						<div class="col-sm-10 col-md-4">
							<input type="text" class="form-control" id="user"
								placeholder="Your user name.." name="userName">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-md-offset-1" for="type">Type
							:</label>
						<div class="col-sm-10 col-md-4">
							<select class="form-control" id="type" name="type">
								<option value="professional">Professionals</option>
								<option value="personal">Personal</option>
								<option value="company">Company</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class=" col-xs-2 col-md-offset-4">
							<button type="submit" class="btn btn-default" value="Submit">Sign-Up</button>
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


	<script src="boot-library/js/email-validation.js"></script>
</body>
</html>