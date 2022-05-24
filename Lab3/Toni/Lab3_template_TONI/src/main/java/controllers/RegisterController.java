package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   System.out.print("RegisterController: ");
		
	   try {
	
		   User user = new User();
		   BeanUtils.populate(user, request.getParameterMap());
		   ManageUsers manager = new ManageUsers();
		
		   if (manager.isComplete(user) && !manager.mailExists(user.getMail())) { //if the mail doesn't exist yet
			   
			   
			   	user.setSalt(); //Setting the users salt
				user.setHashtag(); //Setting the users hashtag!
				System.out.println("Model is complete");
				System.out.println(user.getUser()+ user.getMail() + user.encriptPwd() + user.getGender() + user.getSalt() + user.getHashtag());
				
				//Adding the user
				manager.addUser(user.getUser(), user.getMail(), user.encriptPwd(), user.getGender(), user.getSalt(), user.getHashtag());
				if (manager.getSqlException() == false) {
					manager.finalize();
					System.out.println(" user ok, registering and forwarding to ViewLoginForm");
					RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
					dispatcher.forward(request, response);
				}
				
		   
		   } 
		   else if (manager.isComplete(user)) {
			   System.out.println("Everything ok but mail already exists");
			   manager.activateMailExists(); //activating mail exists property that will go to the jsp for error handling
			   request.setAttribute("manager", manager);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response);
		   }
		   else {
		
			   System.out.println(" forwarding to ViewRegisterForm");
			   request.setAttribute("user",user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
