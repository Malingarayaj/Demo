<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Secure Password</title>
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

div>h1, p {
	text-align: center;
}

div>h3 {
	text-align: center;
	font-weight: bold;
	font-family: 'Playball', cursive; font-family: 'Tangerine', cursive;
	font-size: 40px;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="Header.jsp"></jsp:include>
	</div>
	<div class="jumbotron">
		<div style="margin-top: 70px">
			<div>
				<h1 style="font-family: 'Playball', cursive; font-size: 80px;">Secure
					Password</h1>
				<p
					style="font-family: 'Playball', cursive; font-family: 'Tangerine', cursive; font-size: 40px; font-weight: bold; font-style: italic; margin-top: -35px;">
					Best secure app to shield your password......!</p>
			</div>

			<div>
				<h3>${userName}</h3>
				<h3>${userNotFound}</h3>
				<h3>${updateSuccess}</h3>
			</div>
		</div>
	</div>
	<div>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>

</body>
</html>