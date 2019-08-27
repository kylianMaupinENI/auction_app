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
		class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<!-- Sidebar Toggle (Topbar) -->
		<button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
			ENI-Enchères</button>

		<!-- Topbar Navbar -->
		<ul class="navbar-nav ml-auto">
			<%
				if (session != null) {
					Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
					if (utilisateur != null) {
			%>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle"
				href="<%= request.getContextPath() + ServletUtils.ACCUEIL %>" id="accueil">
					Enchères </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" 
				href="<%= request.getContextPath() + ServletUtils.NOUVELLE_VENTE%>" 
				id="vendre">
					Vendre un article </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle"
				href="<%= request.getContextPath() + ServletUtils.DETAILS_PROFIL + ServletUtils.PSEUDO_UTILISATEUR_PARAM + utilisateur.getPseudo() %>"
				id="profilUtilisateur"> Mon Profil </a></li>
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle"
				href="<%= request.getContextPath() + ServletUtils.DECONNEXION %>" id="index">
					Déconnexion </a></li>
			<%
				} else {
			%>
			<li class="nav-item dropdown no-arrow"><a
				class="nav-link dropdown-toggle"
				href="<%= request.getContextPath() + ServletUtils.CONNEXION %>" id="userDropdown">
					S'inscrire - Se connecter </a></li>
			<%
				}
				}
			%>
		</ul>

	</nav>
	<!-- End of Topbar -->
</body>
</html>
