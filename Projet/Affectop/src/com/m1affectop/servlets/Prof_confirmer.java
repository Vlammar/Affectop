package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.utils.TxtToMail;
import Calcul.Calcul.bean.Option;
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
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
		
		List<Option> options = new ArrayList<>();
		List<Student> eleves = new ArrayList<>();
		
		Student eleve1 = new Student(null, null, 0);
		Student eleve2 = new Student(null, null, 0);
		
		eleve1.setNomPrenom("Jean1", "Martin1");
		eleve1.setMail("jean.martin1@etu.univ-amu.fr");
		eleve2.setNomPrenom("Jean2", "Martin2");
		eleve2.setMail("jean.martin2@etu.univ-amu.fr");
		
		eleves.add(eleve1);
		eleves.add(eleve2);
				
		Option option1 = new Option(0, "Option test 1", 0);
		option1.setDescription("Lorem ipsum");
		Option option2 = new Option(0, "Option test 2", 0);
		option2.setDescription("Lorem ipsum");
		Option option3 = new Option(0, "Option test 3", 0);
		option3.setDescription("Lorem ipsum");
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
        
        request.setAttribute("options", options);
        request.setAttribute("eleves", eleves);
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
		
		List<Option> options = new ArrayList<>();
		List<Student> eleves = new ArrayList<>();
		
		Student eleve1 = new Student(null, null, 0);
		Student eleve2 = new Student(null, null, 0);
		
		eleve1.setNomPrenom("Jean1", "Martin1");
		eleve1.setMail("jean.martin1@etu.univ-amu.fr");
		eleve2.setNomPrenom("Jean2", "Martin2");
		eleve2.setMail("jean.martin2@etu.univ-amu.fr");
		
		eleves.add(eleve1);
		eleves.add(eleve2);
				
		Option option1 = new Option(0, "Option test 1", 0);
		option1.setDescription("Lorem ipsum");
		Option option2 = new Option(0, "Option test 2", 0);
		option2.setDescription("Lorem ipsum");
		Option option3 = new Option(0, "Option test 3", 0);
		option3.setDescription("Lorem ipsum");
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
        
        request.setAttribute("options", options);
        request.setAttribute("eleves", eleves);
		
		String mail = request.getParameter("mail");
		
		Map<String, ArrayList<String>> res = new HashMap<>();
		
		res = TxtToMail.tagChecker(mail);
		
		ArrayList<String> result = res.get("Invalid");
		
		request.setAttribute("result", result);
		
		request.setAttribute("mail", mail);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

}
