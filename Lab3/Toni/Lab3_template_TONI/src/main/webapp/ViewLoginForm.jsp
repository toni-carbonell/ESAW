<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div>
<form action="LoginController" method="POST">
  		<label for="mail"> 
  		<span> Mail:</span><br>
  		<input type="email" id="mail" name="mail" placeholder="Mail" value="${login.mail}" required>
  		
  		
  		</label><br>
  		<label for="pwd"> Password: </label><br>
  		<input type="password" id="pwd" name="pwd" placeholder="Password" value="${login.pwd}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"><br>
  		
  
  		<br><br>
  		<input class="botons" type="submit" value="Submit">
</form>
</div>
  		