<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body class="text-lg">
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="entete.jsp"%>
	<!-- FIN HAUT DE PAGE -->

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-3 sidenav">
<!-- 				<img src="imageDuProduit.jpg" class="img-thumbnail" -->
<!-- 					alt="Photos produit"> -->
			</div>
			<div class="col-sm-6 text-left bg-gray-100">
			
			<c:if test="${sessionScope.sessionUtilisateur.pseudo == gagnant.pseudo}">
				<h1 class="text-center" id="titreVente">Vous avez remporté la vente</h1>
			</c:if>
			<c:if test="${sessionScope.sessionUtilisateur.pseudo != gagnant.pseudo}">
				<h1 class="text-center" id="titreVente">${gagnant.pseudo} a remporté l'enchère</h1>
			</c:if>
				<br><br>
				<h2>
					${noArticle.nomArticle}
				</h2>
				<br><br>
				<div>
					Description : ${noArticle.description}
				</div>
<br>
				<div>
					Meilleure offre : ${noArticle.prixVente} point(s)
				</div>
				<br>
				<div>
					Mise à prix : ${noArticle.miseAPrix} point(s)
				</div>
				
				<c:if test="${sessionScope.sessionUtilisateur.pseudo != gagnant.pseudo}">
				<br>
					<div>
					Date de fin : ${noArticle.dateFinEncheres}
					</div>
				</c:if>
				<br>
				<div>
					Retrait : ${noArticle.lieuRetrait.rue} ${noArticle.lieuRetrait.codePostal} ${noArticle.lieuRetrait.ville}
				</div>
				<br>
				<div>
					Vendeur : ${noArticle.proprietaire.pseudo}
				</div>
				<c:if test="${sessionScope.sessionUtilisateur.pseudo == gagnant.pseudo}">
				<br>
				<div>
					Téléphone : ${noArticle.proprietaire.telephone}
				</div>
				</c:if>		
				<hr>
				<br>
				<div class="row content">
					<div class="col-md-6">
					
					<c:if test="${sessionScope.sessionUtilisateur.pseudo == gagnant.pseudo}">
						<a href="index.jsp" class="btn btn-primary btn-user btn-block"
							id="boutonRetourEnchere"> Retour </a>
					</c:if>
					<c:if test="${sessionScope.sessionUtilisateur.pseudo != gagnant.pseudo}">
						<a href="<%=request.getContextPath() + ServletUtils.ACCUEIL%>" class="btn btn-primary btn-user btn-block"
							id="boutonRetourEnchere"> Retrait effectué </a>
					</c:if>
					</div>
				</div>
			</div>
			<div class="col-sm-3 sidenav"></div>
		</div>
	</div>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>
