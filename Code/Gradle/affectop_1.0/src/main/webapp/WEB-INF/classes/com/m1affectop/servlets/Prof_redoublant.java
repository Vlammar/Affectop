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
import com.m1affectop.beans.Eleve;
import com.m1affectop.beans.Option;

/**
 * Servlet implementation class Prof_redoublant
 */
@WebServlet("/Prof_redoublant")
public class Prof_redoublant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_redoublant() {
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
		List<Eleve> eleves = new ArrayList<>();
		
		eleves.add(new Eleve("Jean1", "Martin1", "jean.martin1@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean2", "Martin2", "jean.martin2@etu.univ-amu.fr", null, null, null));
		
		options.add(new Option("Name_test1","Description_test1", null));
		options.add(new Option("Name_test2","Description_test2", null));
		options.add(new Option("Name_test3","Description_test3", null));
        
        request.setAttribute("options", options);
        request.setAttribute("eleves", eleves);
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_redoublant.jsp").forward(request, response);
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
		
		List<Option> options = new ArrayList<>();
		List<Eleve> eleves = new ArrayList<>();
		
		eleves.add(new Eleve("Jean1", "Martin1", "jean.martin1@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean2", "Martin2", "jean.martin2@etu.univ-amu.fr", null, null, null));
		
		options.add(new Option("Name_test1","Description_test1", null));
		options.add(new Option("Name_test2","Description_test2", null));
		options.add(new Option("Name_test3","Description_test3", null));
		
		String valide1_1 = request.getParameter("valide1_1");
		String valide1_2 = request.getParameter("valide1_2");
		String valide1_3 = request.getParameter("valide1_3");
		String valide2_1 = request.getParameter("valide2_1");
		String valide2_2 = request.getParameter("valide2_2");
		String valide2_3 = request.getParameter("valide2_3");
		
        
		request.setAttribute("valide1_1", valide1_1);
		request.setAttribute("valide1_2", valide1_2);
		request.setAttribute("valide1_3", valide1_3);
		request.setAttribute("valide2_1", valide2_1);
		request.setAttribute("valide2_2", valide2_2);
		request.setAttribute("valide2_3", valide2_3);
		
        request.setAttribute("options", options);
        request.setAttribute("eleves", eleves);
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_redoublant.jsp").forward(request, response);
	}

}
