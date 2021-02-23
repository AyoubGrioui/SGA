package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Adherent;
import com.sga.repositories.HibernateAdherentPersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/listeDesAdherents" )
public class ListeDesAdherentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long   serialVersionUID        = 1L;
    public static final String  VUE_LISTE_DES_ADHERENTS = "/WEB-INF/listeDesAdherent.jsp";
    private static final String ATT_ADHERENTLIST        = "adherentList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();
        List<Adherent> adherentList = adherentPersister.getAll();

        request.setAttribute( ATT_ADHERENTLIST, adherentList );

        this.getServletContext().getRequestDispatcher( VUE_LISTE_DES_ADHERENTS ).forward( request, response );
    }

}
