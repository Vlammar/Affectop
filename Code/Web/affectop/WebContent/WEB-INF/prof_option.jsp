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
	<section>  
		<h2>Nom de la promotion</h2>
	</section>
        
    <ul>
	    <c:forEach var="option"  items="${ options }">
	       <li>
	         <p class="option">
	           	<c:out value="${ option.nom }" /> : </br>  	
	         </p>
	       	 <p>
		        Description : <c:out value="${ option.description }" />
	         </p>
	        </li>
	    </c:forEach>
   	</ul>        
        
    <form method="post" action="prof_option">
        <label for="nom">Nom : </br></label>
        <input type="text" name="nom" id="nom" />
        </br>
        <label for="description">Description : </br></label>
        <textarea cols="150" rows="5"name="description" form="description">Enter text here...</textarea>
        </br>    
        <input type="submit" value="Ajouter l'option"/>
    </form>
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_confirmer">Suivant</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_ajout">Retour</a>
			</p>
		</div>
	</footer>
</body>
</html>