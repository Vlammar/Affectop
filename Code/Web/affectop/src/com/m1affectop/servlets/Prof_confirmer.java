package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.m1affectop.beans.Eleve;
import com.m1affectop.beans.Option;
/**
 * Servlet implementation class Prof_confirmer
 */
@WebServlet("/Prof_confirmer")
public class Prof_confirmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_confirmer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Option> options = new ArrayList<>();
		List<Eleve> eleves = new ArrayList<>();
		
		eleves.add(new Eleve("Jean1", "Martin1", "jean.martin1@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean2", "Martin2", "jean.martin2@etu.univ-amu.fr", null, null, null));
		
		options.add(new Option("Name_test1","Description_test1", null));
		options.add(new Option("Name_test2","Description_test2", null));
		options.add(new Option("Name_test3","Description_test3", null));
        
        request.setAttribute("options", options);
        request.setAttribute("eleves", eleves);
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

}
