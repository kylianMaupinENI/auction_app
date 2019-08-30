INSERT INTO CATEGORIES(libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES(libelle) VALUES ('Ameublement');
INSERT INTO CATEGORIES(libelle) VALUES ('Vêtement');
INSERT INTO CATEGORIES(libelle) VALUES ('Sport et loisirs');

INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) 
VALUES('cvoyance','Claire','VOYANCE','cvoyance@gmail.com','0643210698','2 rue des lilas','34270','Saint-Jean de Cuculles','Azerty12345!',40000000,0);
INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) 
VALUES('ssonsec','Sophie','SONSEC','ssonsec@gmail.com','07643988','4 avenue foch','51290','Saint-Remy','Azerty12345!',30000000,0);
INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) 
VALUES('juzzi','Jacques','UZZI','juzzi@gmail.com','0643281408','12 rue des ','70100','Beaujeu-Saint-Vallier','Azerty12345!',38000000,0);

INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('PC portable','Ordinateur portable professionnel','20190801','20190820',1000,NULL,1,1);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('Armoire en chêne','Armoire en chêne massif de 1896','20190405','20190503',600,NULL,2,1);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('Jean taille 38','Jean de taille 38','20191024','20191125',20,NULL,3,2);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('VTT Rockrrider 16 pouces','VTT Rockrrider de 16 pouces','20191002','20191020',300,NULL,2,4);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('PC gamer','Ordinateur pour jouer','20190801','20190820',1500,NULL,1,3);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('Meuble IKEA','Meuble IKEA neuf','20190405','20190503',40,NULL,2,1);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('Chemise blanche taille M','Chemise blanche de taille M','20191024','20191125',30,NULL,3,2);
INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) 
VALUES ('Chaussures running 45','Chaussures de running taille 45','20191002','20191020',40,NULL,3,4);

INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (1,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (2,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (3,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (4,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (5,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (6,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (7,'2 rue des lillas','35000','Rennes');
INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES (8,'2 rue des lillas','35000','Rennes');
