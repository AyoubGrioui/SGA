package com.sga.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;
import com.sga.services.LoginDonneurForm;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/loginDonateur" )
public class LoginDonateurServlet extends HttpServlet {

    /**
     * 
     */
    private static final long   serialVersionUID          = 1L;
    public static final String  VUE_LOGIN                 = "/WEB-INF/loginDonateur.jsp";
    public static final String  ATT_SESSION_USER          = "userDonateur";
    public static final String  COOKIE_DERNIERE_CONNEXION = "derniereConnexion";
    public static final String  FORMAT_DATE               = "dd/MM/yyyy";
    public static final String  CHAMP_MEMOIRE             = "memoire";
    public static final int     COOKIE_MAX_AGE            = 60 * 60 * 24 * 365;          // 1
                                                                                         // an
    private static final String ATT_LOGIN_DONNEUR_FORM    = "loginDonneurForm";
    public static final String  VUE_DASHBOARD_DONATEUR    = "/WEB-INF/indexDonateur.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Donneur user = null;
        try {
            user = (Donneur) session.getAttribute( ATT_SESSION_USER );
        } catch ( Exception e ) {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        if ( user != null ) {
            response.sendRedirect( request.getContextPath() + "/indexDonateur" );
        } else {
            String email = null;
            String pass = null;

            Cookie[] cookies = request.getCookies();

            if ( cookies != null ) {
                for ( Cookie cookie : cookies ) {
                    if ( cookie.getName().equals( "username" ) ) {
                        request.setAttribute( "username", cookie.getValue() );
                    } else if ( cookie.getName().equals( "password" ) ) {
                        request.setAttribute( "password", cookie.getValue() );
                    }
                }

            }
            this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginDonneurForm loginDonneurForm = new LoginDonneurForm();
        Donneur user = loginDonneurForm.creerDonneur( req );

        if ( loginDonneurForm.getErreurs().isEmpty() )
            session.setAttribute( ATT_SESSION_USER, user );
        else
            session.setAttribute( ATT_SESSION_USER, null );

        if ( user != null ) {
            if ( req.getParameter( CHAMP_MEMOIRE ) != null ) {
                setCookie( resp, "username", user.getEmail(), 60 * 60 * 24 * 30 );
                setCookie( resp, "password", user.getMotDePasse(), 60 * 60 * 24 * 30 );
            } else {
                setCookie( resp, "username", "", 60 * 60 * 24 * 30 );
                setCookie( resp, "password", "", 60 * 60 * 24 * 30 );

            }
            resp.sendRedirect( req.getContextPath() + "/indexDonateur" );
        }

        else {
            resp.sendRedirect( req.getContextPath() + "/loginDonateur" );
        }

    }

    /*
     * Méthode utilitaire gérant la création d'un cookie et son ajout à la
     * réponse HTTP.
     */
    private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge ) {
        Cookie cookie = new Cookie( nom, valeur );
        cookie.setMaxAge( maxAge );
        response.addCookie( cookie );
    }

}
