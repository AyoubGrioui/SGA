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

import com.sga.entities.Adherent;
import com.sga.repositories.HibernateAdherentPersister;
import com.sga.services.LoginAdherentForm;

/**
 * Servlet implementation class DashBoardDonateurServlet
 */
@WebServlet( "/loginAdherent" )
public class LoginAdherentServlet extends HttpServlet {

    private static final long   serialVersionUID          = 1L;
    public static final String  VUE_LOGIN                 = "/WEB-INF/loginAdherent.jsp";
    public static final String  VUE_DASHBOARD_PRESIDENT   = "/WEB-INF/indexPresident.jsp";
    public static final String  VUE_DASHBOARD_SECRETAIRE  = "/WEB-INF/indexSecretaire.jsp";
    public static final String  ATT_SESSION_USER          = "userAdherent";
    public static final String  COOKIE_DERNIERE_CONNEXION = "derniereConnexion";
    public static final String  FORMAT_DATE               = "dd/MM/yyyy";
    public static final String  CHAMP_MEMOIRE             = "memoire";
    public static final int     COOKIE_MAX_AGE            = 60 * 60 * 24 * 365;            // 1
                                                                                           // an
    private static final String ATT_LOGIN_ADHERENT_FORM   = "loginAdherentForm";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        Adherent user = null;

        HttpSession session = request.getSession();
        try {
            user = (Adherent) session.getAttribute( ATT_SESSION_USER );

        } catch ( Exception e ) {
            session.setAttribute( ATT_SESSION_USER, null );
        }

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

                this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
                return;
        }

        if ( user != null ) {
            String role = user.getLigneFonction().getFonction().getRole();

            if ( role.equals( "President(e)" ) )
                response.sendRedirect( request.getContextPath() + "/indexPresident" );
            else if ( role.equals( "Secretaire" ) )
                response.sendRedirect( request.getContextPath() + "/indexSecretaire" );
           

        } else {
            this.getServletContext().getRequestDispatcher( VUE_LOGIN ).forward( request, response );
        }
    }

    /**
     *
     */
    /**
     *
     */
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginAdherentForm loginAdherentForm = new LoginAdherentForm();

        Adherent user = loginAdherentForm.creerAdherent( req );

        if ( loginAdherentForm.getErreurs().isEmpty() )
            session.setAttribute( ATT_SESSION_USER, user );
        else
            session.setAttribute( ATT_SESSION_USER, null );

        /* Stockage du formulaire et du bean dans l'objet request */
        session.setAttribute( ATT_LOGIN_ADHERENT_FORM, loginAdherentForm );

        if ( user != null ) {

            /* Si et seulement si la case du formulaire est cochée */
            if ( req.getParameter( CHAMP_MEMOIRE ) != null ) {
                setCookie( resp, "username", user.getEmail(), 60 * 60 * 24 * 30 );
                setCookie( resp, "password", user.getMotDePasse(), 60 * 60 * 24 * 30 );
            } else {
                setCookie( resp, "username", "", 60 * 60 * 24 * 30 );
                setCookie( resp, "password", "", 60 * 60 * 24 * 30 );

            }

            String role = user.getLigneFonction().getFonction().getRole();

            if ( role.equals( "President(e)" ) )
                resp.sendRedirect( req.getContextPath() + "/indexPresident" );
            if ( role.equals( "Secretaire" ) )
                resp.sendRedirect( req.getContextPath() + "/indexSecretaire" );

        }

        else {
            resp.sendRedirect( req.getContextPath() + "/loginAdherent" );
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
