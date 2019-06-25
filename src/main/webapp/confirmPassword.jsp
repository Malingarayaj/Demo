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
	height: 100%;
	margin-top: 0px;
	margin-bottom: 0px;
	padding-bottom: 210px;
}

div>label {
	font-size: 20px;
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

button[type=reset]:hover {
	background-color: red;
}

div>p {
	text-align: center;
	font-weight: bold;
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
					<p>${updateSuccess}</p>
					<p>${AlredyUpdated}</p>
					<p>${wrongPwd}</p>
					<p>${emptyFields}</p>

					<p>${userNull}</p>
				</div>
				<form class="form-horizontal" action="changedPassword" method="post">
					<div class="form-group">
						<label class="control-label col-sm-3" for="userName">User
							Name :</label>
						<div class="col-sm-10 col-md-4">
							<input type="text" class="form-control" id="userName"
								value="${userName}" name="userName" readonly>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3" for="oldPassword">Old
							Password :</label>
						<div class="col-sm-10 col-md-4">
							<input type="password" class="form-control" id="oldPassword"
								placeholder="Enter old Password" name="oldPassword">
						</div>
					</div>

					<div>
						<p>${pwdLenth}</p>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3" for="newPassword">New
							Password :</label>
						<div class="col-sm-10 col-md-4">
							<input type="password" class="form-control" id="newPassword"
								placeholder="Enter New Password" name="newPassword">
						</div>
					</div>

					<div>
						<p>${pwdNotSame}</p>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="confirmPassword">Confirm
							Password :</label>
						<div class="col-sm-10 col-md-4">
							<input type="password" class="form-control" id="confirmPassword"
								placeholder="Enter Confirm password" name="confirmPassword">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-3" for="securePhrase">Secure
							Phrase :</label>
						<div class="col-sm-10 col-md-4">
							<input name="securePhrase" class="form-control" id="securePhrase"
								minlength="10" maxlength="20">
							<!-- <textarea name="securePhrase" class="form-control"
								id="securePhrase" rows="2" cols="30" minlength="10"
								maxlength="20"></textarea> -->

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-2">
							<button type="submit" class="btn btn-default" value="Submit">Update</button>
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