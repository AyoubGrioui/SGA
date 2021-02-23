package com.sga.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sga.entities.Donneur;
import com.sga.repositories.HibernateDonneurPersister;

public class LoginDonneurForm {

    public static final String  CHAMPS_EMAIL     = "userEmail";
    public static final String  CHAMPS_PASSWORD  = "userPassword";

    HibernateDonneurPersister   donneurPersister = new HibernateDonneurPersister();

    private Map<String, String> erreurs          = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Donneur creerDonneur( HttpServletRequest req ) {

        String userEmail = getValeurChamp( req, CHAMPS_EMAIL );
        String userPassword = getValeurChamp( req, CHAMPS_PASSWORD );

        Donneur donneur = null;
        boolean loginSucces = false;

        try {
            donneur = validationEmail( userEmail );
        } catch ( Exception e ) {
            setErreur( CHAMPS_EMAIL, e.getMessage() );
        }

        try {
            loginSucces = validationMotDePasse( userPassword, donneur );
        } catch ( Exception e ) {
            setErreur( CHAMPS_PASSWORD, e.getMessage() );
        }

        if ( donneur != null && loginSucces == true )
            return donneur;
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
    private Donneur validationEmail( String email ) throws Exception {
        if ( email != null && email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            Donneur donneur = donneurPersister.getByEmail( email );
            if ( donneur == null ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
            return donneur;
        } else {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    // Validation mot de Passe

    // Foction de validation du mot de passe
    private boolean validationMotDePasse( String motDePasse, Donneur donneur ) throws Exception {
        if ( motDePasse == null ) {
            throw new Exception( "Merci de saisir un mot de passe valide." );
        } else if ( donneur != null ) {
            if ( donneur.getMotDePasse().equals( motDePasse ) )
                return true;
        }

        return false;
    }

}
