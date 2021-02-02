package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Depense;
import com.sga.services.DepenseForm;

@WebServlet( "/ajouterDepense" )
public class AjouterDepenseServlet extends HttpServlet {
    /**
     * 
     */
    private static final long  serialVersionUID    = 1L;
    public static final String VUE_AJOUTER_DEPENSE = "/WEB-INF/ajouterDepensePage.jsp";
    public static final String ATT_DEPENSEFORM = "depenseForm";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DEPENSE ).forward( request, response );
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepenseForm depenseForm = new DepenseForm();
        Depense depense = depenseForm.creerDepense(request);


        request.setAttribute(ATT_DEPENSEFORM,depenseForm);

}

}
