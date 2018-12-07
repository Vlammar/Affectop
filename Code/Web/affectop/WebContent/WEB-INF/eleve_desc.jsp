<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Description</title>
	<style>
		<%@ include file="css/styleEleve.css"%>
	</style>

	<%@ include file="menu_eleve.jsp" %>
</head>
<body>  
        <section>
                <h2>Description des options</h2>
        </section>
        
        <ul>
	        <c:forEach var="option"  items="${ options }" varStatus="status">
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
			   <a href="eleve_choix">Choisir ses options</a>
			  </p>
			</div>
        </footer>
        
    </body>
</html>

