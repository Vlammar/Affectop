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
		progress(7);
	</script>
	<div class= "content">
	<section>  
		<h2>Edition des mails</h2>
	</section>

	<form method="post" action="prof_mail?token=${ token }" id="mailform" class="mail">   
		<h4>Mail pour les élèves</h4>
		
		<textarea rows="10" cols="150" name="mail_eleve" form="mailform" required="required">${ !empty mail_eleve ? mail_eleve : 'Ecrivez le contenu du mail...' }</textarea>
	    </br>
	    
	    <h4>Mail pour le secrétariat</h4>
	    
	    <textarea rows="10" cols="150" name="mail_secretariat" form="mailform" required="required">${ !empty mail_secretariat ? mail_secretariat : 'Ecrivez le contenu du mail...' }</textarea>
	    </br>
	    
	    <input type="submit" value="Valider les mails"/>
    </form>
        
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_final?token=${ token }" onclick="return confirm('Etes vous sur de vouloir envoyer les mails ?')">Envoyer les mails</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>