package com.sga.controllers;

import com.sga.entities.Adherent;
import com.sga.entities.LigneFonction;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.services.AdherentForm;
import com.sga.services.LigneFonctionForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/ajouterAdherent" )
public class AjouterAdherentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID     = 1L;
    public static final String VUE_AJOUTER_ADHERENT = "/WEB-INF/ajouterAdherent.jsp";
    public static final String ATT_ERREURS ="erreurs";
    public static final String ATT_ADHERENT="adherent";
    public static final String ATT_LIST_STRUCTURE ="StructureList";


    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HibernateStructurePersister structurePersister =new HibernateStructurePersister();
        List<Structure> structureList = structurePersister.getAll();

        request.setAttribute(ATT_LIST_STRUCTURE,structureList);
        
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_ADHERENT ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdherentForm adherentForm = new AdherentForm();
        Adherent adherent = adherentForm.creerAdherent(request);

        Map<String, String> erreurs = adherentForm.getErreurs();
        request.setAttribute(ATT_ERREURS,erreurs);
        request.setAttribute(ATT_ADHERENT,adherent);


        this.getServletContext().getRequestDispatcher(VUE_AJOUTER_ADHERENT).forward( request , response );
    }


}
