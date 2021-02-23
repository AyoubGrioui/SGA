package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sga.entities.Depense;
import com.sga.entities.DonCheque;
import com.sga.entities.DonEspece;
import com.sga.entities.DonVersement;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateDepensePersister;
import com.sga.repositories.HibernateDonChequePersister;
import com.sga.repositories.HibernateDonEspecePersister;
import com.sga.repositories.HibernateDonPersister;
import com.sga.repositories.HibernateDonVersementPersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/indexPresident" )
public class IndexPresidentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long   serialVersionUID        = 1L;
    public static final String  VUE_DASHBOARD_PRESIDENT = "/WEB-INF/indexPresident.jsp";
    public static final String  ATT_SESSION_USER        = "userAdherent";
    private static final String ATT_ADHERENT_SIZE       = "adherentSize";
    private static final String ATT_DON_SIZE            = "donSize";
    private static final String ATT_DEPENSE_MONTANT     = "depenseMontant";
    private static final String ATT_DON_MONTANT         = "donMontant";

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HibernateDonPersister donPersister = new HibernateDonPersister();
        HibernateDepensePersister depensePersister = new HibernateDepensePersister();
        HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();
        HibernateDonEspecePersister donEspecePersister = new HibernateDonEspecePersister();
        HibernateDonChequePersister donChequePersister = new HibernateDonChequePersister();
        HibernateDonVersementPersister donVersementPersister = new HibernateDonVersementPersister();

        int adherentSize = adherentPersister.getAll().size();
        int donSize = donPersister.getAll().size();

        List<Depense> depenseList = depensePersister.getAll();

        double depenseMontant = 0;

        for ( Depense depense : depenseList ) {
            depenseMontant += depense.getMontant();
        }

        List<DonCheque> donChequeList = donChequePersister.getAll();
        List<DonVersement> donVersementList = donVersementPersister.getAll();
        List<DonEspece> donEspeceList = donEspecePersister.getAll();

        double donMontant = 0;
        for ( DonCheque donCheque : donChequeList ) {
            donMontant += donCheque.getMontant();
        }
        for ( DonVersement donVersement : donVersementList ) {
            donMontant += donVersement.getMontant();
        }
        for ( DonEspece donEspece : donEspeceList ) {
            donMontant += donEspece.getMontant();
        }

        request.setAttribute( ATT_ADHERENT_SIZE, adherentSize );
        request.setAttribute( ATT_DON_SIZE, donSize );
        request.setAttribute( ATT_DEPENSE_MONTANT, depenseMontant );
        request.setAttribute( ATT_DON_MONTANT, donMontant );

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_PRESIDENT ).forward( request, response );
    }

}
