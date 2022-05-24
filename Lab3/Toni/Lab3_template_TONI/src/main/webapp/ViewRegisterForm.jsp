<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
<form action="RegisterController" method="post">
  		<label for="user"> User name:</label><br>
  		<input type="text" id="user" name="user" placeholder="Name" value="${model.user}" required><br>
  		
  		<label for="mail"> 
  		<span> Mail:</span><br>
  		<input type="email" id="mail" name="mail" placeholder="Mail" value="${model.mail}" required>
  		<span class="error"></span>
  		<c:if test = "${manager.mailExists}">
		<li> This mail is already registered </li>
		<script> 
		document.getElementById("mail").classList.add("wrongMail");
		</script>
		</c:if>
  		
  		
  		</label><br>
  		<label for="pwd1"> Password: </label><br>
  		<input type="password" id="pwd1" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"><br>
  		
  		<label for="pwd2"> Confirm Password: </label><br>
  		<input type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"><br>
  		<c:if test = "${model.error[3]}">
		<li> Passwords must coincide </li>
		</c:if>
  		
  		
  		<label for="gender"> Choose Gender: </label> <br>
  		<select id="gender" name="gender" required>
  			<option value="Male">Male</option>
  			<option value="Male">Female</option>
  			<option value="Male">Other</option>
  		</select> <br>
  
  		<br><br>
  		<input class="botons" type="submit" value="Submit">
	</form>
</div>
<script>

//There are many ways to pick a DOM node; here we get the form itself and the email
//input box, as well as the span element into which we will place the error message.
const form  = document.getElementsByTagName('form')[0];
const email = document.getElementById('mail');
const emailError = document.querySelector('#mail + span.error');

email.addEventListener('input', function (event) {
	// Each time the user types something, we check if the
	// form fields are valid.
	if (email.validity.valid) {
	 // In case there is an error message visible, if the field
	 // is valid, we remove the error message.
	 emailError.innerHTML = ""; // Reset the content of the message
	} else {
	 // If there is still an error, show the correct error
	 showError();
	}
});

form.addEventListener('submit', function (event) {
	// if the email field is valid, we let the form submit
	
	if(!email.validity.valid) {
	 // If it isn't, we display an appropriate error message
	 showError();
	 // Then we prevent the form from being sent by canceling the event
	 event.preventDefault();
	}
});

function showError() {
	if(email.validity.valueMissing) {
	 // If the field is empty
	 // display the following error message.
	 emailError.textContent = 'You need to enter an e-mail address.';
	} else if(email.validity.typeMismatch) {
	 // If the field doesn't contain an email address
	 // display the following error message.
	 emailError.textContent = 'Entered value needs to be an e-mail address.';
	}
}

</script>