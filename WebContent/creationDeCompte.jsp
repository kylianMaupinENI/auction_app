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
					
						<!-- VERSION PC-->
						<div class="row" id="versionPc">
							<div class="col-lg-6 ">
								<div class="text-left">
									<h1 class="h4 text-gray-900 mb-4">ENI-Enchères</h1>
								</div>
								<div class="p-5" id="block1Creation">
									<div class="form-group">
										<label for="pseudoCreationCompte">Pseudo :</label> <input
											type="text" class="form-control form-control-user"
											id="pseudoCreationCompte">
									</div>
									<div class="form-group">
										<label for="prenomCreationCompte">Prénom :</label> <input
											type="text" class="form-control form-control-user"
											id="prenomCreationCompte">
									</div>
									<div class="form-group">
										<label for="telephoneCreationCompte">Téléphone :</label> <input
											type="text" class="form-control form-control-user"
											id="telephoneCreationCompte">
									</div>
									<div class="form-group">
										<label for="codePostalCreationCompte">Code Postal :</label> <input
											type="text" class="form-control form-control-user"
											id="codePostalCreationCompte">
									</div>
									<div class="form-group">
										<label for="mdpCreationCompte">Mot de passe :</label> <input
											type="password" class="form-control form-control-user"
											id="mdpCreationCompte">
									</div>
									<div class="text-right">
										<div class="form-group" id="boutonCree">
											<a href="index.jsp"
												class="btn btn-primary btn-user btn-block"> Créer </a>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="text-left">
									<h4 class="h4 text-gray-900 mb-4" id="titreProfil">Mon
										Profil</h4>
								</div>
								<div class="p-5">
									<div class="form-group">
										<label for="nomCreationCompte">Nom :</label> <input
											type="text" class="form-control form-control-user"
											id="nomCreationCompte">
									</div>
									<div class="form-group">
										<label for="emailCreationCompte">Email :</label> <input
											type="text" class="form-control form-control-user"
											id="emailCreationCompte">
									</div>
									<div class="form-group">
										<label for="rueCreationCompte">Rue :</label> <input
											type="text" class="form-control form-control-user"
											id="rueCreationCompte">
									</div>
									<div class="form-group">
										<label for="villeCreationCompte">Ville :</label> <input
											type="text" class="form-control form-control-user"
											id="villeCreationCompte">
									</div>
									<div class="form-group">
										<label for="confirmationMdpCreationCompte">Confirmation
											:</label> <input type="password"
											class="form-control form-control-user"
											id="confirmationMdpCreationCompte">
									</div>
									<div class="text-left">
										<div class="form-group" id="boutonAnnuler">
											<a href="index.jsp"
												class="btn btn-primary btn-user btn-block">Annuler </a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- VERSION MOBILE -->
	<div class="row" id="versionMobile">
		<div class="col-lg-6 ">
			<div class="text-left">
				<h1 class="h4 text-gray-900 mb-4">ENI-Enchères</h1>
			</div>
			<div class="text-center">
				<h4 class="h4 text-gray-900 mb-4" id="titreProfil">Créer un
					compte</h4>
			</div>
			<div class="p-5" id="block1Creation">
				<form class="user">
					<div class="form-group">
						<label for="pseudoCreationCompte">Pseudo :</label> <input
							type="text" class="form-control form-control-user"
							id="pseudoCreationCompte">
					</div>
					<div class="form-group">
						<label for="nomCreationCompte">Nom :</label> <input type="text"
							class="form-control form-control-user" id="nomCreationCompte">
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
					<div class="form-group">
						<label for="rueCreationCompte">Rue :</label> <input type="text"
							class="form-control form-control-user" id="rueCreationCompte">
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
						<label for="confirmationMdpCreationCompte">Confirmation :</label>
						<input type="password" class="form-control form-control-user"
							id="confirmationMdpCreationCompte">
					</div>
					<div class="containerCreation">
						<div class="form-group" id="boutonCreer">
							<a href="index.jsp" class="btn btn-primary btn-user btn-block">
								Créer </a>
						</div>
						<div class="form-group" id="boutonAnnuler">
							<a href="index.jsp" class="btn btn-primary btn-user btn-block">Annuler
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

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
</body>
</html>