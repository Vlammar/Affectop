package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m1affectop.beans.Option;
import com.m1affectop.beans.Utilisateur;

/**
 * Servlet implementation class Eleve_desc
 */
@WebServlet("/Eleve_desc")
public class Eleve_desc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eleve_desc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Option> options = new ArrayList<>();
		
		Option option = new Option("Option test", "Lorem ipsum dolor sit amet, consectetuer adipiscing\r\n" + 
				"elit. Sed non risus. Suspendisse lectus tortor,\r\n" + 
				"dignissim sit amet, adipiscing nec, ultricies sed,\r\n" + 
				"dolor.", null);
		
		Option option2 = new Option("Option test 2", "Lorem ipsum dolor sit amet, consectetuer adipiscing\r\n" + 
				"elit. Sed non risus. Suspendisse lectus tortor,\r\n" + 
				"dignissim sit amet, adipiscing nec, ultricies sed,\r\n" + 
				"dolor.", null);
		
		options.add(option);
		options.add(option2);
		request.setAttribute("options", options);

		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_desc.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_desc.jsp").forward(request, response);;
	}

}
