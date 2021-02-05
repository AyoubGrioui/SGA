package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Donneur;
import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/listeDonateur" )
public class ListeDonateurServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID   = 1L;
    public static final String VUE_LISTE_DONATEUR = "/WEB-INF/listeDonateurPage.jsp";
    public static final String ATT_DONATEUR="donateur";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	
        RepositoryFactory repositoryFactory =new RepositoryFactory();
        Repository repository =repositoryFactory.getDepenseRepository();
        Donneur donateur= repository.read();//++++++++++
        
        request.setAttribute(ATT_DONATEUR, donateur);
        
        this.getServletContext().getRequestDispatcher( VUE_LISTE_DONATEUR ).forward( request, response );
    }

}