<%@page import="fr.eni.encheres.bo.Utilisateur"%>
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

<body id="bodyIndex">
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="enteteCo.jsp"%>
	<!-- FIN HAUT DE PAGE -->

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
									<%Utilisateur u = (Utilisateur)request.getAttribute("utilisateur"); %>
									<div>Pseudo :<%--  <%=u.getPseudo() %> --%></div>
									<br>
									<div>Nom : <%=u.getNom() %></div>
									<br>
									<div>Prénom : <%=u.getPrenom() %></div>
									<br>
									<div>Email : <%=u.getEmail() %></div>
									<br>
									<div>Téléphone : <%=u.getTelephone() %></div>
									<br>
									<div>Adresse : <%=u.getAdresse() %></div>
									<% if(Boolean.getBoolean(request.getParameter("modifiable"))){ %>
									<hr>
									<a href="creationDeCompte.jsp"
										class="btn btn-primary btn-user btn-block"
										id="boutonCreerCompte"> Modifier </a>
									<% } %>
								</div>
							</div>
						</div>
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