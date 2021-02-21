package com.sga.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Adherent;
import com.sga.services.AdherentForm;


@WebServlet("/ajouterAdherent")
public class AjouterAdherentServlet extends HttpServlet {
	/**
     * 
     */
    private static final long  serialVersionUID     = 1L;
    public static final String VUE_AJOUTER_ADHERENT = "/WEB-INF/ajouterAdherent.jsp";
    public static final String ATT_ERREURS ="erreurs";
    public static final String ATT_ADHERENT="adherent";
    private static final String SUCCESS_MSG = "successMsg";
    private static final String ERREUR_MSG = "erreurMsg";
    
    
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {        
        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_ADHERENT ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    	AdherentForm adherentForm = new AdherentForm();
        Adherent adherent = adherentForm.creerAdherent(request);

        Map<String, String> erreurs = adherentForm.getErreurs();
        
        String successMsg = null;
        String erreurMsg = null;
         
         if(!erreurs.isEmpty() )
         {
        	if(adherentForm.getErrorMessage()==null) 
         		erreurMsg = "Veuillez vérifier les champs saisies.";
        	else
        		erreurMsg=adherentForm.getErrorMessage();

         }else 
         {
         	successMsg ="L'adhérent a été bien enregistré.";
         }
         
        request.setAttribute(ERREUR_MSG, erreurMsg);
        request.setAttribute(SUCCESS_MSG, successMsg);        
        request.setAttribute(ATT_ERREURS,erreurs);
        request.setAttribute(ATT_ADHERENT,adherent);
        
        this.getServletContext().getRequestDispatcher(VUE_AJOUTER_ADHERENT).forward( request , response );
    }

}
