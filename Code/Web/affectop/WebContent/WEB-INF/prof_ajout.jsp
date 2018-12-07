<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Test</title>
	<style>
		<%@ include file="css/styleEleve.css"%>
	</style>
	
</head>
<body>   
	<p>Ajoutez un fichier excel dans le format suivant: </p>
	
	<table>
	  <tr>
	    <td>Nom</td>
	    <td>Prénom</td>
	    <td>Carte</td>
	    <td>Étape</td>
	    <td>Nom de l'étape</td>
	    <td>VET</td>
	    <td>Année</td>
	    <td>Courriel</td>
	  </tr>
	  <tr>
	    <td>Martin</td>
	    <td>Dupond</td>
	    <td>15019999</td>
	    <td>xxx999</td>
	    <td>AMU.Xx Informatique</td>
	    <td>Vxxx</td>
	    <td>xxxx</td>
	    <td>martin.dupond@etu.univ-amu.fr</td>
	  </tr>
	  <tr>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	    <td>...</td>
	  </tr>
	</table>

    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="prof_ajout" enctype="multipart/form-data">
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" />
    </form>
    

	<footer>
		<div class="bouton">
			<p>
				<a href="prof_option">Suivant</a>
			</p>
		</div>
	</footer>
</body>
</html>
