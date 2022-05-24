<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/me.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 3 template </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

 <!-- AQUÍ FIQUEM LO DEL DARRER LAB QUE ESTAVA AL HEAD DEL CONSTRAINEDVALIDATIONHTML5.JSP --> 
<link rel="stylesheet" type="text/css" href="css/estil.css">

<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu", async function(event) { //si clicas a algo de una clase tipo menu (de lo creado con W3SS)
		//$('#content').load('ContentController',{content: $(this).attr('id')});
		const response = await fetch($(this).attr('id')); //this se refiere a donde hayas clicado, y el atribute id es el Controller : "<a class="menu w3-bar ..." id="RegisterController/LoginController" href=#> Registration </a> "
		$('#content').html(await response.text());
		//$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	$(document).on("submit","form", function(event) { //si haces un submit de la form...
		$('#content').load($(this).attr('action'),$(this).serialize()); //this es donde hayas hecho click y la acción es el servlet RegisterController : "<form action="RegisterController" method="POST">"
	    event.preventDefault();
	});
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-bar w3-red" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
	<!-- Begin Content --> 
	<div class="w3-container w3-card-4 w3-padding-24" id="content"> <!-- El id content es lo que se referencia desde el Ajax -->
	<jsp:include page="${content}" />
	</div>
	<!-- End Content -->
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>