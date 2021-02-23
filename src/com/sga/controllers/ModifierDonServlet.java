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
import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.entities.DonVersement;
import com.sga.entities.Structure;
import com.sga.repositories.HibernateDonPersister;
import com.sga.repositories.HibernateStructurePersister;
import com.sga.services.DonChequeForm;
import com.sga.services.DonEspeceForm;
import com.sga.services.DonVersementForm;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/modifierDon" )
public class ModifierDonServlet extends HttpServlet {

    /**
     * 
     */
    private static final long   serialVersionUID        = 2796098788407146261L;
    public static final String  VUE_MODIF_DON_ESPECE    = "/WEB-INF/modifDonEspece.jsp";
    public static final String  VUE_MODIF_DON_CHEQUE    = "/WEB-INF/modifDonCheque.jsp";
    public static final String  VUE_MODIF_DON_VERSEMENT = "/WEB-INF/modifDonVersement.jsp";
    public static final String  ATT_DONFORM             = "donForm";
    public static final String  ATT_DON                 = "don";
    private static final String ATT_LIST_STRUCTURE      = "structureList";
    public static final String  PARAM_TYPEDON           = "typeDon";
    public static final String  INTERNAL_ID_DON         = "idDon";
    private static final String SUCCESS_MSG             = "successMsg";
    private static final String ERREUR_MSG              = "erreurMsg";

    public static final String  PARAMETRE_ID_DON        = "donID";

    public static final String  VUE                     = "/listeDon";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateDonPersister donPersister = new HibernateDonPersister();

        /* Recuperation du param */
        String idDon = getValeurParametre( request, PARAMETRE_ID_DON );

        // si l'id n'est pas vide

        if ( idDon != null ) {

            Long id = Long.parseLong( idDon );

            HttpSession session = request.getSession();

            Don don = donPersister.read( id );

            HibernateStructurePersister structurePersister = new HibernateStructurePersister();
            List<Structure> structureList = structurePersister.getAll();

            request.setAttribute( ATT_LIST_STRUCTURE, structureList );
            session.setAttribute( INTERNAL_ID_DON, id );

            if ( don instanceof DonEspece ) {
                System.out.println( "Don espece" );
                request.setAttribute( ATT_DON, don );
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_ESPECE ).forward( request, response );
            } else if ( don instanceof DonCheque ) {
                System.out.println( "Don Cheque" );
                request.setAttribute( ATT_DON, don );
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_CHEQUE ).forward( request, response );
            } else {
                System.out.println( "Don Cheque" );
                request.setAttribute( ATT_DON, don );
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_VERSEMENT ).forward( request, response );
            }

        }

        else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String typeDon = (String) request.getParameter( PARAM_TYPEDON );
        String successMsg = null;
        String erreurMsg = null;

        if ( typeDon.equals( "espece" ) ) {
            DonEspeceForm donForm = new DonEspeceForm();
            DonEspece don = donForm.modifierDonEspece( request );
            request.setAttribute( ATT_DONFORM, donForm );
            request.setAttribute( ATT_DON, don );
            if ( donForm.getErreurs().isEmpty() ) {
                successMsg = "Le don a été bien modifié.";
            } else {
                erreurMsg = "Veuillez vérifier les champs saisies.";
            }

            request.setAttribute( ERREUR_MSG, erreurMsg );
            request.setAttribute( SUCCESS_MSG, successMsg );
            if ( donForm.getErreurs().isEmpty() )
                response.sendRedirect( request.getContextPath() + VUE );
            else
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_ESPECE ).forward( request, response );
        } else if ( typeDon.equals( "cheque" ) ) {
            DonChequeForm donForm = new DonChequeForm();
            DonCheque don = donForm.modifierDonCheque( request );
            request.setAttribute( ATT_DONFORM, donForm );
            request.setAttribute( ATT_DON, don );
            if ( donForm.getErreurs().isEmpty() ) {
                successMsg = "Le don a été bien modifié.";
            } else {
                erreurMsg = "Veuillez vérifier les champs saisies.";
            }

            request.setAttribute( ERREUR_MSG, erreurMsg );
            request.setAttribute( SUCCESS_MSG, successMsg );
            if ( donForm.getErreurs().isEmpty() )
                response.sendRedirect( request.getContextPath() + VUE );
            else
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_CHEQUE ).forward( request, response );
        } else if ( typeDon.equals( "versement" ) ) {
            DonVersementForm donForm = new DonVersementForm();
            DonVersement don = donForm.modifierDonVersement( request );
            request.setAttribute( ATT_DONFORM, donForm );
            request.setAttribute( ATT_DON, don );
            if ( donForm.getErreurs().isEmpty() ) {
                successMsg = "Le don a été bien modifié.";
            } else {
                erreurMsg = "Veuillez vérifier les champs saisies.";
            }

            request.setAttribute( ERREUR_MSG, erreurMsg );
            request.setAttribute( SUCCESS_MSG, successMsg );
            if ( donForm.getErreurs().isEmpty() )
                response.sendRedirect( request.getContextPath() + VUE );
            else
                this.getServletContext().getRequestDispatcher( VUE_MODIF_DON_VERSEMENT ).forward( request, response );
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
