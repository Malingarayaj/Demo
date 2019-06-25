<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Welcome</title>

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
	<link href="./mycss/style.css" rel="stylesheet">
	
	
<style type="text/css">


.jumbotron {
	background-color: gray;
	height: 650px;
	margin-top: 0px;
	margin-bottom: 0px;
	padding-bottom: 210px;
}

div > h1,p{
text-align:center;
}

</style>
</head>
<body class="bg">
	<div>
		<jsp:include page="Header.jsp"></jsp:include>
	</div>
	
	</div>
	<div class="jumbotron bg">
		<div style="margin-top: 150px">
			<div>
				<h1 style="font-family: 'Playball', cursive;font-size:100px;">Secure Password</h1>
				<p
					style="font-family: 'Playball', cursive; font-family: 'Tangerine', cursive;font-size:40px;font-weight:bold;font-style:italic;margin-top:-35px;">
					Best secure app to Shield your password.....!</p>
			</div>
		</div>
	</div>
	<div>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>

