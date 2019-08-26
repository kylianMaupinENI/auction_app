<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/cssAccueil.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>ENI-Encheres</title>
</head>
<body>
	<!-- DEBUT ENTETE -->
	<%@include file="entete.jsp"%>
	<!-- FIN ENTETE -->

	<!-- DEBUT CORPS -->
	<div class="container-fluid text-center">
		<div class="row content ">
			<h1>Liste des enchères</h1>
			<div class="col-md-3 sidenav"></div>
			<div class="col-md-4  sidenav " id="block1Accueil">
				<form>
					<input type="text" id="rechercher"
						placeholder="Le nom de l'article contient" name="search">
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
					<div class="form-group">
						<label for="exampleFormControlSelect1">Catégories</label> <select
							class="form-control" id="selectCategoriesAccueilDeco">
							<option>Toutes</option>
							<option>Informatique</option>
							<option>Ameublement</option>
							<option>Vêtement</option>
							<option>Sport</option>
							<option>Loisirs</option>
						</select>
					</div>
				</form>
			</div>
			<div class="col-md-4 sidenav">
				<button type="submit" class="btn btn-default" id="boutonAccueilDeco">Rechercher</button>
			</div>
			<div class="col-md-3 sidenav"></div>
		</div>

		<div class="row content" id="block2AccueilDeco">
			<div class="col-sm-6 sidenav">
				<h3>tous les objets</h3>
			</div>
			<div class="col-sm-6 sidenav">
				<h3>tous les objets</h3>
			</div>
		</div>
	</div>
	<!-- FIN CORPS -->

	<!-- DEBUT ENTETE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN ENTETE -->

</body>
</html>