package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;
import com.sga.entities.DonneurMoral;
import com.sga.entities.DonneurPhysique;
import com.sga.services.DonneurMoraleForm;
import com.sga.services.DonneurPhysiqueForm;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonneurPersister;
import com.sga.repositories.HibernateStructurePersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@SuppressWarnings( "serial" )
@WebServlet( "/modifierDonateur" )
public class ModifierDonateurServlet extends HttpServlet {

    public static final String  VUE_MODIF_DONATEUR_PHYSIQUE = "/WEB-INF/modifDonateurPhysique.jsp";
    public static final String  VUE_MODIF_DONATEUR_MORALE   = "/WEB-INF/modifDonateurMorale.jsp";
    public static final String  ATT_DONATEURFORM            = "donneurForm";
    public static final String  ATT_DONATEUR                = "donneur";
    private static final String ATT_LIST_STRUCTURE          = "structureList";
    public static final String  PARAM_TYPEDONATEUR          = "typeDonateur";
    public static final String  INTERNAL_ID_DONATEUR        = "idDonneur";
    private static final String SUCCESS_MSG                 = "successMsg";
    private static final String ERREUR_MSG                  = "erreurMsg";

    public static final String  PARAMETRE_ID_DONATEUR       = "donneurID";

    // public static final String VUE = "/listeDonateur";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String url = request.getHeader( "referer" );
        String vue = "/" + url.substring( url.lastIndexOf( '/' ) + 1 );
        request.setAttribute( "vue", vue );

        HibernateDonneurPersister donneurPersister = new HibernateDonneurPersister();

        /* Recuperation du param */
        String idDonneur = getValeurParametre( request, PARAMETRE_ID_DONATEUR );

        // si l'id n'est pas vide

        if ( idDonneur != null ) {

            Long id = Long.parseLong( idDonneur );

            HttpSession session = request.getSession();
            // suppression de l'adherent de la BD
            Donneur donneur = donneurPersister.read( id );
            HibernateStructurePersister structurePersister = new HibernateStructurePersister();
            List<Structure> structureList = structurePersister.getAll();

            request.setAttribute( ATT_LIST_STRUCTURE, structureList );
            session.setAttribute( INTERNAL_ID_DONATEUR, id );
            if ( donneur instanceof DonneurPhysique ) {
                request.setAttribute( ATT_DONATEUR, donneur );
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR_PHYSIQUE ).forward( request,
                        response );
            } else {
                request.setAttribute( ATT_DONATEUR, donneur );
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR_MORALE ).forward( request, response );
            }

        }

        else {
            this.getServletContext().getRequestDispatcher( vue ).forward( request, response );
        }

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String typeDonateur = (String) request.getParameter( PARAM_TYPEDONATEUR );
        String successMsg = null;
        String erreurMsg = null;
        String vue = request.getParameter( "vue" );

        if ( typeDonateur.equals( "physique" ) ) {
            DonneurPhysiqueForm donneurForm = new DonneurPhysiqueForm();
            DonneurPhysique donneur = donneurForm.modifierDonneurPhysique( request );
            request.setAttribute( ATT_DONATEURFORM, donneurForm );
            request.setAttribute( ATT_DONATEUR, donneur );
            request.setAttribute( "vue", vue );

            if ( donneurForm.getErreurs().isEmpty() ) {
                successMsg = "Le donateur a été bien modifié.";
            } else {
                erreurMsg = "Veuillez vérifier les champs saisies.";
            }

            request.setAttribute( ERREUR_MSG, erreurMsg );
            request.setAttribute( SUCCESS_MSG, successMsg );
            if ( donneurForm.getErreurs().isEmpty() )
                response.sendRedirect( request.getContextPath() + vue );
            else
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR_PHYSIQUE ).forward( request,
                        response );
        } else {
            DonneurMoraleForm donneurForm = new DonneurMoraleForm();
            DonneurMoral donneur = donneurForm.modifierDonneurMorale( request );
            request.setAttribute( ATT_DONATEURFORM, donneurForm );
            request.setAttribute( ATT_DONATEUR, donneur );
            request.setAttribute( "vue", vue );
            if ( donneurForm.getErreurs().isEmpty() ) {
                successMsg = "Le donateur a été bien modifié.";
            } else {
                erreurMsg = "Veuillez vérifier les champs saisies.";
            }

            request.setAttribute( ERREUR_MSG, erreurMsg );
            request.setAttribute( SUCCESS_MSG, successMsg );
            if ( donneurForm.getErreurs().isEmpty() )
                response.sendRedirect( request.getContextPath() + vue );
            else
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DONATEUR_MORALE ).forward( request, response );
        }

    }

    /*
     * MÃ©thode utilitaire qui retourne null si un paramÃ¨tre est vide, et son
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
