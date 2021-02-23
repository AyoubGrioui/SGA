package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Don;
import com.sga.repositories.HibernateDonPersister;

/**
 * Servlet implementation class SupprimerDon
 */
@WebServlet( "/supprimerDon" )
public class SupprimerDonServlet extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String PARAMETRE_ID_DON = "donID";

    public static final String VUE              = "/listeDon";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateDonPersister donPersister = new HibernateDonPersister();

        /* Recuperation du param */
        String idDon = getValeurParametre( request, PARAMETRE_ID_DON );

        // si l'id et la map ne sont pas vides

        if ( idDon != null ) {

            Long id = Long.parseLong( idDon );

            // suppression de l'adherent de la BD

            Don don = donPersister.read( id );
            donPersister.delete( don );
        }
        /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + VUE );
    }

    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
