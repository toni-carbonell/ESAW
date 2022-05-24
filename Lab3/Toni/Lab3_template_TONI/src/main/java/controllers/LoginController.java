package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.Login;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("LoginController: ");
		
		Login login = new Login();
		
	    try {
			
	    	BeanUtils.populate(login, request.getParameterMap());
	    	ManageUsers manager = new ManageUsers();
			 //!!!!!!!!!!!!!!!!!!!!
	    	//!!!!!!!!!!!!!!!!!!!!!!
	    	//!!!!!!!!!!!!!!!!!!!!!
	    	//Aquí se tendrá que verificar con el servidor que el mail y la contraseña (bueno el hash) coincidan
	    	//Si se verifica, es complete etc vemos como estamos completando el mail y tal en la session, si le damos a ejecutar en el MainController,
	    	//ahí ya sí que nos redirigiría al ViewLoginDone y tal 
	    	
	    	//Entonces no nos basta con que el login "is complete"!! La contraseña tiene que ser correcta también
	    	//if (login.isComplete() and login.isCorrectPwd()) then ...
	    	if (login.isComplete() && manager.isCorrectPwd(login.getMail(), login.getPwd())) {
		    	
	    		System.out.println("login OK, forwarding to ViewLoginDone ");
		    	HttpSession session = request.getSession();
		    	session.setAttribute("mail",login.getMail());
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
			    dispatcher.forward(request, response);
			    
		    } 
	    	else if (login.isComplete()) //Eso es que la contraseña está mal... 
	    	{
				System.out.println("wrong password, forwarding to ViewLoginForm ");
			    request.setAttribute("login",login);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			    dispatcher.forward(request, response);		
	    	}
			else {
		     
				System.out.println("user is not logged, forwarding to ViewLoginForm ");
			    request.setAttribute("login",login);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			    dispatcher.forward(request, response);
		    	
		    }
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

