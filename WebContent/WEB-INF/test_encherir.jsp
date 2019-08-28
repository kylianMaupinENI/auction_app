<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
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
	<form method="post" action="encherir">
		<div class="container-fluid text-center">
			<div class="row content">
				<div class="col-sm-4 sidenav">
					<img src="imageDuProduit.jpg" class="img-thumbnail"
						alt="Photos produit">
				</div>
				<div class="col-sm-6 text-left">
					<h1 class="text-center" id="titreVente">Détail de la vente</h1>
					<%
						ArticleVendu av = (ArticleVendu) request.getAttribute("articleVendu");
	
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
						Catégorie :
						<%=av.getCategorie()%>
					</div>
					<div>
						Meilleure offre :
						<%=av.getPrixVente()%>
					</div>
					<div>
						Mise à prix :
						<%=av.getMiseAPrix()%>
					</div>
					<div>
						Fin de l'enchère :
						<%=av.getDateFinEncheres()%>
					</div>
					<div>
						Retrait <br>
						<br> Rue :
						<%=av.getLieuRetrait().getRue()%>
						<br> Code postal :
						<%=av.getLieuRetrait().getCodePostal()%>
						Ville :
						<%=av.getLieuRetrait().getVille()%>
					</div>
					<div>
						Vendeur :
						<%=av.getProprietaire().getPseudo()%>
					</div>
					<div class="form-group">
						<label for="propositionEnchere">Ma proposition :</label> <input
							type="text" class="form-control form-control-user"
							id="propositionEnchere" name="propositionEnchere">
					</div>
					<hr>
					<div class="row content">
						<div class="col-md-6">
						<input type="hidden" value="<%= av.getNoArticle() %>" name="noArticle">
						<input type="submit" name="encherir"  class="btn btn-primary btn-user btn-block" value="Encherir">
						</div>
						<div class="col-md-6">
							<a href="index.jsp" class="btn btn-primary btn-user btn-block"
								id="boutonAnnulerEnchere"> Annuler </a>
						</div>
					</div>
				</div>
				<div class="col-sm-2 sidenav"></div>
			</div>
		</div>

		<!-- DEBUT BAS DE PAGE -->
		<%@include file="basDePage.jsp"%>
		<!-- FIN BAS DE PAGE -->
	</form>
</body>
</html>
