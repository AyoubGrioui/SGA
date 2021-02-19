package com.sga.controllers;

import com.sga.entities.Adherent;
import com.sga.entities.Depense;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.repositories.HibernateDepensePersister;
import com.sga.repositories.HibernateDonPersister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/indexPresident" )
public class IndexPresidentServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID        = 1L;
    public static final String VUE_DASHBOARD_PRESIDENT = "/WEB-INF/indexPresident.jsp";
    public static final String ATT_SESSION_USER="user";
    private static final String ATT_ADHERENT_SIZE = "adherentSize" ;
    private static final String ATT_DON_SIZE = "donSize";
    private static final String ATT_DEPENSE_MONTANT = "depenseMontant";

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HibernateDonPersister donPersister =new HibernateDonPersister();
        HibernateDepensePersister depensePersister= new HibernateDepensePersister();
        HibernateAdherentPersister adherentPersister = new HibernateAdherentPersister();

        int adherentSize = depensePersister.getAll().size();
        int donSize = donPersister.getAll().size();

        List<Depense> depenseList = depensePersister.getAll();

        double depenseMontant = 0;

        for (Depense depense : depenseList)
        {
            depenseMontant += depense.getMontant();
        }

        request.setAttribute(ATT_ADHERENT_SIZE,adherentSize);
        request.setAttribute(ATT_DON_SIZE,donSize);
        request.setAttribute(ATT_DEPENSE_MONTANT,depenseMontant);

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_PRESIDENT ).forward( request, response );
    }

}
