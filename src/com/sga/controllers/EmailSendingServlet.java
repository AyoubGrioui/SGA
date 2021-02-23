package com.sga.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sga.entities.Donneur;
import com.sga.entities.Structure;
import com.sga.helpers.EmailUtility;
import com.sga.repositories.HibernateDonneurPersister;

/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet( "/envoyerMail" )
public class EmailSendingServlet extends HttpServlet {
    /**
     * 
     */
    private static final long   serialVersionUID = 1L;
    private String              host;
    private String              port;
    private String              user;
    private String              pass;
    private static final String PARAM_ID_DONNEUR = "donneurID";
    private static final String ATT_STRUCTURE    = "structure";

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter( "host" );
        port = context.getInitParameter( "port" );
        user = context.getInitParameter( "user" );
        pass = context.getInitParameter( "pass" );
    }

    protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException {

        HibernateDonneurPersister donneurPersister = new HibernateDonneurPersister();
        Donneur donneur = donneurPersister.read( Long.parseLong( getValeurChamp( request, PARAM_ID_DONNEUR ) ) );
        HttpSession session = request.getSession();
        Structure structure = (Structure) session.getAttribute( ATT_STRUCTURE );

        // reads form fields
        String recipient = donneur.getEmail();
        String subject = "Suivi de Don";
        String content = "L'association " + structure.getNom() + " vous remercie pour votre don généreux ,\n"
                + "ci-dessous vous trouverez les informations nécessaires "
                + "pour vous connecter à votre compte:\n\n"

                + "nom d'utilisateur: " + donneur.getEmail() + "\n"
                + "mot de passe: " + donneur.getMotDePasse() + "\n\n"

                + "Veuillez changer votre mot de passe "
                + "lorsque vous accéder à votre compte.\n"
                + "Cordialement";
        String successMsg = null;
        String errorMsg = null;

        try {
            EmailUtility.sendEmail( host, port, user, pass, recipient, subject,
                    content );
            successMsg = "l'email a été envoyé avec succés";
        } catch ( Exception ex ) {
            ex.printStackTrace();
            errorMsg = "Veulliez vérifier votre connexion";
        } finally {
            session.setAttribute( "erreurMsg", errorMsg );
            session.setAttribute( "successMsg", successMsg );
            response.sendRedirect( request.getContextPath() + "/listeDesDonateurs" );

        }

    }

    // methode utilitaire qui retourne null si un champ est vide, et son contenu
    // sinon

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
