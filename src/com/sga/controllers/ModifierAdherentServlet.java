package com.sga.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Adherent;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.services.AdherentForm;

/**
 * Servlet implementation class ModifierAdherent
 */
@WebServlet( "/modifierAdherent" )
public class ModifierAdherentServlet extends HttpServlet {
    private static final long   serialVersionUID      = 1L;

    public static final String  VUE_MODIFIER_ADHERENT = "/WEB-INF/modifierAdherentPage.jsp";
    public static final String  ATT_ERREURS           = "erreurs";
    public static final String  ATT_ADHERENT          = "adherent";
    public static final String  INTERNAL_ID_ADHERENT  = "idAdherent";
    private static final String SUCCESS_MSG           = "successMsg";
    private static final String ERREUR_MSG            = "erreurMsg";

    public static final String  PARAMETRE_ID_ADHERENT = "adherentID";

    public static final String  VUE                   = "/listeDesAdherents";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();

        /* Recuperation du param */
        String idAdherent = getValeurParametre( request, PARAMETRE_ID_ADHERENT );

        // si l'id et la map ne sont pas vides

        if ( idAdherent != null ) {

            Long id = Long.parseLong( idAdherent );

            // HttpSession session = request.getSession();
            // suppression de l'adherent de la BD
            Adherent adherent = adherentPersister.read( id );
            
            System.out.println(adherent.getEmail());
            request.setAttribute( ATT_ADHERENT, adherent );

            // session.setAttribute(INTERNAL_ID_ADHERENT, id);
            this.getServletContext().getRequestDispatcher( VUE_MODIFIER_ADHERENT ).forward( request, response );
        }

        else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        AdherentForm adherentForm = new AdherentForm();
        Adherent adherent = adherentForm.modifierAdherent( request );

        Map<String, String> erreurs = adherentForm.getErreurs();

        String successMsg = null;
        String erreurMsg = null;

        if ( !erreurs.isEmpty() ) {
            if ( adherentForm.getErrorMessage() == null )
                erreurMsg = "Veuillez vérifier les champs saisies.";
            else
                erreurMsg = adherentForm.getErrorMessage();

        } else {
            successMsg = "L'adhérent a été bien enregistré.";
        }

        request.setAttribute( ERREUR_MSG, erreurMsg );
        request.setAttribute( SUCCESS_MSG, successMsg );

        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_ADHERENT, adherent );

        if ( erreurs.isEmpty() ) {
            response.sendRedirect( request.getContextPath() + VUE );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_MODIFIER_ADHERENT ).forward( request, response );
        }
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
