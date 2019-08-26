<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
<%@page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/feuilleDeStyle.css">
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

<body>
	<div class="container">
		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<form class="user" method="post" action="login"
							id="blockFormulaireConnexion" >
							<!-- Nested Row within Card Body -->
							<div class="row">
								<div class="col-lg-3">
									<div class="text-left">
										<h1 class="h4 text-gray-900 mb-4">ENI-Enchères</h1>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="p-5">
										<%List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
											if (listeCodesErreur != null) {%>
										<%for (int codeErreur : listeCodesErreur) {%>
										<p style="color: red";><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
										<%}}%>
										<div class="form-group">
											<label for="<%= ServletUtils.CHAMP_PSEUDO %>">Identifiant :</label> 
											<input type="text" class="form-control form-control-user"
												id="<%= ServletUtils.CHAMP_PSEUDO %>" name="<%= ServletUtils.CHAMP_PSEUDO %>">
										</div>
										<div class="form-group">
											<label for="<%= ServletUtils.CHAMP_MOT_DE_PASSE %>">Mot de passe :</label> 
											<input type="password" class="form-control form-control-user"
												id="<%= ServletUtils.CHAMP_MOT_DE_PASSE %>" name="<%= ServletUtils.CHAMP_MOT_DE_PASSE %>">
										</div>
										<div class="row">
										<div class="col-lg-6">
											<input type="submit"
												class="btn btn-primary btn-user btn-block" value="Connexion">
												</div>
											<div class="col-lg-6 text-right">
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
										<hr>
										<a href="signin"
											class="btn btn-primary btn-user btn-block"
											id="boutonCreerCompte"> Creer un compte </a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>
