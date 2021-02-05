package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Adherent;

@WebServlet("/indexHandler")
public class IndexHandlerServlet extends HttpServlet
{
	
    public static final String VUE_DASHBOARD_PRESIDENT = "/WEB-INF/indexPresident.jsp";
    public static final String VUE_DASHBOARD_SECRETAIRE = "/WEB-INF/indexSecretaire.jsp";
    public static final String VUE_DASHBOARD_DONATEUR = "/WEB-INF/indexDonateur.jsp";
    public static final String VUE_LOGIN        = "/WEB-INF/homePage.jsp";
    public static final String ATT_SESSION_USER = "user";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session=req.getSession();
		Adherent user=(Adherent)session.getAttribute(ATT_SESSION_USER);
		
		if(user != null)
		{
			String role = user.getLigneFonction().getFonction().getRole();
			switch (role)
			{
			case "Président(e)" : 
				this.getServletContext().getRequestDispatcher(VUE_DASHBOARD_PRESIDENT).forward(req, resp);break;
			case "Secretaire" : 
				this.getServletContext().getRequestDispatcher(VUE_DASHBOARD_SECRETAIRE).forward(req, resp);break;
			case "Donateur" : 
				this.getServletContext().getRequestDispatcher(VUE_DASHBOARD_DONATEUR).forward(req, resp);break;
			}
		}
		
		this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(req, resp);
	}
	
}
