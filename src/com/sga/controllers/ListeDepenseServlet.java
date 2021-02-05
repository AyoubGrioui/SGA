package com.sga.controllers;

import com.sga.entities.Depense;
import com.sga.repositories.HibernateDepensePersister;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/listeDepense" )
public class ListeDepenseServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID  = 1L;
    public static final String VUE_LISTE_DEPENSE = "/WEB-INF/listeDepensePage.jsp";
    public static final String ATT_DEPENSELIST = "depenseList";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HibernateDepensePersister depensePersister=new HibernateDepensePersister();
        List<Depense> depenseList= depensePersister.getAll();

        request.setAttribute(ATT_DEPENSELIST,depenseList);


        this.getServletContext().getRequestDispatcher( VUE_LISTE_DEPENSE ).forward( request, response );
    }

}
