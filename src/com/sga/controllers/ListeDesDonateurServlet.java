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
@WebServlet( "/listeDesDonateurs" )
public class ListeDesDonateurServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID       = 1L;
    public static final String VUE_LISTE_DES_DONATEUR = "/WEB-INF/listeDesDonateurs.jsp";
    public static final String ATT_DONATEURSLIST="donateurList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
        RepositoryFactory repositoryFactory =new RepositoryFactory();
        Repository repository =repositoryFactory.getDepenseRepository();
        List<DonneurPhysique> donateurPhysiqueList=repository.getAll();
        List<DonneurMoral> donateurMoraleList=repository.getAll();
        List<Donneur> donateurList = null;
        
        for (DonneurPhysique donateur: donateurPhysiqueList) {
			donateurList.add(donateur);
		}
        
        for (DonneurMoral donateur : donateurMoraleList) {
			donateurList.add(donateur);
		}
        
        request.setAttribute(ATT_DONATEURSLIST, donateurList);

        this.getServletContext().getRequestDispatcher( VUE_LISTE_DES_DONATEUR ).forward( request, response );
    }

}
