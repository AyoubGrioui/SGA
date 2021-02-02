package com.sga.controllers;

import com.sga.entities.Adherent;
import com.sga.entities.LigneFonction;
import com.sga.services.AdherentForm;
import com.sga.services.LigneFonctionForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public static final String ATT_ADHERENTFORM ="adherentForm";
    public static final String ATT_LIGNEFONCTIONFORM ="ligneFonctionForm";

    public static final String ATT_ADHERENT="adherent";
    public static final String ATT_SESSION_USER="user";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_AJOUTER_ADHERENT ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdherentForm adherentForm = new AdherentForm();
        Adherent adherent = adherentForm.creerAdherent(request);

        LigneFonctionForm ligneFonctionForm = new LigneFonctionForm();
        LigneFonction ligneFonction = ligneFonctionForm.creerLigneFonction(request);

        request.setAttribute(ATT_ADHERENTFORM,adherentForm);
        request.setAttribute(ATT_LIGNEFONCTIONFORM,ligneFonction);

        this.getServletContext().getRequestDispatcher(VUE_AJOUTER_ADHERENT).forward( request, response );
    }


}
