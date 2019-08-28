
<%@page import="fr.eni.encheres.servlets.ServletUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
</head>

<body>
	<h1>CREATION</h1>

	<form action="<%= ServletUtils.NOUVELLE_VENTE %>" method="post">
		<label>Article : </label> <input type="text" name="nom_vente"
			id="nom_vente" placeholder="Fauteuil" /> <br>
		<br> <label>Description : </label> <input type="text"
			name="description_vente" id="description_vente"
			placeholder="Description" /> <br>
		<br> <label for="exampleFormControlSelect1">Catégories</label> <select
			class="form-control" id="selectCategoriesAccueilDeco"
			name="selectCategoriesAccueilDeco">
			<option>Toutes</option>
			<option>Informatique</option>
			<option>Ameublement</option>
			<option>Vêtement</option>
			<option>Sport et loisirs</option>
		</select> <br>
		<br> <label>Mise à prix : </label> <input type="text"
			name="prix_initial" id="prix_initial" placeholder="100" /> <br>
		<br> <label>Debut de l'enchere : </label> <input type="date"
			name="date_debut_enchere" id="date_debut_enchere"
			placeholder="10/08/2018" /> <br>
		<br> <label>Fin de l'enchere : </label> <input type="date"
			name="date_fin_enchere" id="date_fin_enchere"
			placeholder="01/09/2018" /> <br>
		<br> <label>Rue : </label> <input type="text"
			name="rue_proprietaire" id="rue_proprietaire"
			placeholder="21 rue de la liberté" /> <br>
		<br> <label>Code postal :</label> <input type="text"
			name="code_postal_proprietaire" id="code_postal_proprietaire"
			placeholder="35000" /> <br>
		<br> <label>Ville : </label> <input type="text"
			name="ville_proprietaire" id="ville_proprietaire"
			placeholder="Rennes" /> <br>
		<br> <br>
		<input type="submit" value="Créer">
	</form>
</body>
</html>