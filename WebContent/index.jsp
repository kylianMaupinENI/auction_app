<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/feuilleDeStyle.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>ENI-Encheres</title>
</head>
<body id="bodyIndex">
	<div id="enteteCo"><%@include file="entete.jsp"%></div>
	
	<form>
		<div class="container-fluid justify-content-center">
			<div class="row">
				<div class="col-xl-3 col-md-6 mb-4"></div>
				<div class="col-xl-3 col-md-6 mb-4">
					<div id="blockFiltreIndex">
						<input type="text" placeholder="Le nom de l'article contient"
							name="search" id="search">
						<button type="submit">
							<i class="fa fa-search"></i>
						</button>
						<div class="form-group" id="blockCategorieIndex">
							<label for="categorieIndex" id="categorieIndex">Catégories</label>
							<select class="form-control" id="SelectIndex">
								<option>Toutes</option>
								<option>Informatique</option>
								<option>Ameublement</option>
								<option>Vêtement</option>
								<option>Sport et loisirs</option>
							</select>
						</div>
						<%
							if (session.getAttribute(ServletUtils.ATT_SESSION_USER) != null) {
						%>
						<div id="enteteCo"><%@include file="entete.jsp"%></div>

						<%
							}
						%>
					</div>
				</div>
				<div class="col-xl-3 col-md-6 mb-4" id="boutonRechercher">
					<a href="#" class="btn btn-primary btn-user btn-block"
						id="boutonRechercher"> Rechercher </a>
				</div>
				<div class="col-xl-3 col-md-6 mb-4"></div>
			</div>
		</div>
		<%
			if (session.getAttribute(ServletUtils.ATT_SESSION_USER) != null) {
		%>
		<div class="row">
			<div class="col-xl-3 col-md-6 mb-4"></div>
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="radioCategories"
						value="option1" checked> <label
						class="form-check-label" for="exampleRadios1" id="radioAchat" > Achat</label>
				</div>
				<div id="checkboxAchat">
					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"> <label class="form-check-label"
							id="checkboxVenteLabel" for="defaultCheck1"> Enchères ouvertes </label>
					</div>
					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"> <label class="form-check-label"
							id="checkboxVenteLabel " for="defaultCheck1"> Mes enchères en cours </label>
					</div>
					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"> <label class="form-check-label"
							id="checkboxVenteLabel " for="defaultCheck1"> Mes enchères remportées </label>
					</div>
				</div>
			</div>
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="radioCategories"
						value="option2" checked> <label
						class="form-check-label" for="exampleRadios1" id="radioVente"> Mes vente</label>
				</div>
				<div id="checkboxVente">

					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"> <label class="form-check-label"
							id="checkboxVenteLabel " for="defaultCheck1"> Mes ventes en cours </label>
					</div>
					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"> <label class="form-check-label"
							id="checkboxVenteLabel " for="defaultCheck1"> Ventes non débutées </label>
					</div>
					<div class="form-check">
						<input type="checkbox" value=""
							id="checkboxVente"><label class="form-check-label"
							id="checkboxVenteLabel" for="defaultCheck1"> Vente terminées </label>
					</div>
				</div>
			</div>
			<div class="col-xl-3 col-md-6 mb-4"></div>
		</div>
	</form>
	<%
			}
	%>
	<!-- DEBUT FICHE PRODUIT -->
	<!-- Afficher tous les produits -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="row no-gutters border-left-primary shadow h-100 py-2">
			<!-- 			<div class="col-md-4"> -->
			<!-- 				<img src="imageDuProduit.jpg" id="imageProduit" class="card-img" -->
			<!-- 					alt="Photo du produit"> -->
			<!-- 			</div> -->
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">Nom du produit</h5>
					<p class="card-text">Prix :</p>
					<p class="card-text">Fin de l'enchére :</p>
					</br>
					<p class="card-text">Vendeur :</p>
				</div>
			</div>
		</div>
	</div>
	<!-- FIN FICHE PRODUIT -->

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>

<style>
#checkboxVente, #checkboxAchat{
	margin-left: 5%;
}

#checkboxIndex{
	margin-left: 15%
}

#radioVente,#radioAchat{
	margin-left:5%;
}
</style>
