<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
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
	<!-- Topbar -->
	<nav
		class="navbar navbar-expand navbar-light bg-white topbar mb-8 static-top shadow">

		<!-- Sidebar Toggle (Topbar) -->
		<div id="logoPc">
		<a href="<%= request.getContextPath() + ServletUtils.ACCUEIL%>">
		<img id="logoPc" alt="logo Eni-Enchère" src="logo.png"/>
		</a></div>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Topbar Navbar -->
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<%
					if (session != null) {
						Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
						if (utilisateur != null) {
				%>
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle"
					href="<%=request.getContextPath() + ServletUtils.ACCUEIL%>"
					id="accueil"> Enchères </a></li>
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle"
					href="<%=request.getContextPath() + ServletUtils.NOUVELLE_VENTE%>"
					id="vendre"> Vendre un article </a></li>
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle"
					href="<%=request.getContextPath() + ServletUtils.DETAILS_PROFIL
							+ ServletUtils.PSEUDO_UTILISATEUR_PARAM + utilisateur.getPseudo()%>"
					id="profilUtilisateur"> Mon Profil </a></li>
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle"
					href="<%=request.getContextPath() + ServletUtils.DECONNEXION%>"
					id="index"> Déconnexion </a></li>
				<%
					} else {
				%>
				<li class="nav-item dropdown no-arrow"><a
					class="nav-link dropdown-toggle"
					href="<%=request.getContextPath() + ServletUtils.CONNEXION%>"
					id="userDropdown"> S'inscrire - Se connecter </a></li>
				<%
					}
					}
				%>
			</ul>
		</div>
	</nav>
	<!-- End of Topbar -->
</body>
</html>