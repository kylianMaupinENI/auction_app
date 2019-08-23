<%@page import="fr.eni.encheres.bo.Utilisateur"%>
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
	<!-- DEBUT ENTETE -->
	<!-- Outer Row -->
	<form class="user" id="formCreationDeCompte">
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">

						<div class="row">
							<div class="col-lg-6 ">
								<div class="text-left">
									<h1 class="h4 text-gray-900 mb-4">ENI-Enchères</h1>
								</div>
								<div class="text-right">
									<h4 class="h4 text-gray-900 mb-4" id="titreProfil">Création
										de compte</h4>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 ">
								<div class="p-5" id="block1Creation">
									<div class="form-group">
										<label for="pseudoCreationCompte">Pseudo :</label> <input
											type="text" class="form-control form-control-user"
											id="pseudoCreationCompte">
									</div>
									<div class="form-group">
										<label for="nomCreationCompte">Nom :</label> <input
											type="text" class="form-control form-control-user"
											id="nomCreationCompte">
									</div>
									<div class="form-group">
										<label for="prenomCreationCompte">Prénom :</label> <input
											type="text" class="form-control form-control-user"
											id="prenomCreationCompte">
									</div>
									<div class="form-group">
										<label for="emailCreationCompte">Email :</label> <input
											type="text" class="form-control form-control-user"
											id="emailCreationCompte">
									</div>
									<div class="form-group">
										<label for="telephoneCreationCompte">Téléphone :</label> <input
											type="text" class="form-control form-control-user"
											id="telephoneCreationCompte">
									</div>
									<div id="creditCreationDeCompte">Crédit :</div>
									<br>
								</div>
							</div>
							<div class="col-lg-6" id="block2CreationDeCompte">
								<div class="p-5">
									<div class="form-group">
										<label for="rueCreationCompte">Rue :</label> <input
											type="text" class="form-control form-control-user"
											id="rueCreationCompte">
									</div>
									<div class="form-group">
										<label for="codePostalCreationCompte">Code Postal :</label> <input
											type="text" class="form-control form-control-user"
											id="codePostalCreationCompte">
									</div>
									<div class="form-group">
										<label for="villeCreationCompte">Ville :</label> <input
											type="text" class="form-control form-control-user"
											id="villeCreationCompte">
									</div>
									<div class="form-group">
										<label for="mdpCreationCompte">Mot de passe :</label> <input
											type="password" class="form-control form-control-user"
											id="mdpCreationCompte">
									</div>
									<div class="form-group">
										<label for="confirmationMdpCreationCompte">Confirmation
											:</label> <input type="password"
											class="form-control form-control-user"
											id="confirmationMdpCreationCompte">
									</div>
								</div>
							</div>
							<%
								List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
								if (listeCodesErreur != null) {
							%>
							<p style="color: red;">Erreur, Toutes les informations ne sont pas rentrées correctement.
								:</p>
							<%
								for (int codeErreur : listeCodesErreur) {
							%>
							<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
							<%
								}
								}
							%>
						</div>

						<div class="row" id="blockBoutonCreationDeCompte">
							<div class="col-sm-2"></div>
							<div class="col-sm-4">
								<div class="text-right form-group" id="boutonCree">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">
										Créer </a>
								</div>
								<div class="text-right form-group" id="boutonEnregistrer">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">
										Enregistrer </a>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="text-left form-group" id="boutonAnnuler">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">Annuler
									</a>
								</div>
								<div class="text-left form-group" id="boutonSupprimer">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">Supprimer
										mon compte </a>
								</div>
							</div>
							<div class="col-sm-2"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Fonction pour savoir si on est dans le cas d'une modif ou création -->
	<script>
		
	<% HttpSession sess = request.getSession();
		Utilisateur u = sess.getAttribute("sessionUtilisateur");
		if (u != null) {%>
		document.getElementById('creditCreationDeCompte').style.visibility = 'visible';
		document.getElementById('boutonAnnuler').style.display = 'none';
		document.getElementById('boutonCree').style.display = 'none';
		document.getElementById('boutonEnregistrer').style.visibility = 'visible';
		document.getElementById('boutonSupprimer').style.visibility = 'visible';
	<%} else {%>
		document.getElementById('creditCreationDeCompte').style.display = 'none';
		document.getElementById('boutonAnnuler').style.visibility = 'visible';
		document.getElementById('boutonCree').style.visibility = 'visible';
		document.getElementById('boutonEnregistrer').style.display = 'none';
		document.getElementById('boutonSupprimer').style.display = 'none';
	<%}%>
		
	</script>
</body>
</html>