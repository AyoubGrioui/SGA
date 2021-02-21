package com.sga.controllers;

import com.sga.entities.Depense;
import com.sga.services.DepenseForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/ajouterDepense" )
public class AjouterDepenseServlet extends HttpServlet {
    /**
     * 
     */
    private static final long  serialVersionUID    = 1L;
    public static final String VUE_AJOUTER_DEPENSE = "/WEB-INF/ajouterDepensePage.jsp";
    public static final String ATT_DEPENSEFORM = "depenseForm";
    public static final String ATT_DEPENSE = "depense";
    private static final String SUCCESS_MSG = "successMsg";
    private static final String ERREUR_MSG = "erreurMsg";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DEPENSE ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        DepenseForm depenseForm = new DepenseForm();
        Depense depense = depenseForm.creerDepense(request);
        
        String successMsg = null;
        String erreurMsg = null;
        
        if(depenseForm.getErreurs().isEmpty())
        {
        	successMsg ="La dépense a été bien enregistré.";
        }else {
        	erreurMsg = "Veuillez vérifier les champs saisies.";
        }
        
        request.setAttribute(ERREUR_MSG, erreurMsg);
        request.setAttribute(SUCCESS_MSG, successMsg);
        
        request.setAttribute(ATT_DEPENSEFORM,depenseForm);
        request.setAttribute(ATT_DEPENSE,depense);

        
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DEPENSE ).forward( request, response );

    }

}
