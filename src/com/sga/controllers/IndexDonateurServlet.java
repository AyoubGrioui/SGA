package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/indexDonateur" )
public class IndexDonateurServlet extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID       = 1L;
    public static final String VUE_DASHBOARD_DONATEUR = "/WEB-INF/indexDonateur.jsp";
    public static final String ATT_SESSION_USER       = "userDonateur";
    public static final String ATT_DON_LIST           = "donList";
    public static final String ATT_DONNEUR            = "donneur";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Donneur donneur = (Donneur) session.getAttribute( ATT_SESSION_USER );
        if ( donneur != null ) {
            session.setAttribute( ATT_DON_LIST, donneur.getDonList() );
        }

        System.out.println( "Donneur index" );

        request.setAttribute( ATT_DONNEUR, donneur );

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_DONATEUR ).forward( request, response );
    }

}
