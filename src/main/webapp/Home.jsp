<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
.main {
	background-color: gray;
	height: 650px;
	margin-top: 0px;
	margin-bottom: 0px;
	padding-bottom: 2px;
}

.panel-heading {
	text-align: center;
	font-weight: bold;
}

@media ( min-width : 768px) {
	.brand-pills>li>a {
		border-top-right-radius: 0px;
		border-bottom-right-radius: 0px;
	}
	li.brand-nav.active a:after {
		content: " ";
		display: block;
		width: 0;
		height: 0;
		border-top: 20px solid transparent;
		border-bottom: 20px solid transparent;
		border-left: 9px solid #428bca;
		position: absolute;
		top: 50%;
		margin-top: -20px;
		left: 100%;
		z-index: 1;
	}
}

.message>p {
	text-align: center;
	font-weight: bold;
}

div.lastLogIn {
	position: fixed;
	bottom: 60px;
	left: 0;
	width: 400px;
}
</style>
</head>
<body>
	<div>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<h1>
						<a style="font-family: 'Playball', cursive;" " href="index.jsp">Secure
							Password</a>
					</h1>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li><p
								style="color: white; padding-top: 10px; padding-right: 10px; font-size: 25px; padding-bottom: 5px;">${userName}</p>
							<span style="margin-left: 2px"></span></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="container"
		style="margin-top: 80px; background-color: :gray">
		<div class="row sidebar">
			<h1>welcome To SecurePassword</h1>
			<div role="tabpanel">
				<div class="col-sm-3">
					<ul class="nav nav-pills brand-pills nav-stacked" role="tablist">
						<li role="presentation" class="brand-nav active"><a
							href="#tab1" aria-controls="tab1" role="tab" id="allCredential"
							data-toggle="tab">All Credentials</a></li>
						<li role="presentation" class="brand-nav"><a href="#tab2"
							aria-controls="tab2" role="tab" data-toggle="tab">Add
								Credentials</a></li>
						<li role="presentation" class="brand-nav"><a href="#tab3"
							aria-controls="tab3" role="tab" data-toggle="tab">Share
								Credentials</a></li>
						<li role="presentation" class="brand-nav"><a href="#tab4"
							aria-controls="tab4" role="tab" data-toggle="tab">Disable
								Account</a></li>
						<li role="presentation" class="brand-nav"><a href="#tab7"
							aria-controls="tab7" role="tab" data-toggle="tab">Log Out</a></li>
					</ul>
				</div>
				<div class="col-sm-9">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane ${allCredential}" id="tab1">
							<div class="panel panel-success">
								<div class="panel-heading">ALL DETAILS</div>
								<div class="panel-body">
									<table class="table table-hover">
										<thead class="thead-dark">
											<tr>
												<th>APPNAME</th>
												<th>USERID</th>
												<th>URL</th>
												<th>PASSWORD</th>
												<th>EDIT</th>
												<th>DELETE</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="list" items="${user}" varStatus="loop">
												<tr>
													<td>${list.appName}</td>
													<td>${list.appUserId}</td>
													<td>${list.appUrl}</td>
													<td>
														<%-- <a 
													role="presentation" 
													id="view${loop.idex}"
													id="view"
														data-target="#myModal" 
														data-toggle="modal" href="#"
														data-userName="${list.appName}"
														data-password="${list.appPassword}" 
														aria-controls="tab4"
														role="tab" 
														data-toggle="tab"
														onClick="getCategoryIndex(${loop.index},${list.appName},${list.appPassword})"
														>View</a> --%> <a style="cursor: pointer;"
														role="presentation"
														<%-- id="view${loop.idex}" --%>
													id="view${loop.index}"
														onClick="getCategoryIndex(${loop.index},'${list.appName}',${list.appPassword})">View</a>

													</td>
													<td><a style="cursor: pointer;"
														href="editUserCredintials?name=${list.appName}&&user=${list.loggedUser}">Edit</a></td>
													<td><a style="cursor: pointer;"
														href="deleteUserCredintials?appName=${list.appName}&&userName=${list.loggedUser}"
														onclick="return confirm('Are you sure you want to delete this item?');">Delete</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header text-center">
										<h4 class="modal-title w-100 font-weight-bold">Your
											Password</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body mx-3">
										<div class="md-form mb-5">
											<label data-error="wrong" data-success="right" for="name">App
												Name</label> <i class="fas fa-envelope prefix grey-text"></i> <input
												type="email" id="name" name="name"
												class="form-control validate">
										</div>
										<div class="md-form mb-4">
											<label data-error="wrong" data-success="right" for="password">Password</label>
											<i class="fas fa-lock prefix grey-text"></i> <input
												type="password" id="password" name="password"
												class="form-control validate">
										</div>
										<div>
											<button onclick="myFunction()">Copy-Password</button>
										</div>

									</div>

								</div>
							</div>
						</div>
						<script type="text/javascript">
								function getCategoryIndex(index,name,pwd){
							
							          $('#name').val(name);
							          $('#password').val(pwd);
							          $("#myModal").modal('show');
							
						        }
								
								function myFunction(){
									 var copyText =document.getElementById("password"); //$("#password").val();
									 copyText.select();
									 document.execCommand("copy");
									  alert("Copied the text: " + copyText.value);
								}
								
								function cancelDisable(){
									$('#allCredential').attr("aria-expanded","true");
									$('#allCredential').parent().addClass('active').siblings().removeClass('active');
								
								}
						
                        </script>


						<div role="tabpanel" class="tab-pane ${activeAddCredential}"
							id="tab2">
							<div class="panel panel-success">
								<div class="panel-heading">ADD CREDENTIALS</div>
								<div class="panel-body">
									<div class="message">
										<p>${existingUser}</p>
										<p>${appNameNull}
									</div>
									<form id="loginform" action="addCredentials" method="post"
										class="form-horizontal" role="form">
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">AppName</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appName" placeholder="Enter the Application Name">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">URL</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appUrl" value="${userExist.appUrl}"
													placeholder="Enter the url of application">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">UserId</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appUserId" value="${userExist.appUserId}"
													placeholder="Enter the userId">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">Password</label>
											<div class="col-md-9">
												<input type="password" class="form-control" min="10"
													max="20" name="appPassword"
													value="${userExist.appPassword}"
													placeholder="Enter the Password">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">Note</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appNote" value="${userExist.appNote}"
													placeholder="Enter the Note" />
											</div>
										</div>
										<div style="margin-top: 10px" class="form-group">
											<!-- Button -->

											<div class="col-md-offset-3 col-md-9">
												<input id="btn-login" type="submit" class="btn btn-success"
													value="submit">&nbsp <input id="btn-login"
													value="Clear" type="reset" class="btn btn-success">
											</div>

										</div>
									</form>

								</div>
							</div>

						</div>
						<div role="tabpanel" class="tab-pane" id="tab3">
							<div class="panel panel-success">
								<div class="panel-heading">SHARE CREDENTIALS</div>
								<div class="panel-body">
									<form id="loginform" action="addCredentialsController"
										method="post" class="form-horizontal" role="form">
										<div class="form-group">
											<label for="password" class="col-md-3 control-label">AppName</label>
											<div class="col-md-9" class="main">
												<select class="form-control" name="type" id="maindropdown">

												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">FieldToShare</label>
											<div class="col-md-9" class="submain">
												<select class="form-control" name="subType" id="subdropdown"
													multiple>

												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">To-Email</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="userEmail"
													placeholder="Email Address">
											</div>
										</div>

										<div style="margin-top: 10px" class="form-group">
											<!-- Button -->

											<div class="col-md-offset-3 col-md-6">
												<input id="btn-login" type="submit" class="btn btn-success"
													value="Share">&nbsp <input id="btn-login"
													value="Clear" type="reset" class="btn btn-success">
													
												<a href="#tab1" style="float: right" aria-controls="tab1" role="tab"
													data-toggle="tab"><button id="btn-login" class="btn btn-success" style="float: right"
														onclick="cancelDisable()">Cancel</button></a>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>

						<script>
						/* $("#maindropdown").empty();
						$each(user,function(index,value){
							var card=<option class="mainoption" id="value.${index}">value</option>}
						$("#maindropdown").append(card);
						
						$('#mainoption').on('click', function(){
							var id=$(this).attr('id');
							var val=$(this).text();
							$each(user,$('#subdropdown').empty(),function(index,value)
									{
								if(id=index){
									var card='<option id"${index}>val</option>'
								}
								$('#subdropdown').append(card)
								}
									})
						})
						)		 */				
						</script>



						<div role="tabpanel" class="tab-pane" id="tab4">
							<div class="panel panel-success">
								<div class="panel-heading">DISABLE ACCOUNT</div>
								<div class="panel-body">
									<div>If you are disable the ACCOUNT then you can't log-in
										to this apllication until you enable the ACCOUNT</div>
									<br> <br>
									<div>
										<a href="disableUserAccount?userName=${userName}"><button>Disable</button></a>
										<a href="#tab1" aria-controls="tab1" role="tab"
											data-toggle="tab"><button style="float: right"
												onclick="cancelDisable()">Cancel</button></a>
									</div>

								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane ${editCredential}" id="tab5">
							<div class="panel panel-success">
								<div class="panel-heading">EDIT CREDENTIALS</div>
								<div class="panel-body">
									<form id="loginform" action="updateCredentials" method="post"
										class="form-horizontal" role="form">
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">AppName</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appName" value="${credential.appName}"
													readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">URL</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appUrl" placeholder="Enter the url of application"
													value="${credential.appUrl}">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">UserId</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appUserId" placeholder="Enter the userId"
													value="${credential.appUserId}">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">Password</label>
											<div class="col-md-9">
												<input type="password" class="form-control" min="10"
													max="20" name="appPassword"
													placeholder="Enter the Password"
													value="${credential.appPassword}">
											</div>
										</div>
										<div class="form-group">
											<label for="email" class="col-md-3 control-label">Note</label>
											<div class="col-md-9">
												<input type="text" class="form-control" min="10" max="20"
													name="appNote" placeholder="Enter the Note"
													/ value="${credential.appNote}">
											</div>
										</div>
										<div style="margin-top: 10px" class="form-group">
											<!-- Button -->

											<div class="col-md-offset-3 col-md-5">
												<input id="btn-login" type="submit" class="btn btn-success"
													value="submit">&nbsp <input id="btn-login"
													value="Clear" type="reset" class="btn btn-success">
											</div>
											<div class="col-md-3">
												<a href="#tab1" aria-controls="tab1" role="tab"
													data-toggle="tab"><button class="btn btn-success"
														onclick="cancelDisable()">Cancel</button></a>
											</div>
										</div>


									</form>

								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="tab6">
							<div class="panel panel-success">
								<div class="panel-heading">DELETE CREDENTIALS</div>
								<div class="panel-body">
									<p>Are you sure.......if click delete this particular
										records will delete.....if you sure.....delete</p>
									<a
										href="deleteUserCredintials?appName=${list.appName}&&userName=${list.loggedUser}">Delete</a>
									<a href="#tab1" aria-controls="tab1" role="tab"
										data-toggle="tab"><button style="float: right"
											onclick="cancelDisable()">Cancel</button></a>


								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="tab7">
							<div class="panel panel-success">
								<div class="panel-heading">LOGOUT YOUR ACCOUNT</div>
								<div class="panel-body">
									<p align="center">Are you sure...you want to logout your
										account..!</p>
									<br>
									<div align="center">
										<span>Click here to &nbsp &nbsp &nbsp</span><a href="logout"><button>Log-Out</button></a>
									</div>
									<a href="#tab1" aria-controls="tab1" role="tab"
										data-toggle="tab"><button style="float: right"
											onclick="cancelDisable()">Cancel</button></a>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="lastLogIn col-sm-12">
		<h4>${lastLogInDate}</h4>
	</div>
	<div>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>