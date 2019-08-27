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
</head>
<body>
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="entete.jsp"%>
	<!-- FIN HAUT DE PAGE -->

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-4 sidenav">
				<img src="imageDuProduit.jpg" class="img-thumbnail"
					alt="Photos produit">
			</div>
			<div class="col-sm-6 text-left">
				<%
					ArticleVendu av = (ArticleVendu) request.getAttribute(ServletUtils.ATT_ARTICLE_GAGNANT);
					if (session != null) {
						Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_REQUEST_USER);
						Utilisateur gg = (Utilisateur) session.getAttribute(ServletUtils.ATT_USER_GAGNANT);
						if (utilisateur.getPseudo().equals(gg.getPseudo()) && utilisateur.getPseudo() != null && gg.getPseudo() != null ) {
				%>
				<h1 class="text-center" id="titreVente">Vous avez remporté la
					vente</h1>
				<%
					} else {
				%>
				<h1 class="text-center" id="titreVente"><%=gg.getPseudo()%>
					a remporté l'enchère
				</h1>
				<%
					}
				%>


				<h3>
					<%=av.getNomArticle()%>
					PC
				</h3>
				<div>
					Description :
					<p>
						<%=av.getDescription()%>
					</p>
				</div>

				<div>
					Meilleure offre :
					<%=av.getPrixVente()%>
				</div>
				<div>
					Mise à prix :
					<%=av.getMiseAPrix()%>
				</div>
				<%
					if (!utilisateur.getPseudo().equals(gg.getPseudo())) {
				%>
				<div>
					Date de fin :
					<%=av.getDateFinEncheres()%>
				</div>
				<%
					}
				%>
				<div>
					Retrait :
					<%=av.getLieuRetrait()%>
				</div>
				<div>
					Vendeur :
					<%=av.getProprietaire()%>
				</div>
				<%
					if (true) {
				%>
				<div>
					Téléphone :
					<%=av.getProprietaire()%>
				</div>
				<%
					}
				%>
				<hr>
				<div class="row content">
					<div class="col-md-6">
						<%
							if (utilisateur.getPseudo().equals(gg.getPseudo())) {
						%>
						<a href="index.jsp" class="btn btn-primary btn-user btn-block"
							id="boutonRetourEnchere"> Retour </a>
						<%
							} else {
						%>
						<a href="index.jsp" class="btn btn-primary btn-user btn-block"
							id="boutonRetourEnchere"> Retrait effectué </a>
						<%
							}
							}
						%>
					</div>
				</div>
			</div>
			<div class="col-sm-2 sidenav"></div>
		</div>
	</div>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>
