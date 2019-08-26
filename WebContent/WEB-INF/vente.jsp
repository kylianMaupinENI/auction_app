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
		<form>
			<div class="row content">
				<div class="col-sm-4 sidenav">
					<img src="imageDuProduit.jpg" class="img-thumbnail"
						alt="Photos produit">
				</div>
				<div class="col-sm-6 text-left">
					<h1 class="text-center" id="titreVente">Nouvelle vente</h1>
					<div class="form-group">
						<label for="articleVente">Article :</label> <input type="text"
							class="form-control form-control-user" id="articleVente"
							name="articleVente">
					</div>

					<div class="form-group">
						<label for="descriptionVente">Déscription :</label>
						<textarea class="form-control form-control-user"
							id="descriptionVente" name="descriptionVente"></textarea>
					</div>
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
					<div class="form-group">
						<label for="fileVente">Photo :</label> <input type="file"
							id="fileVente" name="fileVente" multiple>
					</div>
					<div class="form-group">
						<label for="prixVente">Prix :</label> <input type="text"
							class="form-control form-control-user" id="prixVente"
							name="prixVente">
					</div>
					<div class="form-group" id="blockCategorieIndex">
						<label>Debut de l'enchere : </label><input type="date"
							name="date_debut_enchere" id="date_debut_enchere"
							placeholder="10/08/2018" />
					</div>
					<div class="form-group" id="blockCategorieIndex">
						<label>Fin de l'enchere : </label><input type="date"
							name="date_fin_enchere" id="date_fin_enchere"
							placeholder="01/09/2018" />
					</div>
					<div class="row no-gutters border-left-primary shadow h-auto py-2">
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title">Retrait</h5>
								<div class="form-group">
									<label for="rueVenteLabel">Rue :</label> <input type="text"
										class="form-control form-control-user" id="rueVente"
										name="rueVente">
								</div>
								<div class="form-group">
									<label for="villeVenteLabel">Ville :</label> <input type="text"
										class="form-control form-control-user" id="villeVente"
										name="villeVente">
								</div>
								<div class="form-group">
									<label for="codePostalVenteLabel">Code postal :</label> <input
										type="text" class="form-control form-control-user"
										id="codePostalVente" name="codePostalVente">
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div class="row content">
						<div class="col-md-6">
							<a href="visualiserAnnonce.jsp"
								class="btn btn-primary btn-user btn-block"
								id="boutonEnregistrerVente"> Enregistrer </a>
						</div>
						<div class="col-md-6">
							<a href="index.jsp" class="btn btn-primary btn-user btn-block"
								id="boutonAnnulerVente"> Annuler </a>
						</div>
					</div>
				</div>
				<div class="col-sm-2 sidenav"></div>
			</div>
		</form>
	</div>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>
