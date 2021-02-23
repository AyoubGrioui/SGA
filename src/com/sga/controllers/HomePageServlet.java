package com.sga.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Structure;
import com.sga.repositories.HibernateStructurePersister;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/homePage" )
public class HomePageServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;
    public static final String VUE_LOGIN        = "/WEB-INF/homePage.jsp";
    public static final String ATT_STRUCTURE    = "structure";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        Structure structure = null;

        HibernateStructurePersister structurePersister = new HibernateStructurePersister();
        List<Structure> structureList = structurePersister.getAll();

        if ( !structureList.isEmpty() ) {
            structure = structureList.get( 0 );
        }

        HttpSession session = request.getSession();
        session.setAttribute( ATT_STRUCTURE, structure );

        this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
    }

}