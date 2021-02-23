package com.sga.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sga.entities.Adherent;
import com.sga.repositories.HibernateAdherentPersister;

public class LoginAdherentForm {
    public static final String  CHAMPS_EMAIL      = "userEmail";
    public static final String  CHAMPS_PASSWORD   = "userPassword";

    HibernateAdherentPersister  adherentPersister = new HibernateAdherentPersister();

    private Map<String, String> erreurs           = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Adherent creerAdherent( HttpServletRequest req ) {
        String userEmail = getValeurChamp( req, CHAMPS_EMAIL );
        String userPassword = getValeurChamp( req, CHAMPS_PASSWORD );

        Adherent adherent = null;
        boolean loginSucces = false;

        try {
            adherent = validationEmail( userEmail );
        } catch ( Exception e ) {
            setErreur( CHAMPS_EMAIL, e.getMessage() );
        }

        try {
            loginSucces = validationMotDePasse( userPassword, adherent );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PASSWORD, e.getMessage() );
        }

        if ( adherent != null && loginSucces == true )
            return adherent;
        else
            return null;
    }

    // ajoute un message correspondant au champ specifie a la map des erreurs

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
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

    /**
     * Valide l'adresse email saisie.
     */
    private Adherent validationEmail( String email ) throws Exception {
        if ( email != null && email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {

            Adherent adherent = adherentPersister.getByEmail( email );
            if ( adherent == null ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
            return adherent;
        } else {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    // Validation mot de Passe

    // Foction de validation du mot de passe
    private boolean validationMotDePasse( String motDePasse, Adherent adherent ) throws Exception {
        if ( motDePasse == null || motDePasse.length() < 8 ) {
            throw new Exception( "Merci de saisir un mot de passe valide." );
        } else if ( adherent != null ) {
            if ( adherent.getMotDePasse().equals( motDePasse ) )
                return true;
        }

        return false;
    }
}
