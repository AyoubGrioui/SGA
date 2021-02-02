package com.sga.controllers;

import com.sga.entities.Adherent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_DASHBOARD_PRESIDENT ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        Adherent adherent =(Adherent) session.getAttribute(ATT_SESSION_USER);

        if(adherent!=null )
        {
            this.getServletContext().getRequestDispatcher(VUE_DASHBOARD_PRESIDENT).forward(req, resp);
        }
        else
        {

        }
    }
}
