CREATE VIEW fonctionnaire_donne
AS 
SELECT idfonctionnaire AS 'Numfonctionnair',
		fonctionnaires.nom AS 'Nom',
        prenom AS 'Prenom',
        age AS 'Age',
        fonctionnaires.idfonction AS 'Numfonction',
        fonction.nom AS 'NomFonction',
        fonctionnaires.iddepartement AS 'NumDep',
        departement.nom AS 'NomDep'
FROM fonctionnaires , departement , fonction
WHERE fonctionnaires.idfonction=fonction.idfonction AND fonctionnaires.iddepartement = departement.iddepartement

CREATE VIEW pointage_donne
AS 
SELECT fonctionnaires.nom AS 'nom',
		jour AS 'jour',
        statut AS 'statut'
FROM pointage , fonctionnaires 
WHERE pointage.idfonctionnaire = fonctionnaires.idfonctionnaire