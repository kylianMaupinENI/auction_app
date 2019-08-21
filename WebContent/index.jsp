<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>ENI-Encheres</title>
</head>
<body>
	<!-- DEBUT HAUT DE PAGE -->
	<%@include file="enteteDeco.jsp"%>
	<!-- FIN HAUT DE PAGE -->

	<div class="container-fluid">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 sidenav">
			<div class="text-center">
				<h1>Liste des enchéres</h1>
			</div>
			<div id="container">
				<div class="col-sm-6 sidenav" id="blockFiltreIndex">
					<form action="/action_page.php">
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
								<option>Sport</option>
								<option>Loisirs</option>
							</select>
						</div>
					</form>
				</div>
				<div class="col-sm-6 sidenav" id="boutonRechercher">
					<a href="#" class="btn btn-primary btn-user btn-block"
						id="boutonRechercher"> Rechercher </a>
				</div>
			</div>
		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>

	<!-- DEBUT BAS DE PAGE -->
	<%@include file="basDePage.jsp"%>
	<!-- FIN BAS DE PAGE -->
</body>
</html>

<style>
#container{
	border:solid blue;
	width: 100%;
}
#search {
	width: 25%;
}

#blockFiltreIndex{
	width: 50%
}

#blockCategorieIndex {
	margin-top: 2%;
	width: 55%;
}

#categorieIndex {
	display: inline-block;
}

#SelectIndex {
	width: 40%;
	display: inline-block;
}

#boutonRechercher{
	width: 150px;
	float: right;
}
</style>