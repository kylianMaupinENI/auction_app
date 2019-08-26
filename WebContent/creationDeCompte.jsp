<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@page import="fr.eni.encheres.messages.LecteurMessage"%>
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
	<form class="user" id="formCreationDeCompte" method="post"
		action="signin">
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
								<%List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
									if (listeCodesErreur != null) {%>
								<%for (int codeErreur : listeCodesErreur) {%>
								<p style="color: red";><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
								<%}}%>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 ">
								<div class="p-5" id="block1Creation">
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_PSEUDO%>">Pseudo :</label>
										<input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_PSEUDO%>"
											name="<%=ServletUtils.CHAMP_PSEUDO%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_NOM%>">Nom :</label> <input
											type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_NOM%>"
											name="<%=ServletUtils.CHAMP_NOM%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_PRENOM%>">Prenom :</label>
										<input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_PRENOM%>"
											name="<%=ServletUtils.CHAMP_PRENOM%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_EMAIL%>">Email :</label> <input
											type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_EMAIL%>"
											name="<%=ServletUtils.CHAMP_EMAIL%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_TELEPHONE%>">Téléphone
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_TELEPHONE%>"
											name="<%=ServletUtils.CHAMP_TELEPHONE%>">
									</div>
									<div id=<%=ServletUtils.CHAMP_CREDIT%>>Crédit :</div>
									<br>
								</div>
							</div>
							<div class="col-lg-6" id="block2CreationDeCompte">
								<div class="p-5">
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_RUE%>">Rue :</label> <input
											type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_RUE%>"
											name="<%=ServletUtils.CHAMP_RUE%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_CODE_POSTAL%>">Code
											postal :</label> <input type="text"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_CODE_POSTAL%>"
											name="<%=ServletUtils.CHAMP_CODE_POSTAL%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_VILLE%>">Ville :</label> <input
											type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_VILLE%>"
											name="<%=ServletUtils.CHAMP_VILLE%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_MOT_DE_PASSE%>">
											Mot de passe :</label> 
										<input type="password"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_MOT_DE_PASSE%>"
											name="<%=ServletUtils.CHAMP_MOT_DE_PASSE%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_CONFIRMATION%>">
											Confirmation :
										</label> 
										<input type="password"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_CONFIRMATION%>"
											name="<%=ServletUtils.CHAMP_CONFIRMATION%>">
									</div>
								</div>
							</div>
						</div>

						<div class="row" id="blockBoutonCreationDeCompte">
							<div class="col-sm-2"></div>
							<div class="col-sm-4">
								<div class="text-right form-group" id="<%= ServletUtils.BTN_INSCRIPTION %>">
									<input type="submit" class="btn btn-primary btn-user btn-block"
										value="S'inscrire">
								</div>
								<div class="text-right form-group" id="<%= ServletUtils.BTN_ENREGISTRER %>">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">
										Enregistrer </a>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="text-left form-group" id="<%= ServletUtils.BTN_ANNULER %>">
									<a href="index.jsp" class="btn btn-primary btn-user btn-block">Annuler
									</a>
								</div>
								<div class="text-left form-group" id="<%= ServletUtils.BTN_SUPPRIMER %>">
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
		
	<%HttpSession sess = request.getSession();
			Utilisateur u = (Utilisateur) sess.getAttribute("sessionUtilisateur");
			
			if (u != null) {%>
		document.getElementById('<%= ServletUtils.CHAMP_CREDIT %>').style.visibility = 'visible';
		document.getElementById('<%= ServletUtils.BTN_ANNULER %>').style.display = 'none';
		document.getElementById('<%= ServletUtils.BTN_INSCRIPTION %>').style.display = 'none';
		document.getElementById('<%= ServletUtils.BTN_ENREGISTRER %>').style.visibility = 'visible';
		document.getElementById('<%= ServletUtils.BTN_SUPPRIMER %>').style.visibility = 'visible';
	<%} else {%>
		document.getElementById('<%= ServletUtils.CHAMP_CREDIT %>').style.display = 'none';
		document.getElementById('<%= ServletUtils.BTN_ANNULER %>').style.visibility = 'visible';
		document.getElementById('<%= ServletUtils.BTN_INSCRIPTION %>').style.visibility = 'visible';
		document.getElementById('<%= ServletUtils.BTN_ENREGISTRER %>').style.display = 'none';
		document.getElementById('<%= ServletUtils.BTN_SUPPRIMER %>').style.display = 'none';
	<%}%>
		
	</script>
</body>
</html>