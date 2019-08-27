<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
<%@page import="java.util.List"%>
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
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="entete.jsp"%>
	<!-- FIN HAUT DE PAGE -->
	<form action="accueil" method="post">
		<div class="container-fluid text-center">
			<div class="row content">
				<div class="col-sm-2 sidenav"></div>
				<div class="col-sm-8 text-left bg-gray-100 boderRadiusIndex">
					<div class="text-center">
						<h1 class="h4 text-gray-900 mb-4">Liste des enchères</h1>
					</div>
					<div class="containerIndex">
						<div class="row">
							<div class="col-sm-6">
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
								</div>
							</div>
							<div class="col-sm-6">
								<a href="#" class="btn btn-primary btn-user btn-block"
									id="boutonRechercher"> Rechercher </a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2 sidenav"></div>

				<%
					
					if (session != null) {
						Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_REQUEST_USER);
						if (utilisateur != null) {
				%>
				<div class="col-sm-2 sidenav"></div>
				<div class="col-sm-8 text-left ">
					<div>
						<div class="row">
							<div class="col-sm-6  bg-gray-100">
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="radioCategories" value="option1" checked> <label
										class="form-check-label" for="exampleRadios1" id="radioAchat">
										Achat</label>
								</div>
								<div id="checkboxAchat">
									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"> <label
											class="form-check-label" id="checkboxVenteLabel"
											for="defaultCheck1"> Enchères ouvertes </label>
									</div>
									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"> <label
											class="form-check-label" id="checkboxVenteLabel "
											for="defaultCheck1"> Mes enchères en cours </label>
									</div>
									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"> <label
											class="form-check-label" id="checkboxVenteLabel "
											for="defaultCheck1"> Mes enchères remportées </label>
									</div>
								</div>
							</div>
							<div class="col-sm-6  bg-gray-100">
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="radioCategories" value="option2" checked> <label
										class="form-check-label" for="exampleRadios1" id="radioVente">
										Mes vente</label>
								</div>
								<div id="checkboxVente">

									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"> <label
											class="form-check-label" id="checkboxVenteLabel "
											for="defaultCheck1"> Mes ventes en cours </label>
									</div>
									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"> <label
											class="form-check-label" id="checkboxVenteLabel "
											for="defaultCheck1"> Ventes non débutées </label>
									</div>
									<div class="form-check">
										<input type="checkbox" value="" id="checkboxVente"><label
											class="form-check-label" id="checkboxVenteLabel"
											for="defaultCheck1"> Vente terminées </label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2 sidenav"></div>
				<%
					}}
				%>

				<div class="col-sm-2 sidenav"></div>
				<div class="col-sm-8 ">
					<hr>
				</div>
				<div class="col-sm-2 sidenav"></div>


				<!-- 				ON AFFICHE SELON LE CRITERE SELECTIONNE -->
				<div class="col-sm-2 sidenav"></div>
				<div class="col-sm-8 text-left bg-gray-100" id="block3index">
					<div class="containerIndex">
						<div class="row">
							<div class="col-sm-4" id="Block3Article">
								<%
									List<ArticleVendu> articles = (List<ArticleVendu>) request.getAttribute(ServletUtils.ATT_LISTE_ARTICLES);
									if ((articles != null) && (articles.size() > 0)) {
										for (ArticleVendu article : articles) {
								%>
								<div
									class="row no-gutters border-left-primary shadow h-100 py-2">
									<div class="col-md-4">
										<img src="imageDuProduit.jpg" id="imageProduit"
											class="card-img" alt="Photo du produit">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title">
												<a
													href="<%=request.getContextPath() + ServletUtils.DETAILS_ARTICLE + ServletUtils.ID_ARTICLE_PARAM
							+ article.getNoArticle()%>">
													<%=article.getNomArticle()%>
												</a>
											</h5>
											<p class="card-text">
												Prix :
												<%=article.getPrixVente()%></p>
											<p class="card-text">
												Fin de l'enchére :
												<%=article.getDateFinEncheres().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%></p>
											</br> <a
												href="<%=request.getContextPath() + ServletUtils.DETAILS_PROFIL
							+ ServletUtils.PSEUDO_UTILISATEUR_PARAM + article.getProprietaire().getPseudo()%>">
												<p class="card-text">
													Vendeur :<%=article.getProprietaire().getNom()%></p>
											</a>

										</div>
									</div>
								</div>
								<%
									}
									}
								%>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2 sidenav"></div>
			</div>
		</div>
	</form>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>