package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import models.User;
import utils.MD5;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); //we only instantiate the object if the session was created before!!
		//la cuesti�n es d�nde crear la sesi�n? ... Entiendo que al hacer el Login ;) Eso ser� en el LoginController
		//En el LoginController si el mail y contrase�a son correctos crearemos una sesi�n con el mail 

		
		if (session==null || session.getAttribute("mail")==null) { //en la sesi�n pondremos el atributo mail
			System.out.println("MainController: NO active session has been found,");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewRegisterForm.jsp");
		}
		else {
			System.out.println("Main Controller: active session has been found,");
			request.setAttribute("menu","ViewMenuLogged.jsp");
			request.setAttribute("content","ViewLoginDone.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); //se ejecuta el index, el request tendr� dentro otras p�ginas web por si las quieres llamar y cargar desde el index
		dispatcher.forward(request, response);	
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

