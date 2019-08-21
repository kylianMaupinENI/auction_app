<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>ENI-ENCHERE</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">
	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-3">
								<div class="text-left">
									<h1 class="h4 text-gray-900 mb-4">ENI-Enchères</h1>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="p-5">
									<form class="user" method="post" action="login">
										<div class="form-group">
											<label for="identifiantConnexion">Identifiant :</label>
											<input type="identifiantConnexion"
												class="form-control form-control-user"
												id="identifiantConnexion"
												name="identifiantConnexion">
										</div>
										<div class="form-group">
											<label for="motDePasseConnexion">Mot de passe :</label>
											<input type="password" 
												class="form-control form-control-user"
												id="motDePasseConnexion" name="motDePasseConnexion">
										</div>
										<div id="containerConnexion">
											<div class="form-group" id="boutonConnexion">
												<input type="submit" 
												class="btn btn-primary btn-user btn-block"
												id="submit">
											</div>
											<div id="blockMdpOublie">
												<div class="form-group">
													<div class="custom-control custom-checkbox small">
														<input type="checkbox" class="custom-control-input"
															id="customCheck"> <label
															class="custom-control-label" for="customCheck">Se
															souvenir de moi</label>
													</div>
												</div>
												<a class="small" href="forgot-password.html">Mot de
													passe oublié</a>
											</div>
										</div>
									</form>
									<hr>
									<a href="creation.jsp"
										class="btn btn-primary btn-user btn-block"
										id="boutonCreerCompte"> Creer un compte </a>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>

<style>
#boutonCreerCompte {
	width: 50%;
	margin: auto;
}

@media screen and (min-width: 768px) {
	#boutonConnexion {
		width: 50%;
		float: left;
	}
}

@media screen and (max-width: 768px) {
	#boutonConnexion {
		width: 42%;
		float: left;
	}
	
	#blockMdpOublie{
		width: 30%;
	}
}
#containerConnexion {
	width: 100%;
	height: 65px;
}

#blockMdpOublie {
	float: right;
}
</style>