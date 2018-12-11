package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.bean.Option;

/**
 * Servlet implementation class Prof_option
 */
@WebServlet("/Prof_option")
public class Prof_option extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_option() {
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_option.jsp").forward(request, response);
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

        String group = request.getParameter("group");
        
        HttpSession session = request.getSession() ;
        
        if(group != null) {
	        session.setAttribute("group.session", group) ;
        }
        
        if(group == null) {
        	group = (String)session.getAttribute("group.session");
        }
        
        request.setAttribute("group", group);
        
		List<Option> options = new ArrayList<>();
		List<String> groupes = new ArrayList<>();
		
		for(int i = 1; i <= Integer.parseInt(group); i++)
			groupes.add(request.getParameter("groupe_" + i));
		
		//options.get(0).setMail_prof(request.getParameter("mail_prof"));
		
		Option option1 = new Option(0, "Option test 1", 0);
		option1.setDescription("Lorem ipsum");
		option1.setGroupes(groupes);
		option1.setMail_prof(request.getParameter("mail_prof"));
		options.add(option1);
        
        request.setAttribute("options", options);
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_option.jsp").forward(request, response);
	}

}
