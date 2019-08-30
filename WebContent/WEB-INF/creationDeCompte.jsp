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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>

<body>

	<%
		HttpSession sess = request.getSession();
		Utilisateur u = (Utilisateur) sess.getAttribute("sessionUtilisateur");
	%>
	<!-- DEBUT ENTETE -->
	<!-- Outer Row -->
	<%
		String actionAFaire = null;
	%>
	<c:if test="${sessionScope.sessionUtilisateur == null}">
		<%
			actionAFaire = ServletUtils.INSCRIPTION;
		%>
	</c:if>
	<c:if test="${sessionScope.sessionUtilisateur != null}">
		<%
			actionAFaire = ServletUtils.MODIFIER_PROFIL;
		%>
	</c:if>
	<form class="user" id="formCreationDeCompte" method="post"
		action="<%=request.getContextPath() + actionAFaire%>">
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">

						<div class="row">
							<div class="col-lg-6 ">

								<div class="text-right">
									<c:if test="${sessionScope.sessionUtilisateur == null}">
										<h4 class="h4 text-center text-gray-900 mb-4 titreProfil">Création de
											compte</h4>
									</c:if>
									<c:if test="${sessionScope.sessionUtilisateur != null}">
										<h4 class="h4 text-center text-gray-900 mb-4 titreProfil">Modification de compte</h4>
									</c:if>

								</div>
								<%
									List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
									if (listeCodesErreur != null) {
								%>
								<%
									for (int codeErreur : listeCodesErreur) {
								%>
								<p style="color: red";><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
								<%
									}
									}
								%>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 ">
								<div class="p-5" id="block1Creation">
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_PSEUDO_INSCRIPTION%>">Pseudo
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_PSEUDO_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_PSEUDO_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_NOM_INSCRIPTION%>">Nom
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_NOM_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_NOM_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_PRENOM_INSCRIPTION%>">Prenom
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_PRENOM_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_PRENOM_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_EMAIL_INSCRIPTION%>">Email
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_EMAIL_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_EMAIL_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_TELEPHONE_INSCRIPTION%>">Téléphone
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_TELEPHONE_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_TELEPHONE_INSCRIPTION%>">
									</div>
									<c:if test="${sessionScope.sessionUtilisateur != null}">
										<div id=<%=ServletUtils.CHAMP_CREDIT_INSCRIPTION%>>Crédit
											: ${sessionScope.sessionUtilisateur.credit}</div>
										<br>
									</c:if>
								</div>
							</div>
							<div class="col-lg-6" id="block2CreationDeCompte">
								<div class="p-5">
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_RUE_INSCRIPTION%>">Rue
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_RUE_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_RUE_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_CODE_POSTAL_INSCRIPTION%>">Code
											postal :</label> <input type="text"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_CODE_POSTAL_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_CODE_POSTAL_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_VILLE_INSCRIPTION%>">Ville
											:</label> <input type="text" class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_VILLE_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_VILLE_INSCRIPTION%>">
									</div>
									<c:if test="${sessionScope.sessionUtilisateur != null}">
										<div class="form-group">
											<label for="<%=ServletUtils.CHAMP_MOT_DE_PASSE_ACTUEL%>">
												Mot de passe actuel :</label> <input type="password"
												class="form-control form-control-user"
												id="<%=ServletUtils.CHAMP_MOT_DE_PASSE_ACTUEL%>"
												name="<%=ServletUtils.CHAMP_MOT_DE_PASSE_ACTUEL%>">
										</div>
									</c:if>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_MOT_DE_PASSE_INSCRIPTION%>">
											Mot de passe :</label> <input type="password"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_MOT_DE_PASSE_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_MOT_DE_PASSE_INSCRIPTION%>">
									</div>
									<div class="form-group">
										<label for="<%=ServletUtils.CHAMP_CONFIRMATION_INSCRIPTION%>">
											Confirmation : </label> <input type="password"
											class="form-control form-control-user"
											id="<%=ServletUtils.CHAMP_CONFIRMATION_INSCRIPTION%>"
											name="<%=ServletUtils.CHAMP_CONFIRMATION_INSCRIPTION%>">
									</div>
								</div>
							</div>
						</div>


						<div class="row" id="blockBoutonCreationDeCompte">
							<div class="col-sm-2"></div>
							<div class="col-sm-4">
								<c:if test="${sessionScope.sessionUtilisateur == null}">
									<div class="text-left form-group"
										id="<%=ServletUtils.BTN_ANNULER%>">
										<a href="<%=request.getContextPath() + ServletUtils.ACCUEIL%>"
											class="btn btn-danger btn-user btn-block">Annuler </a>
									</div>
								</c:if>
								<c:if test="${sessionScope.sessionUtilisateur != null}">
									<div class="text-left form-group"
										id="<%=ServletUtils.BTN_SUPPRIMER%>">
										<input type="hidden"
											value="${sessionScope.sessionUtilisateur.pseudo}"
											name="pseudoUtilisateur"> <input type="submit"
											class="btn  btn-danger  btn-user btn-block"
											value="Supprimer mon compte" name="ChoixBouton">
									</div>
								</c:if>
							</div>
							<div class="col-sm-4">
								<c:if test="${sessionScope.sessionUtilisateur == null}">
									<div class="text-right form-group"
										id="<%=ServletUtils.BTN_INSCRIPTION%>">
										<input type="submit" name="ChoixBouton"
											class="btn btn-primary btn-user btn-block" value="S'inscrire">
									</div>
								</c:if>
								<c:if test="${sessionScope.sessionUtilisateur != null}">
									<div class="text-right form-group" name=""
										id="<%=ServletUtils.BTN_ENREGISTRER%>">
										<input type="submit" name="ChoixBouton"
											class="btn btn-primary btn-user btn-block"
											value="Enregistrer">
									</div>
								</c:if>
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

</body>
</html>