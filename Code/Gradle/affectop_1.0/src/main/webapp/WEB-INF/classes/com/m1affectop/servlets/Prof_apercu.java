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
 * Servlet implementation class Prof_apercu
 */
@WebServlet("/Prof_apercu")
public class Prof_apercu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_apercu() {
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
        
		List<Eleve> eleves = new ArrayList<>();
		
		eleves.add(new Eleve("Jean1", "Martin1", "jean.martin1@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean2", "Martin2", "jean.martin2@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean3", "Martin3", "jean.martin3@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean4", "Martin4", "jean.martin4@etu.univ-amu.fr", null, null, null));
		eleves.add(new Eleve("Jean5", "Martin5", "jean.martin5@etu.univ-amu.fr", null, null, null));
		
		List<Option> optionPref = new ArrayList<>();
		optionPref.add(new Option("Name_test1","Description_test1", null));
		optionPref.add(new Option("Name_test2","Description_test1", null));
		optionPref.add(new Option("Name_test3","Description_test1", null));
		
		for (Eleve eleve : eleves) { 
			eleve.setOptionPref(optionPref);
		}
		eleves.get(3).setOptionPref(null);
        
        request.setAttribute("eleves", eleves);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_apercu.jsp").forward(request, response);
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
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_apercu.jsp").forward(request, response);
	}

}
