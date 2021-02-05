package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Don;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

@WebServlet( "/ajouterDon" )
public class AjouterDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_AJOUTER_DON  = "/WEB-INF/ajouterDonPage.jsp";
    public static final String ATT_DONLIST="donList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        RepositoryFactory repositoryFactory =new RepositoryFactory();
        Repository repository =repositoryFactory.getDepenseRepository();
        List<Don> donList=repository.getAll();
        
        request.setAttribute(ATT_DONLIST, donList);
    	
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DON ).forward( request, response );
    }
}
