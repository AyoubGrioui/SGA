package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Don;
import com.sga.entities.DonneurPhysique;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/listeDon" )
public class ListeDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_LISTE_DON    = "/WEB-INF/listeDonPage.jsp";
    public static final String ATT_DONLIST="donList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	RepositoryFactory repositoryFactory =new RepositoryFactory();
        Repository repository =repositoryFactory.getDepenseRepository();
        List<Don> donList=repository.getAll();
    	
    	request.setAttribute(ATT_DONLIST, donList);

        this.getServletContext().getRequestDispatcher( VUE_LISTE_DON ).forward( request, response );
    }
    
}