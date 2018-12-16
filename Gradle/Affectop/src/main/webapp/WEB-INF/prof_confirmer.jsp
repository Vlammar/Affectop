<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Confirmation</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>
	<%@ include file="menu_eleve.jsp" %>
</head>
<body> 
	<%@ include file="progress.jsp" %>
	<script>
	$('.progress .bar').removeClass().addClass('bar');
		progress(4);
	</script>
	<div class= "content">
	<section>  
		<h2>Récapitulatif</h2>
	</section>
	
	<h2>Contenu du mail</h2> 
	</br>
	<form method="post" action="prof_confirmer?token=${ token }" id="mailform" class="mail">   
		<textarea rows="10" cols="150" name="mail" form="mailform" required="required">${ !empty mail ? mail : 'Ecrivez le contenu du mail...' }</textarea>
	    </br>
	    <input type="submit" value="Valider le mail"/>
    </form>
        
    <h2>Liste des élèves :</h2>    
    <table class="liste">
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
    <div class="listeop">  
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
   	</div>     
   	
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_valider?token=${ token }" onclick="return confirm('Etes vous sur de vouloir envoyer les mails au eleves ?')">Envoyer les e-mail au elèves</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_redoublant?token=${ token }">Retour</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>