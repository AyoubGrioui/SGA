package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Adherent;
import com.sga.repositories.HibernateAdherentPersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/detailAdherent" )
public class DetailAdherentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID    = 1L;
    public static final String VUE_DETAIL_ADHERENT = "/WEB-INF/detailAdherent.jsp";
    public static final String ATT_ADHERENT="adherent";
    public static final String ATT_SESSION_USER = "user";
    
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	HibernateAdherentPersister adherentPersister=new HibernateAdherentPersister();
    	
		HttpSession session=request.getSession();
		Adherent adherent=(Adherent) session.getAttribute(ATT_SESSION_USER);
		
		request.setAttribute(ATT_ADHERENT, adherent);
		

        this.getServletContext().getRequestDispatcher( VUE_DETAIL_ADHERENT ).forward( request, response );
    }

}
