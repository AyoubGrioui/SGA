package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Depense;
import com.sga.repositories.HibernateDepensePersister;
import com.sga.services.DepenseForm;

/**
 * Servlet implementation class ModifierDepenseServlet
 */
@WebServlet( "/modifierDepense" )
public class ModifierDepenseServlet extends HttpServlet {
    private static final long   serialVersionUID     = 1L;

    public static final String  VUE_MODIFIER_DEPENSE = "/WEB-INF/modifierDepensePage.jsp";
    public static final String  ATT_DEPENSEFORM      = "depenseForm";
    public static final String  ATT_DEPENSE          = "depense";
    public static final String  INTERNAL_ID_DEPENSE  = "idDepense";
    public static final String  PARAMETRE_ID_DEPENSE = "depenseID";
    public static final String  VUE                  = "/listeDepense";
    private static final String SUCCESS_MSG          = "successMsg";
    private static final String ERREUR_MSG           = "erreurMsg";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateDepensePersister depensePersister = new HibernateDepensePersister();

        /* Recuperation du param */
        String idDepense = getValeurParametre( request, PARAMETRE_ID_DEPENSE );

        // si l'id n'est pas vide

        if ( idDepense != null ) {

            Long id = Long.parseLong( idDepense );

            // suppression de l'adherent de la BD
            Depense depense = depensePersister.read( id );
            request.setAttribute( ATT_DEPENSE, depense );

            this.getServletContext().getRequestDispatcher( VUE_MODIFIER_DEPENSE ).forward( request, response );
        }

        else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        DepenseForm depenseForm = new DepenseForm();
        Depense depense = depenseForm.modifierDepense( request );

        String successMsg = null;
        String erreurMsg = null;

        if ( depenseForm.getErreurs().isEmpty() ) {
            successMsg = "La dépense a été bien enregistré.";
        } else {
            erreurMsg = "Veuillez vérifier les champs saisies.";
        }

        request.setAttribute( ERREUR_MSG, erreurMsg );
        request.setAttribute( SUCCESS_MSG, successMsg );
        request.setAttribute( ATT_DEPENSEFORM, depenseForm );
        request.setAttribute( ATT_DEPENSE, depense );

        if ( depenseForm.getErreurs().isEmpty() )
            response.sendRedirect( request.getContextPath() + VUE );
        else
            this.getServletContext().getRequestDispatcher( VUE_MODIFIER_DEPENSE ).forward( request, response );

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
