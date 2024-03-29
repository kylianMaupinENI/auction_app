<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@page import ="fr.eni.encheres.servlets.ServletUtils" %>
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

<title>ENI-Enchères</title>

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
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="/WEB-INF/entete.jsp"%>
	<!-- FIN HAUT DE PAGE -->

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- 404 Error Text -->
		<div class="text-center">
			<div class="error mx-auto" data-text="403">403</div>
			<p class="lead text-gray-800 mb-5">Erreur serveur</p>
			<a href="ServletUtile">&larr; Retourner à
				l'accueil</a>
		</div>

	</div>
	<!-- /.container-fluid -->

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="/WEB-INF/basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>