<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>ENI-Encheres</title>
</head>
<body>
	<%
		HttpSession sess = request.getSession();
		Utilisateur u = (Utilisateur) sess.getAttribute("sessionUtilisateur");
	%>
	<!-- DEBUT HAUT DE PAGE -->
	<%
		if (u != null) {
	%>
	<!-- DEBUT TOPBAR QUAND L'UTILISATEUR EST CONNECTE -->
	<nav
		class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<!-- Sidebar Toggle (Topbar) -->
		<img src="logo.jpg"
			alt="Logo Eni Enchère">

		<!-- Topbar Navbar -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="index.jsp" id="indexCo">
					Enchéres </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="vente.jsp" id="vendre">
					Vendre un article </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="profilUtilisateur.jsp"
				id="profilUtilisateur" role="button"> Mon Profil </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="index.jsp" id="indexDeco">
					Déconnexion </a></li>
		</ul>

	</nav>
	<!-- FIN TOPBAR QUAND L'UTILISATEUR EST CONNECTE -->
	
	<%
		} else {
	%>
		<!-- DEBUT TOPBAR QUAND L'UTILISATEUR EST DECONNECTE -->
	<div id="content-wrapper" class="d-flex flex-column">

		<!-- Main Content -->
		<div id="content">

			<!-- Topbar -->
			<nav
				class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

				<!-- Sidebar Toggle (Topbar) -->
				<lu>
				<li id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
					<a href="index.jsp"> ENI-Enchéres</a>
				</li>
				</lu>

				<!-- Topbar Navbar -->
				<ul class="navbar-nav ml-auto">

					<!-- Nav Item - User Information -->
					<li class="nav-item dropdown no-arrow"><a
						class="nav-link dropdown-toggle" href="connexion.jsp"
						id="userDropdown" role="button"
						> <span>S'inscrire
								- Se connecter</span>
					</a></li>
				</ul>
			</nav>
			<!-- End of Topbar -->
		</div>
	</div>
	<!-- FIN TOPBAR QUAND L'UTILISATEUR EST DECONNECTE -->
	<%
		}
	%>
</body>
</html>
