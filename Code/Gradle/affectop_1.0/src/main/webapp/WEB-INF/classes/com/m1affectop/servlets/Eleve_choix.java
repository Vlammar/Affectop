package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m1affectop.beans.BaseReader;
import com.m1affectop.beans.Option;

/**
 * Servlet implementation class Eleve_choix
 */
@WebServlet("/Eleve_choix")
public class Eleve_choix extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eleve_choix() {
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
		
        List<Option> options = new ArrayList<>();
		
		Option option = new Option("Option test", null, null);
		
		Option option2 = new Option("Option test 2", null, null);
		
		Option option3 = new Option("Option test 3", null, null);
		
		Option option4 = new Option("Option test 4", null, null);
		
		Option option5 = new Option("Option test 5", null, null);
		
		options.add(option);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);

		request.setAttribute("options", options);
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
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
		
		List<String> choix = new ArrayList<>();
		List<Option> options = new ArrayList<>();
		
		Option option = new Option("Option test", null, null);
		
		Option option2 = new Option("Option test 2", null, null);
		
		Option option3 = new Option("Option test 3", null, null);
		
		Option option4 = new Option("Option test 4", null, null);
		
		Option option5 = new Option("Option test 5", null, null);
		
		options.add(option);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		for(int i = 0; i< options.size(); i++) {
			choix.add(request.getParameter("" + (i + 1) ));
		}
		
		request.setAttribute("choix", choix);
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
	}

}
