package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Don;
import com.sga.entities.Donneur;
import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.repositories.HibernateDonneurPersister;
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
    public static final String ATT_DONATEURSLIST="donateurList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {


        HibernateDonneurPersister donneurPersister = new HibernateDonneurPersister();
        List<Donneur> donneurList = donneurPersister.getAll();

        request.setAttribute(ATT_DONATEURSLIST, donneurList);

        this.getServletContext().getRequestDispatcher( VUE_LISTE_DONATEUR ).forward( request, response );
    }
}