package com.m1affectop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m1affectop.beans.BaseReader;

/**
 * Servlet implementation class Eleve_accueil
 */
@WebServlet("/Eleve_accueil")
public class Eleve_accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eleve_accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.tagRequest("name", token);
        request.setAttribute("name", name);
        
        String firstname = basereader.tagRequest("firstName", token);
        request.setAttribute("firstname", firstname);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.tagRequest("name", token);
        request.setAttribute("name", name);
        
        String firstname = basereader.tagRequest("firstName", token);
        request.setAttribute("firstname", firstname);

		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_accueil.jsp").forward(request, response);
	}

}
