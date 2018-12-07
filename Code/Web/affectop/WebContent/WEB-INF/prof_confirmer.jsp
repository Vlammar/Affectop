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
		<h2>Récapitulatif</h2>
	</section>
        
        
    <h2>Liste des élèves :</h2>    
    
    <table>
    		<tr>
    			<td>Prénom</td>
    			<td>Nom</td>
    			<td>email</td>
    		</tr>
	    <c:forEach var="eleve"  items="${ eleves }">
	       <tr>
	         <td class="eleve">
	           	<c:out value="${ eleve.nom }" />	
	         </td>
	       	 <td>
		        <c:out value="${ eleve.prenom }" />
	         </td>
	         <td>
		        <c:out value="${ eleve.adresseMail }" />
	         </td>
	       </tr>
	    </c:forEach>  
   	</table>     
        
    <h2>Liste des options :</h2>    
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
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_valider">Valider</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_option">Retour</a>
			</p>
		</div>
	</footer>
</body>
</html>