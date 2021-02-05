package com.sga.controllers;

import com.sga.entities.Depense;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.services.DepenseForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "/ajouterDepense" )
public class AjouterDepenseServlet extends HttpServlet {
    /**
     * 
     */
    private static final long  serialVersionUID    = 1L;
    public static final String VUE_AJOUTER_DEPENSE = "/WEB-INF/ajouterDepensePage.jsp";
    public static final String ATT_DEPENSEFORM = "depenseForm";
    public static final String ATT_DEPENSE = "depense";
    private static final String ATT_LIST_STRUCTURE = "structureList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {

        HibernateStructurePersister structurePersister =new HibernateStructurePersister();
        List<Structure> structureList = structurePersister.getAll();

        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DEPENSE ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        DepenseForm depenseForm = new DepenseForm();
        Depense depense = depenseForm.creerDepense(request);
        
        request.setAttribute(ATT_DEPENSEFORM,depenseForm);
        request.setAttribute(ATT_DEPENSE,depense);

        
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_DEPENSE ).forward( request, response );

    }

}
