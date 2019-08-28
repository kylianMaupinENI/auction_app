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
<style>
form img {
	height: 150px;
}
</style>
</head>
<body>
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="entete.jsp"%>
	<!-- FIN HAUT DE PAGE -->

	<div class="container-fluid text-center ">
		<form>
			<div class="row content">
				<div class="col-sm-4 sidenav">
					<div>
						<label for="image_uploads"><p class="glyphicon glyphicon-camera" style="font-size: 25px; color : black; margin-right: "><p> Sélectionner une photo (PNG,
							JPG)</label> <input type="file" id="image_uploads" name="image_uploads"
							accept=".jpg, .jpeg, .png">
					</div>
					<div class="preview">
						<p>Aucun fichier sélectionné pour le moment</p>
					</div>
				</div>
				<div class="col-sm-6 text-left bg-gray-100"
					style="border-radius: 30px;">
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

					<!-- 					<div class="form-group"> -->
					<!-- 						<label for="fileVente">Photo :</label> <input type="file" -->
					<!-- 							id="fileVente" name="fileVente"> -->
					<!-- 					</div> -->
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

	<script>
		var input = document.querySelector('input');
		var preview = document.querySelector('.preview');

		input.style.opacity = 0;
		input.addEventListener('change', updateImageDisplay);
		function updateImageDisplay() {
			while (preview.firstChild) {
				preview.removeChild(preview.firstChild);
			}

			var curFiles = input.files;
			if (curFiles.length === 0) {
				var para = document.createElement('p');
				para.textContent = 'No files currently selected for upload';
				preview.appendChild(para);
			} else {
				var list = document.createElement('ol');
				preview.appendChild(list);
				for (var i = 0; i < curFiles.length; i++) {
					var listItem = document.createElement('li');
					var para = document.createElement('p');
					if (validFileType(curFiles[i])) {
						para.textContent = 'File name ' + curFiles[i].name
								+ ', file size '
								+ returnFileSize(curFiles[i].size) + '.';
						var image = document.createElement('img');
						image.src = window.URL.createObjectURL(curFiles[i]);

						listItem.appendChild(image);
						listItem.appendChild(para);

					} else {
						para.textContent = 'File name '
								+ curFiles[i].name
								+ ': Not a valid file type. Update your selection.';
						listItem.appendChild(para);
					}

					list.appendChild(listItem);
				}
			}
		}
		var fileTypes = [ 'image/jpeg', 'image/pjpeg', 'image/png' ]

		function validFileType(file) {
			for (var i = 0; i < fileTypes.length; i++) {
				if (file.type === fileTypes[i]) {
					return true;
				}
			}

			return false;
		}
		function returnFileSize(number) {
			if (number < 1024) {
				return number + ' octets';
			} else if (number >= 1024 && number < 1048576) {
				return (number / 1024).toFixed(1) + ' Ko';
			} else if (number >= 1048576) {
				return (number / 1048576).toFixed(1) + ' Mo';
			}
		}
	</script>
</body>
</html>
